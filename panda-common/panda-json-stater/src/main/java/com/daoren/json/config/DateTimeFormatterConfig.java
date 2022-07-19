package com.daoren.json.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.datetime.standard.DateTimeFormatterFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

/**
 * 时间格式化配置
 *
 * @author peng_da
 * @version :
 * @date 2022/3/3 10:19
 * @since :
 */
@Configuration
public class DateTimeFormatterConfig {

    public static String getPattern() {
        return "yyyy-MM-dd HH:mm:ss";
    }

    public static DateTimeFormatter getDateTimeFormatter() {
        return DateTimeFormatter.ofPattern(getPattern());
    }

    @Bean
    public DateTimeFormatterFactory dateTimeFormatterFactory() {
        DateTimeFormatterFactory factory = new DateTimeFormatterFactory();
        factory.setPattern(getPattern());
        return factory;
    }

    @Bean
    public DateFormat simple() {
        return new SimpleDateFormat(getPattern());
    }

    @Bean
    public DateTimeFormatter dateFormatter(DateTimeFormatterFactory factory) {
        return factory.createDateTimeFormatter();
    }
}
