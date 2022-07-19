package com.daoren.graphsqlql.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.daoren.mybatis.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *
 * </p>
 *
 * @author daoren
 * @since 2022-03-04
 */
@Getter
@Setter
@TableName("sys_response_code_message")
@ApiModel(value = "SysResponseCodeMessage对象", description = "")
public class SysResponseCodeMessage extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("code值")
    private String code;

    @ApiModelProperty("message值")
    private String message;

    @ApiModelProperty("异常出现数量")
    private Integer count;


}
