package com.daoren.mongodb;

import com.daoren.web.config.PandaWebMvcConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author peng_da
 * @date 2022/11/14 10:33
 */
@SpringBootApplication(exclude = {PandaWebMvcConfig.class})
public class MongoDbApplication {
    public static void main(String[] args) {
        SpringApplication.run(MongoDbApplication.class, args);
    }
}
