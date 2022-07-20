package com.daoren.graphql.config;


import com.daoren.graphql.extend.FdGraphQLObjectMapper;
import graphql.servlet.GraphQLObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


/**
 * @author peng_da
 * @version :
 * @date 2022/7/19 15:03
 * @since :
 */
@ComponentScan("com.daoren.graphql.extend.**")
@Configuration
public class MyGraphQlConfig {


    @Bean
    public GraphQLObjectMapper graphQLObjectMapper() {
        FdGraphQLObjectMapper.Builder builder = FdGraphQLObjectMapper.newBuilder2();
        return builder.build();
    }

}
