package io.zbus.spring.boot.disruptor;

import io.zbus.spring.boot.event.ZbusDisruptorEvent;

import com.lmax.disruptor.EventFactory;

public class ZbusDisruptorEventFactory implements EventFactory<ZbusDisruptorEvent> {

	@Override
	public ZbusDisruptorEvent newInstance() {
		return new ZbusDisruptorEvent(this);
	}
	
}
