package com.daoren.tomcat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 *
 * @author peng_da
 * @date  2022/12/15 17:49
 */
@SpringBootApplication
@ServletComponentScan(value = "com.daoren.**")
public class TomcatDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(TomcatDemoApplication.class, args);
    }
}
