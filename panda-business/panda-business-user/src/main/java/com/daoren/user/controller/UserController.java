package com.daoren.user.controller;


import com.daoren.log.annotation.Log;
import com.daoren.user.model.entity.Permission;
import com.daoren.user.model.entity.Role;
import com.daoren.user.model.entity.User;
import com.daoren.user.service.UserService;
import com.daoren.web.annotation.ResponseResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author daoren
 * @since 2022-03-29
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService baseService;

    public UserController(UserService baseService) {
        this.baseService = baseService;
    }

    /**
     * 新增信息
     *
     * @param entity : 实体信息
     * @return java.lang.Boolean
     * @author peng_da
     * @since 2022/3/29 15:43
     */
    @ResponseResult
    @PostMapping
    public Boolean insert(@RequestBody User entity) {
        return baseService.save(entity);
    }

    /**
     * 通过id查询用户
     *
     * @param id : Id
     * @return com.daoren.user.model.entity.User
     * @author peng_da
     * @since 2022/3/29 15:57
     */
    @ResponseResult
    @GetMapping("/{id}")
    public User one(@PathVariable String id) {
        return baseService.getById(id);
    }

    /**
     * 通过用户名查询用户
     * @author peng_da
     * @since 2022/8/17 17:13
     * @param userName : 用户名
     * @return com.daoren.user.model.entity.User
     */
    @Log(title = "用户信息")
    @ResponseResult
    @GetMapping("/userName/{userName}")
    public User getUserByUserName(@PathVariable String userName){
        return baseService.getUserByUserName(userName);
    }
    /**
     * 通过用户Id查询权限
     * @author peng_da
     * @since 2022/8/17 17:34
     * @param userId : userId
     * @return java.util.List<com.daoren.user.model.entity.Permission>
     */
    @ResponseResult
    @GetMapping("/{userId}/permissions")
    public List<Permission> getPermissionByUserId(@PathVariable String userId){
        return baseService.getPermissionListByUserId(userId);
    }

    /**
     * 通过用户Id查询角色
     * @author peng_da
     * @since 2022/8/18 10:09
     * @param userId : userId
     * @return java.util.List<com.daoren.user.model.entity.Role>
     */
    @ResponseResult
    @GetMapping("/{userId}/roles")
    public List<Role> getRolesByUserId(@PathVariable("userId")String userId){
        return baseService.findRolesByUserId(userId);
    }
}
