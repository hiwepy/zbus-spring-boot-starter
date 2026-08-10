package io.zbus.spring.boot.handler;

import io.zbus.spring.boot.event.ZbusEvent;

/**
 * Base {@link EventHandler} that implements {@link Nameable}, providing a
 * configurable name used by the handler-chain manager.
 *
 * @param <T> the event type, bound to {@link ZbusEvent}
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public abstract class AbstractNameableMessageHandler<T extends ZbusEvent> implements EventHandler<T>, Nameable {

	/** The unique handler name used for registration and logging. */
	protected String name;

	/** @return the unique handler name */
	protected String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

}
