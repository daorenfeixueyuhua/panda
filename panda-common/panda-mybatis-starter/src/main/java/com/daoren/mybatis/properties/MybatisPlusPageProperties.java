package com.daoren.mybatis.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * 分页配置
 *
 * @author peng_da
 * @version :
 * @date 2022/3/3 11:15
 * @since :
 */
@ConfigurationProperties("mybatis-plus.page")
public class MybatisPlusPageProperties {
    /**
     * 当前页字段
     */
    private String keyCurrent = "current";
    /**
     * 分页记录数字段
     */
    private String keySize = "size";
    /**
     * 排序字段
     */
    private String keyOrders = "orders";
    /**
     * 正序字段
     */
    private String keyAsc = "asc";
    /**
     * 逆序字段
     */
    private String keyDesc = "desc";
    /**
     * 分隔符
     */
    private String split = " ";
    /**
     * 默认当前页
     */
    private Long current = 1L;
    /**
     * 默认分页记录数
     */
    private Long size = 10L;
    /**
     * 默认分页排序
     */
    private boolean asc = false;
    /**
     * 默认分页字段
     */
    private String column = "sys_create_time";

    public String getKeyCurrent() {
        return keyCurrent;
    }

    public void setKeyCurrent(String keyCurrent) {
        this.keyCurrent = keyCurrent;
    }

    public String getKeySize() {
        return keySize;
    }

    public void setKeySize(String keySize) {
        this.keySize = keySize;
    }

    public String getKeyOrders() {
        return keyOrders;
    }

    public void setKeyOrders(String keyOrders) {
        this.keyOrders = keyOrders;
    }

    public String getKeyAsc() {
        return keyAsc;
    }

    public void setKeyAsc(String keyAsc) {
        this.keyAsc = keyAsc;
    }

    public String getKeyDesc() {
        return keyDesc;
    }

    public void setKeyDesc(String keyDesc) {
        this.keyDesc = keyDesc;
    }

    public String getSplit() {
        return split;
    }

    public void setSplit(String split) {
        this.split = split;
    }

    public Long getCurrent() {
        return current;
    }

    public void setCurrent(Long current) {
        this.current = current;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public boolean isAsc() {
        return asc;
    }

    public void setAsc(boolean asc) {
        this.asc = asc;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }
}
