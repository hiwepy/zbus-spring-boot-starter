package io.zbus.client.exception;

/**
 * Stub for zbus MQ client exception.
 */
public class MQClientException extends Exception {

    private static final long serialVersionUID = 1L;

    public MQClientException() {
        super();
    }

    public MQClientException(String message) {
        super(message);
    }

    public MQClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public MQClientException(Throwable cause) {
        super(cause);
    }
}
