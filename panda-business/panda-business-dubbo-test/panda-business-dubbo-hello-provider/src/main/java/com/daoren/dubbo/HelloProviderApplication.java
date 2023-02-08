package com.daoren.dubbo;


import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * xx
 *
 * @author peng_da
 * @date 2022/10/31 17:37
 */
@EnableDubbo
@SpringBootApplication
public class HelloProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(HelloProviderApplication.class, args);
    }
}
