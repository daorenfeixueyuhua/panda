package com.daoren.graphsqlql;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author peng_da
 * @version :
 * @date 2022/7/15 15:28
 * @since :
 */
@EnableCaching
@MapperScan("com.daoren.**.mapper")
@EnableAspectJAutoProxy
@EnableDiscoveryClient
@SpringBootApplication()
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
