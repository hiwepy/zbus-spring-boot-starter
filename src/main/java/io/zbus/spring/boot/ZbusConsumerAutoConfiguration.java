package io.zbus.spring.boot;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;
import org.zbus.broker.Broker;
import org.zbus.broker.ZbusBroker;
import org.zbus.mq.Consumer;
import org.zbus.net.http.Message.MessageHandler;

import io.zbus.client.exception.MQClientException;
import io.zbus.mq.ConsumerConfig;
import io.zbus.spring.boot.config.SubscriptionProvider;
import io.zbus.spring.boot.exception.ZbusException;
import io.zbus.spring.boot.hooks.ZbusConsumerShutdownHook;

/**
 * Spring Boot auto-configuration for the Zbus <strong>consumer</strong>.
 * <p>
 * Activated when {@code spring.zbus.consume.enabled=true}. Registers a Zbus
 * {@link Consumer}, declares the topics discovered via a
 * {@link SubscriptionProvider} or the {@code subscription} property map, binds
 * the supplied {@link MessageHandler} and exposes a
 * {@link ZbusConsumerTemplate}. The consumer is started after a configurable
 * delay so that Spring event listeners are ready before messages arrive.
 * </p>
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
@Configuration
@ConditionalOnClass({ Consumer.class })
@ConditionalOnProperty(prefix = ZbusConsumerProperties.PREFIX, value = "enabled", havingValue = "true")
@AutoConfigureAfter(ZbusPushEventHandlerAutoConfiguration.class)
@EnableConfigurationProperties({ ZbusConsumerProperties.class })
public class ZbusConsumerAutoConfiguration  {

	private static final Logger LOG = LoggerFactory.getLogger(ZbusConsumerAutoConfiguration.class);

	/**
	 * Creates and starts the Zbus {@link Consumer}, wiring subscriptions and the
	 * message handler.
	 *
	 * @param properties  the consumer properties
	 * @param subProvider optional subscription provider
	 * @param messageHandler the message handler to receive events
	 * @return the started Zbus consumer
	 * @throws ZbusException if consumer initialisation fails
	 */
	@Bean
	@ConditionalOnMissingBean
	public Consumer consumer(ZbusConsumerProperties properties,
			@Autowired(required = false) SubscriptionProvider subProvider,
			MessageHandler messageHandler) throws ZbusException {

		try {

			/*
			 * One application should create a single Consumer and maintain it
			 * (e.g. as a singleton). The consumer group name must be unique.
			 */


			Broker broker = new ZbusBroker("localhost:15555");

			ConsumerConfig config = new ConsumerConfig(broker);

			// Specify the message-queue topic; a group channel may also be specified.
			config.setTopic("MyTopic");
			config.setMessageHandler(messageHandler);

			Consumer consumer = new Consumer(config);
			consumer.start();

			consumer.declareGroup(topic, group)
			consumer.declareGroup(topic, group)



			consumer.setAdminServerSelector(adminServerSelector);
			consumer.setConsumeServerSelector(consumeServerSelector);



			/*
			 * Subscribe to the configured topics and selector expressions.
			 */
			Map<String /* topic */, String /* selectorExpress */> subscription = new HashMap<String, String>();
			if(subProvider != null) {
				Map<String /* topic */, String /* selectorExpress */> subs = subProvider.subscription();
				if(!CollectionUtils.isEmpty(subs) ){
					subscription.putAll(subs);
				}
			}
			if(!CollectionUtils.isEmpty(properties.getSubscription()) ){
				subscription.putAll(properties.getSubscription());
			}

			if(!CollectionUtils.isEmpty(subscription) ){

				Iterator<Entry<String, String>> ite = subscription.entrySet().iterator();
				while (ite.hasNext()) {
					Entry<String, String> entry = ite.next();
					/*
					 * entry.getKey()   : topic name
					 * entry.getValue() : selector expression for the topic
					 */
					String topic = entry.getKey();
					String selectorExpress = entry.getValue();

					consumer.declareTopic(topic);

				}

			}

			/*
			 * Register the consume listener.
			 */
			consumer.setMessageHandler(messageHandler);

			/*
			 * Delay the start by a few seconds so Spring event listeners finish
			 * initialising; otherwise consuming a message and immediately
			 * publishing a message-arrived event could lose the event because
			 * its listener is not yet registered.
			 */
			Executors.newScheduledThreadPool(1).schedule(new Thread() {
				public void run() {
					try {

						/*
						 * The consumer must be started once before use.
						 */
						consumer.start();

						LOG.info("Zbus Consumer Started ! groupName:[%s],namesrvAddr:[%s],instanceName:[%s].",
								properties.getConsumerGroup(), properties.getNamesrvAddr(), properties.getInstanceName());

						/**
						 * On application exit call shutdown to release
						 * resources, close network connections and unregister
						 * from the broker. It is recommended to call shutdown
						 * from the JVM shutdown hook (e.g. when running inside
						 * JBoss/Tomcat).
						 */
						Runtime.getRuntime().addShutdownHook(new ZbusConsumerShutdownHook(consumer));

					} catch (Exception e) {
						LOG.error(String.format("Zbus Consumer Start failure ：%s", e.getMessage(), e));
					}
				}
			}, properties.getDelayStartSeconds(), TimeUnit.SECONDS);

			return consumer;

		} catch (Exception e) {
			throw new ZbusException(e);
		}
	}

	/**
	 * @param consumer the Zbus consumer
	 * @return a {@link ZbusConsumerTemplate} wrapping the consumer
	 * @throws MQClientException never thrown directly
	 */
	@Bean
	public ZbusConsumerTemplate rocketmqConsumerTemplate(Consumer consumer) throws MQClientException {
		return new ZbusConsumerTemplate(consumer);
	}

}
