package com.daoren.test.controller;

import com.daoren.log.annotation.Log;
import com.daoren.web.annotation.ApiVersion;
import com.daoren.web.annotation.ResponseResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @author peng_da
 * @version :
 * @date 2022/3/4 10:49
 * @since :
 */
@Api(tags = "欢迎")
@RestController
public class HelloController {
    @Resource
    private ObjectMapper objectMapper;


    @ApiVersion
    @Log
    @ApiOperation("欢迎")
    @ResponseBody
    @ResponseResult
    @GetMapping("/{version}/hello")
    public Integer hello() throws JsonProcessingException {
        // todo 需要解决版本控制器中url重复的问题。
        System.out.println(objectMapper.writeValueAsString(LocalDateTime.now()));
        return 1;
    }


    @ApiVersion(version = 2.0)
    @Log
    @ApiOperation("欢迎")
    @ResponseBody
    @ResponseResult
    @GetMapping("/{version}/hello1")
    public Integer helloV2() throws JsonProcessingException {
        System.out.println(objectMapper.writeValueAsString(LocalDateTime.now()));
        return 1;
    }
}
