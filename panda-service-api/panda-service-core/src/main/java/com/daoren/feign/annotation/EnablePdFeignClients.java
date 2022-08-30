package com.daoren.feign.annotation;

import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;
/**
 * 自定义feign注解
 * 添加basePackages路径
 * @author peng_da
 * @date  2022/8/17 10:23 
 */
@Import(OAuth2ClientProperties.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnableFeignClients
public @interface EnablePdFeignClients {
    String[] value() default {};

    String[] basePackages() default { "com.daoren" };

    Class<?>[] basePackageClasses() default {};

    Class<?>[] defaultConfiguration() default {};

    Class<?>[] clients() default {};
}
