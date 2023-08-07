package com.daoren.web.worker;

import com.daoren.thread.PandaThreadFactory;
import com.daoren.thread.PandaThreadPoolExecutor;
import com.daoren.thread.context.RequestContext;
import com.daoren.thread.worker.AbstractProcessWorker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 请求线程工作者
 *
 * @author peng_da
 * @date 2022/11/7 17:15
 */
public class PandaRequestWorker extends AbstractProcessWorker {
    public volatile static DateTimeFormatter dateTimeFormatter;
    public volatile static boolean isShowResult;
    private Map<String, Object> requestParams;
    private String action;
    private String result;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Exception exception;

    @Override
    public PandaThreadPoolExecutor getThreadPoolExecutor() {
        if (threadPoolExecutor == null) {
            synchronized (PandaRequestWorker.class) {
                if (threadPoolExecutor == null) {
                    threadPoolExecutor = new PandaThreadPoolExecutor(
                            5, Integer.MAX_VALUE, 20,
                            TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10),
                            new PandaThreadFactory("REQUEST")
                    );
                }
            }
        }
        return threadPoolExecutor;
    }

    @Override
    public void init(String action, String result, LocalDateTime startTime, LocalDateTime endTime, Exception e) {
        this.isInit = true;
        this.action = action;
        this.startTime = startTime;
        this.endTime = endTime;
        this.result = result;
        this.exception = e;
        this.requestParams = RequestContext.REQUEST_PARAMS.get();
    }

    @Override
    public void run() {
        if (!this.isInit) {
            return;
        }

        // 控制台日志输出
        System.out.println("请求节点：" + action);
        System.out.println("请求参数：" + requestParams);
        if (isShowResult) {
            System.out.println("请求结果：" + result);
        }
        System.out.println("请求开始时间：" + startTime.format(dateTimeFormatter));
        System.out.println("请求结束时间：" + endTime.format(dateTimeFormatter));
        if (exception != null) {
            System.out.println("异常:" + exception.getMessage());
            exception.printStackTrace();
        }
    }
}
