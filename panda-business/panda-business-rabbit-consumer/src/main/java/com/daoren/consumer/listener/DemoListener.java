package com.daoren.consumer.listener;

import com.daoren.consumer.constant.RabbitMQConstant;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author peng_da
 * @date 2023/1/3 17:37
 */
@Component
@RabbitListener(queuesToDeclare = @Queue(RabbitMQConstant.RABBITMQ_DEMO_TOPIC))
//@RabbitListener(queues = RabbitMQConstant.RABBITMQ_DEMO_TOPIC)
public class DemoListener {
    @RabbitHandler
    public void process(Map<String, Object> map) {
        System.out.println(map);
        System.out.println("DemoListener.process");
    }
}
