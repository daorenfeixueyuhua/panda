package com.daoren.graphql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.daoren.graphql.model.entity.OperateLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 操作日志表 Mapper 接口
 * </p>
 *
 * @author daoren
 * @since 2022-07-19
 */
@Mapper
public interface OperateLogMapper extends BaseMapper<OperateLog> {

}
