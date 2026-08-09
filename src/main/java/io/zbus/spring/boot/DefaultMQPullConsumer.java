package io.zbus.spring.boot;

/**
 * Stub for DefaultMQPullConsumer.
 */
public class DefaultMQPullConsumer {

    private String consumerGroup;

    public DefaultMQPullConsumer() {}
    public DefaultMQPullConsumer(String consumerGroup) { this.consumerGroup = consumerGroup; }

    public String getConsumerGroup() { return consumerGroup; }
    public void setConsumerGroup(String consumerGroup) { this.consumerGroup = consumerGroup; }
    public void start() {}
    public void shutdown() {}
    public void resetClientConfig(Object properties) {}
    public void setBrokerSuspendMaxTimeMillis(long v) {}
    public void setClientCallbackExecutorThreads(int v) {}
    public void setClientIP(String v) {}
    public void setConsumerPullTimeoutMillis(long v) {}
    public void setConsumerTimeoutMillisWhenSuspend(long v) {}
    public void setHeartbeatBrokerInterval(int v) {}
    public void setInstanceName(String v) {}
    public void setMaxReconsumeTimes(int v) {}
    public void setNamesrvAddr(String v) {}
    public void setMessageModel(Object v) {}
    public void setPersistConsumerOffsetInterval(int v) {}
    public void setPollNameServerInterval(int v) {}
    public void setRegisterTopics(Object v) {}
    public void setUnitMode(boolean v) {}
    public void setUnitName(String v) {}
    public void setVipChannelEnabled(boolean v) {}
    public void setAllocateMessageQueueStrategy(Object v) {}
    public void registerMessageQueueListener(String topic, Object listener) {}
}
