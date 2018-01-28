package io.zbus.spring.boot.disruptor;

import com.lmax.disruptor.EventTranslatorOneArg;

import io.zbus.mq.Message;
import io.zbus.spring.boot.event.ZbusDisruptorEvent;

public class ZbusDisruptorEventTranslator implements EventTranslatorOneArg<ZbusDisruptorEvent, Message> {
	
	@Override
	public void translateTo(ZbusDisruptorEvent event, long sequence, Message msgExt) {
		
		event.setMessageExt(msgExt);
		event.setTopic(msgExt.getTopic());
		event.setTag(msgExt.getTag());
		event.setBody(msgExt.getBody());
		
	}
	
}