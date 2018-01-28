package io.zbus.spring.boot.hooks;

import io.zbus.mq.Producer;

public class ZbusProducerShutdownHook extends Thread{
	
	private Producer producer;
	
	public ZbusProducerShutdownHook(Producer producer) {
		this.producer = producer;
	}
	
	@Override
	public void run() {
	}
	
}
