package com.daoren.mybatis.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.incrementer.IKeyGenerator;
import com.baomidou.mybatisplus.extension.incrementer.H2KeyGenerator;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.daoren.mybatis.generator.MybatisPlusGenerator;
import com.daoren.mybatis.interceptor.ParamsDataScopeInterceptor;
import com.daoren.mybatis.properties.MybatisPlusGeneratorProperties;
import com.daoren.mybatis.properties.MybatisPlusPageProperties;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MybatisPLusConfig
 *
 * @author peng_da
 * @version :
 * @date 2022/3/3 9:56
 * @since :
 */
@Configuration
@EnableConfigurationProperties({MybatisPlusPageProperties.class, MybatisPlusGeneratorProperties.class})
public class MybatisPlusConfig {

    @Autowired
    private MybatisPlusGeneratorProperties generatorProperties;

    @Bean("mybatisPlusKeyGenerator")
    public IKeyGenerator keyGenerator() {
        return new H2KeyGenerator();
    }

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

    @Bean
    public MybatisPlusGenerator mybatisPlusGenerator() {
        final MybatisPlusGenerator generator = new MybatisPlusGenerator();
        generator.setProperties(generatorProperties);
        return generator;
    }

    @Bean(name = "paramsDataScopeInterceptor")
    public Interceptor paramsDataScopeInterceptor(){
        // 可能需要调整bean的顺序
        return new ParamsDataScopeInterceptor();
    }
}
