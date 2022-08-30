package com.daoren.feign.config;

import com.daoren.feign.interceptor.OAuth2TokenFeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 *
 * @author peng_da
 * @date  2022/8/17 10:24
 */
@Configuration
public class FeignConfig {

    @Bean
    public OAuth2TokenFeignRequestInterceptor oAuth2TokenFeignRequestInterceptor(){
        return new OAuth2TokenFeignRequestInterceptor();
    }
}
