package io.zbus.client.producer;

import io.zbus.client.exception.MQClientException;

/**
 * Stub for zbus default MQ producer.
 */
public class DefaultMQProducer {

    private String producerGroup;
    private String namesrvAddr;
    private String instanceName;
    private int clientCallbackExecutorThreads;
    private String clientIP;
    private int compressMsgBodyOverHowmuch;
    private String createTopicKey;
    private int defaultTopicQueueNums;
    private int heartbeatBrokerInterval;
    private int latencyMax;
    private int maxMessageSize;
    private int notAvailableDuration;
    private int persistConsumerOffsetInterval;
    private int pollNameServerInterval;
    private boolean retryAnotherBrokerWhenNotStoreOK;
    private int retryTimesWhenSendAsyncFailed;
    private int retryTimesWhenSendFailed;
    private boolean sendLatencyFaultEnable;
    private boolean vipChannelEnabled;
    private int sendMsgTimeout;
    private boolean unitMode;
    private String unitName;

    public DefaultMQProducer() {
    }

    public DefaultMQProducer(String producerGroup) {
        this.producerGroup = producerGroup;
    }

    public void start() throws MQClientException {
    }

    public void shutdown() {
    }

    public String getProducerGroup() { return producerGroup; }
    public void setProducerGroup(String producerGroup) { this.producerGroup = producerGroup; }
    public String getNamesrvAddr() { return namesrvAddr; }
    public void setNamesrvAddr(String namesrvAddr) { this.namesrvAddr = namesrvAddr; }
    public String getInstanceName() { return instanceName; }
    public void setInstanceName(String instanceName) { this.instanceName = instanceName; }
    public int getClientCallbackExecutorThreads() { return clientCallbackExecutorThreads; }
    public void setClientCallbackExecutorThreads(int v) { this.clientCallbackExecutorThreads = v; }
    public String getClientIP() { return clientIP; }
    public void setClientIP(String v) { this.clientIP = v; }
    public int getCompressMsgBodyOverHowmuch() { return compressMsgBodyOverHowmuch; }
    public void setCompressMsgBodyOverHowmuch(int v) { this.compressMsgBodyOverHowmuch = v; }
    public String getCreateTopicKey() { return createTopicKey; }
    public void setCreateTopicKey(String v) { this.createTopicKey = v; }
    public int getDefaultTopicQueueNums() { return defaultTopicQueueNums; }
    public void setDefaultTopicQueueNums(int v) { this.defaultTopicQueueNums = v; }
    public int getHeartbeatBrokerInterval() { return heartbeatBrokerInterval; }
    public void setHeartbeatBrokerInterval(int v) { this.heartbeatBrokerInterval = v; }
    public int getLatencyMax() { return latencyMax; }
    public void setLatencyMax(int v) { this.latencyMax = v; }
    public int getMaxMessageSize() { return maxMessageSize; }
    public void setMaxMessageSize(int v) { this.maxMessageSize = v; }
    public int getNotAvailableDuration() { return notAvailableDuration; }
    public void setNotAvailableDuration(int v) { this.notAvailableDuration = v; }
    public int getPersistConsumerOffsetInterval() { return persistConsumerOffsetInterval; }
    public void setPersistConsumerOffsetInterval(int v) { this.persistConsumerOffsetInterval = v; }
    public int getPollNameServerInterval() { return pollNameServerInterval; }
    public void setPollNameServerInterval(int v) { this.pollNameServerInterval = v; }
    public boolean isRetryAnotherBrokerWhenNotStoreOK() { return retryAnotherBrokerWhenNotStoreOK; }
    public void setRetryAnotherBrokerWhenNotStoreOK(boolean v) { this.retryAnotherBrokerWhenNotStoreOK = v; }
    public int getRetryTimesWhenSendAsyncFailed() { return retryTimesWhenSendAsyncFailed; }
    public void setRetryTimesWhenSendAsyncFailed(int v) { this.retryTimesWhenSendAsyncFailed = v; }
    public int getRetryTimesWhenSendFailed() { return retryTimesWhenSendFailed; }
    public void setRetryTimesWhenSendFailed(int v) { this.retryTimesWhenSendFailed = v; }
    public boolean isSendLatencyFaultEnable() { return sendLatencyFaultEnable; }
    public void setSendLatencyFaultEnable(boolean v) { this.sendLatencyFaultEnable = v; }
    public boolean isVipChannelEnabled() { return vipChannelEnabled; }
    public void setVipChannelEnabled(boolean v) { this.vipChannelEnabled = v; }
    public void setSendMessageWithVIPChannel(boolean v) { this.vipChannelEnabled = v; }
    public int getSendMsgTimeout() { return sendMsgTimeout; }
    public void setSendMsgTimeout(int v) { this.sendMsgTimeout = v; }
    public boolean isUnitMode() { return unitMode; }
    public void setUnitMode(boolean v) { this.unitMode = v; }
    public String getUnitName() { return unitName; }
    public void setUnitName(String v) { this.unitName = v; }
}
