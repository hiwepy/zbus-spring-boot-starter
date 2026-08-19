package io.zbus.mq;

/**
 * Stub for zbus Broker with inner ServerSelector.
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class Broker {

    public Broker() {}
    public Broker(String address) {}

    /**
     * close.
     *
     */
    public void close() {}

    /**
     * Stub for Broker.ServerSelector.
     */
    public interface ServerSelector {
    }
}
