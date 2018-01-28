package io.zbus.spring.boot;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(ZbusConsumerEventProperties.PREFIX)
public class ZbusConsumerEventProperties {
	
	public static final String PREFIX = ZbusConsumerProperties.PREFIX + ".event";

	private String definitions = null;

    private Map<String /* rule */, String /* handler names */> definitionMap = new LinkedHashMap<String, String>();

	public String getDefinitions() {
		return definitions;
	}

	public void setDefinitions(String definitions) {
		this.definitions = definitions;
	}

	public Map<String, String> getDefinitionMap() {
		return definitionMap;
	}

	public void setDefinitionMap(Map<String, String> definitionMap) {
		this.definitionMap = definitionMap;
	}

	
    
    
}
