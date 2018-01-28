package io.zbus.spring.boot.handler.chain;

import io.zbus.spring.boot.event.ZbusEvent;

public interface HandlerChain<T extends ZbusEvent>{

	void doHandler(T event) throws Exception;
	
}
