package io.zbus.spring.boot.handler.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lmax.disruptor.dsl.Disruptor;

import io.zbus.mq.Message;
import io.zbus.spring.boot.disruptor.ZbusDisruptorEventTranslator;
import io.zbus.spring.boot.event.ZbusDisruptorEvent;
import io.zbus.spring.boot.handler.ZbusMessageHandler;

public class DisruptorEventMessageOrderlyHandler implements ZbusMessageHandler {

	private static final Logger LOG = LoggerFactory.getLogger(DisruptorEventMessageOrderlyHandler.class);
	
	private Disruptor<ZbusDisruptorEvent> disruptor;
	
	private ZbusDisruptorEventTranslator translator = new ZbusDisruptorEventTranslator();
		
	@Override
	public boolean preHandle(Message msgExt) throws Exception {
		return true;
	}
	
	@Override
	public void handleMessage(Message msgExt) throws Exception {
		// 生产消息
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