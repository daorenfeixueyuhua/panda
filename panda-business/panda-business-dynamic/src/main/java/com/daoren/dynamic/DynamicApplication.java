package com.daoren.dynamic;

import com.daoren.feign.annotation.EnablePdFeignClients;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author peng_da
 * @date 2022/11/3 15:29
 */
@EnablePdFeignClients
@EnableCaching
@MapperScan("com.daoren.**.mapper")
@EnableAspectJAutoProxy
@EnableDiscoveryClient
@SpringBootApplication
public class DynamicApplication {
    public static void main(String[] args) {
        SpringApplication.run(DynamicApplication.class, args);
    }
}
