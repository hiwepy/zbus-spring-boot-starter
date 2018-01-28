package io.zbus.spring.boot.hooks;

import java.io.IOException;

import io.zbus.mq.Consumer;

public class ZbusConsumerShutdownHook extends Thread{
	
	private Consumer consumer;
	
	public ZbusConsumerShutdownHook(Consumer consumer) {
		this.consumer = consumer;
	}
	
	@Override
	public void run() {
		try {
			consumer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
