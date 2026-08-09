package io.zbus.mq;

import io.zbus.mq.MessageHandler;

import java.io.IOException;

/**
 * Stub for zbus ConsumerConfig.
 */
public class ConsumerConfig {

    private Object broker;
    private String topic;
    private MessageHandler messageHandler;

    public ConsumerConfig() {}
    public ConsumerConfig(Object broker) { this.broker = broker; }

    public String getTopic() { return topic; }
    public void setTopic(String topic) { this.topic = topic; }
    public MessageHandler getMessageHandler() { return messageHandler; }
    public void setMessageHandler(MessageHandler messageHandler) { this.messageHandler = messageHandler; }
}
