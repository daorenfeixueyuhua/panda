package com.daoren.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Swagger 配置
 *
 * @author peng_da
 * @version :
 * @date 2022/3/3 9:42
 * @since :
 */
@EnableOpenApi
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .enable(true)
                .groupName("ZRJ")
                .select()
                .build();
    }

    public ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("测试")
                .version("0.0.1")
                .description("测试")
                .build();
    }
}
