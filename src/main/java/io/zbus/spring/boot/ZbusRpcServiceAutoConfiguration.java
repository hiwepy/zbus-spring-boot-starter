package io.zbus.spring.boot;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import io.zbus.rpc.bootstrap.mq.ServiceBootstrap;
import io.zbus.spring.boot.annotation.RocketmqPullTopic;
import io.zbus.spring.boot.exception.ZbusException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ObjectUtils;

/**
 * Spring Boot auto-configuration for the Zbus RPC service.
 * <p>
 * Activated when {@code spring.zbus.consume-actively.enabled=true}. Registers
 * the {@link ServiceBootstrap} that hosts the RPC service and binds it to the
 * Zbus broker.
 * </p>
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
@Configuration
@ConditionalOnClass({ ServiceBootstrap.class })
@ConditionalOnProperty(prefix = ZbusServiceProperties.PREFIX, value = "enabled", havingValue = "true")
@AutoConfigureAfter(ZbusPushEventHandlerAutoConfiguration.class)
@EnableConfigurationProperties({ ZbusServiceProperties.class })
public class ZbusRpcServiceAutoConfiguration  implements ApplicationContextAware {

	private static final Logger LOG = LoggerFactory.getLogger(ZbusRpcServiceAutoConfiguration.class);
	private ApplicationContext applicationContext;

	/**
	 * Creates and starts the Zbus RPC service bootstrap.
	 *
	 * @return the started service bootstrap
	 * @throws Exception if the service fails to start
	 */
	@Bean
	@ConditionalOnMissingBean
	public ServiceBootstrap ServiceBootstrap() throws Exception {
		ServiceBootstrap bootstrap = new ServiceBootstrap();


		bootstrap.serviceName("MyRpc")
		.host("host")
		.addModule(module, clazz)

		.serviceAddress(addressList)
		.port(15555)  // Starts an embedded zbus server; zbus and the RPC service communicate in-process.
		 //.serviceAddress("localhost:15555")   // Alternatively connect to a remote server over the network.
		 //.ssl("ssl/zbus.crt", "ssl/zbus.key") // Enable SSL.
		 //.serviceToken("myrpc_service")       // Enable token authentication.
		 .port(15555)
		 .autoDiscover(true)
		 .start();
		return bootstrap;
	}

	/**
	 * Configures the supplied consumer from the bound properties.
	 *
	 * @param consumer   the consumer to configure
	 * @param properties the service properties
	 */
	public void configure(DefaultMQPullConsumer consumer, ZbusServiceProperties properties) {
		
		consumer.resetClientConfig(properties);
		
		consumer.setBrokerSuspendMaxTimeMillis(properties.getBrokerSuspendMaxTimeMillis());
		consumer.setClientCallbackExecutorThreads(properties.getClientCallbackExecutorThreads());
		consumer.setClientIP(properties.getClientIP());
		consumer.setConsumerGroup(properties.getConsumerGroup());
		consumer.setConsumerPullTimeoutMillis(properties.getConsumerPullTimeoutMillis());
		consumer.setConsumerTimeoutMillisWhenSuspend(properties.getConsumerTimeoutMillisWhenSuspend());
		consumer.setHeartbeatBrokerInterval(properties.getHeartbeatBrokerInterval());
		consumer.setInstanceName(properties.getInstanceName());
		consumer.setMaxReconsumeTimes(properties.getMaxReconsumeTimes());
		consumer.setNamesrvAddr(properties.getNamesrvAddr());
		try {
			consumer.setMessageModel(MessageModel.valueOf(properties.getMessageModel()));
		} catch (Exception e) {
			consumer.setMessageModel(MessageModel.CLUSTERING);
		}
		//consumer.setOffsetStore(offsetStore);
		consumer.setPersistConsumerOffsetInterval(properties.getPersistConsumerOffsetInterval());
		consumer.setPollNameServerInterval(properties.getPollNameServerInterval());
		consumer.setRegisterTopics(properties.getRegisterTopics());
		consumer.setUnitMode(properties.isUnitMode());
		consumer.setUnitName(properties.getUnitName());
		consumer.setVipChannelEnabled(properties.isVipChannelEnabled());
		
	}
	
	@Bean
	@ConditionalOnMissingBean
	public DefaultMQPullConsumer pullConsumer(ZbusServiceProperties properties,
			AllocateMessageQueueStrategy allocateMessageQueueStrategy) throws MQClientException {
		

		if (StringUtils.isEmpty(properties.getConsumerGroup())) {
			throw new ZbusException("consumerGroup is empty");
		}
		if (StringUtils.isEmpty(properties.getNamesrvAddr())) {
			throw new ZbusException("nameServerAddr is empty");
		}
		
		DefaultMQPullConsumer consumer = new DefaultMQPullConsumer(properties.getConsumerGroup());

		// Initialise consumer parameters.
		this.configure(consumer, properties);

		consumer.setAllocateMessageQueueStrategy(allocateMessageQueueStrategy);

		// Look up MessageQueueListener beans registered in the Spring context.
		Map<String, MessageQueueListener> beansOfType = getApplicationContext().getBeansOfType(MessageQueueListener.class);
		if (!ObjectUtils.isEmpty(beansOfType)) {
			Iterator<Entry<String, MessageQueueListener>> ite = beansOfType.entrySet().iterator();
			while (ite.hasNext()) {
				Entry<String, MessageQueueListener> entry = ite.next();
				// Resolve the @RocketmqPullTopic annotation on the bean.
				RocketmqPullTopic annotationType = getApplicationContext().findAnnotationOnBean(entry.getKey(), RocketmqPullTopic.class);
				if(annotationType == null) {
					// No annotation: skip and log an error.
					LOG.error("Not Found AnnotationType {0} on Bean {1} Whith Name {2}", RocketmqPullTopic.class, entry.getValue().getClass(), entry.getKey());
					continue;
				}
				consumer.registerMessageQueueListener(annotationType.value(), entry.getValue());
			}
		}

		/*
		 * Delay the start by a few seconds so Spring event listeners finish
		 * initialising; otherwise consuming a message and immediately
		 * publishing a message-arrived event could lose the event because its
		 * listener is not yet registered.
		 */
		Executors.newScheduledThreadPool(1).schedule(new Thread() {
			public void run() {
				try {

					/*
					 * The consumer must be started once before use.
					 */
					consumer.start();

					LOG.info("Zbus MQPullConsumer Started ! groupName:[%s],namesrvAddr:[%s],instanceName:[%s].",
							properties.getConsumerGroup(), properties.getNamesrvAddr(), properties.getInstanceName());

					/**
					 * On application exit call shutdown to release resources,
					 * close network connections and unregister from the broker.
					 * It is recommended to call shutdown from the JVM shutdown
					 * hook (e.g. when running inside JBoss/Tomcat).
					 */
					Runtime.getRuntime().addShutdownHook(new MQPullConsumerShutdownHook(consumer));

				} catch (Exception e) {
					LOG.error(String.format("Zbus MQPullConsumer Start failure ：%s", e.getMessage(), e));
				}
			}
		}, properties.getDelayStartSeconds(), TimeUnit.SECONDS);

		return consumer;
	} 
	
	@Bean
	@ConditionalOnProperty(prefix = ZbusServiceProperties.PREFIX, name = "schedulable", havingValue = "true")
	public MQPullConsumerScheduleService schedulePullConsumer(ZbusServiceProperties properties) throws MQClientException {

		if (StringUtils.isEmpty(properties.getConsumerGroup())) {
			throw new ZbusException("consumerGroup is empty");
		}
		if (StringUtils.isEmpty(properties.getNamesrvAddr())) {
			throw new ZbusException("nameServerAddr is empty");
		}
		
		MQPullConsumerScheduleService scheduleService = new MQPullConsumerScheduleService(properties.getConsumerGroup());

		DefaultMQPullConsumer consumer = scheduleService.getDefaultMQPullConsumer();
		// Initialise consumer parameters.
		this.configure(consumer, properties);

		try {
			scheduleService.setMessageModel(MessageModel.valueOf(properties.getMessageModel()));
		} catch (Exception e) {
			scheduleService.setMessageModel(MessageModel.CLUSTERING);
		}

		scheduleService.setPullThreadNums(properties.getPullThreadNums());

		// Look up PullTaskCallback beans registered in the Spring context.
		Map<String, PullTaskCallback> beansOfType = getApplicationContext().getBeansOfType(PullTaskCallback.class);
		if (!ObjectUtils.isEmpty(beansOfType)) {
			Iterator<Entry<String, PullTaskCallback>> ite = beansOfType.entrySet().iterator();
			while (ite.hasNext()) {
				Entry<String, PullTaskCallback> entry = ite.next();
				// Resolve the @RocketmqPullTopic annotation on the bean.
				RocketmqPullTopic annotationType = getApplicationContext().findAnnotationOnBean(entry.getKey(), RocketmqPullTopic.class);
				if(annotationType == null) {
					// No annotation: skip and log an error.
					LOG.error("Not Found AnnotationType {0} on Bean {1} Whith Name {2}", RocketmqPullTopic.class, entry.getValue().getClass(), entry.getKey());
					continue;
				}
				scheduleService.registerPullTaskCallback(annotationType.value(), entry.getValue());
			}
		}

		/*
		 * Delay the start by a few seconds so Spring event listeners finish
		 * initialising; otherwise consuming a message and immediately
		 * publishing a message-arrived event could lose the event because its
		 * listener is not yet registered.
		 */
		Executors.newScheduledThreadPool(1).schedule(new Thread() {
			public void run() {
				try {

					/*
					 * The schedule service must be started once before use.
					 */
					scheduleService.start();

					LOG.info("Zbus MQPullConsumerScheduleService Started ! groupName:[%s],namesrvAddr:[%s],instanceName:[%s].",
							properties.getConsumerGroup(), properties.getNamesrvAddr(), properties.getInstanceName());

					/**
					 * On application exit call shutdown to release resources,
					 * close network connections and unregister from the broker.
					 * It is recommended to call shutdown from the JVM shutdown
					 * hook (e.g. when running inside JBoss/Tomcat).
					 */
					Runtime.getRuntime().addShutdownHook(new MQPullConsumerScheduleShutdownHook(scheduleService));

				} catch (Exception e) {
					LOG.error(String.format("Zbus MQPullConsumerScheduleService Start failure ：%s", e.getMessage(), e));
				}
			}
		}, properties.getDelayStartSeconds(), TimeUnit.SECONDS);

		return scheduleService;
	}
	
	@Bean
	public ZbusPullConsumerTemplate rocketmqConsumerTemplate(MQPullConsumer consumer) throws MQClientException {
		return new ZbusPullConsumerTemplate(consumer);
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}
}
