package io.zbus.mq;

/**
 * Stub for zbus ConsumeGroup.
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class ConsumeGroup {

    private String groupName;
    private String topic;

    public ConsumeGroup() {}

    public ConsumeGroup(String groupName) {
        this.groupName = groupName;
    }

    /**
     * Returns the group name.
     *
     * @return the group name
     */
    public String getGroupName() { return groupName; }
    /**
     * Sets the group name.
     *
     * @param groupName the group name
     */
    public void setGroupName(String groupName) { this.groupName = groupName; }
    /**
     * Returns the topic.
     *
     * @return the topic
     */
    public String getTopic() { return topic; }
    /**
     * Sets the topic.
     *
     * @param topic the topic
     */
    public void setTopic(String topic) { this.topic = topic; }
}
