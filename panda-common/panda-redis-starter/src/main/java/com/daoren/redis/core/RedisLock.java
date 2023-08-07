package com.daoren.redis.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.UUID;

/**
 * redis锁
 *
 * @author peng_da
 * @date 2022/11/2 11:33
 */
@Slf4j
public class RedisLock extends AbstractRedisLockOps {

    private ThreadLocal<String> lockFalg = new ThreadLocal<>();

    private RedisTemplate<String, Object> redisTemplate;

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean lock(String key, long expire, int retryTimes, long sleepMillis) {

        return false;
    }

    @Override
    public boolean releaseLock(String key) {
        return false;
    }

    private boolean setRedis(final String key, final long expire) {
        try {
            final String uuid = UUID.randomUUID().toString();
            lockFalg.set(uuid);
            setNx(key, uuid, expire);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            log.debug("redis lock error", e);
        }
        return false;
    }

    /**
     * 加锁
     *
     * @return boolean
     * @author peng_da
     * @since 2022/11/2 14:04
     */
    public boolean setNx(final String key, final String value, final long expire) {
        return (boolean) redisTemplate.execute((RedisCallback) connection -> {
            final Boolean flag = connection.setNX(key.getBytes(), value.getBytes());
            if (flag) {
                connection.expire(key.getBytes(), expire);
            }
            return flag;
        });
    }
}
