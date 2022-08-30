package com.daoren.api.filter;


import com.daoren.api.matcher.AntPathRequestMatcher;
import com.daoren.auth.constant.SecurityConstant;
import com.daoren.auth.properties.PandaSecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.MacSigner;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Token解析
 * @author peng_da
 * @date  2022/8/25 18:08
 */
@Component
@Slf4j
public class TokenFilter implements GlobalFilter, Ordered {
    @Autowired
    private PandaSecurityProperties securityProperties;
    private final List<AntPathRequestMatcher> requestMatchers = new LinkedList<>();

    @PostConstruct
    public void init(){
        final List<String> ignoreUrl = securityProperties.getIgnoreUrl();
        ignoreUrl.addAll(Arrays.asList(SecurityConstant.IGNORE_PATH));
        for (String url : ignoreUrl) {
            requestMatchers.add(new AntPathRequestMatcher(url));
        }
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 白名单处理
        for (AntPathRequestMatcher matcher : requestMatchers) {
            if (matcher.matches(exchange, securityProperties.getStripPrefix())){
                return chain.filter(exchange);
            }
        }
        final String token = exchange
                .getRequest()
                .getHeaders()
                .getFirst(HttpHeaders.AUTHORIZATION);
        if (StringUtils.isBlank(token)){
            return unAuthorized(exchange);
        }
        String payLoad;
        try{
            final Jwt jwt = JwtHelper.decodeAndVerify(token.replace(SecurityConstant.BEAR_HEADER, ""), new MacSigner(SecurityConstant.SIGNING_KEY));
            payLoad = jwt.getClaims();
        }catch (Exception e){
            log.error("验签失败",e);
            return unAuthorized(exchange);
        }
        final ServerHttpRequest.Builder builder = exchange.getRequest().mutate();
        // 设置明文token
        builder.header(SecurityConstant.TOKEN_INFO, payLoad).build();
        return chain.filter(exchange.mutate().request(builder.build()).build());

    }

    private Mono<Void> unAuthorized(ServerWebExchange exchange){
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }
    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
