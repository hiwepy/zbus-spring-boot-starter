package io.zbus.spring.boot;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import io.zbus.client.exception.MQClientException;
import io.zbus.client.producer.DefaultMQProducer;
import io.zbus.client.producer.TransactionCheckListener;
import io.zbus.client.producer.TransactionMQProducer;
import io.zbus.mq.Broker;
import io.zbus.mq.Message;
import io.zbus.mq.Producer;
import io.zbus.spring.boot.exception.ZbusException;
import io.zbus.spring.boot.hooks.ZbusProducerShutdownHook;
import io.zbus.spring.boot.listener.DefaultTransactionCheckListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

/**
 * Spring Boot auto-configuration for the Zbus producer.
 * <p>
 * Activated when {@code spring.zbus.producer.enabled=true}. Registers a
 * {@link DefaultMQProducer} (or a {@link TransactionMQProducer} when
 * {@code transaction=true}), wires a JVM shutdown hook and exposes a
 * {@link ZbusProducerTemplate}.
 * </p>
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
@Configuration
@ConditionalOnClass({ DefaultMQProducer.class })
@ConditionalOnProperty(prefix = ZbusProducerProperties.PREFIX, value = "enabled", havingValue = "true")
@AutoConfigureOrder(Ordered.LOWEST_PRECEDENCE - 10)
@EnableConfigurationProperties({ ZbusProducerProperties.class })
public class ZbusProducerAutoConfiguration {

	private static final Logger LOG = LoggerFactory.getLogger(ZbusProducerAutoConfiguration.class);



	/**
	 * Configures the supplied producer from the bound properties.
	 *
	 * @param producer    the producer to configure
	 * @param properties  the producer properties
	 */
	public void configure(DefaultMQProducer producer, ZbusProducerProperties properties) {
		producer.setClientCallbackExecutorThreads(properties.getClientCallbackExecutorThreads());
		producer.setClientIP(properties.getClientIP());
		producer.setCompressMsgBodyOverHowmuch(properties.getCompressMsgBodyOverHowmuch());
		producer.setCreateTopicKey(properties.getCreateTopicKey());
		producer.setDefaultTopicQueueNums(properties.getDefaultTopicQueueNums());
		producer.setHeartbeatBrokerInterval(properties.getHeartbeatBrokerInterval());
		producer.setInstanceName(properties.getInstanceName());
		producer.setLatencyMax(properties.getLatencyMax());
		producer.setMaxMessageSize(properties.getMaxMessageSize());
		producer.setNamesrvAddr(properties.getNamesrvAddr());
		producer.setNotAvailableDuration(properties.getNotAvailableDuration());
		producer.setPersistConsumerOffsetInterval(properties.getPersistConsumerOffsetInterval());
		producer.setPollNameServerInterval(properties.getPollNameServerInterval());
		producer.setProducerGroup(properties.getProducerGroup());
		producer.setRetryAnotherBrokerWhenNotStoreOK(properties.isRetryAnotherBrokerWhenNotStoreOK());
		producer.setRetryTimesWhenSendAsyncFailed(properties.getRetryTimesWhenSendAsyncFailed());
		producer.setRetryTimesWhenSendFailed(properties.getRetryTimesWhenSendFailed());
		producer.setSendLatencyFaultEnable(properties.isSendLatencyFaultEnable());
		producer.setSendMessageWithVIPChannel(properties.isVipChannelEnabled());
		producer.setSendMsgTimeout(properties.getSendMsgTimeout());
		producer.setUnitMode(properties.isUnitMode());
		producer.setUnitName(properties.getUnitName());
		producer.setVipChannelEnabled(properties.isVipChannelEnabled());
	}

	/**
	 * Creates the Zbus producer (transactional or plain) and starts it.
	 */
	@Bean
	@ConditionalOnProperty(prefix = ZbusProducerProperties.PREFIX, value = "producerGroup")
	public DefaultMQProducer defaultProducer(ZbusProducerProperties properties,
			TransactionCheckListener transactionCheckListener) throws MQClientException {

		if (StringUtils.isEmpty(properties.getProducerGroup())) {
			throw new ZbusException("producerGroup is empty");
		}
		if (StringUtils.isEmpty(properties.getNamesrvAddr())) {
			throw new ZbusException("nameServerAddr is empty");
		}
		if (StringUtils.isEmpty(properties.getInstanceName())) {
			throw new ZbusException("instanceName is empty");
		}
		String trackerList, String token
		// Broker: local abstraction of the zbus server, multi-address for HA.
		Broker broker = new Broker("localhost:15555");

		Producer p = new Producer(broker);


		p.declareTopic("MyTopic");    // Declare the topic when it is known not to exist.


		Message res = p.publish(msg);
		System.out.println(res);

		broker.close();


		/*
		 * One application should create a single Producer and maintain it
		 * (e.g. as a singleton). The producer group name must be unique.
		 * The producer group matters little for plain messages but is
		 * critical for distributed transaction messages because the server
		 * back-checks any producer within the group.
		 */

		// Whether a transactional producer is required.
		if (properties.isTransaction()) {
			try {
				/*
				 * Initialise the transactional message producer.
				 */
				TransactionMQProducer producer = new TransactionMQProducer(properties.getProducerGroup());

				// Initialise producer parameters.
				this.configure(producer, properties);

				// Minimum concurrency for transaction back-checks.
				producer.setCheckThreadPoolMinSize(properties.getCheckThreadPoolMinSize());
				// Maximum concurrency for transaction back-checks.
				producer.setCheckThreadPoolMaxSize(properties.getCheckThreadPoolMaxSize());
				// Queue size for pending back-check requests.
				producer.setCheckRequestHoldMax(properties.getCheckRequestHoldMax());
				// Note: the community server build strips back-check support, so this has no effect there.
				producer.setTransactionCheckListener(transactionCheckListener);

				/*
				 * The producer must be started once before use; do not call
				 * start on every send.
				 */
				producer.start();

				LOG.info("Zbus TransactionMQProducer Started ! groupName:[%s],namesrvAddr:[%s],instanceName:[%s].",
						properties.getProducerGroup(), properties.getNamesrvAddr(), properties.getInstanceName());
				/**
				 * On application exit call shutdown to release resources,
				 * close network connections and unregister from the broker.
				 * It is recommended to call shutdown from the JVM shutdown
				 * hook (e.g. when running inside JBoss/Tomcat).
				 */
				Runtime.getRuntime().addShutdownHook(new ZbusProducerShutdownHook(producer));

				return producer;

			} catch (Exception e) {
				LOG.error(String.format("Producer is error {}", e.getMessage(), e));
				throw new ZbusException(e);
			}

		} else {

			try {

				// Create the plain producer.
				DefaultMQProducer producer = new DefaultMQProducer(properties.getProducerGroup());

				// Initialise producer parameters.
				this.configure(producer, properties);

				/*
				 * The producer must be started once before use; do not call
				 * start on every send.
				 */
				producer.start();

				LOG.info("Zbus MQProducer Started ! groupName:[%s],namesrvAddr:[%s],instanceName:[%s].",
						properties.getProducerGroup(), properties.getNamesrvAddr(), properties.getInstanceName());

				/**
				 * On application exit call shutdown to release resources,
				 * close network connections and unregister from the broker.
				 * It is recommended to call shutdown from the JVM shutdown
				 * hook (e.g. when running inside JBoss/Tomcat).
				 */
				Runtime.getRuntime().addShutdownHook(new ZbusProducerShutdownHook(producer));

				return producer;
			} catch (Exception e) {
				LOG.error(String.format("Zbus MQProducer Start failure ：%s", e.getMessage(), e));
				throw new ZbusException(e);
			}
		}

	}

	/**
	 * @param producer the Zbus producer
	 * @return a {@link ZbusProducerTemplate} wrapping the producer
	 * @throws MQClientException never thrown directly
	 */
	@Bean
	public ZbusProducerTemplate rocketmqProducerTemplate(DefaultMQProducer producer) throws MQClientException {
		return new ZbusProducerTemplate(producer);
	}

}
