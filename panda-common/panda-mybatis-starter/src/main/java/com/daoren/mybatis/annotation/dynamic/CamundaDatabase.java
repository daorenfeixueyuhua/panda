package com.daoren.mybatis.annotation.dynamic;

import com.baomidou.dynamic.datasource.annotation.DS;

import java.lang.annotation.*;

/**
 * camunda数据库
 *
 * @author peng_da
 * @date 2022/11/3 16:18
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@DS("camunda")
public @interface CamundaDatabase {
}
