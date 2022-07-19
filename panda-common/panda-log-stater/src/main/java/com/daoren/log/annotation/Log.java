package com.daoren.log.annotation;

import com.daoren.log.enums.BusinessType;
import com.daoren.log.enums.OperatorType;

import java.lang.annotation.*;

/**
 * 日志注解
 *
 * @author peng_da
 * @version :
 * @date 2022/3/4 9:30
 * @since :
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Log {
    /**
     * 功能描述
     */
    String title() default "";

    /**
     * 操作方式
     */
    BusinessType businessType() default BusinessType.OTHER;

    /**
     * 操作类型
     */
    OperatorType operatorType() default OperatorType.MANAGE;

}
