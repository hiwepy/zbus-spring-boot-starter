package io.zbus.mq;

/**
 * Stub for zbus ConsumeGroup.
 */
public class ConsumeGroup {

    private String groupName;
    private String topic;

    public ConsumeGroup() {}

    public ConsumeGroup(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupName() { return groupName; }
    public void setGroupName(String groupName) { this.groupName = groupName; }
    public String getTopic() { return topic; }
    public void setTopic(String topic) { this.topic = topic; }
}
