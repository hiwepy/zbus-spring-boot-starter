package io.zbus.mq;

/**
 * Stub for zbus MessageHandler.
 */
public interface MessageHandler {
    void handle(Message message, MqClient client) throws Exception;
}
