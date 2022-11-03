package com.daoren.dynamic.model.entity;

import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.daoren.mybatis.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * <p>
 * 动态表测试
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
@TableName("sys_dynamic_table")
@ApiModel(value = "sys_dynamic_table对象", description = "动态表测试")
public class DynamicTable extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableField(value = "name", condition = SqlCondition.LIKE)
    @ApiModelProperty("姓名")
    private String name;
}
