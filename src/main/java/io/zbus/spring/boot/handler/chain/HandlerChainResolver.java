package io.zbus.spring.boot.handler.chain;

import io.zbus.spring.boot.event.ZbusEvent;

/**
 * Strategy for resolving the {@link HandlerChain} to execute for a given event.
 *
 * @param <T> the event type, bound to {@link ZbusEvent}
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
public interface HandlerChainResolver<T extends ZbusEvent> {

	/**
	 * Resolves the handler chain for the event, wrapping the original chain.
	 *
	 * @param event         the event being processed
	 * @param originalChain the original (root) chain to delegate to
	 * @return the resolved handler chain
	 */
	HandlerChain<T> getChain(T event , HandlerChain<T> originalChain);

}
