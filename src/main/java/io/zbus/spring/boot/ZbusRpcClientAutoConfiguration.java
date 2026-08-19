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
 * Spring Boot auto-configuration for the Zbus RPC client.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
@Configuration
@ConditionalOnClass({ ServiceBootstrap.class })
@ConditionalOnProperty(prefix = ZbusServiceProperties.PREFIX, value = "enabled", havingValue = "true")
@AutoConfigureAfter(ZbusPushEventHandlerAutoConfiguration.class)
@EnableConfigurationProperties({ ZbusServiceProperties.class })
public class ZbusRpcClientAutoConfiguration implements ApplicationContextAware {

    private static final Logger LOG = LoggerFactory.getLogger(ZbusRpcClientAutoConfiguration.class);
    private ApplicationContext applicationContext;

    @Bean
    @ConditionalOnMissingBean
    /**
     * client Bootstrap.
     *
     * @return the result
     * @throws Exception if an error occurs
     */
    public SpringClientBootstrap clientBootstrap() throws Exception {
        SpringClientBootstrap b = new SpringClientBootstrap();
        b.serviceAddress("localhost:15555")
            .serviceToken("myrpc_service");
        return b;
    }

    @Bean
    @ConditionalOnMissingBean
    /**
     * RPC Invoker.
     *
     * @param b the b
     * @return the result
     * @throws Exception if an error occurs
     */
    public RpcInvoker rpcInvoker(ClientBootstrap b) throws Exception {
        return b.invoker();
    }

    @Override
    /**
     * Sets the application context.
     *
     * @param applicationContext the application context
     * @throws BeansException if an error occurs
     */
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * Returns the application context.
     *
     * @return the application context
     */
    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
