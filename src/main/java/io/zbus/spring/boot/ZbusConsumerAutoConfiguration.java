package io.zbus.spring.boot;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import io.zbus.client.exception.MQClientException;
import io.zbus.mq.Broker;
import io.zbus.mq.Consumer;
import io.zbus.mq.ConsumerConfig;
import io.zbus.mq.MessageHandler;
import io.zbus.spring.boot.config.SubscriptionProvider;
import io.zbus.spring.boot.exception.ZbusException;
import io.zbus.spring.boot.hooks.ZbusConsumerShutdownHook;

@Configuration
@ConditionalOnClass({ Consumer.class })
@ConditionalOnProperty(prefix = ZbusConsumerProperties.PREFIX, value = "enabled", havingValue = "true")
@AutoConfigureAfter(ZbusPushEventHandlerAutoConfiguration.class)
@EnableConfigurationProperties({ ZbusConsumerProperties.class })
public class ZbusConsumerAutoConfiguration  {

	private static final Logger LOG = LoggerFactory.getLogger(ZbusConsumerAutoConfiguration.class);
	  
	/**
	 * 初始化Zbus消息监听方式的消费者
	 */
	@Bean
	@ConditionalOnMissingBean
	public Consumer consumer(ZbusConsumerProperties properties,
			@Autowired(required = false) SubscriptionProvider subProvider,
			MessageHandler messageHandler) throws ZbusException {

		try {

			/*
			 * 一个应用创建一个Consumer，由应用来维护此对象，可以设置为全局对象或者单例<br> 注意：ConsumerGroupName需要由应用来保证唯一
			 */
			
			
			Broker broker = new Broker("localhost:15555");    
			
			ConsumerConfig config = new ConsumerConfig(broker);
			
			//指定消息队列主题，同时可以指定分组通道
			config.setTopic("MyTopic");  
			config.setMessageHandler(messageHandler);

			Consumer consumer = new Consumer(config);
			consumer.start(); 
			
			consumer.declareGroup(topic, group)
			consumer.declareGroup(topic, group)
			
			
			
			consumer.setAdminServerSelector(adminServerSelector);
			consumer.setConsumeServerSelector(consumeServerSelector);
			
			

			/*
			 * 订阅指定topic下selectorExpress
			 */
			Map<String /* topic */, String /* selectorExpress */> subscription = new HashMap<String, String>();
			if(subProvider != null) {
				Map<String /* topic */, String /* selectorExpress */> subs = subProvider.subscription();
				if(!CollectionUtils.isEmpty(subs) ){
					subscription.putAll(subs);
				}
			}
			if(!CollectionUtils.isEmpty(properties.getSubscription()) ){
				subscription.putAll(properties.getSubscription());
			}
			
			if(!CollectionUtils.isEmpty(subscription) ){
				
				Iterator<Entry<String, String>> ite = subscription.entrySet().iterator();
				while (ite.hasNext()) {
					Entry<String, String> entry = ite.next();
					/* 
					 * entry.getKey() 	： topic名称 
					 * entry.getValue() : 根据实际情况设置消息的selectorExpress 
					 */
					String topic = entry.getKey();
					String selectorExpress = entry.getValue();
			           
					consumer.declareTopic(topic);
					
				}
				
			}

			/*
			 * 注册消费监听
			 */
			consumer.setMessageHandler(messageHandler);
			
			/*
			 * 延迟5秒再启动，主要是等待spring事件监听相关程序初始化完成，否则，回出现对RocketMQ的消息进行消费后立即发布消息到达的事件，
			 * 然而此事件的监听程序还未初始化，从而造成消息的丢失
			 */
			Executors.newScheduledThreadPool(1).schedule(new Thread() {
				public void run() {
					try {

						/*
						 * Consumer对象在使用之前必须要调用start初始化，初始化一次即可<br>
						 */
						consumer.start();

						LOG.info("Zbus Consumer Started ! groupName:[%s],namesrvAddr:[%s],instanceName:[%s].",
								properties.getConsumerGroup(), properties.getNamesrvAddr(), properties.getInstanceName());
						
						/**
						 * 应用退出时，要调用shutdown来清理资源，关闭网络连接，从RocketMQ服务器上注销自己
						 * 注意：我们建议应用在JBOSS、Tomcat等容器的退出钩子里调用shutdown方法
						 */
						Runtime.getRuntime().addShutdownHook(new ZbusConsumerShutdownHook(consumer));

					} catch (Exception e) {
						LOG.error(String.format("RocketMQ MQPushConsumer Start failure ：%s", e.getMessage(), e));
					}
				}
			}, properties.getDelayStartSeconds(), TimeUnit.SECONDS);

			return consumer;

		} catch (Exception e) {
			throw new ZbusException(e);
		}
	}
	
	@Bean
	public ZbusConsumerTemplate rocketmqConsumerTemplate(Consumer consumer) throws MQClientException {
		return new ZbusConsumerTemplate(consumer);
	}

}
