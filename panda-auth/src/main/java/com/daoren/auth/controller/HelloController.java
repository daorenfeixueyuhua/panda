package com.daoren.auth.controller;

import com.daoren.dbagent.service.RemoteUserService;
import com.daoren.user.model.entity.Permission;
import com.daoren.user.model.entity.User;
import com.daoren.web.annotation.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Hello
 * @author peng_da
 * @date  2022/8/17 16:17
 */
@RestController
public class HelloController {

    private RemoteUserService remoteUserService;

    @Autowired
    public void setRemoteUserService(RemoteUserService remoteUserService) {
        this.remoteUserService = remoteUserService;
    }

    @GetMapping("/hello")
    public String hello(){
        return "Hello";
    }

    /**
     * 通过用户名查询用户
     * @author peng_da
     * @since 2022/8/17 17:13
     * @param userName :
     * @return com.daoren.user.model.entity.User
     */
    @ResponseResult
    @GetMapping("/userName/{userName}")
    public User getUserByUserName(@PathVariable String userName){
        return remoteUserService.getUserByUserName(userName).getData();
    }

    @ResponseResult
    @GetMapping("/{userId}/permission")
    public List<Permission> getPermissionByUserId(@PathVariable String userId){
        return remoteUserService.getPermissionByUserId(userId).getData();
    }


    @GetMapping("/decision/1")
    public String decision1(){
        return "decision1";
    }

    @GetMapping("/admin/1")
    public String admin1(){
        return "admin1";
    }

    @ResponseResult
    @ResponseBody
    @GetMapping("/details")
    public Object getCurrentUser(){
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final Object userDetails = authentication.getPrincipal();
        return userDetails;
    }
}
