package com.daoren.common.base.exception;

/**
 * 错误信息
 *
 * @author peng_da
 * @date 2022/9/1 17:39
 */
public class ErrorCode {
    public final int errorCode;
    public final String errorMsg;

    public ErrorCode(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
}
