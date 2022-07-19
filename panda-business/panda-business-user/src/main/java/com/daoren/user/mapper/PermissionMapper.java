package com.daoren.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.daoren.user.model.entity.Permission;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 权限 Mapper 接口
 * </p>
 *
 * @author daoren
 * @since 2022-03-29
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

}
