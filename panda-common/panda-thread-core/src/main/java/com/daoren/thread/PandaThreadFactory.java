package com.daoren.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程工厂
 *
 * @author peng_da
 * @date 2022/11/8 9:42
 */
public class PandaThreadFactory implements ThreadFactory {
    public static final String PREFIX = "PanDa";
    private final String threadFlag;
    private final AtomicInteger count;

    /**
     * 自定义线程工厂
     *
     * @param threadFlag : 线程标识
     * @author peng_da
     * @since 2022/11/8 9:45
     */
    public PandaThreadFactory(String threadFlag) {
        this.threadFlag = threadFlag;
        count = new AtomicInteger(0);
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        String threadName = PREFIX + "-" + threadFlag + "-" + count.addAndGet(1);
        thread.setName(threadName);
        return thread;
    }
}
