package io.zbus.spring.boot.exception;

/**
 * Runtime exception thrown by the Zbus starter when consumer/producer/RPC
 * initialisation or message processing fails.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
@SuppressWarnings("serial")
public class ZbusException extends RuntimeException {

    /**
     * @param e the underlying exception
     */
    public ZbusException(Exception e) {
        super(e.getMessage(), null);
    }

    /**
     * @param errorMessage the error message
     */
    public ZbusException(String errorMessage) {
        super(errorMessage, null);
    }

    /**
     * @param errorMessage the error message
     * @param cause        the underlying cause
     */
    public ZbusException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }


}
