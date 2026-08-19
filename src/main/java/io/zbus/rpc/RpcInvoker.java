package io.zbus.rpc;

/**
 * Stub for zbus RpcInvoker.
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class RpcInvoker {

    public RpcInvoker() {}

    /**
     * invoke Sync.
     *
     * @param method the method
     * @param args the args
     * @return the result
     */
    public Object invokeSync(String method, Object... args) { return null; }
    /**
     * invoke Async.
     *
     * @param method the method
     * @param args the args
     */
    public void invokeAsync(String method, Object... args) {}
    /**
     * close.
     *
     */
    public void close() {}
}
