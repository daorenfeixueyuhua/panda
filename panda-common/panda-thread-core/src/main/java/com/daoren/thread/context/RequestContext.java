package com.daoren.thread.context;

import com.daoren.common.base.context.PandaThreadLocalContext;

import java.util.HashMap;
import java.util.Map;

/**
 * request请求上下信息
 *
 * @author peng_da
 * @date 2022/8/31 14:14
 */
public class RequestContext extends PandaThreadLocalContext {
    /**
     * 请求参数
     */
    public static final ThreadLocal<Map<String, Object>> REQUEST_PARAMS = new ThreadLocal<>();

    /**
     * 执行初始化
     *
     * @author peng_da
     * @since 2022/8/30 16:07
     */
    @Override
    public void init() {
        REQUEST_PARAMS.set(new HashMap<>(0));
    }

    /**
     * 执行清理
     *
     * @author peng_da
     * @since 2022/8/30 16:08
     */
    @Override
    public void clear() {
        REQUEST_PARAMS.remove();
    }

    /**
     * 临时清理
     *
     * @author peng_da
     * @since 2022/8/30 16:15
     */
    @Override
    public void clearTemp() {
        REQUEST_PARAMS.remove();
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
