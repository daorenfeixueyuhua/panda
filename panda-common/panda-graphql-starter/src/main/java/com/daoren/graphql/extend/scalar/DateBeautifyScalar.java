package com.daoren.graphql.extend.scalar;

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
public class DateBeautifyScalar extends GraphQLScalarType {
    public DateBeautifyScalar() {
        super("DateBeautify", "Built-in Date as timestamp", new Coercing<Date, String>() {
            @Override
            public String serialize(Object input) {
                return "刚刚";
            }

            @Override
            public Date parseValue(Object input) {
                return new Date();
            }

            @Override
            public Date parseLiteral(Object input) {
                return new Date();
            }
        });
    }
}
