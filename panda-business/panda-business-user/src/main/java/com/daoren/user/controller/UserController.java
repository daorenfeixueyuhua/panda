package com.daoren.user.controller;


import com.daoren.user.model.entity.User;
import com.daoren.user.service.UserService;
import com.daoren.web.annotation.ResponseResult;
import org.springframework.web.bind.annotation.*;

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
}
