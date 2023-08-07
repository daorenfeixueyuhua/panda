package com.daoren.test.model.entity;

import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.daoren.mybatis.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "Person对象", description = "个人信息表")
public class Person extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField(condition = SqlCondition.LIKE)
    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("出生日期")
    private LocalDateTime birth;

    @ApiModelProperty("身份证号码")
    private String idCard;


}
