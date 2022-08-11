package com.daoren.web.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Optional;

/**
 * @author pengda
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class Result extends HashMap<String, Object> implements Serializable {
    public static final String FORMATTER = "yyyy-MM-dd hh:dd:ss";
    public static final String MSG_SUCCESS = "success";
    public static final String MSG_FAIL = "fail";
    public static final Integer CODE_SUCCESS = 200;
    public static final Integer CODE_FAIL = 500;
    public static final Boolean IS_SUCCESS = true;
    public static final Boolean IS_FAIL = false;
    public static final String FIELD_NAME_CODE = "code";
    public static final String FIELD_NAME_MSG = "msg";
    public static final String FIELD_NAME_DATA = "data";
    public static final String FIELD_NAME_TIMESTAMP = "timestamp";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(FORMATTER);
    private static final long serialVersionUID = 1103179949302415498L;
//    private int code;
//    private String msg;
//    private Object data;
//    private String timestamp;

    public Result() {

    }

    public Result(final Integer code,
                  final String msg,
                  final Object data) {
        super.put(FIELD_NAME_CODE, code);
        super.put(FIELD_NAME_MSG, msg);
        super.put(FIELD_NAME_DATA, data);
        super.put(FIELD_NAME_TIMESTAMP, DATE_TIME_FORMATTER.format(LocalDateTime.now()));
    }

    public static Result success(final Integer code,
                                 final String msg,
                                 final Object data) {
        String msgTemp = getMsg(msg, IS_SUCCESS);
        Integer codeTemp = Optional.ofNullable(code).orElse(CODE_SUCCESS);
        return new Result(codeTemp, msgTemp, data);
    }

    public static Result fail(final Integer code,
                              final String msg,
                              final Object data) {
        String msgTemp = getMsg(msg, IS_FAIL);
        Integer codeTemp = Optional.ofNullable(code).orElse(CODE_FAIL);
        return new Result(codeTemp, msgTemp, data);
    }

    /**
     * 对message进行默认处理
     *
     * @param msg       : message
     * @param isSuccess : 是否成功
     * @return java.lang.String
     * @author peng_da
     * @since 2022/1/26 10:21
     */
    private static String getMsg(final String msg, final Boolean isSuccess) {
        if (StringUtils.isNotBlank(msg)) {
            return msg;
        }
        if (isSuccess == null) {
            return MSG_SUCCESS;
        }
        if (isSuccess) {
            return MSG_SUCCESS;
        }
        return MSG_FAIL;
    }

    public static Result success(String msg,
                                 final Object data) {
        return success(null, msg, data);
    }

    public static Result success(final Object data) {
        return success(null, data);
    }

    public static Result success(final String msg) {
        return success(msg, null);
    }

    public static Result success() {
        return success(null, null);
    }

    public static Result fail(final String msg,
                              final Object data) {
        return fail(null, msg, data);
    }

    public static Result fail(final Object data) {
        return fail(null, data);
    }

    public static Result fail(final String msg) {
        return fail(msg, null);
    }

    public static Result fail() {
        return fail(null, null);
    }
}

