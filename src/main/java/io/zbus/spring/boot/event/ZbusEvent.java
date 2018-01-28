/*
 * Copyright (c) 2010-2020, vindell (https://github.com/vindell).
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
package io.zbus.spring.boot.event;

import java.io.UnsupportedEncodingException;

import org.springframework.context.ApplicationEvent;

import io.zbus.mq.Message;

@SuppressWarnings("serial")
public class ZbusEvent extends ApplicationEvent {
	
	private Message messageExt;
	private String topic;
	private String tag;
	private byte[] body;
	
	/** Route Expression*/
	private String routeExpression;

	public ZbusEvent(Message msgExt) throws Exception {
		super(msgExt);
		this.topic = msgExt.getTopic();
		this.tag = msgExt.getTag();
		this.body = msgExt.getBody();
		this.messageExt = msgExt;
		this.routeExpression = this.buildRouteExpression(msgExt);
	}
	
	private String buildRouteExpression(Message msgExt) {
		return new StringBuilder("/").append(msgExt.getTopic()).append("/").append(msgExt.getTag()).append("/")
				.append(msgExt.getId()).toString();
	}

	public String getMsgBody() {
		try {
			return new String(this.body, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	public String getMsgBody(String code) {
		try {
			return new String(this.body, code);
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	public Message getMessageExt() {
		return messageExt;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public byte[] getBody() {
		return body;
	}

	public void setBody(byte[] body) {
		this.body = body;
	}

	public String getRouteExpression() {
		return routeExpression;
	}

	public void setRouteExpression(String routeExpression) {
		this.routeExpression = routeExpression;
	}
	
}