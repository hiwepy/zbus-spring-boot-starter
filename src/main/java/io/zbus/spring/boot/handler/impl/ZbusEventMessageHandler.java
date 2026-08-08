package io.zbus.spring.boot.handler.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.zbus.mq.Message;
import io.zbus.spring.boot.event.ZbusEvent;
import io.zbus.spring.boot.handler.AbstractRouteableMessageHandler;
import io.zbus.spring.boot.handler.ZbusMessageHandler;
import io.zbus.spring.boot.handler.chain.HandlerChain;
import io.zbus.spring.boot.handler.chain.HandlerChainResolver;
import io.zbus.spring.boot.handler.chain.ProxiedHandlerChain;

/**
 * Entry-point handler for Zbus message consumption that wraps each received
 * {@link Message} in a {@link ZbusEvent} and dispatches it through the
 * configured {@link HandlerChain}.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
public class ZbusEventMessageHandler extends AbstractRouteableMessageHandler<ZbusEvent> implements ZbusMessageHandler {

	private static final Logger LOG = LoggerFactory.getLogger(ZbusEventMessageHandler.class);

	/**
	 * @param filterChainResolver the chain resolver used to route events
	 */
	public ZbusEventMessageHandler(HandlerChainResolver<ZbusEvent> filterChainResolver) {
		super(filterChainResolver);
	}

	@Override
	public boolean preHandle(Message msgExt) throws Exception {
		return true;
	}

	@Override
	public void handleMessage(Message msgExt) throws Exception {
		// Build the original (root) chain.
		HandlerChain<ZbusEvent>	originalChain = new ProxiedHandlerChain();
		// Execute the event handler chain.
		this.doHandler(new ZbusEvent(msgExt), originalChain);
	}

	@Override
	public void postHandle(Message msgExt) throws Exception {
		
	}

	@Override
	public void afterCompletion(Message msgExt, Exception ex) throws Exception {
		if(ex != null) {
			LOG.warn("Consume message failed. messageExt:{}", msgExt, ex);
		}
	}
	
}