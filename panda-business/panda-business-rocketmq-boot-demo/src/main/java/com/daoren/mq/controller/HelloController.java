package com.daoren.mq.controller;


import com.daoren.mq.producer.Demo01Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired
    private Demo01Producer producer;

    @GetMapping("/hello")
    public String hello(Integer messageId) throws Exception {
        producer.syncSend(messageId);
        return "Hello";
    }
}
