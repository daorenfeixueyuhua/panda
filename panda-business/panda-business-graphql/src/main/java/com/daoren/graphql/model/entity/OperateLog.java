package com.daoren.graphql.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.daoren.mybatis.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * <p>
 * 操作日志表
 * </p>
 *
 * @author daoren
 * @since 2022-07-19
 */
@Getter
@Setter
@TableName("sys_operate_log")
@ApiModel(value = "OperateLog对象", description = "操作日志表")
public class OperateLog extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("操作人")
    private String operateUserId;

    @ApiModelProperty("操作人姓名")
    private String operateUserName;

    @ApiModelProperty("操作开始时间")
    private LocalDateTime operateStartTime;

    @ApiModelProperty("操作结束时间")
    private LocalDateTime operateEndTime;

    @ApiModelProperty("操作sql语句")
    private String operateSql;

    @ApiModelProperty("入参")
    private String operateParams;

    @ApiModelProperty("返回结果")
    private String operateResult;

    @ApiModelProperty("操作标题")
    private String operateTitle;

    @ApiModelProperty("操作Url")
    private String operateUrl;

    @ApiModelProperty("操作地址")
    private String operateAddress;

    @ApiModelProperty("操作类型")
    private String operateType;

    @ApiModelProperty("操作业务类型")
    private String operateBusinessType;

    @ApiModelProperty("操作状态")
    private String operateBusinessStatus;

    @ApiModelProperty("失败原因")
    private String operateErrorMessage;


}
