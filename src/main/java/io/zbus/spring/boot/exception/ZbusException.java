package io.zbus.spring.boot.exception;

@SuppressWarnings("serial")
public class ZbusException extends RuntimeException {

    public ZbusException(Exception e) {
        super(e.getMessage(), null);
    }
    
    public ZbusException(String errorMessage) {
        super(errorMessage, null);
    }
    
    public ZbusException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
 
    
}
