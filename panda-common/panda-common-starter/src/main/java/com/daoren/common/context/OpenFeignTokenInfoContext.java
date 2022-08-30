package com.daoren.common.context;
/**
 * todo 但是需要考虑销毁问题
 * 用户信息上下文信息
 * @author peng_da
 * @date  2022/8/29 17:06
 */
public class OpenFeignTokenInfoContext {
    public static ThreadLocal<String> token = new ThreadLocal<>();
    public static void setToken(String tokenInfo){
        token.set(tokenInfo);
    }
    public static void clearToken(){
        token.remove();
    }
}
