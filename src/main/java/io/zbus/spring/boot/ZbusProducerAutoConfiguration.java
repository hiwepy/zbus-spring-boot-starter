package io.zbus.spring.boot;

import org.apache.commons.lang3.StringUtils;
import io.zbus.client.exception.MQClientException;
import io.zbus.client.producer.DefaultMQProducer;
import io.zbus.client.producer.TransactionCheckListener;
import io.zbus.client.producer.TransactionMQProducer;
import io.zbus.spring.boot.exception.ZbusException;
import io.zbus.spring.boot.listener.DefaultTransactionCheckListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

/**
 * Spring Boot auto-configuration for the Zbus producer.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
@Configuration
@ConditionalOnClass({ DefaultMQProducer.class })
@ConditionalOnProperty(prefix = ZbusProducerProperties.PREFIX, value = "enabled", havingValue = "true")
@AutoConfigureOrder(Ordered.LOWEST_PRECEDENCE - 10)
@EnableConfigurationProperties({ ZbusProducerProperties.class })
public class ZbusProducerAutoConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(ZbusProducerAutoConfiguration.class);

    public void configure(DefaultMQProducer producer, ZbusProducerProperties properties) {
        producer.setProducerGroup(properties.getProducerGroup());
        producer.setDefaultTopicQueueNums(properties.getDefaultTopicQueueNums());
        producer.setSendMsgTimeout(properties.getSendMsgTimeout());
        producer.setCompressMsgBodyOverHowmuch(properties.getCompressMsgBodyOverHowmuch());
        producer.setRetryTimesWhenSendFailed(properties.getRetryTimesWhenSendFailed());
        producer.setRetryTimesWhenSendAsyncFailed(properties.getRetryTimesWhenSendAsyncFailed());
        producer.setRetryAnotherBrokerWhenNotStoreOK(properties.isRetryAnotherBrokerWhenNotStoreOK());
        producer.setSendLatencyFaultEnable(properties.isSendLatencyFaultEnable());
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = ZbusProducerProperties.PREFIX, value = "producerGroup")
    public DefaultMQProducer defaultProducer(ZbusProducerProperties properties,
            TransactionCheckListener transactionCheckListener) throws MQClientException {

        if (StringUtils.isEmpty(properties.getProducerGroup())) {
            throw new ZbusException("producerGroup is empty");
        }

        if (properties.isTransaction()) {
            try {
                TransactionMQProducer producer = new TransactionMQProducer(properties.getProducerGroup());
                this.configure(producer, properties);
                producer.setCheckThreadPoolMinSize(properties.getCheckThreadPoolMinSize());
                producer.setCheckThreadPoolMaxSize(properties.getCheckThreadPoolMaxSize());
                producer.setCheckRequestHoldMax(properties.getCheckRequestHoldMax());
                producer.setTransactionCheckListener(transactionCheckListener);
                producer.start();
                LOG.info("Zbus TransactionMQProducer Started ! groupName:[{}].", properties.getProducerGroup());
                return producer;
            } catch (Exception e) {
                LOG.error("Producer error: {}", e.getMessage(), e);
                throw new ZbusException(e);
            }
        } else {
            try {
                DefaultMQProducer producer = new DefaultMQProducer(properties.getProducerGroup());
                this.configure(producer, properties);
                producer.start();
                LOG.info("Zbus MQProducer Started ! groupName:[{}].", properties.getProducerGroup());
                return producer;
            } catch (Exception e) {
                LOG.error("Zbus MQProducer Start failure: {}", e.getMessage(), e);
                throw new ZbusException(e);
            }
        }
    }

    @Bean
    public ZbusProducerTemplate rocketmqProducerTemplate(DefaultMQProducer producer) throws MQClientException {
        return new ZbusProducerTemplate(producer);
    }

}
