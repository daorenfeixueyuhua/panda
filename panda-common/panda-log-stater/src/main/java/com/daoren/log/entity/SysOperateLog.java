package com.daoren.log.entity;

import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.daoren.log.enums.BusinessStatus;
import com.daoren.log.enums.BusinessType;
import com.daoren.log.enums.OperatorType;
import com.daoren.mybatis.entity.BaseEntity;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 操作日志表
 * </p>
 *
 * @author pengda
 * @since 2022-02-11
 */

@TableName("sys_operate_log")
public class SysOperateLog extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 3636265721933858877L;
    /**
     * 操作人
     */
    private String operateUserId;

    /**
     * 操作人姓名
     */
    private String operateUserName;

    /**
     * 操作开始时间
     */
    private LocalDateTime operateStartTime;

    /**
     * 操作结束时间
     */
    private LocalDateTime operateEndTime;

    /**
     * 操作sql语句
     */
    private String operateSql;

    /**
     * 入参
     */
    private String operateParams;

    /**
     * 返回结果
     */
    private String operateResult;

    /**
     * 操作标题
     */
    @TableField(condition = SqlCondition.LIKE)
    private String operateTitle;

    /**
     * 操作Url
     */
    private String operateUrl;

    /**
     * 操作地址
     */
    private String operateAddress;

    /**
     * 操作类型
     */
    private OperatorType operateType;

    /**
     * 操作业务类型
     */
    private BusinessType operateBusinessType;

    /**
     * 操作状态
     */
    private BusinessStatus operateBusinessStatus;

    /**
     * 失败原因
     */
    private String operateErrorMessage;

    public String getOperateUserId() {
        return operateUserId;
    }

    public void setOperateUserId(String operateUserId) {
        this.operateUserId = operateUserId;
    }

    public String getOperateUserName() {
        return operateUserName;
    }

    public void setOperateUserName(String operateUserName) {
        this.operateUserName = operateUserName;
    }

    public LocalDateTime getOperateStartTime() {
        return operateStartTime;
    }

    public void setOperateStartTime(LocalDateTime operateStartTime) {
        this.operateStartTime = operateStartTime;
    }

    public LocalDateTime getOperateEndTime() {
        return operateEndTime;
    }

    public void setOperateEndTime(LocalDateTime operateEndTime) {
        this.operateEndTime = operateEndTime;
    }

    public String getOperateSql() {
        return operateSql;
    }

    public void setOperateSql(String operateSql) {
        this.operateSql = operateSql;
    }

    public String getOperateParams() {
        return operateParams;
    }

    public void setOperateParams(String operateParams) {
        this.operateParams = operateParams;
    }

    public String getOperateResult() {
        return operateResult;
    }

    public void setOperateResult(String operateResult) {
        this.operateResult = operateResult;
    }

    public String getOperateTitle() {
        return operateTitle;
    }

    public void setOperateTitle(String operateTitle) {
        this.operateTitle = operateTitle;
    }

    public String getOperateUrl() {
        return operateUrl;
    }

    public void setOperateUrl(String operateUrl) {
        this.operateUrl = operateUrl;
    }

    public String getOperateAddress() {
        return operateAddress;
    }

    public void setOperateAddress(String operateAddress) {
        this.operateAddress = operateAddress;
    }

    public OperatorType getOperateType() {
        return operateType;
    }

    public void setOperateType(OperatorType operateType) {
        this.operateType = operateType;
    }

    public BusinessType getOperateBusinessType() {
        return operateBusinessType;
    }

    public void setOperateBusinessType(BusinessType operateBusinessType) {
        this.operateBusinessType = operateBusinessType;
    }

    public BusinessStatus getOperateBusinessStatus() {
        return operateBusinessStatus;
    }

    public void setOperateBusinessStatus(BusinessStatus operateBusinessStatus) {
        this.operateBusinessStatus = operateBusinessStatus;
    }

    public String getOperateErrorMessage() {
        return operateErrorMessage;
    }

    public void setOperateErrorMessage(String operateErrorMessage) {
        this.operateErrorMessage = operateErrorMessage;
    }
}
