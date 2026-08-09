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
 * @author [@Loong Wan](https://github.com/loong10k)
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

    public Message publish(final Message msg) throws IOException, InterruptedException {
        if (producer != null) {
            return producer.publish(msg);
        }
        return msg;
    }

    public Message publish(final Message msg, final long timeout) throws IOException, InterruptedException {
        if (producer != null) {
            return producer.publish(msg, timeout);
        }
        return msg;
    }

    public void publish(final Collection<Message> msgs) throws IOException, InterruptedException {
        for (Message message : msgs) {
            publish(message);
        }
    }

    public void publish(final Collection<Message> msgs, final long timeout) throws IOException, InterruptedException {
        for (Message message : msgs) {
            publish(message, timeout);
        }
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public DefaultMQProducer getMqProducer() {
        return mqProducer;
    }

    public void setMqProducer(DefaultMQProducer mqProducer) {
        this.mqProducer = mqProducer;
    }

}
