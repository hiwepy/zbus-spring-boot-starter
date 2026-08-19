package io.zbus.spring.boot;

/**
 * Stub for ZbusPullConsumerTemplate.
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class ZbusPullConsumerTemplate {

    private MQPullConsumer consumer;

    public ZbusPullConsumerTemplate() {}
    public ZbusPullConsumerTemplate(MQPullConsumer consumer) {
        this.consumer = consumer;
    }

    /**
     * Returns the consumer.
     *
     * @return the consumer
     */
    public MQPullConsumer getConsumer() { return consumer; }
    /**
     * Sets the consumer.
     *
     * @param consumer the consumer
     */
    public void setConsumer(MQPullConsumer consumer) { this.consumer = consumer; }
}
