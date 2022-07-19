package com.daoren.graphsqlql.config;

import com.daoren.graphsqlql.extend.FdGraphQLObjectMapper;
import graphql.servlet.GraphQLObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author peng_da
 * @version :
 * @date 2022/7/19 15:03
 * @since :
 */
@Configuration
public class MyGraphQlConfig {


    @Bean
    public GraphQLObjectMapper graphQLObjectMapper() {
        FdGraphQLObjectMapper.Builder builder = FdGraphQLObjectMapper.newBuilder2();
        return builder.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
