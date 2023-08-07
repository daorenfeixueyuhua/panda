package com.daoren.common.base.exception.business;

import com.daoren.common.base.exception.AbstractRuntimeException;
import com.daoren.common.base.exception.ErrorCode;

/**
 * 业务运行时异常
 *
 * @author peng_da
 * @date 2022/9/2 9:25
 */
public class BusinessRuntimeException extends AbstractRuntimeException {

    private static final long serialVersionUID = -2611278271525987464L;

    public BusinessRuntimeException(String message) {
        super(message);
    }

    public BusinessRuntimeException(int errorCode) {
        super(errorCode);
    }

    public BusinessRuntimeException(String message, int errorCode) {
        super(message, errorCode);
    }

    public BusinessRuntimeException(String message, Throwable cause, int errorCode) {
        super(message, cause, errorCode);
    }

    public BusinessRuntimeException(Throwable cause, int errorCode) {
        super(cause, errorCode);
    }

    public BusinessRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int errorCode) {
        super(message, cause, enableSuppression, writableStackTrace, errorCode);
    }

    public BusinessRuntimeException(ErrorCode errorCode) {
        super(errorCode);
    }

    public BusinessRuntimeException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }
}
