package com.daoren.test.controller;

import com.daoren.web.annotation.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * <p>RestTemplateController</p>
 *
 * @author peng_da
 * @date 2022/11/26 18:06
 */
@RestController("/openApi/rest")
public class RTController {
    private final RestTemplate restTemplate;

    @Autowired
    public RTController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    /**
     * <p>hello</p>
     *
     * @return int
     * @author peng_da
     * @since 2022/11/26 18:09
     */
    @GetMapping("/")
    public int hello() {
        System.out.println(restTemplate);
        return 1;
    }

    @ResponseResult
    @PostMapping("/")
    public Boolean save(String name, Integer age) {
        return true;
    }


}
