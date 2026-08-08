package io.zbus.spring.boot.handler;

import io.zbus.spring.boot.event.ZbusEvent;

/**
 * Strategy for resolving the {@link EventHandler} bound to a given dispatch
 * path (e.g. {@code topic/tag/keys}).
 *
 * @param <T> the event type, bound to {@link ZbusEvent}
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
public interface PathProcessor<T extends ZbusEvent> {

	/**
	 * Resolves the handler associated with the given path.
	 *
	 * @param path the dispatch path (e.g. {@code topic/tag/keys})
	 * @return the matching handler, or {@code null} if none is bound
	 */
	EventHandler<T> processPath(String path);

}
