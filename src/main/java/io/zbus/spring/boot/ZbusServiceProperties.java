/*
 * Copyright (c) 2018, hiwepy (https://github.com/hiwepy).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package io.zbus.spring.boot;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties for the Zbus RPC service.
 * <p>
 * Bound to the {@code spring.zbus.consume-actively.*} namespace. Controls
 * service name, connection count, runtime diagnostics and module registration.
 * </p>
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
@ConfigurationProperties(ZbusServiceProperties.PREFIX)
public class ZbusServiceProperties {

	/**
     * Configuration prefix. {@code CONSUME_PASSIVELY} corresponds to the "PULL"
     * consume type.
     */
	public static final String PREFIX = "spring.zbus.consume-actively";

	/** Whether the Zbus RPC auto-configuration is enabled. */
	private boolean enabled = false;

	/** Logical service name registered with the Zbus broker. */
    private String serviceName;
	
	private int connectionCount;
	
	private boolean responseTypeInfo = false;
	private boolean methodPage = false;
	private boolean stackTrace = false;
	
	/** If topic(ServiceName) in zbus is missing, should we declare it or not. */
	private boolean declareOnMissing = true;
	
	private Map<String /* module */, String /* class full name */> modules = new LinkedHashMap<String, String>();

	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * Sets the enabled.
	 *
	 * @param enabled the enabled
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * Returns the service name.
	 *
	 * @return the service name
	 */
	public String getServiceName() {
		return serviceName;
	}

	/**
	 * Sets the service name.
	 *
	 * @param serviceName the service name
	 */
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	/**
	 * Returns the connection count.
	 *
	 * @return the connection count
	 */
	public int getConnectionCount() {
		return connectionCount;
	}

	/**
	 * Sets the connection count.
	 *
	 * @param connectionCount the connection count
	 */
	public void setConnectionCount(int connectionCount) {
		this.connectionCount = connectionCount;
	}

	/**
	 * Returns the response type info.
	 *
	 * @return the response type info
	 */
	public boolean isResponseTypeInfo() {
		return responseTypeInfo;
	}

	/**
	 * Sets the response type info.
	 *
	 * @param responseTypeInfo the response type info
	 */
	public void setResponseTypeInfo(boolean responseTypeInfo) {
		this.responseTypeInfo = responseTypeInfo;
	}

	/**
	 * Returns the method page.
	 *
	 * @return the method page
	 */
	public boolean isMethodPage() {
		return methodPage;
	}

	/**
	 * Sets the method page.
	 *
	 * @param methodPage the method page
	 */
	public void setMethodPage(boolean methodPage) {
		this.methodPage = methodPage;
	}

	/**
	 * Returns the stack trace.
	 *
	 * @return the stack trace
	 */
	public boolean isStackTrace() {
		return stackTrace;
	}

	/**
	 * Sets the stack trace.
	 *
	 * @param stackTrace the stack trace
	 */
	public void setStackTrace(boolean stackTrace) {
		this.stackTrace = stackTrace;
	}

	/**
	 * Returns the declare on missing.
	 *
	 * @return the declare on missing
	 */
	public boolean isDeclareOnMissing() {
		return declareOnMissing;
	}

	/**
	 * Sets the declare on missing.
	 *
	 * @param declareOnMissing the declare on missing
	 */
	public void setDeclareOnMissing(boolean declareOnMissing) {
		this.declareOnMissing = declareOnMissing;
	}

	public Map<String, String> getModules() {
		return modules;
	}

	/**
	 * Sets the modules.
	 *
	 * @param modules the modules
	 */
	public void setModules(Map<String, String> modules) {
		this.modules = modules;
	}

  
	
	
}
