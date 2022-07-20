package com.daoren.graphql.extend.scalar;

import graphql.schema.Coercing;
import graphql.schema.GraphQLScalarType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.format.datetime.standard.DateTimeFormatterFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 自定义 常量定义
 * scalar LocalDateTime
 *
 * @author peng_da
 * @version :
 * @date 2022/7/19 15:02
 * @since :
 */
@Component
public class LocalDateTimeScalar extends GraphQLScalarType {
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static DateTimeFormatter dateTimeFormatter = null;

    static {
        DateTimeFormatterFactory factory = new DateTimeFormatterFactory();
        factory.setPattern(DATE_FORMAT);
        dateTimeFormatter = factory.createDateTimeFormatter();
    }


    public LocalDateTimeScalar() {
        super("LocalDateTime", "LocalDateTime value", new Coercing<LocalDateTime, String>() {
            @Override
            public String serialize(Object o) {
                LocalDateTime date = (LocalDateTime) o;
                return dateTimeFormatter.format(date);
            }

            @Override
            public LocalDateTime parseValue(Object o) {
                String value = String.valueOf(o);
                if (StringUtils.isBlank(value)) {
                    return null;
                }
                return (LocalDateTime) dateTimeFormatter.parse(value);
            }

            @Override
            public LocalDateTime parseLiteral(Object o) {
                String value = String.valueOf(o);
                if (StringUtils.isBlank(value)) {
                    return null;
                }
                return (LocalDateTime) dateTimeFormatter.parse(value);
            }
        });

    }

}
