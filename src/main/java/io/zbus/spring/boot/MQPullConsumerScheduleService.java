package io.zbus.spring.boot;

/**
 * Stub for MQPullConsumerScheduleService.
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class MQPullConsumerScheduleService {

    private DefaultMQPullConsumer defaultMQPullConsumer;

    public MQPullConsumerScheduleService() {}
    public MQPullConsumerScheduleService(String consumerGroup) {
        this.defaultMQPullConsumer = new DefaultMQPullConsumer(consumerGroup);
    }

    /**
     * Returns the default m q pull consumer.
     *
     * @return the default m q pull consumer
     */
    public DefaultMQPullConsumer getDefaultMQPullConsumer() { return defaultMQPullConsumer; }
    /**
     * Sets the message model.
     *
     * @param model the model
     */
    public void setMessageModel(Object model) {}
    /**
     * Sets the pull thread nums.
     *
     * @param nums the nums
     */
    public void setPullThreadNums(int nums) {}
    /**
     * register Pull Task Callback.
     *
     * @param topic the topic
     * @param callback the callback
     */
    public void registerPullTaskCallback(String topic, Object callback) {}
    /**
     * start.
     *
     */
    public void start() {}
    /**
     * shutdown.
     *
     */
    public void shutdown() {}
}
