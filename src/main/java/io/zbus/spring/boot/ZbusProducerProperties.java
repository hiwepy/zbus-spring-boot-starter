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

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties for the Zbus producer.
 * <p>
 * Bound to the {@code spring.zbus.producer.*} namespace. Controls producer
 * group, send timeout, retry behaviour, message-size limits and optional
 * transactional producer settings.
 * </p>
 *
 * <h3>Configuration keys</h3>
 * <ul>
 *   <li>{@code spring.zbus.producer.enabled} — opt-in switch (default {@code false})</li>
 *   <li>{@code spring.zbus.producer.producer-group} — producer group (required)</li>
 *   <li>{@code spring.zbus.producer.transaction} — whether to use a transactional producer (default {@code false})</li>
 * </ul>
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
@ConfigurationProperties(ZbusProducerProperties.PREFIX)
public class ZbusProducerProperties{

	/** Configuration prefix used by Spring Boot to bind properties. */
	public static final String PREFIX = "spring.zbus.producer";

	/** Whether the producer auto-configuration is enabled. */
	protected boolean enabled = false;
	
	/**
	 * Producer group conceptually aggregates all producer instances of exactly
	 * same role, which is particularly important when transactional messages
	 * are involved.
	 * </p>
	 *
	 * For non-transactional messages, it does not matter as long as it's unique
	 * per process.
	 * </p>
	 *
	 * See {@linktourl http://rocketmq.incubator.apache.org/docs/core-concept/}
	 * for more discussion.
	 */
	private String producerGroup;
	
	protected String token;   
	protected long invokeTimeout = 10000;  // 10 s  
	
	protected boolean verbose = false; 


	/**
	 * Number of queues to create per default topic.
	 */
	private volatile int defaultTopicQueueNums = 4;

	/**
	 * Timeout for sending messages.
	 */
	private int sendMsgTimeout = 3000;
	
	private boolean sendLatencyFaultEnable = false;
	
	/**
	 * Compress message body threshold, namely, message body larger than 4k will
	 * be compressed on default.
	 */
	private int compressMsgBodyOverHowmuch = 1024 * 4;

	/**
	 * Maximum number of retry to perform internally before claiming sending
	 * failure in synchronous mode.
	 * </p>
	 *
	 * This may potentially cause message duplication which is up to application
	 * developers to resolve.
	 */
	private int retryTimesWhenSendFailed = 2;

	/**
	 * Maximum number of retry to perform internally before claiming sending
	 * failure in asynchronous mode.
	 * </p>
	 *
	 * This may potentially cause message duplication which is up to application
	 * developers to resolve.
	 */
	private int retryTimesWhenSendAsyncFailed = 2;

	/**
	 * Indicate whether to retry another broker on sending failure internally.
	 */
	private boolean retryAnotherBrokerWhenNotStoreOK = false;

	/**
	 * Maximum allowed message size in bytes.
	 */
	private int maxMessageSize = 1024 * 1024 * 4; // 4M
	
	private long[] latencyMax;
	
	private long[] notAvailableDuration;
	
	/** Whether to enable transactional message production. */
	private boolean transaction = false;
	/** Minimum thread-pool size for transaction status back-checks. */
	private int checkThreadPoolMinSize = 1;
	/** Maximum thread-pool size for transaction status back-checks. */
	private int checkThreadPoolMaxSize = 1;
	/** Queue size for pending transaction back-check requests. */
	private int checkRequestHoldMax = 2000;
	
	
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
	 * Returns the producer group.
	 *
	 * @return the producer group
	 */
	public String getProducerGroup() {
		return StringUtils.isEmpty(producerGroup) ? "ProducerGroup" : producerGroup;
	}

	/**
	 * Sets the producer group.
	 *
	 * @param producerGroup the producer group
	 */
	public void setProducerGroup(String producerGroup) {
		this.producerGroup = producerGroup;
	}

	/**
	 * Returns the default topic queue nums.
	 *
	 * @return the default topic queue nums
	 */
	public int getDefaultTopicQueueNums() {
		return defaultTopicQueueNums;
	}

	/**
	 * Sets the default topic queue nums.
	 *
	 * @param defaultTopicQueueNums the default topic queue nums
	 */
	public void setDefaultTopicQueueNums(int defaultTopicQueueNums) {
		this.defaultTopicQueueNums = defaultTopicQueueNums;
	}

	/**
	 * Returns the send msg timeout.
	 *
	 * @return the send msg timeout
	 */
	public int getSendMsgTimeout() {
		return sendMsgTimeout;
	}

	/**
	 * Sets the send msg timeout.
	 *
	 * @param sendMsgTimeout the send msg timeout
	 */
	public void setSendMsgTimeout(int sendMsgTimeout) {
		this.sendMsgTimeout = sendMsgTimeout;
	}

	/**
	 * Returns the send latency fault enable.
	 *
	 * @return the send latency fault enable
	 */
	public boolean isSendLatencyFaultEnable() {
		return sendLatencyFaultEnable;
	}

	/**
	 * Sets the send latency fault enable.
	 *
	 * @param sendLatencyFaultEnable the send latency fault enable
	 */
	public void setSendLatencyFaultEnable(boolean sendLatencyFaultEnable) {
		this.sendLatencyFaultEnable = sendLatencyFaultEnable;
	}

	/**
	 * Returns the compress msg body over howmuch.
	 *
	 * @return the compress msg body over howmuch
	 */
	public int getCompressMsgBodyOverHowmuch() {
		return compressMsgBodyOverHowmuch;
	}

	/**
	 * Sets the compress msg body over howmuch.
	 *
	 * @param compressMsgBodyOverHowmuch the compress msg body over howmuch
	 */
	public void setCompressMsgBodyOverHowmuch(int compressMsgBodyOverHowmuch) {
		this.compressMsgBodyOverHowmuch = compressMsgBodyOverHowmuch;
	}

	/**
	 * Returns the retry times when send failed.
	 *
	 * @return the retry times when send failed
	 */
	public int getRetryTimesWhenSendFailed() {
		return retryTimesWhenSendFailed;
	}

	/**
	 * Sets the retry times when send failed.
	 *
	 * @param retryTimesWhenSendFailed the retry times when send failed
	 */
	public void setRetryTimesWhenSendFailed(int retryTimesWhenSendFailed) {
		this.retryTimesWhenSendFailed = retryTimesWhenSendFailed;
	}

	/**
	 * Returns the retry times when send async failed.
	 *
	 * @return the retry times when send async failed
	 */
	public int getRetryTimesWhenSendAsyncFailed() {
		return retryTimesWhenSendAsyncFailed;
	}

	/**
	 * Sets the retry times when send async failed.
	 *
	 * @param retryTimesWhenSendAsyncFailed the retry times when send async failed
	 */
	public void setRetryTimesWhenSendAsyncFailed(int retryTimesWhenSendAsyncFailed) {
		this.retryTimesWhenSendAsyncFailed = retryTimesWhenSendAsyncFailed;
	}

	/**
	 * Returns the retry another broker when not store o k.
	 *
	 * @return the retry another broker when not store o k
	 */
	public boolean isRetryAnotherBrokerWhenNotStoreOK() {
		return retryAnotherBrokerWhenNotStoreOK;
	}

	/**
	 * Sets the retry another broker when not store o k.
	 *
	 * @param retryAnotherBrokerWhenNotStoreOK the retry another broker when not store o k
	 */
	public void setRetryAnotherBrokerWhenNotStoreOK(boolean retryAnotherBrokerWhenNotStoreOK) {
		this.retryAnotherBrokerWhenNotStoreOK = retryAnotherBrokerWhenNotStoreOK;
	}

	/**
	 * Returns the max message size.
	 *
	 * @return the max message size
	 */
	public int getMaxMessageSize() {
		return maxMessageSize;
	}

	/**
	 * Sets the max message size.
	 *
	 * @param maxMessageSize the max message size
	 */
	public void setMaxMessageSize(int maxMessageSize) {
		this.maxMessageSize = maxMessageSize;
	}

	/**
	 * Returns the latency max.
	 *
	 * @return the latency max
	 */
	public long[] getLatencyMax() {
		return latencyMax;
	}

	/**
	 * Sets the latency max.
	 *
	 * @param latencyMax the latency max
	 */
	public void setLatencyMax(long[] latencyMax) {
		this.latencyMax = latencyMax;
	}

	/**
	 * Returns the not available duration.
	 *
	 * @return the not available duration
	 */
	public long[] getNotAvailableDuration() {
		return notAvailableDuration;
	}

	/**
	 * Sets the not available duration.
	 *
	 * @param notAvailableDuration the not available duration
	 */
	public void setNotAvailableDuration(long[] notAvailableDuration) {
		this.notAvailableDuration = notAvailableDuration;
	}
	
	/**
	 * Returns the transaction.
	 *
	 * @return the transaction
	 */
	public boolean isTransaction() {
		return transaction;
	}

	/**
	 * Sets the transaction.
	 *
	 * @param transaction the transaction
	 */
	public void setTransaction(boolean transaction) {
		this.transaction = transaction;
	}

	/**
	 * Returns the check thread pool min size.
	 *
	 * @return the check thread pool min size
	 */
	public int getCheckThreadPoolMinSize() {
		return checkThreadPoolMinSize;
	}

	/**
	 * Sets the check thread pool min size.
	 *
	 * @param checkThreadPoolMinSize the check thread pool min size
	 */
	public void setCheckThreadPoolMinSize(int checkThreadPoolMinSize) {
		this.checkThreadPoolMinSize = checkThreadPoolMinSize;
	}

	/**
	 * Returns the check thread pool max size.
	 *
	 * @return the check thread pool max size
	 */
	public int getCheckThreadPoolMaxSize() {
		return checkThreadPoolMaxSize;
	}

	/**
	 * Sets the check thread pool max size.
	 *
	 * @param checkThreadPoolMaxSize the check thread pool max size
	 */
	public void setCheckThreadPoolMaxSize(int checkThreadPoolMaxSize) {
		this.checkThreadPoolMaxSize = checkThreadPoolMaxSize;
	}

	/**
	 * Returns the check request hold max.
	 *
	 * @return the check request hold max
	 */
	public int getCheckRequestHoldMax() {
		return checkRequestHoldMax;
	}

	/**
	 * Sets the check request hold max.
	 *
	 * @param checkRequestHoldMax the check request hold max
	 */
	public void setCheckRequestHoldMax(int checkRequestHoldMax) {
		this.checkRequestHoldMax = checkRequestHoldMax;
	}
	
}

