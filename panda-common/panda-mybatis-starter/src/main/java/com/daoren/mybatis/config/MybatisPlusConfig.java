package com.daoren.mybatis.config;

import com.baomidou.dynamic.datasource.plugin.MasterSlaveAutoRoutingPlugin;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceProperties;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.incrementer.IKeyGenerator;
import com.baomidou.mybatisplus.extension.incrementer.H2KeyGenerator;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.daoren.mybatis.generator.MybatisPlusGenerator;
import com.daoren.mybatis.interceptor.ParamsDataScopeInterceptor;
import com.daoren.mybatis.interceptor.SqlLogInterceptor;
import com.daoren.mybatis.properties.MybatisPlusGeneratorProperties;
import com.daoren.mybatis.properties.MybatisPlusPageProperties;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

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
    private MybatisPlusGeneratorProperties generatorProperties;
    private DynamicDataSourceProperties dynamicDataSourceProperties;

    @Autowired
    public void setGeneratorProperties(MybatisPlusGeneratorProperties generatorProperties) {
        this.generatorProperties = generatorProperties;
    }

    @Autowired
    public void setDynamicDataSourceProperties(DynamicDataSourceProperties dynamicDataSourceProperties) {
        this.dynamicDataSourceProperties = dynamicDataSourceProperties;
    }

    @Bean("mybatisPlusKeyGenerator")
    public IKeyGenerator keyGenerator() {
        return new H2KeyGenerator();
    }

    /**
     * MybatisPlus插件
     *
     * @return com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor
     * @author peng_da
     * @since 2022/11/10 9:45
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        interceptor.addInnerInterceptor(new SqlLogInterceptor());
        return interceptor;
    }

    @Bean
    public MybatisPlusGenerator mybatisPlusGenerator() {
        final MybatisPlusGenerator generator = new MybatisPlusGenerator();
        generator.setProperties(generatorProperties);
        generator.setDynamicDataSourceProperties(dynamicDataSourceProperties);
        return generator;
    }

    /**
     * 读写分离插件
     *
     * @return org.apache.ibatis.plugin.Interceptor
     * @author peng_da
     * @since 2022/12/26 15:19
     */
    @Bean(name = "masterSlaveAutoRoutingPlugin")
    public Interceptor masterSlaveAutoRoutingPlugin() {
        return new MasterSlaveAutoRoutingPlugin();
    }

    /**
     * 自定义数据权限拦截器
     *
     * @return org.apache.ibatis.plugin.Interceptor
     * @author peng_da
     * @since 2022/12/26 15:41
     */
    @DependsOn(value = "masterSlaveAutoRoutingPlugin")
    @Bean(name = "paramsDataScopeInterceptor")
    public Interceptor paramsDataScopeInterceptor() {
        // 可能需要调整bean的顺序
        return new ParamsDataScopeInterceptor();
    }
}
