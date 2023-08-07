package com.daoren.tomcat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author peng_da
 * @date 2023/1/2 13:09
 */
@SpringBootApplication
@ServletComponentScan(value = "com.daoren.**")
public class TomcatDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(TomcatDemoApplication.class, args);
    }
}
