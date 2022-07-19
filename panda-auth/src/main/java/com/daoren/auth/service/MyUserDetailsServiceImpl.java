package com.daoren.auth.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.daoren.auth.dto.UserDetailsExpand;
import com.daoren.auth.entity.User;
import com.daoren.auth.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

/**
 * @author kdyzm
 */
@Service
@Slf4j
public class MyUserDetailsServiceImpl implements UserDetailsService {

    private final UserMapper userMapper;

    public MyUserDetailsServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUserName, username));
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException(username + "账号不存在");
        }
        List<String> allPermissions = userMapper.findAllPermissions(user.getId());
        String[] array = null;
        if (CollectionUtils.isEmpty(allPermissions)) {
            log.warn("{} 无任何权限", user.getUserName());
            array = new String[]{};
        } else {
            array = new String[allPermissions.size()];
            allPermissions.toArray(array);
        }
        return new UserDetailsExpand(user.getUserName(), user.getPassWord(), AuthorityUtils.createAuthorityList(array));
    }
}
