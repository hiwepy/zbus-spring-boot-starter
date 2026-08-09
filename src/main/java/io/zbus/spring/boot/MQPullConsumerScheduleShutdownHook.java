package io.zbus.spring.boot;

/**
 * Stub for MQPullConsumerScheduleShutdownHook.
 */
public class MQPullConsumerScheduleShutdownHook extends Thread {

    private MQPullConsumerScheduleService service;

    public MQPullConsumerScheduleShutdownHook(MQPullConsumerScheduleService service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.shutdown();
    }
}
