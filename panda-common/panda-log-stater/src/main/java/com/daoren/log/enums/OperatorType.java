package com.daoren.log.enums;

import com.baomidou.mybatisplus.annotation.IEnum;

/**
 * 操作方式
 *
 * @author peng_da
 * @version :
 * @date 2022/3/4 9:38
 * @since :
 */
public enum OperatorType implements IEnum<Integer> {
    /**
     * 其他
     */
    OTHER(3, "其他"),
    /**
     * 后台
     */
    MANAGE(2, "后台"),
    /**
     * 手机
     */
    MOBILE(1, "手机");

    private final Integer code;
    /**
     * 标记响应json值
     */
    private final String description;

    OperatorType(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    @Override
    public Integer getValue() {
        return this.code;
    }
}
