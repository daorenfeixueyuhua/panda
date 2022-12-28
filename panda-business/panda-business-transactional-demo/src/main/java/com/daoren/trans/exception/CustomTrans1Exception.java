package com.daoren.trans.exception;
/**
 *
 * @author peng_da
 * @date  2022/12/27 9:56
 */
public class CustomTrans1Exception extends RuntimeException{
    public CustomTrans1Exception() {
        super();
    }

    public CustomTrans1Exception(String message) {
        super(message);
    }

    public CustomTrans1Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomTrans1Exception(Throwable cause) {
        super(cause);
    }

    protected CustomTrans1Exception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
