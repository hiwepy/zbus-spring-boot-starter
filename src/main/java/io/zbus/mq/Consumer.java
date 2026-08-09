package io.zbus.mq;

import java.io.IOException;

/**
 * Stub for zbus Consumer.
 */
public class Consumer {

    public Consumer() {}
    public Consumer(ConsumerConfig config) {}

    public void start() {}
    public void close() throws IOException {}
    public void declareGroup(String topic, String group) {}
    public void declareTopic(String topic) {}
    public void subscribe(String topic, String selectorExpress) {}
    public void subscribe(String topic, Object selector) {}
    public void unsubscribe(String topic) {}
    public void removeTopic(String topic) {}
    public void queryTopic(String topic) {}
    public void setMessageHandler(MessageHandler handler) {}
    public void setAdminServerSelector(Object selector) {}
    public void setConsumeServerSelector(Object selector) {}
}
