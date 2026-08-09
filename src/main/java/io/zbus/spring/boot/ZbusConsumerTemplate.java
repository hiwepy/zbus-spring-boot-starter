package io.zbus.spring.boot;

import io.zbus.mq.Consumer;
import io.zbus.spring.boot.enums.ConsumeMode;
import io.zbus.spring.boot.event.RocketmqEvent;
import io.zbus.spring.boot.exception.ZbusException;
import io.zbus.spring.boot.handler.EventHandler;
import io.zbus.spring.boot.handler.chain.HandlerChainManager;
import io.zbus.spring.boot.handler.chain.def.PathMatchingHandlerChainResolver;
import io.zbus.spring.boot.handler.impl.RocketmqEventMessageConcurrentlyHandler;
import io.zbus.spring.boot.handler.impl.RocketmqEventMessageOrderlyHandler;
import io.zbus.spring.boot.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Helper template for the Zbus consumer, exposing the underlying
 * {@link Consumer} together with convenience methods for subscribing to topics
 * and binding {@link EventHandler} instances to the handler-chain registry.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
public class ZbusConsumerTemplate {

    /** Separator used when joining multiple tag expressions. */
    public final String SELECTOR_EXPRESSS_EPARATOR = " || ";

    @Autowired
    private RocketmqEventMessageOrderlyHandler messageOrderlyHandler;
    @Autowired
    private RocketmqEventMessageConcurrentlyHandler messageConcurrentlyHandler;
    @Autowired
    private ZbusConsumerProperties pushConsumerProperties;

    private Consumer consumer;

    public ZbusConsumerTemplate(Consumer consumer) {
        this.consumer = consumer;
    }

    public void subscribe(String topic, String handlerName, EventHandler<RocketmqEvent> handler) throws ZbusException {
        consumer.subscribe(topic, "");
    }

    public void unsubscribe(String topic, String tags, String handlerName) {
        consumer.removeTopic(topic);
        consumer.queryTopic(topic);
        consumer.unsubscribe(topic);
    }

    public RocketmqEventMessageOrderlyHandler getMessageOrderlyHandler() {
        return messageOrderlyHandler;
    }

    public void setMessageOrderlyHandler(RocketmqEventMessageOrderlyHandler messageOrderlyHandler) {
        this.messageOrderlyHandler = messageOrderlyHandler;
    }

    public RocketmqEventMessageConcurrentlyHandler getMessageConcurrentlyHandler() {
        return messageConcurrentlyHandler;
    }

    public void setMessageConcurrentlyHandler(RocketmqEventMessageConcurrentlyHandler messageConcurrentlyHandler) {
        this.messageConcurrentlyHandler = messageConcurrentlyHandler;
    }

    public Consumer getConsumer() {
        return consumer;
    }

    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }

}
