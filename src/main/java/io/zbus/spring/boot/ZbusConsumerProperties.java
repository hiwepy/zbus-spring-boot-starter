/*
 * Copyright (c) 2018, hiwepy (https://github.com/hiwepy).
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

/**
 * Configuration properties for the Zbus consumer.
 * <p>
 * Bound to the {@code spring.zbus.consume.*} namespace. Controls consumer
 * group, message model, subscription map, thread-pool sizing, retry behaviour
 * and delayed start.
 * </p>
 *
 * <h3>Configuration keys</h3>
 * <ul>
 *   <li>{@code spring.zbus.consume.enabled} — opt-in switch (default {@code false})</li>
 *   <li>{@code spring.zbus.consume.consumer-group} — globally unique consumer group (required)</li>
 *   <li>{@code spring.zbus.consume.subscription} — topic {@code ->} selector expression map</li>
 *   <li>{@code spring.zbus.consume.delay-start-seconds} — delayed start in seconds (default {@code 10})</li>
 * </ul>
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
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
	 * Delay before the consumer starts, in seconds. Lets Spring event listeners
	 * finish initialising before messages are consumed, avoiding message loss
	 * when a message-arrived event is published before its listener is ready.
	 */
	private int delayStartSeconds = 10;
	
	@NestedConfigurationProperty
	private ZbusConsumerEventProperties event;
    
	/**
	 * Returns the enabled.
	 *
	 * @return the enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * Sets the enabled.
	 *
	 * @param enabled the enabled
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
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
	 * Returns the message model.
	 *
	 * @return the message model
	 */
	public String getMessageModel() {
		return messageModel;
	}

	/**
	 * Sets the message model.
	 *
	 * @param messageModel the message model
	 */
	public void setMessageModel(String messageModel) {
		this.messageModel = messageModel;
	}

	public Map<String, String> getSubscription() {
		return subscription;
	}

	/**
	 * Sets the subscription.
	 *
	 * @param subscription the subscription
	 */
	public void setSubscription(Map<String, String> subscription) {
		this.subscription = subscription;
	}

	/**
	 * Returns the consume thread min.
	 *
	 * @return the consume thread min
	 */
	public int getConsumeThreadMin() {
		return consumeThreadMin;
	}

	/**
	 * Sets the consume thread min.
	 *
	 * @param consumeThreadMin the consume thread min
	 */
	public void setConsumeThreadMin(int consumeThreadMin) {
		this.consumeThreadMin = consumeThreadMin;
	}

	/**
	 * Returns the consume thread max.
	 *
	 * @return the consume thread max
	 */
	public int getConsumeThreadMax() {
		return consumeThreadMax;
	}

	/**
	 * Sets the consume thread max.
	 *
	 * @param consumeThreadMax the consume thread max
	 */
	public void setConsumeThreadMax(int consumeThreadMax) {
		this.consumeThreadMax = consumeThreadMax;
	}

	/**
	 * Returns the adjust thread pool nums threshold.
	 *
	 * @return the adjust thread pool nums threshold
	 */
	public long getAdjustThreadPoolNumsThreshold() {
		return adjustThreadPoolNumsThreshold;
	}

	/**
	 * Sets the adjust thread pool nums threshold.
	 *
	 * @param adjustThreadPoolNumsThreshold the adjust thread pool nums threshold
	 */
	public void setAdjustThreadPoolNumsThreshold(long adjustThreadPoolNumsThreshold) {
		this.adjustThreadPoolNumsThreshold = adjustThreadPoolNumsThreshold;
	}

	/**
	 * Returns the consume concurrently max span.
	 *
	 * @return the consume concurrently max span
	 */
	public int getConsumeConcurrentlyMaxSpan() {
		return consumeConcurrentlyMaxSpan;
	}

	/**
	 * Sets the consume concurrently max span.
	 *
	 * @param consumeConcurrentlyMaxSpan the consume concurrently max span
	 */
	public void setConsumeConcurrentlyMaxSpan(int consumeConcurrentlyMaxSpan) {
		this.consumeConcurrentlyMaxSpan = consumeConcurrentlyMaxSpan;
	}

	/**
	 * Returns the pull threshold for queue.
	 *
	 * @return the pull threshold for queue
	 */
	public int getPullThresholdForQueue() {
		return pullThresholdForQueue;
	}

	/**
	 * Sets the pull threshold for queue.
	 *
	 * @param pullThresholdForQueue the pull threshold for queue
	 */
	public void setPullThresholdForQueue(int pullThresholdForQueue) {
		this.pullThresholdForQueue = pullThresholdForQueue;
	}

	/**
	 * Returns the pull interval.
	 *
	 * @return the pull interval
	 */
	public long getPullInterval() {
		return pullInterval;
	}

	/**
	 * Sets the pull interval.
	 *
	 * @param pullInterval the pull interval
	 */
	public void setPullInterval(long pullInterval) {
		this.pullInterval = pullInterval;
	}

	/**
	 * Returns the consume message batch max size.
	 *
	 * @return the consume message batch max size
	 */
	public int getConsumeMessageBatchMaxSize() {
		return consumeMessageBatchMaxSize;
	}

	/**
	 * Sets the consume message batch max size.
	 *
	 * @param consumeMessageBatchMaxSize the consume message batch max size
	 */
	public void setConsumeMessageBatchMaxSize(int consumeMessageBatchMaxSize) {
		this.consumeMessageBatchMaxSize = consumeMessageBatchMaxSize;
	}

	/**
	 * Returns the pull batch size.
	 *
	 * @return the pull batch size
	 */
	public int getPullBatchSize() {
		return pullBatchSize;
	}

	/**
	 * Sets the pull batch size.
	 *
	 * @param pullBatchSize the pull batch size
	 */
	public void setPullBatchSize(int pullBatchSize) {
		this.pullBatchSize = pullBatchSize;
	}

	/**
	 * Returns the post subscription when pull.
	 *
	 * @return the post subscription when pull
	 */
	public boolean isPostSubscriptionWhenPull() {
		return postSubscriptionWhenPull;
	}

	/**
	 * Sets the post subscription when pull.
	 *
	 * @param postSubscriptionWhenPull the post subscription when pull
	 */
	public void setPostSubscriptionWhenPull(boolean postSubscriptionWhenPull) {
		this.postSubscriptionWhenPull = postSubscriptionWhenPull;
	}

	/**
	 * Returns the max reconsume times.
	 *
	 * @return the max reconsume times
	 */
	public int getMaxReconsumeTimes() {
		return maxReconsumeTimes;
	}

	/**
	 * Sets the max reconsume times.
	 *
	 * @param maxReconsumeTimes the max reconsume times
	 */
	public void setMaxReconsumeTimes(int maxReconsumeTimes) {
		this.maxReconsumeTimes = maxReconsumeTimes;
	}

	/**
	 * Returns the suspend current queue time millis.
	 *
	 * @return the suspend current queue time millis
	 */
	public long getSuspendCurrentQueueTimeMillis() {
		return suspendCurrentQueueTimeMillis;
	}

	/**
	 * Sets the suspend current queue time millis.
	 *
	 * @param suspendCurrentQueueTimeMillis the suspend current queue time millis
	 */
	public void setSuspendCurrentQueueTimeMillis(long suspendCurrentQueueTimeMillis) {
		this.suspendCurrentQueueTimeMillis = suspendCurrentQueueTimeMillis;
	}

	/**
	 * Returns the consume timeout.
	 *
	 * @return the consume timeout
	 */
	public long getConsumeTimeout() {
		return consumeTimeout;
	}

	/**
	 * Sets the consume timeout.
	 *
	 * @param consumeTimeout the consume timeout
	 */
	public void setConsumeTimeout(long consumeTimeout) {
		this.consumeTimeout = consumeTimeout;
	}
	
	/**
	 * Returns the retry times when consume failed.
	 *
	 * @return the retry times when consume failed
	 */
	public int getRetryTimesWhenConsumeFailed() {
		return retryTimesWhenConsumeFailed;
	}

	/**
	 * Sets the retry times when consume failed.
	 *
	 * @param retryTimesWhenConsumeFailed the retry times when consume failed
	 */
	public void setRetryTimesWhenConsumeFailed(int retryTimesWhenConsumeFailed) {
		this.retryTimesWhenConsumeFailed = retryTimesWhenConsumeFailed;
	}
	
	/**
	 * Returns the delay level when next consume.
	 *
	 * @return the delay level when next consume
	 */
	public int getDelayLevelWhenNextConsume() {
		return delayLevelWhenNextConsume;
	}

	/**
	 * Sets the delay level when next consume.
	 *
	 * @param delayLevelWhenNextConsume the delay level when next consume
	 */
	public void setDelayLevelWhenNextConsume(int delayLevelWhenNextConsume) {
		this.delayLevelWhenNextConsume = delayLevelWhenNextConsume;
	}

	/**
	 * Returns the delay start seconds.
	 *
	 * @return the delay start seconds
	 */
	public int getDelayStartSeconds() {
		return delayStartSeconds;
	}

	/**
	 * Sets the delay start seconds.
	 *
	 * @param delayStartSeconds the delay start seconds
	 */
	public void setDelayStartSeconds(int delayStartSeconds) {
		this.delayStartSeconds = delayStartSeconds;
	}

	/**
	 * Returns the event.
	 *
	 * @return the event
	 */
	public ZbusConsumerEventProperties getEvent() {
		return event;
	}

	/**
	 * Sets the event.
	 *
	 * @param event the event
	 */
	public void setEvent(ZbusConsumerEventProperties event) {
		this.event = event;
	}
	
	
}
