package io.zbus.spring.boot.disruptor;

import com.lmax.disruptor.EventTranslatorOneArg;

import io.zbus.mq.Message;
import io.zbus.spring.boot.event.ZbusDisruptorEvent;

/**
 * Disruptor {@link EventTranslatorOneArg} that copies a received
 * {@link Message} into a {@link ZbusDisruptorEvent} for asynchronous processing.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
public class ZbusDisruptorEventTranslator implements EventTranslatorOneArg<ZbusDisruptorEvent, Message> {

	@Override
	public void translateTo(ZbusDisruptorEvent event, long sequence, Message msgExt) {

		event.setMessageExt(msgExt);
		event.setTopic(msgExt.getTopic());
		event.setTag(msgExt.getTag());
		event.setBody(msgExt.getBody());

	}

}