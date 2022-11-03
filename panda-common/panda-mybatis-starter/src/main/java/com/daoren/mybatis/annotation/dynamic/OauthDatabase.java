package com.daoren.mybatis.annotation.dynamic;

import com.baomidou.dynamic.datasource.annotation.DS;

import java.lang.annotation.*;

/**
 * 权限相关数据源
 *
 * @author peng_da
 * @date 2022/11/3 16:12
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@DS("oauth")
public @interface OauthDatabase {
}
