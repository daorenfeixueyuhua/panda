package com.daoren.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author peng_da
 * @date 2022/12/12 17:27
 */
@RestController
public class IndexController {
    @GetMapping("/")
    public String index() {
        return "index";
    }
}
