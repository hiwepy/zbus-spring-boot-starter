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
package io.zbus.spring.boot.handler.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import io.zbus.mq.Message;
import io.zbus.spring.boot.handler.ZbusMessageHandler;

/**
 * 
 * @className	： NestedMessageOrderlyHandler
 * @description	： 嵌套的顺序消息处理器：解决统一消息交由多个处理实现处理问题
 * @author 		： <a href="https://github.com/hiwepy">hiwepy</a>
 * @date		： 2017年11月13日 上午10:35:10
 * @version 	V1.0
 */
public class NestedMessageOrderlyHandler implements ZbusMessageHandler {

	private static final Logger LOG = LoggerFactory.getLogger(NestedMessageOrderlyHandler.class);
	private final List<ZbusMessageHandler> handlers;

	public NestedMessageOrderlyHandler(List<ZbusMessageHandler> handlers) {
		this.handlers = handlers;
	}

	@Override
	public boolean preHandle(Message msgExt) throws Exception {
		return true;
	}
	
	@Override
	public void handleMessage(Message msgExt) throws Exception {
		if(isNested()){
			for (ZbusMessageHandler handler : getHandlers()) {
				handler.handleMessage(msgExt);
			}
		} else {
			 throw new IllegalArgumentException(" Not Found MessageOrderlyHandler .");
		}
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
	
	protected boolean isNested() {
		if(CollectionUtils.isEmpty(getHandlers())){
			return false;
		}
		return true;
	}
	
	public List<ZbusMessageHandler> getHandlers() {
		return handlers;
	}

}
