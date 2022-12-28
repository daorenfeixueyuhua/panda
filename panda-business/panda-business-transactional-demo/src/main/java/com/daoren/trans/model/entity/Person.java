package com.daoren.trans.model.entity;

import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.daoren.mybatis.entity.BaseEntity;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 个人信息表
 * </p>
 *
 * @author daoren
 * @since 2022-07-20
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_person")
public class Person extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField(condition = SqlCondition.LIKE)

    private String name;

    private LocalDateTime birth;

    private String idCard;


}
