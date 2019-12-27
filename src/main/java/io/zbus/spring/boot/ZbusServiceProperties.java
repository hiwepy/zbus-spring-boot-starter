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

@ConfigurationProperties(ZbusServiceProperties.PREFIX)
public class ZbusServiceProperties {
	
	/**
     * ConsumeType.CONSUME_PASSIVELY : "PULL"
     */
	public static final String PREFIX = "spring.zbus.consume-actively";
	
	/** 是否启用 **/
	private boolean enabled = false;
	
	/** */
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

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public int getConnectionCount() {
		return connectionCount;
	}

	public void setConnectionCount(int connectionCount) {
		this.connectionCount = connectionCount;
	}

	public boolean isResponseTypeInfo() {
		return responseTypeInfo;
	}

	public void setResponseTypeInfo(boolean responseTypeInfo) {
		this.responseTypeInfo = responseTypeInfo;
	}

	public boolean isMethodPage() {
		return methodPage;
	}

	public void setMethodPage(boolean methodPage) {
		this.methodPage = methodPage;
	}

	public boolean isStackTrace() {
		return stackTrace;
	}

	public void setStackTrace(boolean stackTrace) {
		this.stackTrace = stackTrace;
	}

	public boolean isDeclareOnMissing() {
		return declareOnMissing;
	}

	public void setDeclareOnMissing(boolean declareOnMissing) {
		this.declareOnMissing = declareOnMissing;
	}

	public Map<String, String> getModules() {
		return modules;
	}

	public void setModules(Map<String, String> modules) {
		this.modules = modules;
	}

  
	
	
}
