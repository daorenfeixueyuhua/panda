package com.daoren.auth.service;

import com.daoren.common.base.entity.R;
import com.daoren.common.base.entity.Result;
import com.daoren.dbagent.service.RemoteUserService;
import com.daoren.user.model.entity.Role;
import com.daoren.user.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author peng_da
 * @date 2022/8/17 17:28
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    private RemoteUserService remoteUserService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setRemoteUserService(RemoteUserService remoteUserService) {
        this.remoteUserService = remoteUserService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final R<User> userR = remoteUserService.getUserByUserName(username);
        if (userR.getCode() == Result.CODE_SUCCESS && userR.getData() != null) {
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            final User user = userR.getData();
            final R<List<Role>> listR = remoteUserService.getRolesByUserId(user.getId());
            if (listR.getCode() == Result.CODE_SUCCESS && listR.getData().size() != 0) {
                final List<Role> permissions = listR.getData();
                permissions.stream().filter(Objects::nonNull).forEach(p -> {
                    grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + p.getName()));
                });
            }

            return new org.springframework.security.core.userdetails.User(
                    user.getUserName(),
                    user.getPassWord(),
                    grantedAuthorities
            );
        }
        throw new UsernameNotFoundException("用户不存在");
    }
}
