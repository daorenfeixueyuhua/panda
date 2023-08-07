package com.daoren.spring.aop;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author peng_da
 * @date 2022/11/24 17:33
 */
public class UserServiceAop implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("UserServiceAop.before");
    }
}
