package com.daoren.feign.interceptor;

import com.daoren.auth.constant.SecurityConstant;
import com.daoren.common.base.context.TokenInfoContext;
import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * 传递token
 * @author peng_da
 * @date  2022/8/29 16:20
 */
public class OAuth2TokenFeignRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        final String tokenInfo = TokenInfoContext.token.get();
        template.header(SecurityConstant.TOKEN_INFO, tokenInfo);
    }
}
