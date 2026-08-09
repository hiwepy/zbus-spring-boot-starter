package io.zbus.spring.boot;

/**
 * Stub for MQPullConsumerScheduleService.
 */
public class MQPullConsumerScheduleService {

    private DefaultMQPullConsumer defaultMQPullConsumer;

    public MQPullConsumerScheduleService() {}
    public MQPullConsumerScheduleService(String consumerGroup) {
        this.defaultMQPullConsumer = new DefaultMQPullConsumer(consumerGroup);
    }

    public DefaultMQPullConsumer getDefaultMQPullConsumer() { return defaultMQPullConsumer; }
    public void setMessageModel(Object model) {}
    public void setPullThreadNums(int nums) {}
    public void registerPullTaskCallback(String topic, Object callback) {}
    public void start() {}
    public void shutdown() {}
}
