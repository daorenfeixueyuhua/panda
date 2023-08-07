package com.daoren.common.base.exception.business;

import com.daoren.common.base.exception.AbstractException;
import com.daoren.common.base.exception.ErrorCode;

/**
 * 业务info异常
 *
 * @author peng_da
 * @date 2022/9/2 9:28
 */
public class BusinessInfoException extends AbstractException {

    private static final long serialVersionUID = 4020797989972395074L;

    public BusinessInfoException(String message) {
        super(message);
    }

    public BusinessInfoException(int errorCode) {
        super(errorCode);
    }

    public BusinessInfoException(String message, int errorCode) {
        super(message, errorCode);
    }

    public BusinessInfoException(String message, Throwable cause, int errorCode) {
        super(message, cause, errorCode);
    }

    public BusinessInfoException(Throwable cause, int errorCode) {
        super(cause, errorCode);
    }

    public BusinessInfoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int errorCode) {
        super(message, cause, enableSuppression, writableStackTrace, errorCode);
    }

    public BusinessInfoException(ErrorCode errorCode) {
        super(errorCode);
    }

    public BusinessInfoException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }
}
