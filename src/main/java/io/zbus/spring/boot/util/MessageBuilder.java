package io.zbus.spring.boot.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.Builder;

import io.zbus.mq.Message;
import io.zbus.spring.boot.exception.MessageBuildException;

import com.alibaba.fastjson.JSONObject;

public class MessageBuilder implements Builder<Message> {

	private String topic;
	private String tag;
	private String keys;
	private byte[] body;
	
	private Message msg = new Message();

	public MessageBuilder topic(String topic) {
		this.topic = topic;
		return this;
	}
	
	public MessageBuilder tag(String tag) {
		this.tag = tag;
		return this;
	}
	
	public MessageBuilder keys(String keys) {
		this.keys = keys;
		return this;
	}
	
	public MessageBuilder body(String body) {
		this.body = body.getBytes();
		return this;
	}
	
	public MessageBuilder body(Object body) {
		this.body = JSONObject.toJSONString(body).getBytes();
		return this;
	}
	
	public MessageBuilder body(byte[] body) {
		this.body = body;
		return this;
	}
	
	@Override
	public Message build() {
		
		if (StringUtils.isEmpty(topic)) {
			throw new MessageBuildException("topic is empty");
		}
		if (StringUtils.isEmpty(tag)) {
			throw new MessageBuildException("tag is empty");
		}
		if (StringUtils.isEmpty(keys)) {
			throw new MessageBuildException("keys is empty");
		}
		if ( null == body ) {
			throw new MessageBuildException("body is null");
		}
		Message msg = new Message();
		msg.setTopic(value)
		msg.setBody(body)
		
		msg.setTag(value)
		
		
		return new Message(topic, // topic
				tag, // tags
				keys, // key用于标识业务的唯一性； key 消息关键词，多个Key用KEY_SEPARATOR隔开（查询消息使用）
				body// body 二进制字节数组
		);
	}

}
