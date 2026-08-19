package io.zbus.spring.boot;

/**
 * Stub for MQPullConsumerScheduleShutdownHook.
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
public class MQPullConsumerScheduleShutdownHook extends Thread {

    private MQPullConsumerScheduleService service;

    public MQPullConsumerScheduleShutdownHook(MQPullConsumerScheduleService service) {
        this.service = service;
    }

    @Override
    /**
     * run.
     *
     */
    public void run() {
        service.shutdown();
    }
}
