package com.daoren.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.daoren.user.model.entity.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
    /**
     * 通过用户ID查询该用户的去全部权限
     * @author peng_da
     * @since 2022/8/17 17:07
     * @param userId :
     * @return java.util.List<com.daoren.user.model.entity.Permission>
     */
    List<Permission> findByUserId(@Param("userId") String userId);

}
