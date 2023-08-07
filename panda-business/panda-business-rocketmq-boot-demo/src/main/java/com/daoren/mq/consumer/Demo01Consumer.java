package com.daoren.mq.consumer;

import com.daoren.mq.message.Demo01Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RocketMQMessageListener(
        topic = Demo01Message.TOPIC,
        consumerGroup = "demo01-consumer-group-" + Demo01Message.TOPIC
)
public class Demo01Consumer implements RocketMQListener<Demo01Message> {
    @Override
    public void onMessage(Demo01Message message) {
        log.info("[message][id:{}, content:{}]", Thread.currentThread().getId(), message);
    }
}
