package com.daoren.web.annotation;

import java.lang.annotation.*;

/**
 * 防重复提交
 *
 * @author peng_da
 * @date 2022/11/1 15:58
 */
@Target({ElementType.METHOD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface RepeatSubmit {
    /**
     * 间隔时间，小于该时间视为重复提交，单位：毫秒
     */
    int interval() default 5000;

    /**
     * 提示消息
     */
    String message() default "不允许重复提交，请稍后再试";

    /**
     * 错误时重试次数
     */
    int retryTime() default 2;

    /**
     * 是否自动解锁
     */
    boolean isAutoReleaseLock() default true;
}
