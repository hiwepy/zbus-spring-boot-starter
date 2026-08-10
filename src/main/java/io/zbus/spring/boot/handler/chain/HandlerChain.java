package io.zbus.spring.boot.handler.chain;

import io.zbus.spring.boot.event.ZbusEvent;

/**
 * A chain of {@link EventHandler}s invoked in sequence for a single event.
 *
 * @param <T> the event type, bound to {@link ZbusEvent}
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public interface HandlerChain<T extends ZbusEvent>{

	/**
	 * Continues processing the event down the chain.
	 *
	 * @param event the event to handle
	 * @throws Exception if a handler in the chain fails
	 */
	void doHandler(T event) throws Exception;

}
