package io.zbus.client.producer;

import io.zbus.client.exception.MQClientException;

/**
 * Stub for zbus transaction MQ producer.
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

    public int getCheckThreadPoolMinSize() { return checkThreadPoolMinSize; }
    public void setCheckThreadPoolMinSize(int v) { this.checkThreadPoolMinSize = v; }
    public int getCheckThreadPoolMaxSize() { return checkThreadPoolMaxSize; }
    public void setCheckThreadPoolMaxSize(int v) { this.checkThreadPoolMaxSize = v; }
    public int getCheckRequestHoldMax() { return checkRequestHoldMax; }
    public void setCheckRequestHoldMax(int v) { this.checkRequestHoldMax = v; }
    public TransactionCheckListener getTransactionCheckListener() { return transactionCheckListener; }
    public void setTransactionCheckListener(TransactionCheckListener v) { this.transactionCheckListener = v; }
}
