package io.zbus.spring.boot.disruptor;

import io.zbus.spring.boot.event.ZbusDisruptorEvent;

import com.lmax.disruptor.EventFactory;

/**
 * Disruptor {@link EventFactory} that produces fresh
 * {@link ZbusDisruptorEvent} instances for the ring buffer.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class ZbusDisruptorEventFactory implements EventFactory<ZbusDisruptorEvent> {

	@Override
	public ZbusDisruptorEvent newInstance() {
		return new ZbusDisruptorEvent(this);
	}

}
