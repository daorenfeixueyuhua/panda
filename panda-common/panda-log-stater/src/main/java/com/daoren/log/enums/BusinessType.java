package com.daoren.log.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

/**
 * 操作类型
 *
 * @author peng_da
 * @version :
 * @date 2022/3/4 9:38
 * @since :
 */
public enum BusinessType {
    /**
     * 新增
     */
    INSERT(1, "新增"),
    /**
     * 更新
     */
    UPDATE(2, "更新"),
    /**
     * 删除
     */
    DELETE(3, "删除"),
    /**
     * 其他
     */
    OTHER(99, "其他");
    @EnumValue
    private final Integer code;
    private final String description;

    BusinessType(Integer code, String description) {
        this.code = code;
        this.description = description;
    }
}
