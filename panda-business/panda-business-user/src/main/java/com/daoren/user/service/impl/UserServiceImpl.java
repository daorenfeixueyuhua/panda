package com.daoren.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.daoren.user.mapper.PermissionMapper;
import com.daoren.user.mapper.RoleMapper;
import com.daoren.user.mapper.UserMapper;
import com.daoren.user.model.entity.Permission;
import com.daoren.user.model.entity.Role;
import com.daoren.user.model.entity.User;
import com.daoren.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author daoren
 * @since 2022-03-29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    private PermissionMapper permissionMapper;
    private RoleMapper roleMapper;

    @Autowired
    public void setPermissionMapper(PermissionMapper permissionMapper) {
        this.permissionMapper = permissionMapper;
    }

    @Autowired
    public void setRoleMapper(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public User getUserByUserName(String username) {
        final LambdaQueryWrapper<User> wrapper = new QueryWrapper<User>().lambda();
        wrapper.eq(User::getUserName, username);
        return this.baseMapper.selectOne(wrapper);
    }

    @Override
    public List<Permission> getPermissionListByUserId(String userId) {
        return permissionMapper.findByUserId(userId);
    }

    /**
     * 通过用户Id查询角色
     *
     * @param userId :
     * @return java.util.List<com.daoren.user.model.entity.Role>
     * @author peng_da
     * @since 2022/8/18 10:05
     */
    @Override
    public List<Role> findRolesByUserId(String userId) {
        return roleMapper.findRoleByUserId(userId);
    }
}
