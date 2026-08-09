package io.zbus.mq;

/**
 * Stub for zbus Message.
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

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTopic() { return topic; }
    public void setTopic(String topic) { this.topic = topic; }
    public String getTag() { return tag; }
    public void setTag(String tag) { this.tag = tag; }
    public String getStringBody() { return body; }
    public byte[] getBody() { return bodyBytes; }
    public void setBody(String body) { this.body = body; this.bodyBytes = body != null ? body.getBytes() : null; }
    public void setBody(byte[] body) { this.bodyBytes = body; this.body = body != null ? new String(body) : null; }
    public void setJsonBody(String jsonBody) { this.body = jsonBody; }
    public Integer getRetry() { return retry; }
    public void setRetry(int retry) { this.retry = retry; }
}
