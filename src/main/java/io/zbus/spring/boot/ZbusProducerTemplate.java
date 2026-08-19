package io.zbus.spring.boot;

import java.io.IOException;
import java.util.Collection;

import io.zbus.client.producer.DefaultMQProducer;
import io.zbus.mq.Message;
import io.zbus.mq.Producer;

/**
 * Helper template for the Zbus producer, exposing the underlying
 * {@link Producer} together with convenience methods for declaring
 * topics/groups and publishing messages.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class ZbusProducerTemplate {

    /** The underlying Zbus producer. */
    protected Producer producer;

    /** The underlying MQ producer. */
    protected DefaultMQProducer mqProducer;

    /** Creates an empty template. */
    public ZbusProducerTemplate() {
    }

    /**
     * @param producer the underlying Zbus producer
     */
    public ZbusProducerTemplate(Producer producer) {
        this.producer = producer;
    }

    /**
     * @param mqProducer the underlying MQ producer
     */
    public ZbusProducerTemplate(DefaultMQProducer mqProducer) {
        this.mqProducer = mqProducer;
    }

    /**
     * publish.
     *
     * @param topic the topic
     * @param tag the tag
     * @param key the key
     * @param body the body
     * @return the result
     */
    public Message publish(final String topic, final String tag, final String key, final String body)
            throws IOException, InterruptedException {
        Message msg = new Message();
        msg.setId(key);
        msg.setTopic(topic);
        msg.setTag(tag);
        msg.setBody(body);
        if (producer != null) {
            return producer.publish(msg);
        }
        return msg;
    }

    /**
     * publish.
     *
     * @param msg the msg
     * @return the result
     * @throws IOException if an error occurs
     * @throws InterruptedException if an error occurs
     */
    public Message publish(final Message msg) throws IOException, InterruptedException {
        if (producer != null) {
            return producer.publish(msg);
        }
        return msg;
    }

    /**
     * publish.
     *
     * @param msg the msg
     * @param timeout the timeout
     * @return the result
     * @throws IOException if an error occurs
     * @throws InterruptedException if an error occurs
     */
    public Message publish(final Message msg, final long timeout) throws IOException, InterruptedException {
        if (producer != null) {
            return producer.publish(msg, timeout);
        }
        return msg;
    }

    /**
     * publish.
     *
     * @param msgs the msgs
     * @throws IOException if an error occurs
     * @throws InterruptedException if an error occurs
     */
    public void publish(final Collection<Message> msgs) throws IOException, InterruptedException {
        for (Message message : msgs) {
            publish(message);
        }
    }

    /**
     * publish.
     *
     * @param msgs the msgs
     * @param timeout the timeout
     * @throws IOException if an error occurs
     * @throws InterruptedException if an error occurs
     */
    public void publish(final Collection<Message> msgs, final long timeout) throws IOException, InterruptedException {
        for (Message message : msgs) {
            publish(message, timeout);
        }
    }

    /**
     * Returns the producer.
     *
     * @return the producer
     */
    public Producer getProducer() {
        return producer;
    }

    /**
     * Sets the producer.
     *
     * @param producer the producer
     */
    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    /**
     * Returns the mq producer.
     *
     * @return the mq producer
     */
    public DefaultMQProducer getMqProducer() {
        return mqProducer;
    }

    /**
     * Sets the mq producer.
     *
     * @param mqProducer the mq producer
     */
    public void setMqProducer(DefaultMQProducer mqProducer) {
        this.mqProducer = mqProducer;
    }

}
