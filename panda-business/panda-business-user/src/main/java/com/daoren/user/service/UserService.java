package com.daoren.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.daoren.user.model.entity.Permission;
import com.daoren.user.model.entity.Role;
import com.daoren.user.model.entity.User;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author daoren
 * @since 2022-03-29
 */
public interface UserService extends IService<User> {

    /**
     * 通过用户名查询用户信息
     * @author peng_da
     * @since 2022/8/17 16:59
     * @param username : 用户名
     * @return com.daoren.user.model.entity.User
     */
    User getUserByUserName(String username);

    /**
     * 通过用户Id查询权限
     * @author peng_da
     * @since 2022/8/17 17:10
     * @param userId : 用户Id
     * @return java.util.List<com.daoren.user.model.entity.Permission>
     */
    List<Permission> getPermissionListByUserId(String userId);


    /**
     * 通过用户Id查询角色
     * @author peng_da
     * @since 2022/8/18 10:05
     * @param userId :
     * @return java.util.List<com.daoren.user.model.entity.Role>
     */
    List<Role> findRolesByUserId(String userId);

}
