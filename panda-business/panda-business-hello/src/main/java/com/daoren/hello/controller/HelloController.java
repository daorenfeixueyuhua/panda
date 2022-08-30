package com.daoren.hello.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public Integer hello(){
        return 1;
    }
    @GetMapping("/openApi/hello")
    public Integer openHello(){
        return 1;
    }
}
