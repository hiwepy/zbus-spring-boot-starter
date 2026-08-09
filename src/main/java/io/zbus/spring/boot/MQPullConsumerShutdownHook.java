package io.zbus.spring.boot;

/**
 * Stub for MQPullConsumerShutdownHook.
 */
public class MQPullConsumerShutdownHook extends Thread {

    private DefaultMQPullConsumer consumer;

    public MQPullConsumerShutdownHook(DefaultMQPullConsumer consumer) {
        this.consumer = consumer;
    }

    @Override
    public void run() {
        consumer.shutdown();
    }
}
