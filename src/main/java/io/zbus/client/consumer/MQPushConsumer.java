package io.zbus.client.consumer;

import io.zbus.client.consumer.listener.MessageListenerConcurrently;
import io.zbus.client.consumer.listener.MessageListenerOrderly;
import io.zbus.client.exception.MQClientException;
import io.zbus.client.producer.MessageQueueSelector;

/**
 * Stub for zbus MQ push consumer.
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
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

    /**
     * start.
     *
     * @throws MQClientException if an error occurs
     */
    public void start() throws MQClientException {
    }

    /**
     * shutdown.
     *
     */
    public void shutdown() {
    }

    /**
     * subscribe.
     *
     * @param topic the topic
     * @param subExpression the sub expression
     * @throws MQClientException if an error occurs
     */
    public void subscribe(String topic, String subExpression) throws MQClientException {
    }

    /**
     * unsubscribe.
     *
     * @param topic the topic
     */
    public void unsubscribe(String topic) {
    }

    /**
     * register Message Listener.
     *
     * @param listener the listener
     */
    public void registerMessageListener(MessageListenerConcurrently listener) {
    }

    /**
     * register Message Listener.
     *
     * @param listener the listener
     */
    public void registerMessageListener(MessageListenerOrderly listener) {
    }

    /**
     * Returns the consumer group.
     *
     * @return the consumer group
     */
    public String getConsumerGroup() {
        return consumerGroup;
    }

    /**
     * Sets the consumer group.
     *
     * @param consumerGroup the consumer group
     */
    public void setConsumerGroup(String consumerGroup) {
        this.consumerGroup = consumerGroup;
    }

    /**
     * Returns the namesrv addr.
     *
     * @return the namesrv addr
     */
    public String getNamesrvAddr() {
        return namesrvAddr;
    }

    /**
     * Sets the namesrv addr.
     *
     * @param namesrvAddr the namesrv addr
     */
    public void setNamesrvAddr(String namesrvAddr) {
        this.namesrvAddr = namesrvAddr;
    }

    /**
     * Returns the instance name.
     *
     * @return the instance name
     */
    public String getInstanceName() {
        return instanceName;
    }

    /**
     * Sets the instance name.
     *
     * @param instanceName the instance name
     */
    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    /**
     * Sets the client callback executor threads.
     *
     * @param threads the threads
     */
    public void setClientCallbackExecutorThreads(int threads) {
    }

    /**
     * Sets the heartbeat broker interval.
     *
     * @param interval the interval
     */
    public void setHeartbeatBrokerInterval(int interval) {
    }

    /**
     * Sets the persist consumer offset interval.
     *
     * @param interval the interval
     */
    public void setPersistConsumerOffsetInterval(int interval) {
    }

    /**
     * Sets the poll name server interval.
     *
     * @param interval the interval
     */
    public void setPollNameServerInterval(int interval) {
    }

    /**
     * Sets the consume thread min.
     *
     * @param min the min
     */
    public void setConsumeThreadMin(int min) {
    }

    /**
     * Sets the consume thread max.
     *
     * @param max the max
     */
    public void setConsumeThreadMax(int max) {
    }

    /**
     * Sets the consume message batch max size.
     *
     * @param size the size
     */
    public void setConsumeMessageBatchMaxSize(int size) {
    }

    /**
     * Sets the pull batch size.
     *
     * @param size the size
     */
    public void setPullBatchSize(int size) {
    }

    /**
     * Sets the max reconsume times.
     *
     * @param times the times
     */
    public void setMaxReconsumeTimes(int times) {
    }

    /**
     * Sets the message model.
     *
     * @param model the model
     */
    public void setMessageModel(Object model) {
    }

    /**
     * Sets the allocate message queue strategy.
     *
     * @param strategy the strategy
     */
    public void setAllocateMessageQueueStrategy(Object strategy) {
    }
}
