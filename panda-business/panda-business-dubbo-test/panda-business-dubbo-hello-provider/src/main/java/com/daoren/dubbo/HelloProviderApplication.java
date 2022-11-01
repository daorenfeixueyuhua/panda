package com.daoren.dubbo;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author peng_da
 * @date 2022/10/31 17:37
 */
@EnableDubboConfiguration
@SpringBootApplication
public class HelloProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(HelloProviderApplication.class, args);
    }
}
