package com.daoren.spring.controller;

import com.daoren.spring.enums.SportType;
import com.daoren.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author peng_da
 * @date 2022/11/25 17:48
 */
@Controller
public class IndexController {
    private final UserService userService;

    @Autowired
    public IndexController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    @ResponseBody
    public String index() {
        userService.say();
//        throw new RuntimeException("卧槽");
        return "index";
    }

    @GetMapping("/hello/{name}")
    @ResponseBody
    public String hello(@PathVariable String name, String age) {
        return name + " " + age;
    }

    @GetMapping("/sport")
    @ResponseBody
    public String sportType(SportType sportType) {
        System.out.println(sportType);
        return "sportType";
    }
}
