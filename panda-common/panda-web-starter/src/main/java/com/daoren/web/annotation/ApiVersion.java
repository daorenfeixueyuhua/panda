package com.daoren.web.annotation;

import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.*;

/**
 * 接口版本
 *
 * @author peng_da
 * @version :
 * @date 2022/4/24 15:53
 * @since :
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Mapping
public @interface ApiVersion {
    double version() default 1;
}
