package io.zbus.spring.boot;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Handler-chain definition properties for the Zbus consumer.
 * <p>
 * Bound to the {@code spring.zbus.consume.event.*} namespace. Allows handler
 * chains to be declared either as a single INI-style string
 * ({@link #definitions}) or as a {@code rule -> handler names} map
 * ({@link #definitionMap}).
 * </p>
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
@ConfigurationProperties(ZbusConsumerEventProperties.PREFIX)
public class ZbusConsumerEventProperties {

	/** Configuration prefix, nested under the consumer namespace. */
	public static final String PREFIX = ZbusConsumerProperties.PREFIX + ".event";

	/** INI-style handler-chain definitions. */
	private String definitions = null;

    /** Rule-to-handler-names mapping used to build handler chains. */
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
