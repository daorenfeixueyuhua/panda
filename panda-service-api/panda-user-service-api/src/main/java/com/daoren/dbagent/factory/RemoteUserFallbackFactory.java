package com.daoren.dbagent.factory;

import com.daoren.common.entity.R;
import com.daoren.dbagent.service.RemoteUserService;
import com.daoren.user.model.entity.Permission;
import com.daoren.user.model.entity.Role;
import com.daoren.user.model.entity.User;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author peng_da
 * @date 2022/8/17 17:23
 */
@Component
public class RemoteUserFallbackFactory implements FallbackFactory<RemoteUserService> {
    @Override
    public RemoteUserService create(Throwable cause) {
        return new RemoteUserService() {
            @Override
            public R<User> getUserByUserName(String userName) {
                return R.fail("查询用户失败：" + cause.getMessage());
            }

            @Override
            public R<List<Permission>> getPermissionByUserId(String userId) {
                return R.fail("查询用户权限失败：" + cause.getMessage());
            }

            @Override
            public R<List<Role>> getRolesByUserId(String userId) {
                return R.fail("查询用户角色失败：" + cause.getMessage());
            }
        };
    }
}
