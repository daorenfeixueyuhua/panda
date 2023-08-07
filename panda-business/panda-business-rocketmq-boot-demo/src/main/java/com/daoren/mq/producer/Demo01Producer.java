package com.daoren.mq.producer;

import com.daoren.mq.message.Demo01Message;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class Demo01Producer {
    @Resource
    private RocketMQTemplate rabbitTemplate;

    public SendResult syncSend(Integer messageId) {
        final Demo01Message message = new Demo01Message();
        message.setId(messageId);
        rabbitTemplate.getProducer().setSendMsgTimeout(10000);
        return rabbitTemplate.syncSend(Demo01Message.TOPIC, message);
    }

    public void asyncSend(Integer id, SendCallback callback) {
        // 创建 Demo01Message 消息
        Demo01Message message = new Demo01Message();
        message.setId(id);
        // 异步发送消息
        rabbitTemplate.asyncSend(Demo01Message.TOPIC, message, callback);
    }

    public void onewaySend(Integer id) {
        // 创建 Demo01Message 消息
        Demo01Message message = new Demo01Message();
        message.setId(id);
        // oneway 发送消息
        rabbitTemplate.sendOneWay(Demo01Message.TOPIC, message);
    }
}
