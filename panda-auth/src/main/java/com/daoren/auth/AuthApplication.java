package com.daoren.auth;

import com.daoren.feign.annotation.EnablePdFeignClients;
import com.daoren.web.config.FilterConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Auth
 *
 * @author peng_da
 * @version 1.0
 * @since 2022/3/17 10:17
 */
@EnableDiscoveryClient
@EnablePdFeignClients
@SpringBootApplication(exclude = FilterConfig.class)
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
