package com.daoren.common.base.exception;

/**
 * 异常父类
 *
 * @author peng_da
 * @date 2022/9/1 17:36
 */
public class AbstractException extends Exception {
    private static final long serialVersionUID = 553402928376768125L;
    private int errorCode;

    public AbstractException(String message) {
        super(message);
    }

    public AbstractException(int errorCode) {
        this.errorCode = errorCode;
    }

    public AbstractException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public AbstractException(String message, Throwable cause, int errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public AbstractException(Throwable cause, int errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }

    public AbstractException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int errorCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorCode = errorCode;
    }

    public AbstractException(ErrorCode errorCode) {
        super(errorCode.errorMsg);
        this.errorCode = errorCode.errorCode;
    }

    public AbstractException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.errorMsg, cause);
        this.errorCode = errorCode.errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
