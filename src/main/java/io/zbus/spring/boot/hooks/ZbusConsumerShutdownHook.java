package io.zbus.spring.boot.hooks;

import java.io.IOException;

import io.zbus.mq.Consumer;

/**
 * JVM shutdown hook that closes a Zbus {@link Consumer}, releasing resources
 * and unregistering from the broker.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
public class ZbusConsumerShutdownHook extends Thread{

	/** The consumer to close. */
	private Consumer consumer;

	/**
	 * @param consumer the consumer to close
	 */
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
