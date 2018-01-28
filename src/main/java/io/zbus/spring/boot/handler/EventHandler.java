package io.zbus.spring.boot.handler;

import io.zbus.spring.boot.event.ZbusEvent;
import io.zbus.spring.boot.handler.chain.HandlerChain;

/**
 */
public interface EventHandler<T extends ZbusEvent> {

	public void doHandler(T event, HandlerChain<T> handlerChain) throws Exception;
	
}
