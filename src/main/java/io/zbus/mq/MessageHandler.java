package io.zbus.mq;

/**
 * Stub for zbus MessageHandler.
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public interface MessageHandler {
    void handle(Message message, MqClient client) throws Exception;
}
