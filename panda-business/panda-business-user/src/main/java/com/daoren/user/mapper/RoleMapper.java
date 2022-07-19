package com.daoren.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.daoren.user.model.entity.Role;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 角色 Mapper 接口
 * </p>
 *
 * @author daoren
 * @since 2022-03-29
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

}
