package io.zbus.rpc.bootstrap.mq;

/**
 * Stub for zbus ServiceBootstrap.
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class ServiceBootstrap {

    public ServiceBootstrap() {}

    /**
     * service Name.
     *
     * @param name the name
     * @return the result
     */
    public ServiceBootstrap serviceName(String name) { return this; }
    /**
     * host.
     *
     * @param host the host
     * @return the result
     */
    public ServiceBootstrap host(String host) { return this; }
    /**
     * add Module.
     *
     * @param module the module
     * @param clazz the clazz
     * @return the result
     */
    public ServiceBootstrap addModule(Object module, Class<?> clazz) { return this; }
    /**
     * service Address.
     *
     * @param address the address
     * @return the result
     */
    public ServiceBootstrap serviceAddress(String address) { return this; }
    /**
     * port.
     *
     * @param port the port
     * @return the result
     */
    public ServiceBootstrap port(int port) { return this; }
    /**
     * auto Discover.
     *
     * @param autoDiscover the auto discover
     * @return the result
     */
    public ServiceBootstrap autoDiscover(boolean autoDiscover) { return this; }
    /**
     * SSL.
     *
     * @param cert the cert
     * @param key the key
     * @return the result
     */
    public ServiceBootstrap ssl(String cert, String key) { return this; }
    /**
     * service Token.
     *
     * @param token the token
     * @return the result
     */
    public ServiceBootstrap serviceToken(String token) { return this; }
    /**
     * start.
     *
     */
    public void start() {}
    /**
     * close.
     *
     */
    public void close() {}
}
