package com.daoren.web.config;

import com.daoren.json.config.DateTimeFormatterConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 转换器config
 * @author peng_da
 * @date  2022/8/23 14:30
 */
@Configuration
@Import(DateTimeFormatterConfig.class)
public class ConversionConfig {

    @Autowired
    private DateTimeFormatter dateTimeFormatter;

    /**
     * LocalDateTime 转换器
     * @author peng_da
     * @since 2022/8/23 14:38
     * @return org.springframework.core.convert.converter.Converter<java.lang.String,java.time.LocalDateTime>
     */
    @Bean
    public Converter<String, LocalDateTime> localDateTimeConverter(){
        return new Converter<String, LocalDateTime>() {
            @Override
            public LocalDateTime convert(String source) {
                return LocalDateTime.parse(source, dateTimeFormatter);
            }
        };
    }
}
