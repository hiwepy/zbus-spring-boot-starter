package io.zbus.mq;

import java.io.IOException;

/**
 * Stub for zbus Consumer.
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class Consumer {

    public Consumer() {}
    public Consumer(ConsumerConfig config) {}

    /**
     * start.
     *
     */
    public void start() {}
    /**
     * close.
     *
     * @throws IOException if an error occurs
     */
    public void close() throws IOException {}
    /**
     * declare Group.
     *
     * @param topic the topic
     * @param group the group
     */
    public void declareGroup(String topic, String group) {}
    /**
     * declare Topic.
     *
     * @param topic the topic
     */
    public void declareTopic(String topic) {}
    /**
     * subscribe.
     *
     * @param topic the topic
     * @param selectorExpress the selector express
     */
    public void subscribe(String topic, String selectorExpress) {}
    /**
     * subscribe.
     *
     * @param topic the topic
     * @param selector the selector
     */
    public void subscribe(String topic, Object selector) {}
    /**
     * unsubscribe.
     *
     * @param topic the topic
     */
    public void unsubscribe(String topic) {}
    /**
     * remove Topic.
     *
     * @param topic the topic
     */
    public void removeTopic(String topic) {}
    /**
     * query Topic.
     *
     * @param topic the topic
     */
    public void queryTopic(String topic) {}
    /**
     * Sets the message handler.
     *
     * @param handler the handler
     */
    public void setMessageHandler(MessageHandler handler) {}
    /**
     * Sets the admin server selector.
     *
     * @param selector the selector
     */
    public void setAdminServerSelector(Object selector) {}
    /**
     * Sets the consume server selector.
     *
     * @param selector the selector
     */
    public void setConsumeServerSelector(Object selector) {}
}
