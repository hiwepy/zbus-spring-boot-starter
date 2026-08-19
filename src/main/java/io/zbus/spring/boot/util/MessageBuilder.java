package io.zbus.spring.boot.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.Builder;

import io.zbus.mq.Message;
import io.zbus.spring.boot.exception.MessageBuildException;

/**
 * Fluent builder for constructing Zbus {@link Message} instances.
 * <p>
 * Topic, tag and keys are required; the body may be supplied as a String
 * or a raw byte array.
 * </p>
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class MessageBuilder implements Builder<Message> {

    /** Message topic. */
    private String topic;
    /** Message tag. */
    private String tag;
    /** Message business keys (unique identifier, query keywords). */
    private String keys;
    /** Raw message body bytes. */
    private byte[] body;

    /**
     * @param topic the message topic (required)
     * @param tag   the message tag (required)
     * @param keys  business unique identifier / query keyword (required)
     */
    public MessageBuilder(String topic, String tag, String keys) {
        this.topic = topic;
        this.tag = tag;
        this.keys = keys;
    }

    /**
     * Sets the message body as a String.
     *
     * @param body the body content
     * @return this builder
     */
    public MessageBuilder withBody(String body) {
        this.body = body != null ? body.getBytes() : null;
        return this;
    }

    /**
     * Sets the message body as raw bytes.
     *
     * @param body the body bytes
     * @return this builder
     */
    public MessageBuilder withBody(byte[] body) {
        this.body = body;
        return this;
    }

    @Override
    /**
     * build.
     *
     * @return the result
     */
    public Message build() {
        if (StringUtils.isEmpty(topic)) {
            throw new MessageBuildException("topic is empty");
        }
        if (StringUtils.isEmpty(tag)) {
            throw new MessageBuildException("tag is empty");
        }
        if (StringUtils.isEmpty(keys)) {
            throw new MessageBuildException("keys is empty");
        }
        if (body == null) {
            throw new MessageBuildException("body is null");
        }
        Message msg = new Message();
        msg.setTopic(topic);
        msg.setTag(tag);
        msg.setId(keys);
        msg.setBody(body);
        return msg;
    }

}
