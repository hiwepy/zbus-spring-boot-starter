package io.zbus.rpc.bootstrap.mq;

/**
 * Stub for zbus ServiceBootstrap.
 */
public class ServiceBootstrap {

    public ServiceBootstrap() {}

    public ServiceBootstrap serviceName(String name) { return this; }
    public ServiceBootstrap host(String host) { return this; }
    public ServiceBootstrap addModule(Object module, Class<?> clazz) { return this; }
    public ServiceBootstrap serviceAddress(String address) { return this; }
    public ServiceBootstrap port(int port) { return this; }
    public ServiceBootstrap autoDiscover(boolean autoDiscover) { return this; }
    public ServiceBootstrap ssl(String cert, String key) { return this; }
    public ServiceBootstrap serviceToken(String token) { return this; }
    public void start() {}
    public void close() {}
}
