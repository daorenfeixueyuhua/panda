package com.daoren.test.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    private Map<String, Object> map = new ConcurrentHashMap<>();

    @Test
    public void writeTest() {
        String key = "test1";
        String value = "12314";
        redisTemplate.opsForValue().set(key, value);
    }
}
