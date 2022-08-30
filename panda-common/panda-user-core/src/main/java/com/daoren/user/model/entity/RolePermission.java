package com.daoren.user.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *
 * </p>
 *
 * @author daoren
 * @since 2022-03-29
 */
@Getter
@Setter
@TableName("sys_role_permission")
public class RolePermission {

    private static final long serialVersionUID = 1L;

    @TableId
    private String id;

    private String roleId;

    private String permissionId;


}
