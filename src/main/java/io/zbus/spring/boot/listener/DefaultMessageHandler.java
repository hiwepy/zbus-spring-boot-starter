package io.zbus.spring.boot.listener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import io.zbus.mq.Message;
import io.zbus.mq.MessageHandler;
import io.zbus.mq.MqClient;
import io.zbus.spring.boot.ZbusConsumerProperties;
import io.zbus.spring.boot.handler.ZbusMessageHandler;
import io.zbus.spring.boot.handler.impl.NestedMessageOrderlyHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.ObjectUtils;

public class DefaultMessageHandler implements MessageHandler, ApplicationContextAware, InitializingBean {

	private static final Logger LOG = LoggerFactory.getLogger(DefaultMessageHandler.class);
	
	@Autowired
	private ZbusConsumerProperties properties;
	/**
	 * 真正处理消息的实现对象
	 */
	private ZbusMessageHandler messageHandler;
	private ApplicationContext applicationContext;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		
		List<ZbusMessageHandler> handlers = new ArrayList<ZbusMessageHandler>();
		
		// 查找Spring上下文中注册的MessageOrderlyHandler接口实现
		Map<String, ZbusMessageHandler> beansOfType = getApplicationContext().getBeansOfType(ZbusMessageHandler.class);
		if (!ObjectUtils.isEmpty(beansOfType)) {
			Iterator<Entry<String, ZbusMessageHandler>> ite = beansOfType.entrySet().iterator();
			while (ite.hasNext()) {
				Entry<String, ZbusMessageHandler> entry = ite.next();
				if (entry.getValue() instanceof NestedMessageOrderlyHandler ) {
					//跳过其他嵌套实现
					continue;
				}
				handlers.add(entry.getValue());
			}
		}
		
		messageHandler = new NestedMessageOrderlyHandler(handlers);
		
	}
	
	@Override
	public void handle(Message msgExt, MqClient client) throws IOException {
		// 消费消息内容
		LOG.debug("Receive msg: {}", msgExt);
		
		// 重试次数
		int retryTimes = properties.getRetryTimesWhenConsumeFailed();
				
		Exception exception = null;
		
		try {

			boolean continueHandle = messageHandler.preHandle(msgExt);
			if (LOG.isTraceEnabled()) {
				LOG.trace("Invoked preHandle method.  Continuing Handle ?: [" + continueHandle + "]");
			}
			
			if (continueHandle) {
				
				long now = System.currentTimeMillis();
				messageHandler.handleMessage(msgExt);
				long costTime = System.currentTimeMillis() - now;
                LOG.info("Message （ID : {} ）Consumed.  cost: {} ms", msgExt.getId(), costTime);
                
			}
			
			messageHandler.postHandle(msgExt);
			if (LOG.isTraceEnabled()) {
				LOG.trace("Successfully invoked postHandle method");
			}

		} catch (Exception e) {
			exception = e;
			
			if (msgExt.getRetry().intValue() < retryTimes) {
				// TODO 消息消费失败，进行日志记录
				String error = e.getCause() == null ? e.getMessage() : e.getCause().getMessage();
				LOG.debug(String.format("Consume Error : %s , Message （ID : %s ） Reconsume.", error, msgExt.getId()));
				msgExt.setRetry(msgExt.getRetry().intValue() + 1);
			}
			
		} finally {
			cleanup(msgExt,  exception);
		}
			
	}
	
	protected void cleanup(Message msgExt, Exception existing) {
		Exception exception = existing;
		try {
			messageHandler.afterCompletion(msgExt, exception);
			if (LOG.isTraceEnabled()) {
				LOG.trace("Successfully invoked afterCompletion method.");
			}
		} catch (Exception e) {
			if (exception == null) {
				exception = e;
			} else {
				LOG.debug("afterCompletion implementation threw an exception.  This will be ignored to "
						+ "allow the original source exception to be propagated.", e);
			}
		}
	}

	public ZbusMessageHandler getMessageHandler() {
		return messageHandler;
	}

	public void setMessageHandler(ZbusMessageHandler messageHandler) {
		this.messageHandler = messageHandler;
	}
	
	public ZbusConsumerProperties getProperties() {
		return properties;
	}

	public void setProperties(ZbusConsumerProperties properties) {
		this.properties = properties;
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	

}
