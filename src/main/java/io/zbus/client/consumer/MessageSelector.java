package io.zbus.client.consumer;

/**
 * Stub for zbus message selector.
 */
public class MessageSelector {

    private String topic;
    private String expression;

    public MessageSelector() {
    }

    public MessageSelector(String topic, String expression) {
        this.topic = topic;
        this.expression = expression;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }
}
