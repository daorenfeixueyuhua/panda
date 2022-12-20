package com.daoren.tomcat.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author peng_da
 * @date  2022/12/16 11:08
 */
@RestController
public class IndexController {
    @GetMapping("/")
    public String index(){
        System.out.println("IndexController.index");
        return "index";
    }
}
