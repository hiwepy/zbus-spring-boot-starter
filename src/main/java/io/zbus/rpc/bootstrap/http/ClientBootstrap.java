package io.zbus.rpc.bootstrap.http;

import io.zbus.rpc.RpcInvoker;

/**
 * Stub for zbus ClientBootstrap.
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class ClientBootstrap {

    public ClientBootstrap() {}

    /**
     * service Address.
     *
     * @param address the address
     * @return the result
     */
    public ClientBootstrap serviceAddress(String address) { return this; }
    /**
     * service Token.
     *
     * @param token the token
     * @return the result
     */
    public ClientBootstrap serviceToken(String token) { return this; }
    /**
     * invoker.
     *
     * @return the result
     */
    public RpcInvoker invoker() { return new RpcInvoker(); }
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
