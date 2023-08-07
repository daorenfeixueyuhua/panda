package com.daoren.thread.worker;

import java.time.LocalDateTime;

/**
 * 自定义父线程
 *
 * @author peng_da
 * @date 2022/8/30 15:43
 */
public abstract class PandaWorker implements Runnable {
    @Override
    public void run() {
        final LocalDateTime startTime = LocalDateTime.now();
        Exception exception = null;
        try {
            customRun();
        } catch (Exception e) {
            exception = e;
        } finally {
            final LocalDateTime endTime = LocalDateTime.now();
            AbstractProcessWorker.submit(this.getClass().toString(), null,
                    startTime, endTime, exception);
        }
    }

    /**
     * 自定义运行
     *
     * @author peng_da
     * @since 2022/8/30 15:43
     */
    public abstract void customRun();
}
