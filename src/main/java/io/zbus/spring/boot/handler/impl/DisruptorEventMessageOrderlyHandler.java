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
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
public class DisruptorEventMessageOrderlyHandler implements ZbusMessageHandler {

	private static final Logger LOG = LoggerFactory.getLogger(DisruptorEventMessageOrderlyHandler.class);

	/** The Disruptor ring buffer used to publish events. */
	private Disruptor<ZbusDisruptorEvent> disruptor;

	/** Translator used to copy message data into Disruptor events. */
	private ZbusDisruptorEventTranslator translator = new ZbusDisruptorEventTranslator();

	@Override
	public boolean preHandle(Message msgExt) throws Exception {
		return true;
	}

	@Override
	public void handleMessage(Message msgExt) throws Exception {
		// Publish the message onto the Disruptor ring buffer.
		disruptor.publishEvent(translator, msgExt);
	}
	
	@Override
	public void postHandle(Message msgExt) throws Exception {
		
	}

	@Override
	public void afterCompletion(Message msgExt, Exception ex) throws Exception {
		if(ex != null) {
			LOG.warn("Consume message failed. messageExt:{}", msgExt, ex);
		}
	}

}