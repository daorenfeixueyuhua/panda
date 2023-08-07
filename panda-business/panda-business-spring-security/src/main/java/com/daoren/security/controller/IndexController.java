package com.daoren.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author peng_da
 * @date 2022/12/5 11:06
 */
@RestController
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "Hello";
    }
}
