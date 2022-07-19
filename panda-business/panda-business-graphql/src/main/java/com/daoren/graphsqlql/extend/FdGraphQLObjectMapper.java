package com.daoren.graphsqlql.extend;

import com.daoren.web.entity.Result;
import graphql.ExecutionResult;
import graphql.servlet.*;

import java.util.Map;
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

}
