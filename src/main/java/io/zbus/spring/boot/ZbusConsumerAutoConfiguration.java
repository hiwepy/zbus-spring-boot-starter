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

import io.zbus.client.exception.MQClientException;
import io.zbus.mq.Broker;
import io.zbus.mq.Consumer;
import io.zbus.mq.ConsumerConfig;
import io.zbus.mq.MessageHandler;
import io.zbus.spring.boot.config.SubscriptionProvider;
import io.zbus.spring.boot.exception.ZbusException;
import io.zbus.spring.boot.hooks.ZbusConsumerShutdownHook;

/**
 * Spring Boot auto-configuration for the Zbus consumer.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
@Configuration
@ConditionalOnClass({ Consumer.class })
@ConditionalOnProperty(prefix = ZbusConsumerProperties.PREFIX, value = "enabled", havingValue = "true")
@AutoConfigureAfter(ZbusPushEventHandlerAutoConfiguration.class)
@EnableConfigurationProperties({ ZbusConsumerProperties.class })
public class ZbusConsumerAutoConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(ZbusConsumerAutoConfiguration.class);

    @Bean
    @ConditionalOnMissingBean
    public Consumer consumer(ZbusConsumerProperties properties,
            @Autowired(required = false) SubscriptionProvider subProvider,
            MessageHandler messageHandler) throws ZbusException {

        try {
            Broker broker = new Broker("localhost:15555");
            ConsumerConfig config = new ConsumerConfig(broker);
            config.setMessageHandler(messageHandler);

            Consumer consumer = new Consumer(config);
            consumer.start();

            Map<String, String> subscription = new HashMap<>();
            if (subProvider != null) {
                Map<String, String> subs = subProvider.subscription();
                if (!CollectionUtils.isEmpty(subs)) {
                    subscription.putAll(subs);
                }
            }
            if (!CollectionUtils.isEmpty(properties.getSubscription())) {
                subscription.putAll(properties.getSubscription());
            }

            if (!CollectionUtils.isEmpty(subscription)) {
                Iterator<Entry<String, String>> ite = subscription.entrySet().iterator();
                while (ite.hasNext()) {
                    Entry<String, String> entry = ite.next();
                    String topic = entry.getKey();
                    consumer.declareTopic(topic);
                }
            }

            consumer.setMessageHandler(messageHandler);

            Executors.newScheduledThreadPool(1).schedule(new Thread() {
                /**
                 * run.
                 *
                 */
                public void run() {
                    try {
                        consumer.start();
                        LOG.info("Zbus Consumer Started ! groupName:[{}].", properties.getConsumerGroup());
                        Runtime.getRuntime().addShutdownHook(new ZbusConsumerShutdownHook(consumer));
                    } catch (Exception e) {
                        LOG.error("Zbus Consumer Start failure: {}", e.getMessage(), e);
                    }
                }
            }, properties.getDelayStartSeconds(), TimeUnit.SECONDS);

            return consumer;

        } catch (Exception e) {
            throw new ZbusException(e);
        }
    }

    @Bean
    /**
     * rocketmq Consumer Template.
     *
     * @param consumer the consumer
     * @return the result
     * @throws MQClientException if an error occurs
     */
    public ZbusConsumerTemplate rocketmqConsumerTemplate(Consumer consumer) throws MQClientException {
        return new ZbusConsumerTemplate(consumer);
    }

}
