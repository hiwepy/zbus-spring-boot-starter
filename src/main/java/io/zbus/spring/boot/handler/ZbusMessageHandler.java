package io.zbus.spring.boot.handler;

import io.zbus.mq.Message;

/**
 */
public interface ZbusMessageHandler {
	
	boolean preHandle(Message msgExt) throws Exception;
	
	void handleMessage(Message msgExt) throws Exception;
    
	void postHandle(Message msgExt) throws Exception;
    
    void afterCompletion(Message msgExt, Exception ex) throws Exception;
    
}