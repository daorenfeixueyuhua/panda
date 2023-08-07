package com.daoren.redis.core;

/**
 * redis锁相关操作
 *
 * @author peng_da
 * @date 2022/11/2 11:15
 */
public interface RedisLockOps {
    /**
     * 默认过期时间
     */
    long TIMEOUT_MILLIS = 30000;
    /**
     * 默认重试次数
     */
    int RETRY_TIMES = 2;
    /**
     * 默认睡眠时间
     */
    long SLEEP_MILLIS = 500;

    /**
     * 加锁
     *
     * @param key : key
     * @return boolean
     * @author peng_da
     * @since 2022/11/2 11:22
     */
    boolean lock(String key);

    /**
     * 加锁
     *
     * @param key    : key
     * @param expire : 重试次数
     * @return boolean
     * @author peng_da
     * @since 2022/11/2 11:22
     */
    boolean lock(String key, long expire);

    /**
     * 加锁
     *
     * @param key        : key
     * @param retryTimes : 重试次数
     * @param expire     : 过期时间
     * @return boolean
     * @author peng_da
     * @since 2022/11/2 11:22
     */
    boolean lock(String key, int retryTimes, long expire);

    /**
     * 加锁
     *
     * @param key         : key
     * @param expire      : 过期时间
     * @param sleepMillis : 睡眠时间
     * @return boolean
     * @author peng_da
     * @since 2022/11/2 11:22
     */
    boolean lock(String key, long expire, int sleepMillis);

    /**
     * 加锁
     *
     * @param key         : key
     * @param expire      : 过期时间
     * @param retryTimes  : 重试次数
     * @param sleepMillis : 睡眠时间
     * @return boolean
     * @author peng_da
     * @since 2022/11/2 11:22
     */
    boolean lock(String key, long expire, int retryTimes, long sleepMillis);

    /**
     * 解锁
     *
     * @param key : key
     * @return boolean
     * @author peng_da
     * @since 2022/11/2 11:22
     */
    boolean releaseLock(String key);
}
