package io.zbus.client.consumer;

import io.zbus.client.consumer.listener.MessageListenerConcurrently;
import io.zbus.client.consumer.listener.MessageListenerOrderly;
import io.zbus.client.exception.MQClientException;
import io.zbus.client.producer.MessageQueueSelector;

/**
 * Stub for zbus MQ push consumer.
 */
public class MQPushConsumer {

    private String consumerGroup;
    private String namesrvAddr;
    private String instanceName;

    public MQPushConsumer() {
    }

    public MQPushConsumer(String consumerGroup) {
        this.consumerGroup = consumerGroup;
    }

    public void start() throws MQClientException {
    }

    public void shutdown() {
    }

    public void subscribe(String topic, String subExpression) throws MQClientException {
    }

    public void unsubscribe(String topic) {
    }

    public void registerMessageListener(MessageListenerConcurrently listener) {
    }

    public void registerMessageListener(MessageListenerOrderly listener) {
    }

    public String getConsumerGroup() {
        return consumerGroup;
    }

    public void setConsumerGroup(String consumerGroup) {
        this.consumerGroup = consumerGroup;
    }

    public String getNamesrvAddr() {
        return namesrvAddr;
    }

    public void setNamesrvAddr(String namesrvAddr) {
        this.namesrvAddr = namesrvAddr;
    }

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    public void setClientCallbackExecutorThreads(int threads) {
    }

    public void setHeartbeatBrokerInterval(int interval) {
    }

    public void setPersistConsumerOffsetInterval(int interval) {
    }

    public void setPollNameServerInterval(int interval) {
    }

    public void setConsumeThreadMin(int min) {
    }

    public void setConsumeThreadMax(int max) {
    }

    public void setConsumeMessageBatchMaxSize(int size) {
    }

    public void setPullBatchSize(int size) {
    }

    public void setMaxReconsumeTimes(int times) {
    }

    public void setMessageModel(Object model) {
    }

    public void setAllocateMessageQueueStrategy(Object strategy) {
    }
}
