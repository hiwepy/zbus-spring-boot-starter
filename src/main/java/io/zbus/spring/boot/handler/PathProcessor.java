package io.zbus.spring.boot.handler;

import io.zbus.spring.boot.event.ZbusEvent;

/**
 * 给Handler设置路径
 */
public interface PathProcessor<T extends ZbusEvent> {
	
	EventHandler<T> processPath(String path);

}
