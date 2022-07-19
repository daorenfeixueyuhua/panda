package com.daoren.web.annotation;

import java.lang.annotation.*;

/**
 * @author peng_da
 * @version :
 * @date 2022/2/24 17:00
 * @since :
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResponseResult {
    String message() default "";
}