package io.zbus.spring.boot.handler.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

import io.zbus.mq.Message;
import io.zbus.spring.boot.event.ZbusEvent;
import io.zbus.spring.boot.handler.ZbusMessageHandler;

public class ApplicationEventMessageOrderlyHandler implements ZbusMessageHandler, ApplicationEventPublisherAware {

	private static final Logger LOG = LoggerFactory.getLogger(ApplicationEventMessageOrderlyHandler.class);
	private ApplicationEventPublisher eventPublisher;
	
	@Override
	public boolean preHandle(Message msgExt) throws Exception {
		return true;
	}
	
	@Override
	public void handleMessage(Message msgExt) throws Exception {
		// 发布消息到达的事件，以便分发到每个tag的监听方法
		getEventPublisher().publishEvent(new ZbusEvent(msgExt));
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

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.eventPublisher = applicationEventPublisher;
	}

	public ApplicationEventPublisher getEventPublisher() {
		return eventPublisher;
	}

}