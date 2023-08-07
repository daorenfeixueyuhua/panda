package com.daoren.common.base.monitor.context;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.daoren.common.base.context.PandaThreadLocalContext;
import com.daoren.common.base.monitor.entity.MonitorElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Stack;

/**
 * @author peng_da
 * @date 2022/9/2 14:44
 */
public class MonitorContext extends PandaThreadLocalContext {
    public static final transient Logger log = LoggerFactory.getLogger(MonitorContext.class);
    private static final TransmittableThreadLocal<Stack<MonitorElement>> THREAD_MONITOR_ELEMENTS
            = new TransmittableThreadLocal<>();

    public static void put(String type, String config, long beginTime, long endTime) {
        if (THREAD_MONITOR_ELEMENTS.get() == null) {
            PandaThreadLocalContext.initAll();
        }
        THREAD_MONITOR_ELEMENTS.get()
                .push(new MonitorElement(type, config, beginTime, endTime));
    }

    public static void put(String type, String config, long beginTime, long endTime, Throwable e) {
        if (THREAD_MONITOR_ELEMENTS.get() == null) {
            PandaThreadLocalContext.initAll();
        }
        THREAD_MONITOR_ELEMENTS.get()
                .push(new MonitorElement(type, config, beginTime, endTime, e));
    }

    /**
     * 执行初始化
     *
     * @author peng_da
     * @since 2022/8/30 16:07
     */
    @Override
    public void init() {
        THREAD_MONITOR_ELEMENTS.set(new Stack<>());
    }

    /**
     * 执行清理
     *
     * @author peng_da
     * @since 2022/8/30 16:08
     */
    @Override
    public void clear() {
        THREAD_MONITOR_ELEMENTS.set(null);
    }

    @Override
    public void count() {
        log.debug("THREAD_MONITOR_ELEMENTS's size is {}", THREAD_MONITOR_ELEMENTS.get().size());
    }

    @PostConstruct
    public void postConstruct() {

    }

    @PreDestroy
    public void preDestroy() {
        clear();
    }
}
