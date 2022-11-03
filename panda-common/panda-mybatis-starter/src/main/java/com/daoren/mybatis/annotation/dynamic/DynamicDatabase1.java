package com.daoren.mybatis.annotation.dynamic;

import com.baomidou.dynamic.datasource.annotation.DS;

import java.lang.annotation.*;

/**
 * @author peng_da
 * @date 2022/11/3 16:20
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@DS("dynamic1")
public @interface DynamicDatabase1 {
}
