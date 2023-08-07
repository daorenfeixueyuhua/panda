package com.daoren.web.annotation;

import java.lang.annotation.*;

/**
 * 获取用户信息注解
 *
 * @author peng_da
 * @date 2022/11/2 16:44
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface User {
}
