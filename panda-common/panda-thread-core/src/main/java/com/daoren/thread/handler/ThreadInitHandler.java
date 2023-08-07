package com.daoren.thread.handler;

import java.time.LocalDateTime;

/**
 * 线程初始化
 * 包括日志、sql、等等相关
 *
 * @author peng_da
 * @date 2022/11/7 16:53
 */
@FunctionalInterface
public interface ThreadInitHandler {
    /**
     * 初始化
     *
     * @param action    : 方法
     * @param result    : 请求结果
     * @param startTime : 开始时间
     * @param endTime   : 结束时间
     * @param e         : 异常
     * @author peng_da
     * @since 2022/11/7 16:54
     */
    void init(String action, String result, LocalDateTime startTime, LocalDateTime endTime, Exception e);
}
