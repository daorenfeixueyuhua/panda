package com.daoren.mybatis.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;

/**
 * 基础配置
 *
 * @author peng_da
 * @version :
 * @date 2022/3/3 11:05
 * @since :
 */
public class BaseEntity {
    private static final long serialVersionUID = -4795566581449614479L;
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;
    /**
     * 创建时间
     */
    @TableField(value = "sys_create_time", fill = FieldFill.INSERT)
    private LocalDateTime sysCreateTime;
    /**
     * 创建者
     */
    @TableField(value = "sys_create_user", fill = FieldFill.INSERT)
    private String sysCreateUser;
    /**
     * 更新时间
     */
    @TableField(value = "sys_update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime sysUpdateTime;
    /**
     * 更新者
     */
    @TableField(value = "sys_update_user", fill = FieldFill.INSERT_UPDATE)
    private String sysUpdateUser;
    /**
     * 删除时间
     */
    @TableField(value = "sys_delete_time")
    private LocalDateTime sysDeleteTime;
    /**
     * 删除者
     */
    @TableField(value = "sys_delete_user")
    private String sysDeleteUser;
    /**
     * 是否有效：1 有效；0 无效
     */
    @TableField(value = "sys_enable", fill = FieldFill.INSERT)
    @TableLogic(value = "1", delval = "0")
    private Integer sysEnable;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getSysCreateTime() {
        return sysCreateTime;
    }

    public void setSysCreateTime(LocalDateTime sysCreateTime) {
        this.sysCreateTime = sysCreateTime;
    }

    public String getSysCreateUser() {
        return sysCreateUser;
    }

    public void setSysCreateUser(String sysCreateUser) {
        this.sysCreateUser = sysCreateUser;
    }

    public LocalDateTime getSysUpdateTime() {
        return sysUpdateTime;
    }

    public void setSysUpdateTime(LocalDateTime sysUpdateTime) {
        this.sysUpdateTime = sysUpdateTime;
    }

    public String getSysUpdateUser() {
        return sysUpdateUser;
    }

    public void setSysUpdateUser(String sysUpdateUser) {
        this.sysUpdateUser = sysUpdateUser;
    }

    public LocalDateTime getSysDeleteTime() {
        return sysDeleteTime;
    }

    public void setSysDeleteTime(LocalDateTime sysDeleteTime) {
        this.sysDeleteTime = sysDeleteTime;
    }

    public String getSysDeleteUser() {
        return sysDeleteUser;
    }

    public void setSysDeleteUser(String sysDeleteUser) {
        this.sysDeleteUser = sysDeleteUser;
    }

    public Integer getSysEnable() {
        return sysEnable;
    }

    public void setSysEnable(Integer sysEnable) {
        this.sysEnable = sysEnable;
    }
}
