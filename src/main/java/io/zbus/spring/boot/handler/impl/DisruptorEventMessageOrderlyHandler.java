package io.zbus.spring.boot.handler.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lmax.disruptor.dsl.Disruptor;

import io.zbus.mq.Message;
import io.zbus.spring.boot.disruptor.ZbusDisruptorEventTranslator;
import io.zbus.spring.boot.event.ZbusDisruptorEvent;
import io.zbus.spring.boot.handler.ZbusMessageHandler;

/**
 * Message handler that publishes received messages onto a LMAX Disruptor ring
 * buffer for asynchronous processing.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class DisruptorEventMessageOrderlyHandler implements ZbusMessageHandler {

	private static final Logger LOG = LoggerFactory.getLogger(DisruptorEventMessageOrderlyHandler.class);

	/** The Disruptor ring buffer used to publish events. */
	private Disruptor<ZbusDisruptorEvent> disruptor;

	/** Translator used to copy message data into Disruptor events. */
	private ZbusDisruptorEventTranslator translator = new ZbusDisruptorEventTranslator();

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
		// Publish the message onto the Disruptor ring buffer.
		disruptor.publishEvent(translator, msgExt);
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

}