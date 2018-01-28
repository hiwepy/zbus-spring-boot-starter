package io.zbus.spring.boot.handler.chain.def;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import io.zbus.spring.boot.event.ZbusEvent;
import io.zbus.spring.boot.handler.EventHandler;
import io.zbus.spring.boot.handler.Nameable;
import io.zbus.spring.boot.handler.NamedHandlerList;
import io.zbus.spring.boot.handler.chain.HandlerChain;
import io.zbus.spring.boot.handler.chain.HandlerChainManager;
import io.zbus.spring.boot.util.StringUtils;

public class DefaultHandlerChainManager implements HandlerChainManager<ZbusEvent> {
	
	private static transient final Logger log = LoggerFactory.getLogger(DefaultHandlerChainManager.class);

    private Map<String, EventHandler<ZbusEvent>> handlers; 

    private Map<String, NamedHandlerList<ZbusEvent>> handlerChains;

    private final static String DEFAULT_CHAIN_DEFINATION_DELIMITER_CHAR = ",";
    
    public DefaultHandlerChainManager() {
        this.handlers = new LinkedHashMap<String, EventHandler<ZbusEvent>>();
        this.handlerChains = new LinkedHashMap<String, NamedHandlerList<ZbusEvent>>();
    }
    
    public Map<String, EventHandler<ZbusEvent>> getHandlers() {
        return handlers;
    }

    public void setHandlers(Map<String, EventHandler<ZbusEvent>> handlers) {
        this.handlers = handlers;
    }

    public Map<String, NamedHandlerList<ZbusEvent>> getHandlerChains() {
        return handlerChains;
    }
    
    public void setHandlerChains(Map<String, NamedHandlerList<ZbusEvent>> handlerChains) {
        this.handlerChains = handlerChains;
    }

    public EventHandler<ZbusEvent> getHandler(String name) {
        return this.handlers.get(name);
    }

    public void addHandler(String name, EventHandler<ZbusEvent> handler) {
        addHandler(name, handler, true);
    }
    
    protected void addHandler(String name, EventHandler<ZbusEvent> handler, boolean overwrite) {
        EventHandler<ZbusEvent> existing = getHandler(name);
        if (existing == null || overwrite) {
            if (handler instanceof Nameable) {
                ((Nameable) handler).setName(name);
            }
            this.handlers.put(name, handler);
        }
    }

    public void createChain(String chainName, String chainDefinition) {
        if (StringUtils.isBlank(chainName)) {
            throw new NullPointerException("chainName cannot be null or empty.");
        }
        if (StringUtils.isBlank(chainDefinition)) {
            throw new NullPointerException("chainDefinition cannot be null or empty.");
        }
        if (log.isDebugEnabled()) {
            log.debug("Creating chain [" + chainName + "] from String definition [" + chainDefinition + "]");
        }
        String[] handlerTokens = splitChainDefinition(chainDefinition);
        for (String token : handlerTokens) {
            addToChain(chainName, token);
        }
    }

    /**
     * Splits the comma-delimited handler chain definition line into individual handler definition tokens.
     */
    protected String[] splitChainDefinition(String chainDefinition) {
    	String trimToNull = StringUtils.trimToNull(chainDefinition);
    	if(trimToNull == null){
    		return null;
    	}
    	String[] split = StringUtils.splits(trimToNull, DEFAULT_CHAIN_DEFINATION_DELIMITER_CHAR);
    	for (int i = 0; i < split.length; i++) {
    		split[i] = StringUtils.trimToNull(split[i]);
		}
        return split;
    }

    public static void main(String[] args) {
		
	}
    
    public void addToChain(String chainName, String handlerName) {
        if (StringUtils.isBlank(chainName)) {
            throw new IllegalArgumentException("chainName cannot be null or empty.");
        }
        EventHandler<ZbusEvent> handler = getHandler(handlerName);
        if (handler == null) {
            throw new IllegalArgumentException("There is no handler with name '" + handlerName +
                    "' to apply to chain [" + chainName + "] in the pool of available Handlers.  Ensure a " +
                    "handler with that name/path has first been registered with the addHandler method(s).");
        }
        NamedHandlerList<ZbusEvent> chain = ensureChain(chainName);
        chain.add(handler);
    }

    protected NamedHandlerList<ZbusEvent> ensureChain(String chainName) {
        NamedHandlerList<ZbusEvent> chain = getChain(chainName);
        if (chain == null) {
            chain = new DefaultNamedHandlerList(chainName);
            this.handlerChains.put(chainName, chain);
        }
        return chain;
    }

    public NamedHandlerList<ZbusEvent> getChain(String chainName) {
        return this.handlerChains.get(chainName);
    }

    public boolean hasChains() {
        return !CollectionUtils.isEmpty(this.handlerChains);
    }

    @SuppressWarnings("unchecked")
	public Set<String> getChainNames() {
        return this.handlerChains != null ? this.handlerChains.keySet() : Collections.EMPTY_SET;
    }

    @Override
    public HandlerChain<ZbusEvent> proxy(HandlerChain<ZbusEvent> original, String chainName) {
        NamedHandlerList<ZbusEvent> configured = getChain(chainName);
        if (configured == null) {
            String msg = "There is no configured chain under the name/key [" + chainName + "].";
            throw new IllegalArgumentException(msg);
        }
        return configured.proxy(original);
    }

	
    

}
