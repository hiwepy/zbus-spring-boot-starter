package io.zbus.spring.boot;

import java.io.IOException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import io.zbus.mq.Broker.ServerSelector;
import io.zbus.mq.ConsumeGroup;
import io.zbus.mq.Message;
import io.zbus.mq.Producer;
import io.zbus.mq.Protocol.ConsumeGroupInfo;
import io.zbus.mq.Protocol.TopicInfo;
import io.zbus.mq.Topic;

/**
 * Helper template for the Zbus producer, exposing the underlying
 * {@link Producer} together with convenience methods for declaring
 * topics/groups and publishing messages.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class ZbusProducerTemplate {

	/** The underlying Zbus producer. */
	@Autowired
	protected Producer producer;

	/** Creates an empty template; the producer must be set via {@link #setProducer(Producer)}. */
	public ZbusProducerTemplate() {
	}

	/**
	 * @param producer the underlying Zbus producer
	 */
	public ZbusProducerTemplate(Producer producer) {
		this.producer = producer;
	}

	public ConsumeGroupInfo[] declareGroup(final Topic topic, final ConsumeGroup group)
			throws IOException, InterruptedException {
		return producer.declareGroup(topic, group);
	}

	public ConsumeGroupInfo[] declareGroup(final String topic, final ConsumeGroup group)
			throws IOException, InterruptedException {
		return producer.declareGroup(topic, group);
	}

	public TopicInfo[] declareTopic(final Topic topic) throws IOException, InterruptedException {
		return producer.declareTopic(topic);
	}

	public TopicInfo[] declareTopic(final String topic) throws IOException, InterruptedException {
		return producer.declareTopic(topic);
	}

	public Object[] emptyGroup(final String topic, final String group) throws IOException, InterruptedException {
		return producer.emptyGroup(topic, group);
	}

	public Object[] emptyTopic(final String topic) throws IOException, InterruptedException {
		return producer.emptyTopic(topic);
	}

	public ConsumeGroupInfo[] queryGroup(final String topic, final String group)
			throws IOException, InterruptedException {
		return producer.queryGroup(topic, group);
	}

	public TopicInfo[] queryTopic(final String topic) throws IOException, InterruptedException {
		return producer.queryTopic(topic);
	}

	public Object[] removeGroup(final String topic, final String group) throws IOException, InterruptedException {
		return producer.removeGroup(topic, group);
	}

	public Object[] removeTopic(final String topic) throws IOException, InterruptedException {
		return producer.removeTopic(topic);
	}

	public ServerSelector getAdminServerSelector() {
		return producer.getAdminServerSelector();
	}

	public Message publish(final String topic, final String tag, final String key, final String body)
			throws IOException, InterruptedException {

		Message msg = new Message();
		msg.setId(key); // key: business unique identifier
		msg.setTopic(topic); // message topic
		msg.setTag(tag); // optional message tag

		msg.setBody(body); // message body
		msg.setJsonBody(body);

		// Other default parameters
		
		// msg.setRetry(value)
		// msg.setStatus(status)

		/*msg.setAck(ack);
		msg.setEncoding(encoding)
		msg.setGroupAckTimeout(value)
		msg.setGroupAckWindow(value)
		msg.setGroupFilter(value)
		msg.setGroupMask(value)
		msg.setGroupNameAuto(value);
		msg.setGroupStartCopy(value)
		msg.setGroupStartMsgId(mq)
		msg.setGroupStartOffset(value)
		msg.setGroupStartTime(value)
		msg.setHost(value);
		msg.setMethod(method);
		msg.setRemoteAddr(value)
		msg.setRetry(value)
		msg.setSender(value)
		msg.setTimestamp(value)
		msg.setToken(value)
		msg.setUrl(url)
		msg.setVersion(value)*/
		
		return producer.publish(msg);
	}

	public Message publish(final Message msg) throws IOException, InterruptedException {
		return producer.publish(msg);
	}

	public Message publish(final Message msg, final long timeout) throws IOException, InterruptedException {
		return producer.publish(msg, timeout);
	}

	// for batch
	public void publish(final Collection<Message> msgs) throws IOException, InterruptedException {
		for (Message message : msgs) {
			producer.publish(message);
		}
	}

	public void publish(final Collection<Message> msgs, final long timeout) throws IOException, InterruptedException {
		for (Message message : msgs) {
			producer.publish(message, timeout);
		}
	}

	public Producer getProducer() {
		return producer;
	}

	public void setProducer(Producer producer) {
		this.producer = producer;
	}

}
