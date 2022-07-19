package com.daoren.file.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.daoren.mybatis.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CustomUser extends BaseEntity {
    @TableField
    private String name;

}
