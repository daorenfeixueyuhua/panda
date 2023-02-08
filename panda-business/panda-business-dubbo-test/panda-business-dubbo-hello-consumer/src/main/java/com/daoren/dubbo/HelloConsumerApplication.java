package com.daoren.dubbo;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author peng_da
 * @date 2022/11/1 10:19
 */
@EnableDubbo
@SpringBootApplication
public class HelloConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(HelloConsumerApplication.class, args);
    }
}
