package io.zbus.mq;

import io.zbus.mq.MessageHandler;

import java.io.IOException;

/**
 * Stub for zbus ConsumerConfig.
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class ConsumerConfig {

    private Object broker;
    private String topic;
    private MessageHandler messageHandler;

    public ConsumerConfig() {}
    public ConsumerConfig(Object broker) { this.broker = broker; }

    /**
     * Returns the topic.
     *
     * @return the topic
     */
    public String getTopic() { return topic; }
    /**
     * Sets the topic.
     *
     * @param topic the topic
     */
    public void setTopic(String topic) { this.topic = topic; }
    /**
     * Returns the message handler.
     *
     * @return the message handler
     */
    public MessageHandler getMessageHandler() { return messageHandler; }
    /**
     * Sets the message handler.
     *
     * @param messageHandler the message handler
     */
    public void setMessageHandler(MessageHandler messageHandler) { this.messageHandler = messageHandler; }
}
