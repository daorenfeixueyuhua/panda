package com.daoren.dbagent.annotation;

import com.daoren.dbagent.validator.SqlValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * sql校验
 * @author peng_da
 * @date  2022/8/11 11:25
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SqlValidator.class)
@Target(value = { ElementType.PARAMETER})
public @interface SqlValidation {
    /**
     * 是否强制校验
     *
     * @return 是否强制校验的boolean值
     */
    boolean required() default true;

    /**
     * 校验不通过时的报错信息
     *
     * @return 校验不通过时的报错信息
     */
    String message() default "sql格式错误";

    /**
     * 将validator进行分类，不同的类group中会执行不同的validator操作
     *
     * @return validator的分类类型
     */
    Class<?>[] groups() default {};

    /**
     * 主要是针对bean，很少使用
     *
     * @return 负载
     */
    Class<? extends Payload>[] payload() default {};
}
