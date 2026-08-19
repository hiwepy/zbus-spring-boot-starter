package io.zbus.mq;

/**
 * Stub for zbus Protocol and its inner classes.
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class Protocol {

    public static class ConsumeGroupInfo {
        private String groupName;
        private String topic;

        public ConsumeGroupInfo() {}
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

    public static class TopicInfo {
        private String topicName;
        private int messageCount;

        public TopicInfo() {}
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
        /**
         * Returns the message count.
         *
         * @return the message count
         */
        public int getMessageCount() { return messageCount; }
        /**
         * Sets the message count.
         *
         * @param messageCount the message count
         */
        public void setMessageCount(int messageCount) { this.messageCount = messageCount; }
    }
}
