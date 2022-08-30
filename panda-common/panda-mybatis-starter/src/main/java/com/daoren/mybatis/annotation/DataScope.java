package com.daoren.mybatis.annotation;

import java.lang.annotation.*;

/**
 * 数据权限
 * @author peng_da
 * @date  2022/8/16 15:46
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DataScope {
    /** 用户表别名 */
    String userAlias() default "";
    /** 部门表别名 */
    String deptAlias() default "";
}
