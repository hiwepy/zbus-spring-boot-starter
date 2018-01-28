package io.zbus.spring.boot.handler.chain;

import io.zbus.spring.boot.event.ZbusEvent;

public interface HandlerChainResolver<T extends ZbusEvent> {

	HandlerChain<T> getChain(T event , HandlerChain<T> originalChain);
	
}
