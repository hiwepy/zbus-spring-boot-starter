package io.zbus.mq;

import io.zbus.mq.Broker.ServerSelector;
import io.zbus.mq.Protocol.ConsumeGroupInfo;
import io.zbus.mq.Protocol.TopicInfo;

import java.io.IOException;

/**
 * Stub for zbus Producer.
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class Producer {

    public Producer() {}
    public Producer(Object broker) {}

    /**
     * declare Group.
     *
     * @param topic the topic
     * @param group the group
     * @return the result
     * @throws IOException if an error occurs
     * @throws InterruptedException if an error occurs
     */
    public ConsumeGroupInfo[] declareGroup(Topic topic, ConsumeGroup group) throws IOException, InterruptedException {
        return new ConsumeGroupInfo[0];
    }
    /**
     * declare Group.
     *
     * @param topic the topic
     * @param group the group
     * @return the result
     * @throws IOException if an error occurs
     * @throws InterruptedException if an error occurs
     */
    public ConsumeGroupInfo[] declareGroup(String topic, ConsumeGroup group) throws IOException, InterruptedException {
        return new ConsumeGroupInfo[0];
    }
    /**
     * declare Topic.
     *
     * @param topic the topic
     * @return the result
     * @throws IOException if an error occurs
     * @throws InterruptedException if an error occurs
     */
    public TopicInfo[] declareTopic(Topic topic) throws IOException, InterruptedException {
        return new TopicInfo[0];
    }
    /**
     * declare Topic.
     *
     * @param topic the topic
     * @return the result
     * @throws IOException if an error occurs
     * @throws InterruptedException if an error occurs
     */
    public TopicInfo[] declareTopic(String topic) throws IOException, InterruptedException {
        return new TopicInfo[0];
    }
    /**
     * empty Group.
     *
     * @param topic the topic
     * @param group the group
     * @return the result
     * @throws IOException if an error occurs
     * @throws InterruptedException if an error occurs
     */
    public Object[] emptyGroup(String topic, String group) throws IOException, InterruptedException {
        return new Object[0];
    }
    /**
     * empty Topic.
     *
     * @param topic the topic
     * @return the result
     * @throws IOException if an error occurs
     * @throws InterruptedException if an error occurs
     */
    public Object[] emptyTopic(String topic) throws IOException, InterruptedException {
        return new Object[0];
    }
    /**
     * query Group.
     *
     * @param topic the topic
     * @param group the group
     * @return the result
     * @throws IOException if an error occurs
     * @throws InterruptedException if an error occurs
     */
    public ConsumeGroupInfo[] queryGroup(String topic, String group) throws IOException, InterruptedException {
        return new ConsumeGroupInfo[0];
    }
    /**
     * query Topic.
     *
     * @param topic the topic
     * @return the result
     * @throws IOException if an error occurs
     * @throws InterruptedException if an error occurs
     */
    public TopicInfo[] queryTopic(String topic) throws IOException, InterruptedException {
        return new TopicInfo[0];
    }
    /**
     * remove Group.
     *
     * @param topic the topic
     * @param group the group
     * @return the result
     * @throws IOException if an error occurs
     * @throws InterruptedException if an error occurs
     */
    public Object[] removeGroup(String topic, String group) throws IOException, InterruptedException {
        return new Object[0];
    }
    /**
     * remove Topic.
     *
     * @param topic the topic
     * @return the result
     * @throws IOException if an error occurs
     * @throws InterruptedException if an error occurs
     */
    public Object[] removeTopic(String topic) throws IOException, InterruptedException {
        return new Object[0];
    }
    /**
     * Returns the admin server selector.
     *
     * @return the admin server selector
     */
    public ServerSelector getAdminServerSelector() { return null; }
    /**
     * publish.
     *
     * @param msg the msg
     * @return the result
     * @throws IOException if an error occurs
     * @throws InterruptedException if an error occurs
     */
    public Message publish(Message msg) throws IOException, InterruptedException { return msg; }
    /**
     * publish.
     *
     * @param msg the msg
     * @param timeout the timeout
     * @return the result
     * @throws IOException if an error occurs
     * @throws InterruptedException if an error occurs
     */
    public Message publish(Message msg, long timeout) throws IOException, InterruptedException { return msg; }
    /**
     * close.
     *
     */
    public void close() {}
}
