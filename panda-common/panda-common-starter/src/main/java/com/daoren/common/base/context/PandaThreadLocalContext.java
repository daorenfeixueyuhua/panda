package com.daoren.common.base.context;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 继承本类，根据不同的业务类型，一般用于存储线程变量
 *
 * @author peng_da
 * @date 2022/8/30 16:04
 */
public abstract class PandaThreadLocalContext {
    public final static List<PandaThreadLocalContext> THREAD_LOCAL_HANDLERS
            = Collections.synchronizedList(new ArrayList<>());
    public final static AtomicInteger CLEARN_TMEP_TIMES = new AtomicInteger(0);
    private transient final static Logger log = LoggerFactory.getLogger(PandaThreadLocalContext.class);
    int zindex = 0;

    public PandaThreadLocalContext() {
        log.info("线程管理: " + this.getClass().toString() + "注册成功");
        THREAD_LOCAL_HANDLERS.add(this);
        THREAD_LOCAL_HANDLERS.sort(Comparator.comparingInt(o -> o.zindex));
    }

    public PandaThreadLocalContext(int zindex) {
        this();
        this.zindex = zindex;
    }

    public static void initAll() {
        THREAD_LOCAL_HANDLERS.forEach(PandaThreadLocalContext::init);
    }

    public static void clearAll() {
        CLEARN_TMEP_TIMES.set(0);
        THREAD_LOCAL_HANDLERS.forEach(PandaThreadLocalContext::clear);
    }

    public static void clearAllTemp() {
        log.info("#######################自动清理内存（" + CLEARN_TMEP_TIMES.getAndIncrement() + "）##########");

        log.info("######################################################");
        THREAD_LOCAL_HANDLERS.forEach(PandaThreadLocalContext::clearTemp);
    }

    public static void countAll() {
        THREAD_LOCAL_HANDLERS.forEach(PandaThreadLocalContext::count);
    }

    /**
     * 执行初始化
     *
     * @author peng_da
     * @since 2022/8/30 16:07
     */
    public abstract void init();

    /**
     * 执行清理
     *
     * @author peng_da
     * @since 2022/8/30 16:08
     */
    public abstract void clear();

    /**
     * 临时清理
     *
     * @author peng_da
     * @since 2022/8/30 16:15
     */
    public void clearTemp() {

    }

    /**
     * 计数 获取线程中数据量
     *
     * @author peng_da
     * @since 2022/8/30 16:08
     */
    public void count() {

    }
}
