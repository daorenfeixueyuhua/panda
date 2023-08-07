package com.daoren.common.base.monitor.entity;

import java.io.Serializable;

/**
 * @author peng_da
 * @date 2022/9/2 14:15
 */
public class MonitorElement implements Serializable {
    private static final long serialVersionUID = -3891639304332847294L;
    private String requestKey;
    private long beginTime;
    private long endTime;
    private String config;
    private String type;
    private Throwable exception;

    public MonitorElement(String type, String config, long beginTime, long endTime) {
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.config = config;
        this.type = type;
    }

    public MonitorElement(String type, String config, long beginTime, long endTime, Throwable exception) {
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.config = config;
        this.type = type;
        this.exception = exception;
    }

    public MonitorElement() {
    }

    public String getRequestKey() {
        return requestKey;
    }

    public void setRequestKey(String requestKey) {
        this.requestKey = requestKey;
    }

    public long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(long beginTime) {
        this.beginTime = beginTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Throwable getException() {
        return exception;
    }

    public void setException(Throwable exception) {
        this.exception = exception;
    }
}
