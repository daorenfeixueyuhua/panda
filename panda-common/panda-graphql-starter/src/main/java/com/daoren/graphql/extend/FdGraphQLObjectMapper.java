package com.daoren.graphql.extend;


import graphql.ExecutionResult;
import graphql.servlet.*;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * @author peng_da
 * @version :
 * @date 2022/7/19 15:01
 * @since :
 */
public class FdGraphQLObjectMapper extends GraphQLObjectMapper {

    protected FdGraphQLObjectMapper(Supplier<ObjectMapperConfigurer> objectMapperConfigurerSupplier, Supplier<GraphQLErrorHandler> graphQLErrorHandlerSupplier) {
        super(objectMapperConfigurerSupplier, graphQLErrorHandlerSupplier);
    }

    public static Builder newBuilder2() {
        return new Builder();
    }

    /**
     * 对graphql返回结果进行封装
     *
     * @param executionResult :
     * @param includeData     :
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @author peng_da
     * @since 2022/7/19 15:02
     */
    @Override
    public Map<String, Object> convertSanitizedExecutionResult(ExecutionResult executionResult, boolean includeData) {
        if (includeData) {
            return Result.success(Result.MSG_SUCCESS, executionResult.getData());
        }
        if (areErrorsPresent(executionResult)) {
            return Result.fail(Result.MSG_FAIL, executionResult.getErrors());
        }
        return null;
    }

    public static class Builder {
        private Supplier<ObjectMapperConfigurer> objectMapperConfigurer = DefaultObjectMapperConfigurer::new;
        private Supplier<GraphQLErrorHandler> graphQLErrorHandler = DefaultGraphQLErrorHandler::new;

        public Builder withObjectMapperConfigurer(ObjectMapperConfigurer objectMapperConfigurer) {
            return withObjectMapperConfigurer(() -> objectMapperConfigurer);
        }

        public Builder withObjectMapperConfigurer(Supplier<ObjectMapperConfigurer> objectMapperConfigurer) {
            this.objectMapperConfigurer = objectMapperConfigurer;
            return this;
        }

        public Builder withGraphQLErrorHandler(GraphQLErrorHandler graphQLErrorHandler) {
            return withGraphQLErrorHandler(() -> graphQLErrorHandler);
        }

        public Builder withGraphQLErrorHandler(Supplier<GraphQLErrorHandler> graphQLErrorHandler) {
            this.graphQLErrorHandler = graphQLErrorHandler;
            return this;
        }

        public FdGraphQLObjectMapper build() {
            return new FdGraphQLObjectMapper(objectMapperConfigurer, graphQLErrorHandler);
        }
    }

    static class Result extends HashMap<String, Object> implements Serializable {
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
            return new Result(code, msg, data);
        }

        public static Result fail(final Integer code,
                                  final String msg,
                                  final Object data) {
            String msgTemp = getMsg(msg, IS_FAIL);
            Integer codeTemp = Optional.ofNullable(code).orElse(CODE_FAIL);
            return new Result(code, msg, data);
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


}
