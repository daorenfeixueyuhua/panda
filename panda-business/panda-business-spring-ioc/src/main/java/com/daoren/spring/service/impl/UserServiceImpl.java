package com.daoren.spring.service.impl;

import com.daoren.spring.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author peng_da
 * @date 2022/11/24 16:45
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public void say() {
        System.out.println("UserServiceImpl.say");
    }
}
