package com.daoren.auth.filter;

import com.daoren.auth.constant.SecurityConstant;
import com.daoren.auth.models.JwtTokenInfo;
import com.daoren.common.context.OpenFeignTokenInfoContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * AuthFilterCustom 用于获取token信息
 *
 * @author peng_da
 * @date 2022/8/25 18:16
 */
@Slf4j
public class AuthFilterCustom extends OncePerRequestFilter {
    private ObjectMapper objectMapper;
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String tokeInfo = request.getHeader(SecurityConstant.TOKEN_INFO);
        if (StringUtils.isBlank(tokeInfo)) {
            log.error("未找到用户信息");
            filterChain.doFilter(request, response);
            return;
        }
        OpenFeignTokenInfoContext.setToken(tokeInfo);
        JwtTokenInfo jwtTokenInfo = objectMapper.readValue(tokeInfo, JwtTokenInfo.class);
        log.info(SecurityConstant.TOKEN_INFO + " : " + objectMapper.writeValueAsString(jwtTokenInfo));
        final List<String> authorities1 = jwtTokenInfo.getAuthorities();
        String[] authorities = new String[authorities1.size()];
        authorities1.toArray(authorities);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        jwtTokenInfo.getUser_name(),
                        null,
                        AuthorityUtils.createAuthorityList(authorities)
                );
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
