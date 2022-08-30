package com.daoren.common.base.annotation;

import java.lang.annotation.*;

/**
 * 字段描述
 *
 * @author peng_da
 * @version :
 * @date 2022/3/23 9:14
 * @since :
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Description {
    /**
     * 描述
     */
    String describe() default "";
}
