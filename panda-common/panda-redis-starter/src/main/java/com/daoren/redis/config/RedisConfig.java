package com.daoren.redis.config;

import com.daoren.redis.core.RedisLock;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import javax.annotation.Resource;
import java.time.Duration;

/**
 * redisConfig
 *
 * @author peng_da
 * @version :
 * @date 2022/3/10 14:08
 * @since :
 */
@Configuration
public class RedisConfig extends CachingConfigurerSupport {
    /**
     * 分隔符
     */
    public static final String SPLIT = "-";
    @Resource
    private ObjectMapper objectMapper;

    /**
     * 自定义缓存Key生成策略
     *
     * @return org.springframework.cache.interceptor.KeyGenerator
     * @author peng_da
     * @since 2022/2/17 11:24
     */
    @Override
    @Bean("redisKeyGenerator")
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            StringBuffer sb = new StringBuffer();
            sb.append(target.getClass().getName());
            sb.append(SPLIT);
            sb.append(method.getName());
            sb.append(SPLIT);
            for (Object param : params) {
                try {
                    // TODO: 2022/2/17 待优化
                    // person::com.example.mvc.service.impl.PersonServiceImpl-list-{"id":"07ad52cf8382fc371d6405d5a8cddc84"}-
                    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
                    String objectJson = objectMapper.writeValueAsString(param);
                    sb.append(objectJson);
                    sb.append(SPLIT);
                } catch (JsonProcessingException e) {
                    sb.append(param.toString());
                }
            }
            return sb;
        };
    }

    @Bean
    @SuppressWarnings("rawtypes")
    public Jackson2JsonRedisSerializer jackson2JsonRedisSerializer(ObjectMapper objectMapper) {
        @SuppressWarnings({"unchecked"}) final Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        return jackson2JsonRedisSerializer;
    }


    /**
     * 配置StringRedisTemplate
     *
     * @param redisConnectionFactory      12
     * @param jackson2JsonRedisSerializer 124
     * @return org.springframework.data.redis.core.RedisTemplate<java.lang.String, java.lang.String>
     * @author peng_da
     * @since 2022/3/11 12:54
     */
    @Bean
    @SuppressWarnings("rawtypes")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory,
                                                       Jackson2JsonRedisSerializer jackson2JsonRedisSerializer) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    /**
     * 配置RedisCacheManager
     *
     * @param redisConnectionFactory :
     * @return org.springframework.cache.CacheManager
     * @author peng_da
     * @since 2022/2/17 14:13
     */
    @Bean
    public CacheManager cacheManager(@Autowired RedisConnectionFactory redisConnectionFactory) {
        return RedisCacheManager
                .builder(redisConnectionFactory)
                .cacheDefaults(RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(6)))
                .transactionAware()
                .build();
    }

    @Bean
    public RedisLock redisLock() {

        return null;
    }
}
