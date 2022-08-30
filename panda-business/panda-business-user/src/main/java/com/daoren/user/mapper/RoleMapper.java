package com.daoren.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.daoren.user.model.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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


    /**
     * 通过用户Id查询角色
     * @author peng_da
     * @since 2022/8/18 10:02
     * @param userId :
     * @return java.util.List<com.daoren.user.model.entity.Role>
     */
    List<Role> findRoleByUserId(@Param("userId") String userId);

}
