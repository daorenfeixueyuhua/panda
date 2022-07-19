package com.daoren.log.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

/**
 * 业务状态枚举
 *
 * @author peng_da
 * @version :
 * @date 2022/3/4 9:37
 * @since :
 */
public enum BusinessStatus {
    /**
     * 成功
     */
    SUCCESS(1, "成功"),
    /**
     * 失败
     */
    FAIL(-1, "失败");
    @EnumValue
    private final Integer code;
    private final String description;

    BusinessStatus(Integer code, String description) {
        this.code = code;
        this.description = description;
    }
}
