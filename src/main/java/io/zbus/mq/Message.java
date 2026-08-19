package io.zbus.mq;

/**
 * Stub for zbus Message.
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class Message {

    private String id;
    private String topic;
    private String tag;
    private String body;
    private byte[] bodyBytes;
    private int retry;

    public Message() {
    }

    /**
     * Returns the id.
     *
     * @return the id
     */
    public String getId() { return id; }
    /**
     * Sets the id.
     *
     * @param id the id
     */
    public void setId(String id) { this.id = id; }
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
    /**
     * Returns the tag.
     *
     * @return the tag
     */
    public String getTag() { return tag; }
    /**
     * Sets the tag.
     *
     * @param tag the tag
     */
    public void setTag(String tag) { this.tag = tag; }
    /**
     * Returns the string body.
     *
     * @return the string body
     */
    public String getStringBody() { return body; }
    /**
     * Returns the body.
     *
     * @return the body
     */
    public byte[] getBody() { return bodyBytes; }
    /**
     * Sets the body.
     *
     * @param body the body
     */
    public void setBody(String body) { this.body = body; this.bodyBytes = body != null ? body.getBytes() : null; }
    /**
     * Sets the body.
     *
     * @param body the body
     */
    public void setBody(byte[] body) { this.bodyBytes = body; this.body = body != null ? new String(body) : null; }
    /**
     * Sets the json body.
     *
     * @param jsonBody the json body
     */
    public void setJsonBody(String jsonBody) { this.body = jsonBody; }
    /**
     * Returns the retry.
     *
     * @return the retry
     */
    public Integer getRetry() { return retry; }
    /**
     * Sets the retry.
     *
     * @param retry the retry
     */
    public void setRetry(int retry) { this.retry = retry; }
}
