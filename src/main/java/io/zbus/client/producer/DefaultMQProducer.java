package io.zbus.client.producer;

import io.zbus.client.exception.MQClientException;

/**
 * Stub for zbus default MQ producer.
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
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
     * Returns the producer group.
     *
     * @return the producer group
     */
    public String getProducerGroup() { return producerGroup; }
    /**
     * Sets the producer group.
     *
     * @param producerGroup the producer group
     */
    public void setProducerGroup(String producerGroup) { this.producerGroup = producerGroup; }
    /**
     * Returns the namesrv addr.
     *
     * @return the namesrv addr
     */
    public String getNamesrvAddr() { return namesrvAddr; }
    /**
     * Sets the namesrv addr.
     *
     * @param namesrvAddr the namesrv addr
     */
    public void setNamesrvAddr(String namesrvAddr) { this.namesrvAddr = namesrvAddr; }
    /**
     * Returns the instance name.
     *
     * @return the instance name
     */
    public String getInstanceName() { return instanceName; }
    /**
     * Sets the instance name.
     *
     * @param instanceName the instance name
     */
    public void setInstanceName(String instanceName) { this.instanceName = instanceName; }
    /**
     * Returns the client callback executor threads.
     *
     * @return the client callback executor threads
     */
    public int getClientCallbackExecutorThreads() { return clientCallbackExecutorThreads; }
    /**
     * Sets the client callback executor threads.
     *
     * @param v the v
     */
    public void setClientCallbackExecutorThreads(int v) { this.clientCallbackExecutorThreads = v; }
    /**
     * Returns the client i p.
     *
     * @return the client i p
     */
    public String getClientIP() { return clientIP; }
    /**
     * Sets the client i p.
     *
     * @param v the v
     */
    public void setClientIP(String v) { this.clientIP = v; }
    /**
     * Returns the compress msg body over howmuch.
     *
     * @return the compress msg body over howmuch
     */
    public int getCompressMsgBodyOverHowmuch() { return compressMsgBodyOverHowmuch; }
    /**
     * Sets the compress msg body over howmuch.
     *
     * @param v the v
     */
    public void setCompressMsgBodyOverHowmuch(int v) { this.compressMsgBodyOverHowmuch = v; }
    /**
     * Returns the create topic key.
     *
     * @return the create topic key
     */
    public String getCreateTopicKey() { return createTopicKey; }
    /**
     * Sets the create topic key.
     *
     * @param v the v
     */
    public void setCreateTopicKey(String v) { this.createTopicKey = v; }
    /**
     * Returns the default topic queue nums.
     *
     * @return the default topic queue nums
     */
    public int getDefaultTopicQueueNums() { return defaultTopicQueueNums; }
    /**
     * Sets the default topic queue nums.
     *
     * @param v the v
     */
    public void setDefaultTopicQueueNums(int v) { this.defaultTopicQueueNums = v; }
    /**
     * Returns the heartbeat broker interval.
     *
     * @return the heartbeat broker interval
     */
    public int getHeartbeatBrokerInterval() { return heartbeatBrokerInterval; }
    /**
     * Sets the heartbeat broker interval.
     *
     * @param v the v
     */
    public void setHeartbeatBrokerInterval(int v) { this.heartbeatBrokerInterval = v; }
    /**
     * Returns the latency max.
     *
     * @return the latency max
     */
    public int getLatencyMax() { return latencyMax; }
    /**
     * Sets the latency max.
     *
     * @param v the v
     */
    public void setLatencyMax(int v) { this.latencyMax = v; }
    /**
     * Returns the max message size.
     *
     * @return the max message size
     */
    public int getMaxMessageSize() { return maxMessageSize; }
    /**
     * Sets the max message size.
     *
     * @param v the v
     */
    public void setMaxMessageSize(int v) { this.maxMessageSize = v; }
    /**
     * Returns the not available duration.
     *
     * @return the not available duration
     */
    public int getNotAvailableDuration() { return notAvailableDuration; }
    /**
     * Sets the not available duration.
     *
     * @param v the v
     */
    public void setNotAvailableDuration(int v) { this.notAvailableDuration = v; }
    /**
     * Returns the persist consumer offset interval.
     *
     * @return the persist consumer offset interval
     */
    public int getPersistConsumerOffsetInterval() { return persistConsumerOffsetInterval; }
    /**
     * Sets the persist consumer offset interval.
     *
     * @param v the v
     */
    public void setPersistConsumerOffsetInterval(int v) { this.persistConsumerOffsetInterval = v; }
    /**
     * Returns the poll name server interval.
     *
     * @return the poll name server interval
     */
    public int getPollNameServerInterval() { return pollNameServerInterval; }
    /**
     * Sets the poll name server interval.
     *
     * @param v the v
     */
    public void setPollNameServerInterval(int v) { this.pollNameServerInterval = v; }
    /**
     * Returns the retry another broker when not store o k.
     *
     * @return the retry another broker when not store o k
     */
    public boolean isRetryAnotherBrokerWhenNotStoreOK() { return retryAnotherBrokerWhenNotStoreOK; }
    /**
     * Sets the retry another broker when not store o k.
     *
     * @param v the v
     */
    public void setRetryAnotherBrokerWhenNotStoreOK(boolean v) { this.retryAnotherBrokerWhenNotStoreOK = v; }
    /**
     * Returns the retry times when send async failed.
     *
     * @return the retry times when send async failed
     */
    public int getRetryTimesWhenSendAsyncFailed() { return retryTimesWhenSendAsyncFailed; }
    /**
     * Sets the retry times when send async failed.
     *
     * @param v the v
     */
    public void setRetryTimesWhenSendAsyncFailed(int v) { this.retryTimesWhenSendAsyncFailed = v; }
    /**
     * Returns the retry times when send failed.
     *
     * @return the retry times when send failed
     */
    public int getRetryTimesWhenSendFailed() { return retryTimesWhenSendFailed; }
    /**
     * Sets the retry times when send failed.
     *
     * @param v the v
     */
    public void setRetryTimesWhenSendFailed(int v) { this.retryTimesWhenSendFailed = v; }
    /**
     * Returns the send latency fault enable.
     *
     * @return the send latency fault enable
     */
    public boolean isSendLatencyFaultEnable() { return sendLatencyFaultEnable; }
    /**
     * Sets the send latency fault enable.
     *
     * @param v the v
     */
    public void setSendLatencyFaultEnable(boolean v) { this.sendLatencyFaultEnable = v; }
    /**
     * Returns the vip channel enabled.
     *
     * @return the vip channel enabled
     */
    public boolean isVipChannelEnabled() { return vipChannelEnabled; }
    /**
     * Sets the vip channel enabled.
     *
     * @param v the v
     */
    public void setVipChannelEnabled(boolean v) { this.vipChannelEnabled = v; }
    /**
     * Sets the send message with v i p channel.
     *
     * @param v the v
     */
    public void setSendMessageWithVIPChannel(boolean v) { this.vipChannelEnabled = v; }
    /**
     * Returns the send msg timeout.
     *
     * @return the send msg timeout
     */
    public int getSendMsgTimeout() { return sendMsgTimeout; }
    /**
     * Sets the send msg timeout.
     *
     * @param v the v
     */
    public void setSendMsgTimeout(int v) { this.sendMsgTimeout = v; }
    /**
     * Returns the unit mode.
     *
     * @return the unit mode
     */
    public boolean isUnitMode() { return unitMode; }
    /**
     * Sets the unit mode.
     *
     * @param v the v
     */
    public void setUnitMode(boolean v) { this.unitMode = v; }
    /**
     * Returns the unit name.
     *
     * @return the unit name
     */
    public String getUnitName() { return unitName; }
    /**
     * Sets the unit name.
     *
     * @param v the v
     */
    public void setUnitName(String v) { this.unitName = v; }
}
