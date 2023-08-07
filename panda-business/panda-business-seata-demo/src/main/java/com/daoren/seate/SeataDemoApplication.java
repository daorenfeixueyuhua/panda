package com.daoren.seate;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author pengda
 */
@ComponentScan(basePackages = {"com.daoren.seate.**"})
@MapperScan(basePackages = {"com.daoren.seate.mapper.**"})
@SpringBootApplication
public class SeataDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(SeataDemoApplication.class, args);
    }
}