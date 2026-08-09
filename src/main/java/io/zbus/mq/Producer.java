package io.zbus.mq;

import io.zbus.mq.Broker.ServerSelector;
import io.zbus.mq.Protocol.ConsumeGroupInfo;
import io.zbus.mq.Protocol.TopicInfo;

import java.io.IOException;

/**
 * Stub for zbus Producer.
 */
public class Producer {

    public Producer() {}
    public Producer(Object broker) {}

    public ConsumeGroupInfo[] declareGroup(Topic topic, ConsumeGroup group) throws IOException, InterruptedException {
        return new ConsumeGroupInfo[0];
    }
    public ConsumeGroupInfo[] declareGroup(String topic, ConsumeGroup group) throws IOException, InterruptedException {
        return new ConsumeGroupInfo[0];
    }
    public TopicInfo[] declareTopic(Topic topic) throws IOException, InterruptedException {
        return new TopicInfo[0];
    }
    public TopicInfo[] declareTopic(String topic) throws IOException, InterruptedException {
        return new TopicInfo[0];
    }
    public Object[] emptyGroup(String topic, String group) throws IOException, InterruptedException {
        return new Object[0];
    }
    public Object[] emptyTopic(String topic) throws IOException, InterruptedException {
        return new Object[0];
    }
    public ConsumeGroupInfo[] queryGroup(String topic, String group) throws IOException, InterruptedException {
        return new ConsumeGroupInfo[0];
    }
    public TopicInfo[] queryTopic(String topic) throws IOException, InterruptedException {
        return new TopicInfo[0];
    }
    public Object[] removeGroup(String topic, String group) throws IOException, InterruptedException {
        return new Object[0];
    }
    public Object[] removeTopic(String topic) throws IOException, InterruptedException {
        return new Object[0];
    }
    public ServerSelector getAdminServerSelector() { return null; }
    public Message publish(Message msg) throws IOException, InterruptedException { return msg; }
    public Message publish(Message msg, long timeout) throws IOException, InterruptedException { return msg; }
    public void close() {}
}
