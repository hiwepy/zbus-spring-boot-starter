package io.zbus.client.exception;

/**
 * Stub for zbus MQ client exception.
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
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
