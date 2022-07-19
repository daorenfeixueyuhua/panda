package com.daoren.graphsqlql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.daoren.graphsqlql.model.entity.SysPerson;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 个人信息表 Mapper 接口
 * </p>
 *
 * @author daoren
 * @since 2022-03-03
 */
@Mapper
public interface SysPersonMapper extends BaseMapper<SysPerson> {

}
