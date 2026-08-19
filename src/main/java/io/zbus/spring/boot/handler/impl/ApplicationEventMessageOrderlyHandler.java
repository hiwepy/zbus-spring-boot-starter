package io.zbus.spring.boot.handler.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

import io.zbus.mq.Message;
import io.zbus.spring.boot.event.ZbusEvent;
import io.zbus.spring.boot.handler.ZbusMessageHandler;

/**
 * Message handler that publishes a {@link ZbusEvent} through the Spring
 * {@link ApplicationEventPublisher}, allowing tag-specific listeners to receive it.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class ApplicationEventMessageOrderlyHandler implements ZbusMessageHandler, ApplicationEventPublisherAware {

	private static final Logger LOG = LoggerFactory.getLogger(ApplicationEventMessageOrderlyHandler.class);
	private ApplicationEventPublisher eventPublisher;

	@Override
	/**
	 * pre Handle.
	 *
	 * @param msgExt the msg ext
	 * @return the result
	 * @throws Exception if an error occurs
	 */
	public boolean preHandle(Message msgExt) throws Exception {
		return true;
	}

	@Override
	/**
	 * handle Message.
	 *
	 * @param msgExt the msg ext
	 * @throws Exception if an error occurs
	 */
	public void handleMessage(Message msgExt) throws Exception {
		// Publish a message-arrived event so tag-specific listeners can handle it.
		getEventPublisher().publishEvent(new ZbusEvent(msgExt));
	}
	
	@Override
	/**
	 * post Handle.
	 *
	 * @param msgExt the msg ext
	 * @throws Exception if an error occurs
	 */
	public void postHandle(Message msgExt) throws Exception {
		
	}

	@Override
	/**
	 * after Completion.
	 *
	 * @param msgExt the msg ext
	 * @param ex the ex
	 * @throws Exception if an error occurs
	 */
	public void afterCompletion(Message msgExt, Exception ex) throws Exception {
		if(ex != null) {
			LOG.warn("Consume message failed. messageExt:{}", msgExt, ex);
		}
	}

	@Override
	/**
	 * Sets the application event publisher.
	 *
	 * @param applicationEventPublisher the application event publisher
	 */
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.eventPublisher = applicationEventPublisher;
	}

	/**
	 * Returns the event publisher.
	 *
	 * @return the event publisher
	 */
	public ApplicationEventPublisher getEventPublisher() {
		return eventPublisher;
	}

}