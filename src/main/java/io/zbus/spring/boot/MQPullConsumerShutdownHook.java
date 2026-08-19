package io.zbus.spring.boot;

/**
 * Stub for MQPullConsumerShutdownHook.
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class MQPullConsumerShutdownHook extends Thread {

    private DefaultMQPullConsumer consumer;

    public MQPullConsumerShutdownHook(DefaultMQPullConsumer consumer) {
        this.consumer = consumer;
    }

    @Override
    /**
     * run.
     *
     */
    public void run() {
        consumer.shutdown();
    }
}
