package com.daoren.dbagent.config;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.daoren.dbagent.properties.DbSourcesProperties;
import lombok.SneakyThrows;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据库配置
 *
 * @author peng_da
 * @date 2022/8/10 17:51
 */
@Configuration
public class MybatisConfig {
    private DbSourcesProperties dbSourcesProperties;
    @Autowired
    public void setDbSourcesProperties(DbSourcesProperties dbSourcesProperties) {
        this.dbSourcesProperties = dbSourcesProperties;
    }

    @Bean
    @Primary
    public DataSource multipleDataSource() {
        final DynmaicDataSource dynmaicDataSource = new DynmaicDataSource();
        Map<Object, Object> targetDataSource = new HashMap<>();
        dbSourcesProperties.getSources().forEach(item -> {
            targetDataSource.put(item.getKey(),
                    DataSourceBuilder.create()
                            .url(item.getUrl())
                            .username(item.getUsername())
                            .password(item.getPassword())
                            .driverClassName(item.getDriverClassName())
                            .build()
            );
        });
        dynmaicDataSource.setTargetDataSources(targetDataSource);
        dynmaicDataSource.setDefaultTargetDataSource(targetDataSource.get(dbSourcesProperties.getSources().get(0).getKey()));
        return dynmaicDataSource;
    }
    @Bean({"sqlSessionFactory"})
    @SneakyThrows
    public SqlSessionFactory sqlSessionFactory(){
        final MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(multipleDataSource());
        final MybatisConfiguration mybatisConfiguration = new MybatisConfiguration();
        mybatisConfiguration.setJdbcTypeForNull(JdbcType.NULL);
        mybatisConfiguration.setMapUnderscoreToCamelCase(true);
        mybatisConfiguration.setCacheEnabled(false);
        sqlSessionFactory.setConfiguration(mybatisConfiguration);
        return sqlSessionFactory.getObject();
    }
}
