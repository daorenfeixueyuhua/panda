package com.daoren.dbagent.service;

import com.daoren.common.constant.ServiceNameConstants;
import com.daoren.common.entity.R;
import com.daoren.dbagent.factory.RemoteUserFallbackFactory;
import com.daoren.user.model.entity.Permission;
import com.daoren.user.model.entity.Role;
import com.daoren.user.model.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author peng_da
 * @date 2022/8/10 17:14
 */
@FeignClient(contextId = "remoteUserService", value = ServiceNameConstants.USER_SERVICE,
        fallbackFactory = RemoteUserFallbackFactory.class)
public interface RemoteUserService {
    /**
     * 通过用户名查询用户
     * @author peng_da
     * @since 2022/8/17 17:13
     * @param userName : 用户名
     * @return com.daoren.user.model.entity.User
     */
    @GetMapping(value = "/users/userName/{userName}")
    R<User> getUserByUserName(@PathVariable(value = "userName") String userName);
    /**
     * 通过用户Id查询权限
     * @author peng_da
     * @since 2022/8/17 17:34
     * @param userId : userId
     * @return java.util.List<com.daoren.user.model.entity.Permission>
     */
    @GetMapping(value = "/users/{userId}/permissions")
    R<List<Permission>> getPermissionByUserId(@PathVariable(value = "userId") String userId);

    /**
     * 通过用户Id查询角色
     * @author peng_da
     * @since 2022/8/18 10:09
     * @param userId : userId
     * @return com.daoren.common.entity.R<java.util.List<com.daoren.user.model.entity.Role>>
     */
    @GetMapping(value = "/users/{userId}/roles")
    R<List<Role>> getRolesByUserId(@PathVariable("userId") String userId);
}
