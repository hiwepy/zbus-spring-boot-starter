package io.zbus.spring.boot.event;

import io.zbus.mq.Message;

/**
 * Stub for RocketmqEvent (zbus event wrapping a message).
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class RocketmqEvent extends ZbusEvent {

    private static final long serialVersionUID = 1L;

    public RocketmqEvent(Message msgExt) throws Exception {
        super(msgExt);
    }
}
