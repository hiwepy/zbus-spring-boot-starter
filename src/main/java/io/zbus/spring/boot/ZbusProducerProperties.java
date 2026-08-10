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
	
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getProducerGroup() {
		return StringUtils.isEmpty(producerGroup) ? "ProducerGroup" : producerGroup;
	}

	public void setProducerGroup(String producerGroup) {
		this.producerGroup = producerGroup;
	}

	public int getDefaultTopicQueueNums() {
		return defaultTopicQueueNums;
	}

	public void setDefaultTopicQueueNums(int defaultTopicQueueNums) {
		this.defaultTopicQueueNums = defaultTopicQueueNums;
	}

	public int getSendMsgTimeout() {
		return sendMsgTimeout;
	}

	public void setSendMsgTimeout(int sendMsgTimeout) {
		this.sendMsgTimeout = sendMsgTimeout;
	}

	public boolean isSendLatencyFaultEnable() {
		return sendLatencyFaultEnable;
	}

	public void setSendLatencyFaultEnable(boolean sendLatencyFaultEnable) {
		this.sendLatencyFaultEnable = sendLatencyFaultEnable;
	}

	public int getCompressMsgBodyOverHowmuch() {
		return compressMsgBodyOverHowmuch;
	}

	public void setCompressMsgBodyOverHowmuch(int compressMsgBodyOverHowmuch) {
		this.compressMsgBodyOverHowmuch = compressMsgBodyOverHowmuch;
	}

	public int getRetryTimesWhenSendFailed() {
		return retryTimesWhenSendFailed;
	}

	public void setRetryTimesWhenSendFailed(int retryTimesWhenSendFailed) {
		this.retryTimesWhenSendFailed = retryTimesWhenSendFailed;
	}

	public int getRetryTimesWhenSendAsyncFailed() {
		return retryTimesWhenSendAsyncFailed;
	}

	public void setRetryTimesWhenSendAsyncFailed(int retryTimesWhenSendAsyncFailed) {
		this.retryTimesWhenSendAsyncFailed = retryTimesWhenSendAsyncFailed;
	}

	public boolean isRetryAnotherBrokerWhenNotStoreOK() {
		return retryAnotherBrokerWhenNotStoreOK;
	}

	public void setRetryAnotherBrokerWhenNotStoreOK(boolean retryAnotherBrokerWhenNotStoreOK) {
		this.retryAnotherBrokerWhenNotStoreOK = retryAnotherBrokerWhenNotStoreOK;
	}

	public int getMaxMessageSize() {
		return maxMessageSize;
	}

	public void setMaxMessageSize(int maxMessageSize) {
		this.maxMessageSize = maxMessageSize;
	}

	public long[] getLatencyMax() {
		return latencyMax;
	}

	public void setLatencyMax(long[] latencyMax) {
		this.latencyMax = latencyMax;
	}

	public long[] getNotAvailableDuration() {
		return notAvailableDuration;
	}

	public void setNotAvailableDuration(long[] notAvailableDuration) {
		this.notAvailableDuration = notAvailableDuration;
	}
	
	public boolean isTransaction() {
		return transaction;
	}

	public void setTransaction(boolean transaction) {
		this.transaction = transaction;
	}

	public int getCheckThreadPoolMinSize() {
		return checkThreadPoolMinSize;
	}

	public void setCheckThreadPoolMinSize(int checkThreadPoolMinSize) {
		this.checkThreadPoolMinSize = checkThreadPoolMinSize;
	}

	public int getCheckThreadPoolMaxSize() {
		return checkThreadPoolMaxSize;
	}

	public void setCheckThreadPoolMaxSize(int checkThreadPoolMaxSize) {
		this.checkThreadPoolMaxSize = checkThreadPoolMaxSize;
	}

	public int getCheckRequestHoldMax() {
		return checkRequestHoldMax;
	}

	public void setCheckRequestHoldMax(int checkRequestHoldMax) {
		this.checkRequestHoldMax = checkRequestHoldMax;
	}
	
}

