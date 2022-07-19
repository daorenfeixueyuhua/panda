package com.daoren.graphsqlql.extend.scalar;

import graphql.schema.Coercing;
import graphql.schema.GraphQLScalarType;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author peng_da
 * @version :
 * @date 2022/7/19 10:39
 * @since :
 */
@Component
public class DateTimeScalar extends GraphQLScalarType {
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public DateTimeScalar() {
        super("DateTime", "DateTime value", new Coercing<Date, String>() {
            @Override
            public String serialize(Object o) {
                Date date = (Date) o;
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
                return simpleDateFormat.format(date);
            }

            @Override
            public Date parseValue(Object o) {
                String value = String.valueOf(o);
                if ("null".equalsIgnoreCase(value) || "".equalsIgnoreCase(value)) {
                    return null;
                }
                return new Date(value);
            }

            @Override
            public Date parseLiteral(Object o) {
                String value = String.valueOf(o);
                if ("null".equalsIgnoreCase(value) || "".equalsIgnoreCase(value)) {
                    return null;
                }
                return new Date(value);
            }
        });
    }
}
