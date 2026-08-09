package io.zbus.rpc.bootstrap.http;

import io.zbus.rpc.RpcInvoker;

/**
 * Stub for zbus ClientBootstrap.
 */
public class ClientBootstrap {

    public ClientBootstrap() {}

    public ClientBootstrap serviceAddress(String address) { return this; }
    public ClientBootstrap serviceToken(String token) { return this; }
    public RpcInvoker invoker() { return new RpcInvoker(); }
    public void start() {}
    public void close() {}
}
