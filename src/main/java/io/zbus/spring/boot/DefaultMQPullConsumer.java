package io.zbus.spring.boot;

/**
 * Stub for DefaultMQPullConsumer.
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class DefaultMQPullConsumer {

    private String consumerGroup;

    public DefaultMQPullConsumer() {}
    public DefaultMQPullConsumer(String consumerGroup) { this.consumerGroup = consumerGroup; }

    /**
     * Returns the consumer group.
     *
     * @return the consumer group
     */
    public String getConsumerGroup() { return consumerGroup; }
    /**
     * Sets the consumer group.
     *
     * @param consumerGroup the consumer group
     */
    public void setConsumerGroup(String consumerGroup) { this.consumerGroup = consumerGroup; }
    /**
     * start.
     *
     */
    public void start() {}
    /**
     * shutdown.
     *
     */
    public void shutdown() {}
    /**
     * reset Client Config.
     *
     * @param properties the properties
     */
    public void resetClientConfig(Object properties) {}
    /**
     * Sets the broker suspend max time millis.
     *
     * @param v the v
     */
    public void setBrokerSuspendMaxTimeMillis(long v) {}
    /**
     * Sets the client callback executor threads.
     *
     * @param v the v
     */
    public void setClientCallbackExecutorThreads(int v) {}
    /**
     * Sets the client i p.
     *
     * @param v the v
     */
    public void setClientIP(String v) {}
    /**
     * Sets the consumer pull timeout millis.
     *
     * @param v the v
     */
    public void setConsumerPullTimeoutMillis(long v) {}
    /**
     * Sets the consumer timeout millis when suspend.
     *
     * @param v the v
     */
    public void setConsumerTimeoutMillisWhenSuspend(long v) {}
    /**
     * Sets the heartbeat broker interval.
     *
     * @param v the v
     */
    public void setHeartbeatBrokerInterval(int v) {}
    /**
     * Sets the instance name.
     *
     * @param v the v
     */
    public void setInstanceName(String v) {}
    /**
     * Sets the max reconsume times.
     *
     * @param v the v
     */
    public void setMaxReconsumeTimes(int v) {}
    /**
     * Sets the namesrv addr.
     *
     * @param v the v
     */
    public void setNamesrvAddr(String v) {}
    /**
     * Sets the message model.
     *
     * @param v the v
     */
    public void setMessageModel(Object v) {}
    /**
     * Sets the persist consumer offset interval.
     *
     * @param v the v
     */
    public void setPersistConsumerOffsetInterval(int v) {}
    /**
     * Sets the poll name server interval.
     *
     * @param v the v
     */
    public void setPollNameServerInterval(int v) {}
    /**
     * Sets the register topics.
     *
     * @param v the v
     */
    public void setRegisterTopics(Object v) {}
    /**
     * Sets the unit mode.
     *
     * @param v the v
     */
    public void setUnitMode(boolean v) {}
    /**
     * Sets the unit name.
     *
     * @param v the v
     */
    public void setUnitName(String v) {}
    /**
     * Sets the vip channel enabled.
     *
     * @param v the v
     */
    public void setVipChannelEnabled(boolean v) {}
    /**
     * Sets the allocate message queue strategy.
     *
     * @param v the v
     */
    public void setAllocateMessageQueueStrategy(Object v) {}
    /**
     * register Message Queue Listener.
     *
     * @param topic the topic
     * @param listener the listener
     */
    public void registerMessageQueueListener(String topic, Object listener) {}
}
