package com.daoren.test.controller;

import com.daoren.web.annotation.RepeatSubmit;
import com.daoren.web.annotation.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 放重复提交测试
 *
 * @author peng_da
 * @date 2022/11/1 16:15
 */
@RestController
@RequestMapping("/repeat")
public class RepeatController {

    @RepeatSubmit
    @ResponseResult
    @GetMapping("/test1")
    public int test1() {
        return 1;
    }
}
