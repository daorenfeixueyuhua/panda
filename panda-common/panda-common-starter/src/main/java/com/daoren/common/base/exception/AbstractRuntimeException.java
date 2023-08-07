package com.daoren.common.base.exception;

/**
 * 运行时异常父类
 *
 * @author peng_da
 * @date 2022/9/1 17:42
 */
public class AbstractRuntimeException extends RuntimeException {
    private int errorCode;

    public AbstractRuntimeException(String message) {
        super(message);
    }

    public AbstractRuntimeException(int errorCode) {
        this.errorCode = errorCode;
    }

    public AbstractRuntimeException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public AbstractRuntimeException(String message, Throwable cause, int errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public AbstractRuntimeException(Throwable cause, int errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }

    public AbstractRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int errorCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorCode = errorCode;
    }

    public AbstractRuntimeException(ErrorCode errorCode) {
        super(errorCode.errorMsg);
        this.errorCode = errorCode.errorCode;
    }

    public AbstractRuntimeException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.errorMsg, cause);
        this.errorCode = errorCode.errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
