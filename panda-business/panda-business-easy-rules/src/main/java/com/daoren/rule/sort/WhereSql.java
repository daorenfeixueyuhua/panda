package com.daoren.rule.sort;

/**
 * @author peng_da
 * @since 2023/3/22 15:54
 */
public interface WhereSql {
    /**
     * 获取SQL Condition
     *
     * @return java.lang.String
     * @author peng_da
     * @since 2023/3/22 15:55
     */
    default String getSql() {
        return "";
    }
}
