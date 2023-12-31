package com.daoren.graphql.extend.scalar;

import graphql.language.IntValue;
import graphql.schema.Coercing;
import graphql.schema.GraphQLScalarType;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 自定义标量类型-测试
 *
 * @author hanliwei
 * @create 2019-03-08 22:34
 */
@Component
public class DateLongScalar extends GraphQLScalarType {
    public DateLongScalar() {
        super("DateLong", "Built-in Date as timestamp", new Coercing<Date, Long>() {
            @Override
            public Long serialize(Object input) {
                if (input instanceof Date) {
                    return ((Date) input).getTime();
                }
                return null;
            }

            @Override
            public Date parseValue(Object input) {
                if (input instanceof Long) {
                    return new Date((Long) input);
                }
                return null;
            }

            @Override
            public Date parseLiteral(Object input) {
                if (input instanceof IntValue) {
                    return new Date(((IntValue) input).getValue().longValue());
                }
                return null;
            }
        });
    }
}
