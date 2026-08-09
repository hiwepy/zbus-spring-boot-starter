package io.zbus.spring.boot;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for Zbus properties classes.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 */
class ZbusPropertiesTest {

    @Test
    void zbusPropertiesDefaultValues() {
        ZbusProperties props = new ZbusProperties();
        assertThat(props.isEnabled()).isFalse();
        assertThat(props.getConfigurationStrategy()).isNull();
        assertThat(props.getCasServerLoginUrl()).isNull();
        assertThat(props.getCasServerUrlPrefix()).isNull();
        assertThat(props.isEagerlyCreateSessions()).isTrue();
        assertThat(props.isAcceptAnyProxy()).isTrue();
        assertThat(props.getArtifactParameterName()).isNull();
        assertThat(props.isArtifactParameterOverPost()).isFalse();
        assertThat(props.getCipherAlgorithm()).isNull();
        assertThat(props.isEncodeServiceUrl()).isTrue();
        assertThat(props.getEncoding()).isNull();
        assertThat(props.isExceptionOnValidationFailure()).isTrue();
        assertThat(props.isGateway()).isFalse();
        assertThat(props.getHostnameVerifier()).isNull();
        assertThat(props.isIgnoreCase()).isFalse();
        assertThat(props.getIgnorePattern()).isNull();
        assertThat(props.isIgnoreInitConfiguration()).isFalse();
        assertThat(props.getLogoutParameterName()).isNull();
        assertThat(props.getMillisBetweenCleanUps()).isEqualTo(60000L);
        assertThat(props.getProxyCallbackUrl()).isNull();
        assertThat(props.getProxyReceptorUrl()).isNull();
        assertThat(props.isRedirectAfterValidation()).isTrue();
        assertThat(props.isRenew()).isFalse();
        assertThat(props.getRelayStateParameterName()).isNull();
        assertThat(props.getRoleAttribute()).isNull();
        assertThat(props.getSecretKey()).isNull();
        assertThat(props.getServiceUrl()).isNull();
        assertThat(props.getServerName()).isNull();
        assertThat(props.getTicketValidatorClass()).isNull();
        assertThat(props.getTolerance()).isEqualTo(1000L);
        assertThat(props.isUseSession()).isTrue();
    }

    @Test
    void zbusPropertiesSettersAndGetters() {
        ZbusProperties props = new ZbusProperties();
        props.setEnabled(true);
        props.setConfigurationStrategy("DEFAULT");
        props.setCasServerLoginUrl("http://localhost:8443/cas/login");
        props.setCasServerUrlPrefix("http://localhost:8443/cas");
        props.setEagerlyCreateSessions(false);
        props.setAcceptAnyProxy(false);
        props.setArtifactParameterName("ticket");
        props.setArtifactParameterOverPost(true);
        props.setCipherAlgorithm("AES");
        props.setEncodeServiceUrl(false);
        props.setEncoding("UTF-8");
        props.setExceptionOnValidationFailure(false);
        props.setGateway(true);
        props.setHostnameVerifier("test");
        props.setIgnoreCase(true);
        props.setIgnorePattern("/ignore");
        props.setIgnoreInitConfiguration(true);
        props.setLogoutParameterName("logout");
        props.setMillisBetweenCleanUps(30000L);
        props.setProxyCallbackUrl("http://callback");
        props.setProxyReceptorUrl("http://receptor");
        props.setRedirectAfterValidation(false);
        props.setRenew(true);
        props.setRelayStateParameterName("relay");
        props.setRoleAttribute("role");
        props.setSecretKey("secret");
        props.setServiceUrl("http://service");
        props.setServerName("http://server");
        props.setTicketValidatorClass("validator");
        props.setTolerance(5000L);
        props.setUseSession(false);

        assertThat(props.isEnabled()).isTrue();
        assertThat(props.getConfigurationStrategy()).isEqualTo("DEFAULT");
        assertThat(props.getCasServerLoginUrl()).isEqualTo("http://localhost:8443/cas/login");
        assertThat(props.getCasServerUrlPrefix()).isEqualTo("http://localhost:8443/cas");
        assertThat(props.isEagerlyCreateSessions()).isFalse();
        assertThat(props.isAcceptAnyProxy()).isFalse();
        assertThat(props.getArtifactParameterName()).isEqualTo("ticket");
        assertThat(props.isArtifactParameterOverPost()).isTrue();
        assertThat(props.getCipherAlgorithm()).isEqualTo("AES");
        assertThat(props.isEncodeServiceUrl()).isFalse();
        assertThat(props.getEncoding()).isEqualTo("UTF-8");
        assertThat(props.isExceptionOnValidationFailure()).isFalse();
        assertThat(props.isGateway()).isTrue();
        assertThat(props.getHostnameVerifier()).isEqualTo("test");
        assertThat(props.isIgnoreCase()).isTrue();
        assertThat(props.getIgnorePattern()).isEqualTo("/ignore");
        assertThat(props.isIgnoreInitConfiguration()).isTrue();
        assertThat(props.getLogoutParameterName()).isEqualTo("logout");
        assertThat(props.getMillisBetweenCleanUps()).isEqualTo(30000L);
        assertThat(props.getProxyCallbackUrl()).isEqualTo("http://callback");
        assertThat(props.getProxyReceptorUrl()).isEqualTo("http://receptor");
        assertThat(props.isRedirectAfterValidation()).isFalse();
        assertThat(props.isRenew()).isTrue();
        assertThat(props.getRelayStateParameterName()).isEqualTo("relay");
        assertThat(props.getRoleAttribute()).isEqualTo("role");
        assertThat(props.getSecretKey()).isEqualTo("secret");
        assertThat(props.getServiceUrl()).isEqualTo("http://service");
        assertThat(props.getServerName()).isEqualTo("http://server");
        assertThat(props.getTicketValidatorClass()).isEqualTo("validator");
        assertThat(props.getTolerance()).isEqualTo(5000L);
        assertThat(props.isUseSession()).isFalse();
    }

    @Test
    void zbusConsumerPropertiesDefaultValues() {
        ZbusConsumerProperties props = new ZbusConsumerProperties();
        assertThat(props.isEnabled()).isFalse();
        assertThat(props.getConsumerGroup()).isNull();
        assertThat(props.getMessageModel()).isEqualTo("CLUSTERING");
        assertThat(props.getSubscription()).isNotNull().isEmpty();
        assertThat(props.getConsumeThreadMin()).isEqualTo(20);
        assertThat(props.getConsumeThreadMax()).isEqualTo(64);
        assertThat(props.getAdjustThreadPoolNumsThreshold()).isEqualTo(100000L);
        assertThat(props.getConsumeConcurrentlyMaxSpan()).isEqualTo(2000);
        assertThat(props.getPullThresholdForQueue()).isEqualTo(1000);
        assertThat(props.getPullInterval()).isEqualTo(0L);
        assertThat(props.getConsumeMessageBatchMaxSize()).isEqualTo(1);
        assertThat(props.getPullBatchSize()).isEqualTo(32);
        assertThat(props.isPostSubscriptionWhenPull()).isFalse();
        assertThat(props.getMaxReconsumeTimes()).isEqualTo(-1);
        assertThat(props.getSuspendCurrentQueueTimeMillis()).isEqualTo(1000L);
        assertThat(props.getConsumeTimeout()).isEqualTo(15L);
        assertThat(props.getRetryTimesWhenConsumeFailed()).isEqualTo(3);
        assertThat(props.getDelayLevelWhenNextConsume()).isEqualTo(0);
        assertThat(props.getDelayStartSeconds()).isEqualTo(10);
    }

    @Test
    void zbusProducerPropertiesDefaultValues() {
        ZbusProducerProperties props = new ZbusProducerProperties();
        assertThat(props.isEnabled()).isFalse();
        assertThat(props.getProducerGroup()).isEqualTo("ProducerGroup");
        assertThat(props.getDefaultTopicQueueNums()).isEqualTo(4);
        assertThat(props.getSendMsgTimeout()).isEqualTo(3000);
        assertThat(props.isSendLatencyFaultEnable()).isFalse();
        assertThat(props.getCompressMsgBodyOverHowmuch()).isEqualTo(4096);
        assertThat(props.getRetryTimesWhenSendFailed()).isEqualTo(2);
        assertThat(props.getRetryTimesWhenSendAsyncFailed()).isEqualTo(2);
        assertThat(props.isRetryAnotherBrokerWhenNotStoreOK()).isFalse();
        assertThat(props.getMaxMessageSize()).isEqualTo(4194304);
        assertThat(props.getLatencyMax()).isNull();
        assertThat(props.getNotAvailableDuration()).isNull();
        assertThat(props.isTransaction()).isFalse();
        assertThat(props.getCheckThreadPoolMinSize()).isEqualTo(1);
        assertThat(props.getCheckThreadPoolMaxSize()).isEqualTo(1);
        assertThat(props.getCheckRequestHoldMax()).isEqualTo(2000);
    }

    @Test
    void zbusServicePropertiesDefaultValues() {
        ZbusServiceProperties props = new ZbusServiceProperties();
        assertThat(props.isEnabled()).isFalse();
        assertThat(props.getServiceName()).isNull();
        assertThat(props.getConnectionCount()).isEqualTo(0);
        assertThat(props.isResponseTypeInfo()).isFalse();
        assertThat(props.isMethodPage()).isFalse();
        assertThat(props.isStackTrace()).isFalse();
        assertThat(props.isDeclareOnMissing()).isTrue();
        assertThat(props.getModules()).isNotNull().isEmpty();
    }

    @Test
    void zbusConsumerEventPropertiesDefaultValues() {
        ZbusConsumerEventProperties props = new ZbusConsumerEventProperties();
        assertThat(props.getDefinitions()).isNull();
        assertThat(props.getDefinitionMap()).isNotNull().isEmpty();
    }
}
