package com.daoren.boot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author peng_da
 * @date 2022/12/6 9:06
 */
@Import(ConfigA.class)
@Configuration
public class Config {
    public Config() {
        System.out.println("Config.Config");
    }
}
