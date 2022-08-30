package com.daoren.dbagent.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * 校验配置
 * @author peng_da
 * @date  2022/8/11 12:35
 */
@Configuration
public class ValidatorConfig {
    @Bean
    public LocalValidatorFactoryBean validatorFactoryBean(){
        return new LocalValidatorFactoryBean();
    }
}
