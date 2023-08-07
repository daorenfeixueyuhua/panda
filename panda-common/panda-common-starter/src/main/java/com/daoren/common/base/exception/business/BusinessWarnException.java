package com.daoren.common.base.exception.business;

import com.daoren.common.base.exception.AbstractException;
import com.daoren.common.base.exception.ErrorCode;

/**
 * 业务warn异常
 *
 * @author peng_da
 * @date 2022/9/2 9:30
 */
public class BusinessWarnException extends AbstractException {

    private static final long serialVersionUID = -9019323461428426380L;

    public BusinessWarnException(String message) {
        super(message);
    }

    public BusinessWarnException(int errorCode) {
        super(errorCode);
    }

    public BusinessWarnException(String message, int errorCode) {
        super(message, errorCode);
    }

    public BusinessWarnException(String message, Throwable cause, int errorCode) {
        super(message, cause, errorCode);
    }

    public BusinessWarnException(Throwable cause, int errorCode) {
        super(cause, errorCode);
    }

    public BusinessWarnException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int errorCode) {
        super(message, cause, enableSuppression, writableStackTrace, errorCode);
    }

    public BusinessWarnException(ErrorCode errorCode) {
        super(errorCode);
    }

    public BusinessWarnException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }
}
