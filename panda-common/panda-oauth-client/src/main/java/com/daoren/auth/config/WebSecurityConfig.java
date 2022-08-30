package com.daoren.auth.config;

import com.daoren.auth.constant.SecurityConstant;
import com.daoren.auth.filter.AuthFilterCustom;
import com.daoren.auth.properties.PandaSecurityProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author peng_da
 * @date 2022/8/23 15:28
 */
@Slf4j
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter implements Ordered {
    private PandaSecurityProperties securityProperties;

    @Autowired
    public void setSecurityProperties(PandaSecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    @Autowired
    private ObjectMapper objectMapper;

    @Bean
    public AuthFilterCustom authFilterCustom() {
        final AuthFilterCustom authFilterCustom = new AuthFilterCustom();
        authFilterCustom.setObjectMapper(objectMapper);
        return authFilterCustom;
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        final List<String> ignoreUrlList = Optional.ofNullable(securityProperties.getIgnoreUrl()).orElse(new ArrayList<>());
        final String[] customIgnorePath = new String[ignoreUrlList.size()];
        ignoreUrlList.toArray(customIgnorePath);
        http.csrf()
                .disable()
                .authorizeRequests()
                // 配置忽略url
                .antMatchers(customIgnorePath).permitAll()
                .antMatchers(SecurityConstant.IGNORE_PATH).permitAll()
                // 其他所有请求都需要认证
                .antMatchers(SecurityConstant.AUTH_PATH).authenticated()
                .anyRequest().permitAll()
                .and()
                .addFilterAfter(authFilterCustom(), BasicAuthenticationFilter.class)
                .sessionManagement()
                // 禁用session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        ;
        log.info("【security 系统白名单 " + Arrays.toString(SecurityConstant.IGNORE_PATH) + "】");
        log.info("【security 自定义白名单 " + Arrays.toString(customIgnorePath) + "】");
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
