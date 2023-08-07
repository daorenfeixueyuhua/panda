package com.daoren.boot;

import com.daoren.boot.config.Config;
import com.daoren.boot.entity.User;
import com.daoren.boot.properties.DemoProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author peng_da
 * @date 2022/12/5 18:17
 */
@EnableConfigurationProperties
@ConfigurationPropertiesScan(value = "com.daoren.**")
@SpringBootApplication
public class BootDemoApplication {
    public static void main(String[] args) {
        final SpringApplication springApplication = new SpringApplication(BootDemoApplication.class);
        final ConfigurableApplicationContext context = springApplication.run(args);
        final User user = context.getBean(User.class);
        final Config config = context.getBean(Config.class);
        final DemoProperties bean = context.getBean(DemoProperties.class);
        System.out.println(context);
    }
}
