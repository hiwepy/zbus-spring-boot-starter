package io.zbus.spring.boot.handler;

import io.zbus.spring.boot.event.ZbusEvent;
import io.zbus.spring.boot.handler.chain.HandlerChain;

/**
 * Contract for a single handler in the Zbus message handler chain.
 * <p>
 * An {@code EventHandler} receives an event and the current {@link HandlerChain};
 * it may perform work and then delegate to the chain to continue processing.
 * </p>
 *
 * @param <T> the event type, bound to {@link ZbusEvent}
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
public interface EventHandler<T extends ZbusEvent> {

	/**
	 * Handles the event, optionally delegating to the supplied handler chain.
	 *
	 * @param event        the event to handle
	 * @param handlerChain the current handler chain
	 * @throws Exception if handling fails
	 */
	public void doHandler(T event, HandlerChain<T> handlerChain) throws Exception;

}
