package com.daoren.auth.constant;
/**
 * 常量
 * @author peng_da
 * @date  2022/8/25 13:58
 */
public class SecurityConstant {
    /** 白名单 */
    public static final String[] IGNORE_PATH = {
            "/oauth/**", "/login/**", "/logout/**",
            "/openApi/**"
    };
    /** 需要认证的接口 */
    public static final String AUTH_PATH = "/**";
    /** token 前缀 */
    public static final String BEAR_HEADER = "Bearer ";
    /** token加密密钥 */
    public static final String SIGNING_KEY = "auth123";
    /** token信息key */
    public static final String TOKEN_INFO = "token-info";
}
