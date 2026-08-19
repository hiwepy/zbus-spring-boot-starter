package io.zbus.client.producer;

import io.zbus.client.exception.MQClientException;

/**
 * Stub for zbus transaction MQ producer.
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class TransactionMQProducer extends DefaultMQProducer {

    private int checkThreadPoolMinSize;
    private int checkThreadPoolMaxSize;
    private int checkRequestHoldMax;
    private TransactionCheckListener transactionCheckListener;

    public TransactionMQProducer() {
        super();
    }

    public TransactionMQProducer(String producerGroup) {
        super(producerGroup);
    }

    /**
     * Returns the check thread pool min size.
     *
     * @return the check thread pool min size
     */
    public int getCheckThreadPoolMinSize() { return checkThreadPoolMinSize; }
    /**
     * Sets the check thread pool min size.
     *
     * @param v the v
     */
    public void setCheckThreadPoolMinSize(int v) { this.checkThreadPoolMinSize = v; }
    /**
     * Returns the check thread pool max size.
     *
     * @return the check thread pool max size
     */
    public int getCheckThreadPoolMaxSize() { return checkThreadPoolMaxSize; }
    /**
     * Sets the check thread pool max size.
     *
     * @param v the v
     */
    public void setCheckThreadPoolMaxSize(int v) { this.checkThreadPoolMaxSize = v; }
    /**
     * Returns the check request hold max.
     *
     * @return the check request hold max
     */
    public int getCheckRequestHoldMax() { return checkRequestHoldMax; }
    /**
     * Sets the check request hold max.
     *
     * @param v the v
     */
    public void setCheckRequestHoldMax(int v) { this.checkRequestHoldMax = v; }
    /**
     * Returns the transaction check listener.
     *
     * @return the transaction check listener
     */
    public TransactionCheckListener getTransactionCheckListener() { return transactionCheckListener; }
    /**
     * Sets the transaction check listener.
     *
     * @param v the v
     */
    public void setTransactionCheckListener(TransactionCheckListener v) { this.transactionCheckListener = v; }
}
