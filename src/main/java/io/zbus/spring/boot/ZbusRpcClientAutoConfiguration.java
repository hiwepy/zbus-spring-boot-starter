package io.zbus.spring.boot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.zbus.rpc.RpcInvoker;
import io.zbus.rpc.bootstrap.http.ClientBootstrap;
import io.zbus.rpc.bootstrap.http.SpringClientBootstrap;
import io.zbus.rpc.bootstrap.mq.ServiceBootstrap;

/**
 * 
 * @className	： ZbusRpcClientAutoConfiguration
 * @description	： RPC客户端
 * @author 		： <a href="https://github.com/vindell">vindell</a>
 * @date		： 2018年1月28日 下午9:58:25
 * @version 	V1.0
 */
@Configuration
@ConditionalOnClass({ ServiceBootstrap.class })
@ConditionalOnProperty(prefix = ZbusServiceProperties.PREFIX, value = "enabled", havingValue = "true")
@AutoConfigureAfter(ZbusPushEventHandlerAutoConfiguration.class)
@EnableConfigurationProperties({ ZbusServiceProperties.class })
public class ZbusRpcClientAutoConfiguration  implements ApplicationContextAware {

	private static final Logger LOG = LoggerFactory.getLogger(ZbusRpcClientAutoConfiguration.class);
	private ApplicationContext applicationContext;
	
	/**
	 * @throws Exception 
	 */
	@Bean
	@ConditionalOnMissingBean
	public SpringClientBootstrap clientBootstrap() throws Exception {
		SpringClientBootstrap b = new SpringClientBootstrap(); 
		b.serviceAddress("localhost:15555")
			.serviceToken("myrpc_service"); 
		return b;
	}
	
	/**
	 * 
	 * @description	： //可以通过该RpcInvoker调用底层同步、异步各种API能力
	 * @author 		： <a href="https://github.com/vindell">vindell</a>
	 * @date 		：2018年1月28日 下午10:02:11
	 * @param b
	 * @return
	 * @throws Exception
	 */
	@Bean
	@ConditionalOnMissingBean
	public RpcInvoker rpcInvoker(ClientBootstrap b) throws Exception {
		return b.invoker(); 
	}
	
	@Bean
	public ZbusPullConsumerTemplate rocketmqConsumerTemplate(MQPullConsumer consumer) throws MQClientException {
		return new ZbusPullConsumerTemplate(consumer);
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}
}
