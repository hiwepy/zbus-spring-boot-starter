package io.zbus.spring.boot.handler;

import io.zbus.spring.boot.event.ZbusEvent;

public abstract class AbstractNameableMessageHandler<T extends ZbusEvent> implements EventHandler<T>, Nameable {

	/**
	 * 过滤器名称
	 */
	protected String name;

	protected String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

}
