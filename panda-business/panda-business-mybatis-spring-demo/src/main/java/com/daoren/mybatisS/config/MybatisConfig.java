package com.daoren.mybatisS.config;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @author peng_da
 * @date 2022/11/16 17:16
 */
@ComponentScan
@Configuration
public class MybatisConfig {
    /**
     * 数据源
     *
     * @author peng_da
     * @date 2022/11/16 17:46
     */
    @Bean
    public DataSource dataSource() {
        final MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl("jdbc:mysql://mysql:3306/ALGORTHIM_DATABASE");
        dataSource.setUser("root");
        dataSource.setPassword("123456");
        return dataSource;
    }

    /**
     * SqlSessionFactory
     *
     * @author peng_da
     * @date 2022/11/16 17:46
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        final SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource());
        String mapperLocations = "mappers/**/*.xml";
        final PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources(mapperLocations);
        factoryBean.setMapperLocations(resources);
        return factoryBean.getObject();
    }

    @Bean
    public MapperScannerConfigurer mapperScanner() {
        final MapperScannerConfigurer scannerConfigurer = new MapperScannerConfigurer();
        scannerConfigurer.setBasePackage("com.daoren.**.mapper");
        return scannerConfigurer;
    }
}
