package com.daoren.dubbo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.daoren.dubbo.api.HelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author peng_da
 * @date 2022/11/1 10:18
 */
@RestController
public class HelloController {

    @Reference
    private HelloService helloService;

    @GetMapping("/hello")
    public String hello() {
        return helloService.sayHello();
    }
}
