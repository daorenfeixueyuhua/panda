package com.daoren.dubbo.service.impl;


import com.daoren.dubbo.api.HelloService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author peng_da
 * @date 2022/10/31 17:36
 */
@DubboService
public class HelloServiceImpl implements HelloService {
    /**
     * say hello
     *
     * @return java.lang.String
     * @author peng_da
     * @since 2022/10/31 16:12
     */
    @Override
    public String sayHello() {
        System.out.println("Hello");
        return "Hello";
    }
}
