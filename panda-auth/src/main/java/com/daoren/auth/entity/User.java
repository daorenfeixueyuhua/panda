package com.daoren.auth.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.daoren.mybatis.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author daoren
 * @since 2022-03-18
 */
@Getter
@Setter
@TableName("sys_user")
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String userName;
    private String passWord;
    private String nickName;


}
