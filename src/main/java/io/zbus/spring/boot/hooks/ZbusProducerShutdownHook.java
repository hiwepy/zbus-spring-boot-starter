package io.zbus.spring.boot.hooks;

import io.zbus.mq.Producer;

/**
 * JVM shutdown hook placeholder for a Zbus {@link Producer}.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
public class ZbusProducerShutdownHook extends Thread{

	/** The producer to shut down. */
	private Producer producer;

	/**
	 * @param producer the producer to shut down
	 */
	public ZbusProducerShutdownHook(Producer producer) {
		this.producer = producer;
	}

	@Override
	public void run() {
	}

}
