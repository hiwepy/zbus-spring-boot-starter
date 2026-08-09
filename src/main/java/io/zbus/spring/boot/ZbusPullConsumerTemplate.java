package io.zbus.spring.boot;

/**
 * Stub for ZbusPullConsumerTemplate.
 */
public class ZbusPullConsumerTemplate {

    private MQPullConsumer consumer;

    public ZbusPullConsumerTemplate() {}
    public ZbusPullConsumerTemplate(MQPullConsumer consumer) {
        this.consumer = consumer;
    }

    public MQPullConsumer getConsumer() { return consumer; }
    public void setConsumer(MQPullConsumer consumer) { this.consumer = consumer; }
}
