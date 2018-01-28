package io.zbus.spring.boot;

import io.zbus.client.consumer.MQPushConsumer;
import io.zbus.client.consumer.MessageSelector;
import io.zbus.client.consumer.listener.MessageListenerConcurrently;
import io.zbus.client.consumer.listener.MessageListenerOrderly;
import io.zbus.client.exception.MQClientException;
import io.zbus.client.producer.MessageQueueSelector;
import io.zbus.client.producer.selector.SelectMessageQueueByHash;
import io.zbus.client.producer.selector.SelectMessageQueueByRandom;
import io.zbus.mq.Consumer;
import io.zbus.spring.boot.enums.ConsumeMode;
import io.zbus.spring.boot.event.RocketmqEvent;
import io.zbus.spring.boot.exception.ZbusException;
import io.zbus.spring.boot.handler.EventHandler;
import io.zbus.spring.boot.handler.chain.HandlerChainManager;
import io.zbus.spring.boot.handler.chain.def.PathMatchingHandlerChainResolver;
import io.zbus.spring.boot.handler.impl.RocketmqEventMessageConcurrentlyHandler;
import io.zbus.spring.boot.handler.impl.RocketmqEventMessageOrderlyHandler;
import io.zbus.spring.boot.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class ZbusConsumerTemplate {

	public final String SELECTOR_EXPRESSS_EPARATOR = " || ";
	
	@Autowired
	private RocketmqEventMessageOrderlyHandler messageOrderlyHandler;
	@Autowired
	private RocketmqEventMessageConcurrentlyHandler messageConcurrentlyHandler;
	@Autowired
	private ZbusConsumerProperties pushConsumerProperties;
	
	private Consumer consumer;

	public ZbusConsumerTemplate(Consumer consumer) {
		this.consumer = consumer;
	}
	
	public void subscribe(String topic, String handlerName, EventHandler<RocketmqEvent> handler) throws ZbusException {

		PathMatchingHandlerChainResolver chainResolver = getChainResolver();
		if(chainResolver == null) {
			return;
		}
		HandlerChainManager<RocketmqEvent> chainManager = chainResolver.getHandlerChainManager();

		//构造一个独一无二的handler名称
		String chainDefinition = handlerName;
		//创建一个新的Handler实例
		chainManager.addHandler(chainDefinition, handler);
		
		//拆分
		String[] tagArr = StringUtils.tokenizeToStringArray(tags, ",");
		for (String tag : tagArr) {
			// 构造一个消息分发规则对应的handler责任链
			// topic/tags/keys
			String rule = new StringBuilder().append("/").append(topic).append("/").append(tag).append("/*").toString();
			chainManager.createChain(rule, chainDefinition);
		}
		
		
		
		// 调用消费端，订阅消息
		String selectorExpress = StringUtils.join(tagArr, SELECTOR_EXPRESSS_EPARATOR);
		switch (pushConsumerProperties.getSelectorType()) {
            case TAG:{
                consumer.subscribe(topic, selectorExpress);
			};break;
            case SQL92:{
                consumer.subscribe(topic, MessageSelector.bySql(selectorExpress));
            };break;
            default:{
                throw new IllegalArgumentException("Property 'selectorType' was wrong.");
            }
        }
		
	}
	
	public void unsubscribe(String topic, String tags, String handlerName) {
		
		PathMatchingHandlerChainResolver chainResolver = getChainResolver();
		if(chainResolver == null) {
			return;
		}
		
		HandlerChainManager<RocketmqEvent> chainManager = chainResolver.getHandlerChainManager();
		
		chainManager.getHandlers().remove(handlerName);
		
		//拆分
		String[] tagArr = StringUtils.tokenizeToStringArray(tags, ",");
		for (String tag : tagArr) {
			// topic/tags/keys
			String rule = new StringBuilder().append(topic).append("/").append(tag).append("/*").toString();
			chainManager.getHandlerChains().remove(rule);
		}
		consumer.removeTopic(topic)
		consumer.queryTopic(topic);
		// 调用消费端，取消消息订阅
		consumer.unsubscribe(topic);
		
	}

	protected PathMatchingHandlerChainResolver getChainResolver() {
		PathMatchingHandlerChainResolver chainResolver = null;
		if( pushConsumerProperties != null && pushConsumerProperties.isEnabled() ) {
			//根据不同的消费模式创建对应的handler
			if (ConsumeMode.ORDERLY.compareTo(pushConsumerProperties.getConsumeMode()) == 0) {
				chainResolver = (PathMatchingHandlerChainResolver) getMessageOrderlyHandler().getHandlerChainResolver();
			}else {
				chainResolver = (PathMatchingHandlerChainResolver) getMessageConcurrentlyHandler().getHandlerChainResolver();
			}
		}
		return chainResolver;
	}

	public RocketmqEventMessageOrderlyHandler getMessageOrderlyHandler() {
		return messageOrderlyHandler;
	}

	public void setMessageOrderlyHandler(RocketmqEventMessageOrderlyHandler messageOrderlyHandler) {
		this.messageOrderlyHandler = messageOrderlyHandler;
	}

	public RocketmqEventMessageConcurrentlyHandler getMessageConcurrentlyHandler() {
		return messageConcurrentlyHandler;
	}

	public void setMessageConcurrentlyHandler(RocketmqEventMessageConcurrentlyHandler messageConcurrentlyHandler) {
		this.messageConcurrentlyHandler = messageConcurrentlyHandler;
	}

	public Consumer getConsumer() {
		return consumer;
	}

	public void setConsumer(Consumer consumer) {
		this.consumer = consumer;
	}
	
}
