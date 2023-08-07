package com.daoren.thread.worker;

import com.daoren.thread.PandaThreadPoolExecutor;
import com.daoren.thread.handler.ThreadInitHandler;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 请求/任务结束之后的回调，通常用于日志输出等等情况
 * 聚合了一个线程池 threadPoolExecutor
 *
 * @author peng_da
 * @date 2022/11/8 9:55
 */
public abstract class AbstractProcessWorker implements Runnable, ThreadInitHandler {
    public static final List<Class<? extends ThreadInitHandler>> HANDLERS = new ArrayList<>();
    protected static PandaThreadPoolExecutor threadPoolExecutor;
    protected boolean isInit = false;

    public AbstractProcessWorker() {
        getThreadPoolExecutor();
        threadPoolExecutor.submit(this);
    }

    /**
     * 执行
     *
     * @param action    : 方法
     * @param result    : 请求结果
     * @param startTime : 开始时间
     * @param endTime   : 结束时间
     * @param e         : 异常
     * @author peng_da
     * @since 2022/11/7 16:54
     */
    public static void submit(String action, String result, LocalDateTime startTime, LocalDateTime endTime, Exception e) {
        HANDLERS.forEach(handler -> {
            try {
                final ThreadInitHandler newInstance = handler.newInstance();
                newInstance.init(action, result, startTime, endTime, e);
            } catch (InstantiationException | IllegalAccessException exception) {
                exception.printStackTrace();
            }
        });
    }

    /**
     * 子类必须实现一个创建线程池的方法
     *
     * @return com.daoren.thread.PandaThreadPoolExecutor
     * @author peng_da
     * @since 2022/11/8 11:22
     */
    public abstract PandaThreadPoolExecutor getThreadPoolExecutor();

    @PostConstruct
    public void register() {
        HANDLERS.add(this.getClass());
    }
}
