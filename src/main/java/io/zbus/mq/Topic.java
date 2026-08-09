package io.zbus.mq;

/**
 * Stub for zbus Topic.
 */
public class Topic {

    private String topicName;

    public Topic() {}

    public Topic(String topicName) {
        this.topicName = topicName;
    }

    public String getTopicName() { return topicName; }
    public void setTopicName(String topicName) { this.topicName = topicName; }
}
