package io.zbus.mq;

/**
 * Stub for zbus Protocol and its inner classes.
 */
public class Protocol {

    public static class ConsumeGroupInfo {
        private String groupName;
        private String topic;

        public ConsumeGroupInfo() {}
        public String getGroupName() { return groupName; }
        public void setGroupName(String groupName) { this.groupName = groupName; }
        public String getTopic() { return topic; }
        public void setTopic(String topic) { this.topic = topic; }
    }

    public static class TopicInfo {
        private String topicName;
        private int messageCount;

        public TopicInfo() {}
        public String getTopicName() { return topicName; }
        public void setTopicName(String topicName) { this.topicName = topicName; }
        public int getMessageCount() { return messageCount; }
        public void setMessageCount(int messageCount) { this.messageCount = messageCount; }
    }
}
