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
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
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

    /**
     * subscribe.
     *
     * @param topic the topic
     * @param handlerName the handler name
     * @param handler the handler
     * @throws ZbusException if an error occurs
     */
    public void subscribe(String topic, String handlerName, EventHandler<RocketmqEvent> handler) throws ZbusException {
        consumer.subscribe(topic, "");
    }

    /**
     * unsubscribe.
     *
     * @param topic the topic
     * @param tags the tags
     * @param handlerName the handler name
     */
    public void unsubscribe(String topic, String tags, String handlerName) {
        consumer.removeTopic(topic);
        consumer.queryTopic(topic);
        consumer.unsubscribe(topic);
    }

    /**
     * Returns the message orderly handler.
     *
     * @return the message orderly handler
     */
    public RocketmqEventMessageOrderlyHandler getMessageOrderlyHandler() {
        return messageOrderlyHandler;
    }

    /**
     * Sets the message orderly handler.
     *
     * @param messageOrderlyHandler the message orderly handler
     */
    public void setMessageOrderlyHandler(RocketmqEventMessageOrderlyHandler messageOrderlyHandler) {
        this.messageOrderlyHandler = messageOrderlyHandler;
    }

    /**
     * Returns the message concurrently handler.
     *
     * @return the message concurrently handler
     */
    public RocketmqEventMessageConcurrentlyHandler getMessageConcurrentlyHandler() {
        return messageConcurrentlyHandler;
    }

    /**
     * Sets the message concurrently handler.
     *
     * @param messageConcurrentlyHandler the message concurrently handler
     */
    public void setMessageConcurrentlyHandler(RocketmqEventMessageConcurrentlyHandler messageConcurrentlyHandler) {
        this.messageConcurrentlyHandler = messageConcurrentlyHandler;
    }

    /**
     * Returns the consumer.
     *
     * @return the consumer
     */
    public Consumer getConsumer() {
        return consumer;
    }

    /**
     * Sets the consumer.
     *
     * @param consumer the consumer
     */
    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }

}
