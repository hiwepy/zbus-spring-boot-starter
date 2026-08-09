package io.zbus.spring.boot;

import io.zbus.rpc.bootstrap.mq.ServiceBootstrap;
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

/**
 * Spring Boot auto-configuration for the Zbus RPC service.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
@Configuration
@ConditionalOnClass({ ServiceBootstrap.class })
@ConditionalOnProperty(prefix = ZbusServiceProperties.PREFIX, value = "enabled", havingValue = "true")
@AutoConfigureAfter(ZbusPushEventHandlerAutoConfiguration.class)
@EnableConfigurationProperties({ ZbusServiceProperties.class })
public class ZbusRpcServiceAutoConfiguration implements ApplicationContextAware {

    private static final Logger LOG = LoggerFactory.getLogger(ZbusRpcServiceAutoConfiguration.class);
    private ApplicationContext applicationContext;

    @Bean
    @ConditionalOnMissingBean
    public ServiceBootstrap serviceBootstrap(ZbusServiceProperties properties) throws Exception {
        ServiceBootstrap bootstrap = new ServiceBootstrap();
        if (properties.getServiceName() != null) {
            bootstrap.serviceName(properties.getServiceName());
        }
        bootstrap.start();
        return bootstrap;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
