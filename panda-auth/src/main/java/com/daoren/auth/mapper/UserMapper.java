package com.daoren.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.daoren.auth.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author daoren
 * @since 2022-03-18
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 查询权限
     *
     * @param userId 用户Id
     * @return java.util.List<java.lang.String>
     * @author peng_da
     * @since 2022/3/18 11:10
     */
    List<String> findAllPermissions(@Param("userId") String userId);
}
