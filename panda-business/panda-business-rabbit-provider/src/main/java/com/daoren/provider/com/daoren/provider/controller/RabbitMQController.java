package com.daoren.provider.com.daoren.provider.controller;

import com.daoren.provider.com.daoren.provider.service.RabbitMQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author peng_da
 * @date 2023/1/3 16:53
 */
@SuppressWarnings("all")
@RestController
@RequestMapping("/panda/messages")
public class RabbitMQController {
    @Autowired
    private RabbitMQService rabbitMQService;

    @PostMapping
    public Boolean sendMsg(String message) {
        try {
            rabbitMQService.sendMsg(message);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
