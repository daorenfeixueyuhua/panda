package com.daoren.log.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.daoren.log.entity.SysOperateLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 操作日志表 Mapper 接口
 * </p>
 *
 * @author pengda
 * @since 2022-02-11
 */
@Mapper
public interface SysOperateLogMapper extends BaseMapper<SysOperateLog> {

}
