package com.daoren.mq.producer;

import com.daoren.mq.RocketMqApplication;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RocketMqApplication.class)
public class Demo01ProducerTest {

    @Autowired
    private Demo01Producer producer;

    @SneakyThrows
    @Test
    public void sysnSend() {
        int id = (int) (System.currentTimeMillis() / 1000);
        SendResult result = producer.syncSend(id);
        log.info("[testSyncSend][发送编号：[{}] 发送结果：[{}]]", id, result);

        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }

    @Test
    public void asyncSend() {
    }

    @Test
    public void onewaySend() {
    }
}