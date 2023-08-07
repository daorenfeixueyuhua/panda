package com.daoren.common.base.context;

import com.alibaba.ttl.TransmittableThreadLocal;

/**
 * 用户信息上下文信息
 *
 * @author peng_da
 * @date 2022/8/29 17:06
 */
public class TokenInfoContext extends PandaThreadLocalContext {
    public static TransmittableThreadLocal<String> token = new TransmittableThreadLocal<>();

    public static void setToken(String tokenInfo) {
        token.set(tokenInfo);
    }

    /**
     * 执行初始化
     *
     * @author peng_da
     * @since 2022/8/30 16:07
     */
    @Override
    public void init() {
        token.set(null);
    }

    /**
     * 执行清理
     *
     * @author peng_da
     * @since 2022/8/30 16:08
     */
    @Override
    public void clear() {
        token.set(null);
    }

    /**
     * 临时清理
     *
     * @author peng_da
     * @since 2022/8/30 16:15
     */
    @Override
    public void clearTemp() {
        token.set(null);
    }

    /**
     * 计数 获取线程中数据量
     *
     * @author peng_da
     * @since 2022/8/30 16:08
     */
    @Override
    public void count() {
        super.count();
    }
}
