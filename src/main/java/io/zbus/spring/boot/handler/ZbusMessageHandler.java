package io.zbus.spring.boot.handler;

import io.zbus.mq.Message;

/**
 * Lifecycle contract for Zbus message handling, modelled after the classic
 * pre-handle / handle / post-handle / after-completion pattern.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public interface ZbusMessageHandler {

	/**
	 * Pre-handle hook invoked before the message is processed.
	 *
	 * @param msgExt the received message
	 * @return {@code true} to continue processing, {@code false} to abort
	 * @throws Exception if the pre-handle fails
	 */
	boolean preHandle(Message msgExt) throws Exception;

	/**
	 * Handles the message.
	 *
	 * @param msgExt the received message
	 * @throws Exception if handling fails
	 */
	void handleMessage(Message msgExt) throws Exception;

	/**
	 * Post-handle hook invoked after successful handling.
	 *
	 * @param msgExt the received message
	 * @throws Exception if the post-handle fails
	 */
	void postHandle(Message msgExt) throws Exception;

	/**
	 * Completion hook invoked after processing regardless of success.
	 *
	 * @param msgExt the received message
	 * @param ex     any exception thrown during handling, or {@code null}
	 * @throws Exception if the completion fails
	 */
	void afterCompletion(Message msgExt, Exception ex) throws Exception;

}