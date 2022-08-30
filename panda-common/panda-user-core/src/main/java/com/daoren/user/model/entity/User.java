package com.daoren.user.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author daoren
 * @since 2022-03-29
 */
@Getter
@Setter
@TableName("sys_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 用户名 */
    private String userName;

    /** 密码 */
    private String passWord;

    /** 昵称 */
    private String nickName;

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

}
