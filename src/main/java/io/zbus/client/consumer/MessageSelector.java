package io.zbus.client.consumer;

/**
 * Stub for zbus message selector.
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
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

    /**
     * Returns the topic.
     *
     * @return the topic
     */
    public String getTopic() {
        return topic;
    }

    /**
     * Sets the topic.
     *
     * @param topic the topic
     */
    public void setTopic(String topic) {
        this.topic = topic;
    }

    /**
     * Returns the expression.
     *
     * @return the expression
     */
    public String getExpression() {
        return expression;
    }

    /**
     * Sets the expression.
     *
     * @param expression the expression
     */
    public void setExpression(String expression) {
        this.expression = expression;
    }
}
