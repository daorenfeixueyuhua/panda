package com.daoren.provider.com.daoren.provider.service.impl;

import com.daoren.provider.com.daoren.provider.constant.RabbitMQConstant;
import com.daoren.provider.com.daoren.provider.service.RabbitMQService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 * @author peng_da
 * @date 2023/1/3 16:45
 */
@Service
public class RabbitMQServiceImpl implements RabbitMQService {
    private final RabbitTemplate rabbitTemplate;
    private final DateTimeFormatter dateTimeFormatter;
    private final ObjectMapper objectMapper;

    public RabbitMQServiceImpl(RabbitTemplate rabbitTemplate, DateTimeFormatter dateTimeFormatter, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.dateTimeFormatter = dateTimeFormatter;
        this.objectMapper = objectMapper;
    }

    @Override
    public String sendMsg(String msg) {
        String msgId = UUID.randomUUID().toString().replace("-", "").substring(0, 32);
        String sendTime = dateTimeFormatter.format(LocalDateTime.now());
        Map<String, Object> map = new HashMap<>();
        map.put("msgId", msgId);
        map.put("sendTime", sendTime);
        map.put("msg", msg);
        try {
            System.out.println(objectMapper.writeValueAsString(map));
        } catch (Exception e) {
            e.printStackTrace();
        }
        rabbitTemplate.convertAndSend(RabbitMQConstant.RABBITMQ_DEMO_DIRECT_EXCHANGE,
                RabbitMQConstant.RABBITMQ_DEMO_DIRECT_ROUTING, map);
        return "ok";
    }
}
