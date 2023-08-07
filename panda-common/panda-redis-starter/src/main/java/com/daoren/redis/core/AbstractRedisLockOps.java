package com.daoren.redis.core;

/**
 * @author peng_da
 * @date 2022/11/2 11:24
 */
public abstract class AbstractRedisLockOps implements RedisLockOps {
    @Override
    public boolean lock(String key) {
        return lock(key, TIMEOUT_MILLIS, RETRY_TIMES, SLEEP_MILLIS);
    }

    @Override
    public boolean lock(String key, long expire) {
        return lock(key, expire, RETRY_TIMES, SLEEP_MILLIS);
    }

    @Override
    public boolean lock(String key, int retryTimes, long expire) {
        return lock(key, expire, retryTimes, SLEEP_MILLIS);
    }

    @Override
    public boolean lock(String key, long expire, int sleepMillis) {
        return lock(key, expire, RETRY_TIMES, sleepMillis);
    }
}
