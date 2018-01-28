/*
 * Copyright (c) 2010-2020, vindell (https://github.com/vindell).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package io.zbus.spring.boot;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@ConfigurationProperties(ZbusConsumerProperties.PREFIX)
public class ZbusConsumerProperties {
	
	/**
     * ConsumeType.CONSUME_PASSIVELY : "PUSH"
     */
	public static final String PREFIX = "spring.zbus.consume";
	
	/** 是否启用 **/
	private boolean enabled = false;
	
	/**
     * Consumers of the same role is required to have exactly same subscriptions and consumerGroup to correctly achieve
     * load balance. It's required and needs to be globally unique.
     * </p>
     *
     * See <a href="http://rocketmq.incubator.apache.org/docs/core-concept/">here</a> for further discussion.
     */
    private String consumerGroup;

    /**
     * Message model defines the way how messages are delivered to each consumer clients.
     * </p>
     *
     * RocketMQ supports two message models: clustering and broadcasting. If clustering is set, consumer clients with
     * the same {@link #consumerGroup} would only consume shards of the messages subscribed, which achieves load
     * balances; Conversely, if the broadcasting is set, each consumer client will consume all subscribed messages
     * separately.
     * 
     * BROADCASTING
     * CLUSTERING
     * </p>
     *
     * This field defaults to clustering.
     * 
     * 消息模式
     * 广播模式消费： BROADCASTING
     * 集群模式消费： CLUSTERING
     */
    private String messageModel = "CLUSTERING";
    
    /**
     * Subscription relationship
     */
    private Map<String /* topic */, String /* selectorExpress */> subscription = new HashMap<String, String>();
    
    /**
     * Minimum consumer thread number
     */
    private int consumeThreadMin = 20;

    /**
     * Max consumer thread number
     */
    private int consumeThreadMax = 64;

    /**
     * Threshold for dynamic adjustment of the number of thread pool
     */
    private long adjustThreadPoolNumsThreshold = 100000;

    /**
     * Concurrently max span offset.it has no effect on sequential consumption
     */
    private int consumeConcurrentlyMaxSpan = 2000;

    /**
     * Flow control threshold
     */
    private int pullThresholdForQueue = 1000;

    /**
     * Message pull Interval
     */
    private long pullInterval = 0;

    /**
     * Batch consumption size
     */
    private int consumeMessageBatchMaxSize = 1;

    /**
     * Batch pull size
     */
    private int pullBatchSize = 32;

    /**
     * Whether update subscription relationship when every pull
     */
    private boolean postSubscriptionWhenPull = false;

    /**
     * Max re-consume times. -1 means 16 times.
     * </p>
     *
     * If messages are re-consumed more than {@link #maxReconsumeTimes} before success, it's be directed to a deletion
     * queue waiting.
     */
    private int maxReconsumeTimes = -1;

    /**
     * Suspending pulling time for cases requiring slow pulling like flow-control scenario.
     */
    private long suspendCurrentQueueTimeMillis = 1000;

    /**
     * Maximum amount of time in minutes a message may block the consuming thread.
     */
    private long consumeTimeout = 15;
    
    /**
	 * Maximum number of retry to perform internally before claiming consume failure.
	 */
	private int retryTimesWhenConsumeFailed = 3;
	 /**
     * Message consume retry strategy<br> 
     * -1,no retry,put into DLQ directly<br> 
     * 0,broker control retry frequency<br>
     * >0,client control retry frequency
     */
	private int delayLevelWhenNextConsume = 0;
    
	/**
	 * 延迟启动时间，单位秒，主要是等待spring事件监听相关程序初始化完成，否则，会出现对RocketMQ的消息进行消费后立即发布消息到达的事件，然而此事件的监听程序还未初始化，从而造成消息的丢失
	 */
	private int delayStartSeconds = 10;
	
	@NestedConfigurationProperty
	private ZbusConsumerEventProperties event;
    
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public String getConsumerGroup() {
		return consumerGroup;
	}

	public void setConsumerGroup(String consumerGroup) {
		this.consumerGroup = consumerGroup;
	}

	public String getMessageModel() {
		return messageModel;
	}

	public void setMessageModel(String messageModel) {
		this.messageModel = messageModel;
	}

	public Map<String, String> getSubscription() {
		return subscription;
	}

	public void setSubscription(Map<String, String> subscription) {
		this.subscription = subscription;
	}

	public int getConsumeThreadMin() {
		return consumeThreadMin;
	}

	public void setConsumeThreadMin(int consumeThreadMin) {
		this.consumeThreadMin = consumeThreadMin;
	}

	public int getConsumeThreadMax() {
		return consumeThreadMax;
	}

	public void setConsumeThreadMax(int consumeThreadMax) {
		this.consumeThreadMax = consumeThreadMax;
	}

	public long getAdjustThreadPoolNumsThreshold() {
		return adjustThreadPoolNumsThreshold;
	}

	public void setAdjustThreadPoolNumsThreshold(long adjustThreadPoolNumsThreshold) {
		this.adjustThreadPoolNumsThreshold = adjustThreadPoolNumsThreshold;
	}

	public int getConsumeConcurrentlyMaxSpan() {
		return consumeConcurrentlyMaxSpan;
	}

	public void setConsumeConcurrentlyMaxSpan(int consumeConcurrentlyMaxSpan) {
		this.consumeConcurrentlyMaxSpan = consumeConcurrentlyMaxSpan;
	}

	public int getPullThresholdForQueue() {
		return pullThresholdForQueue;
	}

	public void setPullThresholdForQueue(int pullThresholdForQueue) {
		this.pullThresholdForQueue = pullThresholdForQueue;
	}

	public long getPullInterval() {
		return pullInterval;
	}

	public void setPullInterval(long pullInterval) {
		this.pullInterval = pullInterval;
	}

	public int getConsumeMessageBatchMaxSize() {
		return consumeMessageBatchMaxSize;
	}

	public void setConsumeMessageBatchMaxSize(int consumeMessageBatchMaxSize) {
		this.consumeMessageBatchMaxSize = consumeMessageBatchMaxSize;
	}

	public int getPullBatchSize() {
		return pullBatchSize;
	}

	public void setPullBatchSize(int pullBatchSize) {
		this.pullBatchSize = pullBatchSize;
	}

	public boolean isPostSubscriptionWhenPull() {
		return postSubscriptionWhenPull;
	}

	public void setPostSubscriptionWhenPull(boolean postSubscriptionWhenPull) {
		this.postSubscriptionWhenPull = postSubscriptionWhenPull;
	}

	public int getMaxReconsumeTimes() {
		return maxReconsumeTimes;
	}

	public void setMaxReconsumeTimes(int maxReconsumeTimes) {
		this.maxReconsumeTimes = maxReconsumeTimes;
	}

	public long getSuspendCurrentQueueTimeMillis() {
		return suspendCurrentQueueTimeMillis;
	}

	public void setSuspendCurrentQueueTimeMillis(long suspendCurrentQueueTimeMillis) {
		this.suspendCurrentQueueTimeMillis = suspendCurrentQueueTimeMillis;
	}

	public long getConsumeTimeout() {
		return consumeTimeout;
	}

	public void setConsumeTimeout(long consumeTimeout) {
		this.consumeTimeout = consumeTimeout;
	}
	
	public int getRetryTimesWhenConsumeFailed() {
		return retryTimesWhenConsumeFailed;
	}

	public void setRetryTimesWhenConsumeFailed(int retryTimesWhenConsumeFailed) {
		this.retryTimesWhenConsumeFailed = retryTimesWhenConsumeFailed;
	}
	
	public int getDelayLevelWhenNextConsume() {
		return delayLevelWhenNextConsume;
	}

	public void setDelayLevelWhenNextConsume(int delayLevelWhenNextConsume) {
		this.delayLevelWhenNextConsume = delayLevelWhenNextConsume;
	}

	public int getDelayStartSeconds() {
		return delayStartSeconds;
	}

	public void setDelayStartSeconds(int delayStartSeconds) {
		this.delayStartSeconds = delayStartSeconds;
	}

	public ZbusConsumerEventProperties getEvent() {
		return event;
	}

	public void setEvent(ZbusConsumerEventProperties event) {
		this.event = event;
	}
	
	
}
