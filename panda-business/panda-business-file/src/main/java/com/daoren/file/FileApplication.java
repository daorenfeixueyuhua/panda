package com.daoren.file;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * FileApplication
 *
 * @author peng_da
 * @version :
 * @date 2022/3/8 9:35
 * @since :
 */
@MapperScan("com.daoren.**.mapper")
@EnableAspectJAutoProxy
@EnableDiscoveryClient
@SpringBootApplication(exclude = {})
public class FileApplication {
    public static void main(String[] args) {
        SpringApplication.run(FileApplication.class, args);
    }
}
