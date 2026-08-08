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

/**
 * Helper template for the Zbus consumer, exposing the underlying
 * {@link Consumer} together with convenience methods for subscribing to topics
 * and binding {@link EventHandler} instances to the handler-chain registry.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
public class ZbusConsumerTemplate {

	/** Separator used when joining multiple tag expressions. */
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

		// Build a unique handler name.
		String chainDefinition = handlerName;
		// Register a new handler instance.
		chainManager.addHandler(chainDefinition, handler);

		// Split the tag expression.
		String[] tagArr = StringUtils.tokenizeToStringArray(tags, ",");
		for (String tag : tagArr) {
			// Build the dispatch rule chain: topic/tags/keys
			String rule = new StringBuilder().append("/").append(topic).append("/").append(tag).append("/*").toString();
			chainManager.createChain(rule, chainDefinition);
		}



		// Subscribe the consumer to the topic.
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

		// Split the tag expression.
		String[] tagArr = StringUtils.tokenizeToStringArray(tags, ",");
		for (String tag : tagArr) {
			// topic/tags/keys
			String rule = new StringBuilder().append(topic).append("/").append(tag).append("/*").toString();
			chainManager.getHandlerChains().remove(rule);
		}
		consumer.removeTopic(topic)
		consumer.queryTopic(topic);
		// Unsubscribe the consumer from the topic.
		consumer.unsubscribe(topic);
		
	}

	protected PathMatchingHandlerChainResolver getChainResolver() {
		PathMatchingHandlerChainResolver chainResolver = null;
		if( pushConsumerProperties != null && pushConsumerProperties.isEnabled() ) {
			// Select the handler based on the configured consume mode.
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
