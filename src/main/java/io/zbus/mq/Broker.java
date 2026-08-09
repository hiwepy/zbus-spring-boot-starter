package io.zbus.mq;

/**
 * Stub for zbus Broker with inner ServerSelector.
 */
public class Broker {

    public Broker() {}
    public Broker(String address) {}

    public void close() {}

    /**
     * Stub for Broker.ServerSelector.
     */
    public interface ServerSelector {
    }
}
