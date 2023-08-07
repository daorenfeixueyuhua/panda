package com.daoren.spring;

import com.daoren.spring.entity.Role;
import com.daoren.spring.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Demo1 {
    @Test
    public void run() {
        final ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application.xml");
        final Role role1 = applicationContext.getBean(Role.class);
        final Role role2 = applicationContext.getBean(Role.class);
    }

    @Test
    public void aopTest() {
        final ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application.xml");
        final UserService userService = applicationContext.getBean("userServiceProxy", UserService.class);
        final UserService userService1 = applicationContext.getBean("userServiceProxy1", UserService.class);
        userService.say();
        userService1.say();
    }
}
