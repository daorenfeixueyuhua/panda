package com.daoren.dbagent.service.impl;

import com.daoren.common.entity.Result;
import com.daoren.dbagent.config.DbContextHolder;
import com.daoren.dbagent.mapper.PublicMapper;
import com.daoren.dbagent.model.dto.RequestParams;
import com.daoren.dbagent.model.vo.DbResult;
import com.daoren.dbagent.properties.ThreadProperties;
import com.daoren.dbagent.service.PreProxyService;
import com.daoren.dbagent.util.DbAgentUtil;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author peng_da
 * @date 2022/8/10 17:39
 */
@Service
public class PreProxyServiceImpl implements PreProxyService {
    private static final DbThreadFactory THREAD_FACTORY = new DbThreadFactory();
    private PublicMapper baseMapper;
    private ThreadProperties threadProperties;

    @Autowired
    public void setBaseMapper(PublicMapper baseMapper) {
        this.baseMapper = baseMapper;
    }

    @Autowired
    public void setThreadProperties(ThreadProperties threadProperties) {
        this.threadProperties = threadProperties;
    }

    @Override
    public DbResult preProxy(RequestParams params) {
        ZonedDateTime startTime = Instant.now().atZone(ZoneId.systemDefault());
        Object data;
        DbContextHolder.setDbType(params.getDb());
        final String sql = DbAgentUtil.sqlDecode(params.getCode());
        final String executeSql = DbAgentUtil.fetchSql(sql, params.getParams());
        switch (params.getType()) {
            case SELECT:
                data = baseMapper.getItems(executeSql, params.getParams());
                break;
            case COUNT:
                data = baseMapper.count(executeSql, params.getParams());
                break;
            case UPDATE:
                data = baseMapper.updateItem(executeSql, params.getParams());
                break;
            default:
                data = null;
        }
        ZonedDateTime endTime = Instant.now().atZone(ZoneId.systemDefault());
        return DbResult.builder()
                .tip(params.getTip())
                .type(params.getType())
                .success(true)
                .msg(Result.MSG_SUCCESS)
                .mills(endTime.getSecond() - startTime.getSecond())
                .data(data).build();
    }

    @SneakyThrows
    @Override
    public List<DbResult> preProxyList(List<RequestParams> paramsList) {
        final CountDownLatch downLatch = new CountDownLatch(paramsList.size());
        ExecutorService executorService;
        List<DbResult> resultList = Collections.synchronizedList(new LinkedList<>());
        if (paramsList.size() > threadProperties.getCorePoolSize()) {
            executorService = new ThreadPoolExecutor(threadProperties.getCorePoolSize(),
                    threadProperties.getMaxPoolSize(),
                    threadProperties.getKeepTime(),
                    threadProperties.getUnit(),
                    new LinkedBlockingQueue<>(),
                    THREAD_FACTORY);
        } else {
            executorService = new ThreadPoolExecutor(paramsList.size(),
                    threadProperties.getMaxPoolSize(),
                    threadProperties.getKeepTime(),
                    threadProperties.getUnit(),
                    new LinkedBlockingQueue<>(),
                    THREAD_FACTORY);

        }
        paramsList.forEach(item -> {
            executorService.submit(() -> {
                try {
                    resultList.add(this.preProxy(item));
                } finally {
                    downLatch.countDown();
                }
            });
        });
        downLatch.await();
        executorService.shutdown();
        while (!executorService.awaitTermination(60L, TimeUnit.SECONDS) && executorService.isTerminated()) {
            executorService.shutdownNow();
        }
        return resultList;
    }
    /**
     * 线程工厂
     * @author peng_da
     * @date  2022/8/11 15:21
     */
    static class DbThreadFactory implements ThreadFactory {
        private static final AtomicInteger POOL_NUMBER = new AtomicInteger(1);
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final String namePrefix;

        public DbThreadFactory() {
            final SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
            namePrefix = "pool-" + POOL_NUMBER.getAndIncrement() + "-thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            final Thread thread = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (thread.isDaemon()){
                thread.setDaemon(false);
            }
            return thread;
        }
    }
}
