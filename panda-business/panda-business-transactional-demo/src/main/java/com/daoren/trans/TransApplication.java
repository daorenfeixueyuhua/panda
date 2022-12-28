package com.daoren.trans;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 *  事务测试
 *
 * @author peng_da
 * @date 2022/12/26 11:03
 */
@MapperScan("com.daoren.**.mapper")
@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class TransApplication {
    public static void main(String[] args) {
        SpringApplication.run(TransApplication.class, args);
    }
}
