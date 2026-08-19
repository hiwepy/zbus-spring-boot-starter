/*
 * Copyright (c) 2018, hiwepy (https://github.com/hiwepy).
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
package io.zbus.spring.boot;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Root configuration properties for the Zbus Spring Boot starter.
 * <p>
 * Bound to the {@code cas.*} namespace. Holds the common Zbus broker and
 * CAS-style client options shared across the producer, consumer and RPC
 * auto-configurations.
 * </p>
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
@ConfigurationProperties(ZbusProperties.PREFIX)
public class ZbusProperties {

	public static final String PREFIX = "cas";

	/**
	 * Enable Cas.
	 */
	private boolean enabled = false;
	/**
	 * DEFAULT,JNDI,WEB_XML,PROPERTY_FILE,SYSTEM_PROPERTIES
	 */
	private String configurationStrategy;

	/**
	 * Defines the location of the CAS server login URL, i.e.
	 * https://localhost:8443/cas/login
	 */
	private String casServerLoginUrl;
	/** The prefix url of the CAS server. */
	private String casServerUrlPrefix;

	private boolean eagerlyCreateSessions = true;

	private boolean acceptAnyProxy = true;
	private String allowedProxyChains;
	/**
	 * Specifies the name of the request parameter on where to find the artifact
	 * (i.e. ticket).
	 */
	private String artifactParameterName;
	private boolean artifactParameterOverPost = false;
	private String[] assertionThreadLocalFilterUrlPatterns = new String[] { "/*" };
	private String authenticationRedirectStrategyClass;
	private String[] authenticationFilterUrlPatterns = new String[] { "/*" };

	private String cipherAlgorithm;
	/**
	 * Sets where response.encodeUrl should be called on service urls when
	 * constructed.
	 */
	private boolean encodeServiceUrl = true;

	private String encoding;

	private boolean exceptionOnValidationFailure = true;

	/**
	 * Whether to send the gateway request or not. Valid values are eithertrue/false
	 * (or no value at all). Note that renew cannot be specified as local
	 * init-paramsetting.
	 */
	private boolean gateway = false;
	private String gatewayStorageClass;

	private String hostnameVerifier;
	private String hostnameVerifierConfig;

	private boolean ignoreCase = false;
	private String ignorePattern;

	private boolean ignoreInitConfiguration = false;
	private String logoutParameterName;

	private long millisBetweenCleanUps = 60000L;
	private String proxyCallbackUrl;
	private String proxyReceptorUrl;
	private String proxyGrantingTicketStorageClass;
	private String[] requestWrapperFilterUrlPatterns = new String[] { "/*" };
	private boolean redirectAfterValidation = true;
	/**
	 * Whether to send the renew request or not. Valid values are eithertrue/false
	 * (or no value at all). Note that renew cannot be specified as local
	 * init-paramsetting.
	 */
	private boolean renew = false;
	/** Name of parameter containing the state of the CAS server webflow. */
	private String relayStateParameterName;
	private String roleAttribute;
	
	private String secretKey;

	/** The exact url of the service. */
	private String serviceUrl;
	/**
     * The name of the server.  Should be in the following format: {protocol}:{hostName}:{port}.
     * Standard ports can be excluded. 
     */
	private String serverName;
	private String[] signOutFilterUrlPatterns = new String[] { "/*" };
	private String sslConfigFile;

	private String[] ticketValidationFilterUrlPatterns = new String[] { "/*" };
	private String ticketValidatorClass;
	private long tolerance = 1000L;

	private boolean useSession = true;

	/**
	 * Returns the enabled.
	 *
	 * @return the enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * Sets the enabled.
	 *
	 * @param enabled the enabled
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * Returns the configuration strategy.
	 *
	 * @return the configuration strategy
	 */
	public String getConfigurationStrategy() {
		return configurationStrategy;
	}

	/**
	 * Sets the configuration strategy.
	 *
	 * @param configurationStrategy the configuration strategy
	 */
	public void setConfigurationStrategy(String configurationStrategy) {
		this.configurationStrategy = configurationStrategy;
	}

	/**
	 * Returns the cas server login url.
	 *
	 * @return the cas server login url
	 */
	public String getCasServerLoginUrl() {
		return casServerLoginUrl;
	}

	/**
	 * Sets the cas server login url.
	 *
	 * @param casServerLoginUrl the cas server login url
	 */
	public void setCasServerLoginUrl(String casServerLoginUrl) {
		this.casServerLoginUrl = casServerLoginUrl;
	}

	/**
	 * Returns the cas server url prefix.
	 *
	 * @return the cas server url prefix
	 */
	public String getCasServerUrlPrefix() {
		return casServerUrlPrefix;
	}

	/**
	 * Sets the cas server url prefix.
	 *
	 * @param casServerUrlPrefix the cas server url prefix
	 */
	public void setCasServerUrlPrefix(String casServerUrlPrefix) {
		this.casServerUrlPrefix = casServerUrlPrefix;
	}

	/**
	 * Returns the eagerly create sessions.
	 *
	 * @return the eagerly create sessions
	 */
	public boolean isEagerlyCreateSessions() {
		return eagerlyCreateSessions;
	}

	/**
	 * Sets the eagerly create sessions.
	 *
	 * @param eagerlyCreateSessions the eagerly create sessions
	 */
	public void setEagerlyCreateSessions(boolean eagerlyCreateSessions) {
		this.eagerlyCreateSessions = eagerlyCreateSessions;
	}

	/**
	 * Returns the accept any proxy.
	 *
	 * @return the accept any proxy
	 */
	public boolean isAcceptAnyProxy() {
		return acceptAnyProxy;
	}

	/**
	 * Sets the accept any proxy.
	 *
	 * @param acceptAnyProxy the accept any proxy
	 */
	public void setAcceptAnyProxy(boolean acceptAnyProxy) {
		this.acceptAnyProxy = acceptAnyProxy;
	}

	/**
	 * Returns the allowed proxy chains.
	 *
	 * @return the allowed proxy chains
	 */
	public String getAllowedProxyChains() {
		return allowedProxyChains;
	}

	/**
	 * Sets the allowed proxy chains.
	 *
	 * @param allowedProxyChains the allowed proxy chains
	 */
	public void setAllowedProxyChains(String allowedProxyChains) {
		this.allowedProxyChains = allowedProxyChains;
	}

	/**
	 * Returns the artifact parameter name.
	 *
	 * @return the artifact parameter name
	 */
	public String getArtifactParameterName() {
		return artifactParameterName;
	}

	/**
	 * Sets the artifact parameter name.
	 *
	 * @param artifactParameterName the artifact parameter name
	 */
	public void setArtifactParameterName(String artifactParameterName) {
		this.artifactParameterName = artifactParameterName;
	}

	/**
	 * Returns the artifact parameter over post.
	 *
	 * @return the artifact parameter over post
	 */
	public boolean isArtifactParameterOverPost() {
		return artifactParameterOverPost;
	}

	/**
	 * Sets the artifact parameter over post.
	 *
	 * @param artifactParameterOverPost the artifact parameter over post
	 */
	public void setArtifactParameterOverPost(boolean artifactParameterOverPost) {
		this.artifactParameterOverPost = artifactParameterOverPost;
	}

	/**
	 * Returns the assertion thread local filter url patterns.
	 *
	 * @return the assertion thread local filter url patterns
	 */
	public String[] getAssertionThreadLocalFilterUrlPatterns() {
		return assertionThreadLocalFilterUrlPatterns;
	}

	/**
	 * Sets the assertion thread local filter url patterns.
	 *
	 * @param assertionThreadLocalFilterUrlPatterns the assertion thread local filter url patterns
	 */
	public void setAssertionThreadLocalFilterUrlPatterns(String[] assertionThreadLocalFilterUrlPatterns) {
		this.assertionThreadLocalFilterUrlPatterns = assertionThreadLocalFilterUrlPatterns;
	}

	/**
	 * Returns the authentication redirect strategy class.
	 *
	 * @return the authentication redirect strategy class
	 */
	public String getAuthenticationRedirectStrategyClass() {
		return authenticationRedirectStrategyClass;
	}

	/**
	 * Sets the authentication redirect strategy class.
	 *
	 * @param authenticationRedirectStrategyClass the authentication redirect strategy class
	 */
	public void setAuthenticationRedirectStrategyClass(String authenticationRedirectStrategyClass) {
		this.authenticationRedirectStrategyClass = authenticationRedirectStrategyClass;
	}

	/**
	 * Returns the authentication filter url patterns.
	 *
	 * @return the authentication filter url patterns
	 */
	public String[] getAuthenticationFilterUrlPatterns() {
		return authenticationFilterUrlPatterns;
	}

	/**
	 * Sets the authentication filter url patterns.
	 *
	 * @param authenticationFilterUrlPatterns the authentication filter url patterns
	 */
	public void setAuthenticationFilterUrlPatterns(String[] authenticationFilterUrlPatterns) {
		this.authenticationFilterUrlPatterns = authenticationFilterUrlPatterns;
	}

	/**
	 * Returns the cipher algorithm.
	 *
	 * @return the cipher algorithm
	 */
	public String getCipherAlgorithm() {
		return cipherAlgorithm;
	}

	/**
	 * Sets the cipher algorithm.
	 *
	 * @param cipherAlgorithm the cipher algorithm
	 */
	public void setCipherAlgorithm(String cipherAlgorithm) {
		this.cipherAlgorithm = cipherAlgorithm;
	}

	/**
	 * Returns the encode service url.
	 *
	 * @return the encode service url
	 */
	public boolean isEncodeServiceUrl() {
		return encodeServiceUrl;
	}

	/**
	 * Sets the encode service url.
	 *
	 * @param encodeServiceUrl the encode service url
	 */
	public void setEncodeServiceUrl(boolean encodeServiceUrl) {
		this.encodeServiceUrl = encodeServiceUrl;
	}

	/**
	 * Returns the encoding.
	 *
	 * @return the encoding
	 */
	public String getEncoding() {
		return encoding;
	}

	/**
	 * Sets the encoding.
	 *
	 * @param encoding the encoding
	 */
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	/**
	 * Returns the exception on validation failure.
	 *
	 * @return the exception on validation failure
	 */
	public boolean isExceptionOnValidationFailure() {
		return exceptionOnValidationFailure;
	}

	/**
	 * Sets the exception on validation failure.
	 *
	 * @param exceptionOnValidationFailure the exception on validation failure
	 */
	public void setExceptionOnValidationFailure(boolean exceptionOnValidationFailure) {
		this.exceptionOnValidationFailure = exceptionOnValidationFailure;
	}

	/**
	 * Returns the gateway.
	 *
	 * @return the gateway
	 */
	public boolean isGateway() {
		return gateway;
	}

	/**
	 * Sets the gateway.
	 *
	 * @param gateway the gateway
	 */
	public void setGateway(boolean gateway) {
		this.gateway = gateway;
	}

	/**
	 * Returns the gateway storage class.
	 *
	 * @return the gateway storage class
	 */
	public String getGatewayStorageClass() {
		return gatewayStorageClass;
	}

	/**
	 * Sets the gateway storage class.
	 *
	 * @param gatewayStorageClass the gateway storage class
	 */
	public void setGatewayStorageClass(String gatewayStorageClass) {
		this.gatewayStorageClass = gatewayStorageClass;
	}

	/**
	 * Returns the hostname verifier.
	 *
	 * @return the hostname verifier
	 */
	public String getHostnameVerifier() {
		return hostnameVerifier;
	}

	/**
	 * Sets the hostname verifier.
	 *
	 * @param hostnameVerifier the hostname verifier
	 */
	public void setHostnameVerifier(String hostnameVerifier) {
		this.hostnameVerifier = hostnameVerifier;
	}

	/**
	 * Returns the hostname verifier config.
	 *
	 * @return the hostname verifier config
	 */
	public String getHostnameVerifierConfig() {
		return hostnameVerifierConfig;
	}

	/**
	 * Sets the hostname verifier config.
	 *
	 * @param hostnameVerifierConfig the hostname verifier config
	 */
	public void setHostnameVerifierConfig(String hostnameVerifierConfig) {
		this.hostnameVerifierConfig = hostnameVerifierConfig;
	}

	/**
	 * Returns the ignore case.
	 *
	 * @return the ignore case
	 */
	public boolean isIgnoreCase() {
		return ignoreCase;
	}

	/**
	 * Sets the ignore case.
	 *
	 * @param ignoreCase the ignore case
	 */
	public void setIgnoreCase(boolean ignoreCase) {
		this.ignoreCase = ignoreCase;
	}

	/**
	 * Returns the ignore pattern.
	 *
	 * @return the ignore pattern
	 */
	public String getIgnorePattern() {
		return ignorePattern;
	}

	/**
	 * Sets the ignore pattern.
	 *
	 * @param ignorePattern the ignore pattern
	 */
	public void setIgnorePattern(String ignorePattern) {
		this.ignorePattern = ignorePattern;
	}

	/**
	 * Returns the ignore init configuration.
	 *
	 * @return the ignore init configuration
	 */
	public boolean isIgnoreInitConfiguration() {
		return ignoreInitConfiguration;
	}

	/**
	 * Sets the ignore init configuration.
	 *
	 * @param ignoreInitConfiguration the ignore init configuration
	 */
	public void setIgnoreInitConfiguration(boolean ignoreInitConfiguration) {
		this.ignoreInitConfiguration = ignoreInitConfiguration;
	}

	/**
	 * Returns the logout parameter name.
	 *
	 * @return the logout parameter name
	 */
	public String getLogoutParameterName() {
		return logoutParameterName;
	}

	/**
	 * Sets the logout parameter name.
	 *
	 * @param logoutParameterName the logout parameter name
	 */
	public void setLogoutParameterName(String logoutParameterName) {
		this.logoutParameterName = logoutParameterName;
	}

	/**
	 * Returns the millis between clean ups.
	 *
	 * @return the millis between clean ups
	 */
	public long getMillisBetweenCleanUps() {
		return millisBetweenCleanUps;
	}

	/**
	 * Sets the millis between clean ups.
	 *
	 * @param millisBetweenCleanUps the millis between clean ups
	 */
	public void setMillisBetweenCleanUps(long millisBetweenCleanUps) {
		this.millisBetweenCleanUps = millisBetweenCleanUps;
	}

	/**
	 * Returns the proxy callback url.
	 *
	 * @return the proxy callback url
	 */
	public String getProxyCallbackUrl() {
		return proxyCallbackUrl;
	}

	/**
	 * Sets the proxy callback url.
	 *
	 * @param proxyCallbackUrl the proxy callback url
	 */
	public void setProxyCallbackUrl(String proxyCallbackUrl) {
		this.proxyCallbackUrl = proxyCallbackUrl;
	}

	/**
	 * Returns the proxy receptor url.
	 *
	 * @return the proxy receptor url
	 */
	public String getProxyReceptorUrl() {
		return proxyReceptorUrl;
	}

	/**
	 * Sets the proxy receptor url.
	 *
	 * @param proxyReceptorUrl the proxy receptor url
	 */
	public void setProxyReceptorUrl(String proxyReceptorUrl) {
		this.proxyReceptorUrl = proxyReceptorUrl;
	}

	/**
	 * Returns the proxy granting ticket storage class.
	 *
	 * @return the proxy granting ticket storage class
	 */
	public String getProxyGrantingTicketStorageClass() {
		return proxyGrantingTicketStorageClass;
	}

	/**
	 * Sets the proxy granting ticket storage class.
	 *
	 * @param proxyGrantingTicketStorageClass the proxy granting ticket storage class
	 */
	public void setProxyGrantingTicketStorageClass(String proxyGrantingTicketStorageClass) {
		this.proxyGrantingTicketStorageClass = proxyGrantingTicketStorageClass;
	}

	/**
	 * Returns the request wrapper filter url patterns.
	 *
	 * @return the request wrapper filter url patterns
	 */
	public String[] getRequestWrapperFilterUrlPatterns() {
		return requestWrapperFilterUrlPatterns;
	}

	/**
	 * Sets the request wrapper filter url patterns.
	 *
	 * @param requestWrapperFilterUrlPatterns the request wrapper filter url patterns
	 */
	public void setRequestWrapperFilterUrlPatterns(String[] requestWrapperFilterUrlPatterns) {
		this.requestWrapperFilterUrlPatterns = requestWrapperFilterUrlPatterns;
	}

	/**
	 * Returns the redirect after validation.
	 *
	 * @return the redirect after validation
	 */
	public boolean isRedirectAfterValidation() {
		return redirectAfterValidation;
	}

	/**
	 * Sets the redirect after validation.
	 *
	 * @param redirectAfterValidation the redirect after validation
	 */
	public void setRedirectAfterValidation(boolean redirectAfterValidation) {
		this.redirectAfterValidation = redirectAfterValidation;
	}

	/**
	 * Returns the renew.
	 *
	 * @return the renew
	 */
	public boolean isRenew() {
		return renew;
	}

	/**
	 * Sets the renew.
	 *
	 * @param renew the renew
	 */
	public void setRenew(boolean renew) {
		this.renew = renew;
	}

	/**
	 * Returns the relay state parameter name.
	 *
	 * @return the relay state parameter name
	 */
	public String getRelayStateParameterName() {
		return relayStateParameterName;
	}

	/**
	 * Sets the relay state parameter name.
	 *
	 * @param relayStateParameterName the relay state parameter name
	 */
	public void setRelayStateParameterName(String relayStateParameterName) {
		this.relayStateParameterName = relayStateParameterName;
	}

	/**
	 * Returns the role attribute.
	 *
	 * @return the role attribute
	 */
	public String getRoleAttribute() {
		return roleAttribute;
	}

	/**
	 * Sets the role attribute.
	 *
	 * @param roleAttribute the role attribute
	 */
	public void setRoleAttribute(String roleAttribute) {
		this.roleAttribute = roleAttribute;
	}

	/**
	 * Returns the secret key.
	 *
	 * @return the secret key
	 */
	public String getSecretKey() {
		return secretKey;
	}

	/**
	 * Sets the secret key.
	 *
	 * @param secretKey the secret key
	 */
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	/**
	 * Returns the service url.
	 *
	 * @return the service url
	 */
	public String getServiceUrl() {
		return serviceUrl;
	}

	/**
	 * Sets the service url.
	 *
	 * @param serviceUrl the service url
	 */
	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}

	/**
	 * Returns the server name.
	 *
	 * @return the server name
	 */
	public String getServerName() {
		return serverName;
	}

	/**
	 * Sets the server name.
	 *
	 * @param serverName the server name
	 */
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	/**
	 * Returns the sign out filter url patterns.
	 *
	 * @return the sign out filter url patterns
	 */
	public String[] getSignOutFilterUrlPatterns() {
		return signOutFilterUrlPatterns;
	}

	/**
	 * Sets the sign out filter url patterns.
	 *
	 * @param signOutFilterUrlPatterns the sign out filter url patterns
	 */
	public void setSignOutFilterUrlPatterns(String[] signOutFilterUrlPatterns) {
		this.signOutFilterUrlPatterns = signOutFilterUrlPatterns;
	}

	/**
	 * Returns the ssl config file.
	 *
	 * @return the ssl config file
	 */
	public String getSslConfigFile() {
		return sslConfigFile;
	}

	/**
	 * Sets the ssl config file.
	 *
	 * @param sslConfigFile the ssl config file
	 */
	public void setSslConfigFile(String sslConfigFile) {
		this.sslConfigFile = sslConfigFile;
	}

	/**
	 * Returns the ticket validation filter url patterns.
	 *
	 * @return the ticket validation filter url patterns
	 */
	public String[] getTicketValidationFilterUrlPatterns() {
		return ticketValidationFilterUrlPatterns;
	}

	/**
	 * Sets the ticket validation filter url patterns.
	 *
	 * @param ticketValidationFilterUrlPatterns the ticket validation filter url patterns
	 */
	public void setTicketValidationFilterUrlPatterns(String[] ticketValidationFilterUrlPatterns) {
		this.ticketValidationFilterUrlPatterns = ticketValidationFilterUrlPatterns;
	}

	/**
	 * Returns the ticket validator class.
	 *
	 * @return the ticket validator class
	 */
	public String getTicketValidatorClass() {
		return ticketValidatorClass;
	}

	/**
	 * Sets the ticket validator class.
	 *
	 * @param ticketValidatorClass the ticket validator class
	 */
	public void setTicketValidatorClass(String ticketValidatorClass) {
		this.ticketValidatorClass = ticketValidatorClass;
	}

	/**
	 * Returns the tolerance.
	 *
	 * @return the tolerance
	 */
	public long getTolerance() {
		return tolerance;
	}

	/**
	 * Sets the tolerance.
	 *
	 * @param tolerance the tolerance
	 */
	public void setTolerance(long tolerance) {
		this.tolerance = tolerance;
	}

	/**
	 * Returns the use session.
	 *
	 * @return the use session
	 */
	public boolean isUseSession() {
		return useSession;
	}

	/**
	 * Sets the use session.
	 *
	 * @param useSession the use session
	 */
	public void setUseSession(boolean useSession) {
		this.useSession = useSession;
	}

}
