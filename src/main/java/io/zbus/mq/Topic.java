package io.zbus.mq;

/**
 * Stub for zbus Topic.
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class Topic {

    private String topicName;

    public Topic() {}

    public Topic(String topicName) {
        this.topicName = topicName;
    }

    /**
     * Returns the topic name.
     *
     * @return the topic name
     */
    public String getTopicName() { return topicName; }
    /**
     * Sets the topic name.
     *
     * @param topicName the topic name
     */
    public void setTopicName(String topicName) { this.topicName = topicName; }
}
