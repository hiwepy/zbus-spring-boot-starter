package io.zbus.spring.boot.handler.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.zbus.mq.Message;
import io.zbus.spring.boot.event.ZbusEvent;
import io.zbus.spring.boot.handler.AbstractRouteableMessageHandler;
import io.zbus.spring.boot.handler.ZbusMessageHandler;
import io.zbus.spring.boot.handler.chain.HandlerChain;
import io.zbus.spring.boot.handler.chain.HandlerChainResolver;
import io.zbus.spring.boot.handler.chain.ProxiedHandlerChain;

public class ZbusEventMessageHandler extends AbstractRouteableMessageHandler<ZbusEvent> implements ZbusMessageHandler {

	private static final Logger LOG = LoggerFactory.getLogger(ZbusEventMessageHandler.class);
	
	public ZbusEventMessageHandler(HandlerChainResolver<ZbusEvent> filterChainResolver) {
		super(filterChainResolver);
	}
	
	@Override
	public boolean preHandle(Message msgExt) throws Exception {
		return true;
	}
	
	@Override
	public void handleMessage(Message msgExt) throws Exception {
		//构造原始链对象
		HandlerChain<ZbusEvent>	originalChain = new ProxiedHandlerChain();
		//执行事件处理链
		this.doHandler(new ZbusEvent(msgExt), originalChain);
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