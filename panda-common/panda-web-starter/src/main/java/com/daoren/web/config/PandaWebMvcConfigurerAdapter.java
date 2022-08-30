package com.daoren.web.config;


import com.daoren.web.handler.ValidationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author peng_da
 * @version :
 * @date 2022/5/27 11:14
 * @since :
 */
//@Configuration
public class PandaWebMvcConfigurerAdapter implements WebMvcConfigurer {
    @Autowired
    private ValidationInterceptor validationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(validationInterceptor).addPathPatterns("/**");
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
