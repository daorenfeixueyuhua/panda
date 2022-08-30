package com.daoren.json.config;

import com.daoren.json.deser.CLocalDateTimeDeserializer;
import com.daoren.json.ser.CLocalDateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.time.LocalDateTime;

/**
 * Json 配置
 *
 * @author peng_da
 * @version :
 * @date 2022/3/3 10:00
 * @since :
 */
@Configuration
public class JacksonConfig {
    /** ObjectMapper Bean Name */
    public static final String OBJECT_MAPPER_NAME = "pandaObjectMapper";
    @Resource
    private DateFormat dateFormat;
    @Resource
    private CLocalDateTimeSerializer localDateTimeSerializer;
    @Resource
    private CLocalDateTimeDeserializer localDateTimeDeserializer;

    @Bean
    public JavaTimeModule javaTimeModule() {
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addDeserializer(LocalDateTime.class, localDateTimeDeserializer);
        javaTimeModule.addSerializer(LocalDateTime.class, localDateTimeSerializer);
        return javaTimeModule;
    }


    /**
     * <p>创建ObjectMapper</p>
     * <p>建议使用@Resource来获取</p>
     * <p>使用多例模式，在项目中很多地方可能会对创建ObjectMapper做修改</p>
     *
     * @return com.fasterxml.jackson.databind.ObjectMapper
     * @author peng_da
     * @since 2022/2/17 17:16
     */
    @Scope("prototype")
    @Bean(OBJECT_MAPPER_NAME)
    public ObjectMapper objectMapper() {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.configure(SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS, false);
        // 设置自定义序列化/反序列化
        objectMapper.registerModule(javaTimeModule());
        // 设置时间格式
        objectMapper.setDateFormat(dateFormat);
        // 通用枚举
        objectMapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
        // 忽略未知属性
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // 删除之后序列化就正常了哎
//        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance,
//                ObjectMapper.DefaultTyping.JAVA_LANG_OBJECT,
//                JsonTypeInfo.As.WRAPPER_ARRAY);
        return objectMapper;
    }
}
