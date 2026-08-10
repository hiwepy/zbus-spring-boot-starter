package io.zbus.spring.boot;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import io.zbus.mq.Consumer;
import io.zbus.spring.boot.annotation.ZbusRule;
import io.zbus.spring.boot.config.Ini;
import io.zbus.spring.boot.event.ZbusEvent;
import io.zbus.spring.boot.handler.EventHandler;
import io.zbus.spring.boot.handler.Nameable;
import io.zbus.spring.boot.handler.chain.HandlerChainManager;
import io.zbus.spring.boot.handler.chain.def.DefaultHandlerChainManager;
import io.zbus.spring.boot.handler.chain.def.PathMatchingHandlerChainResolver;
import io.zbus.spring.boot.handler.impl.ZbusEventMessageHandler;
import io.zbus.spring.boot.util.StringUtils;

/**
 * Spring Boot auto-configuration that discovers {@link EventHandler} beans,
 * indexes them by the {@link ZbusRule} annotation metadata and builds the
 * handler chain used by the Zbus consumer.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
@Configuration
@ConditionalOnClass({ Consumer.class })
@ConditionalOnProperty(prefix = ZbusConsumerProperties.PREFIX, value = "enabled", havingValue = "true")
@AutoConfigureAfter(ZbusProducerAutoConfiguration.class)
@EnableConfigurationProperties({ ZbusConsumerEventProperties.class })
public class ZbusPushEventHandlerAutoConfiguration implements ApplicationContextAware {

	private static final Logger LOG = LoggerFactory.getLogger(ZbusPushEventHandlerAutoConfiguration.class);
	private ApplicationContext applicationContext;

	/**
	 * Handler chain definitions.
	 */
	private Map<String, String> handlerChainDefinitionMap = new HashMap<String, String>();

	/**
	 * Collects every {@link EventHandler} bean (except the built-in entry-point)
	 * into a registry keyed by bean name.
	 */
	@Bean("zbusEventHandlers")
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map<String, EventHandler<ZbusEvent>> zbusEventHandlers() {

		Map<String, EventHandler<ZbusEvent>> rocketmqEventHandlers = new LinkedHashMap<String, EventHandler<ZbusEvent>>();

		Map<String, EventHandler> beansOfType = getApplicationContext().getBeansOfType(EventHandler.class);
		if (!ObjectUtils.isEmpty(beansOfType)) {
			Iterator<Entry<String, EventHandler>> ite = beansOfType.entrySet().iterator();
			while (ite.hasNext()) {
				Entry<String, EventHandler> entry = ite.next();
				if ( entry.getValue() instanceof ZbusEventMessageHandler) {
					// Skip the built-in entry-point handler implementation.
					continue;
				}
				ZbusRule annotationType = getApplicationContext().findAnnotationOnBean(entry.getKey(), ZbusRule.class);
				if(annotationType == null) {
					// No annotation: log an error.
					LOG.error("Not Found AnnotationType {0} on Bean {1} Whith Name {2}", ZbusRule.class, entry.getValue().getClass(), entry.getKey());
				} else {
					handlerChainDefinitionMap.put(annotationType.value(), entry.getKey());
				}

				rocketmqEventHandlers.put(entry.getKey(), entry.getValue());
			}
		}
		// BeanFactoryUtils.beansOfTypeIncludingAncestors(getApplicationContext(), EventHandler.class);

		return rocketmqEventHandlers;
	}
	
	@Bean
	public ZbusEventMessageHandler messageHandler(
			ZbusConsumerEventProperties properties,
			@Qualifier("zbusEventHandlers") Map<String, EventHandler<ZbusEvent>> eventHandlers) {
		
		if( StringUtils.isNotEmpty(properties.getDefinitions())) {
			this.setHandlerChainDefinitions(properties.getDefinitions());
		} else if (!CollectionUtils.isEmpty(properties.getDefinitionMap())) {
			getHandlerChainDefinitionMap().putAll(properties.getDefinitionMap());
		}
		
		HandlerChainManager<ZbusEvent> manager = createHandlerChainManager(eventHandlers);
        PathMatchingHandlerChainResolver chainResolver = new PathMatchingHandlerChainResolver();
        chainResolver.setHandlerChainManager(manager);
        return new ZbusEventMessageHandler(chainResolver);
	}
	
	protected void setHandlerChainDefinitions(String definitions) {
        Ini ini = new Ini();
        ini.load(definitions);
        Ini.Section section = ini.getSection("urls");
        if (CollectionUtils.isEmpty(section)) {
            section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
        }
        getHandlerChainDefinitionMap().putAll(section);
    }
	
	/**
	 * Builds the {@link HandlerChainManager} from the registered handlers and
	 * the chain definitions.
	 *
	 * @param eventHandlers the registered event handlers
	 * @return the configured handler-chain manager
	 */
	protected HandlerChainManager<ZbusEvent> createHandlerChainManager(
			Map<String, EventHandler<ZbusEvent>> eventHandlers) {

		HandlerChainManager<ZbusEvent> manager = new DefaultHandlerChainManager();
		if (!CollectionUtils.isEmpty(eventHandlers)) {
			for (Map.Entry<String, EventHandler<ZbusEvent>> entry : eventHandlers.entrySet()) {
				String name = entry.getKey();
				EventHandler<ZbusEvent> handler = entry.getValue();
				if (handler instanceof Nameable) {
					((Nameable) handler).setName(name);
				}
				manager.addHandler(name, handler);
			}
		}

		Map<String, String> chains = getHandlerChainDefinitionMap();
		if (!CollectionUtils.isEmpty(chains)) {
			for (Map.Entry<String, String> entry : chains.entrySet()) {
				// topic/tags/keys
				String rule = entry.getKey();
				String chainDefinition = entry.getValue();
				manager.createChain(rule, chainDefinition);
			}
		}

		return manager;
	}
	
	public Map<String, String> getHandlerChainDefinitionMap() {
		return handlerChainDefinitionMap;
	}

	public void setHandlerChainDefinitionMap(Map<String, String> handlerChainDefinitionMap) {
		this.handlerChainDefinitionMap = handlerChainDefinitionMap;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
}
