package com.daoren.test.service.impl;


import cn.hutool.core.date.LocalDateTimeUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.daoren.test.mapper.PersonMapper;
import com.daoren.test.model.entity.Person;
import com.daoren.test.service.PersonService;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 个人信息表 服务实现类
 * </p>
 *
 * @author daoren
 * @since 2022-07-20
 */
@Slf4j
@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements PersonService {
    public static final String CACHE_KEY = "person::";
    private final PersonMapper baseMapper;
    private final RedisTemplate<String, Object> redisTemplate;
    private final boolean enableBloomFilter = false;
    /**
     * 布隆过滤器
     */
    private BloomFilter<String> bloomFilter;

    public PersonServiceImpl(PersonMapper baseMapper, RedisTemplate<String, Object> redisTemplate) {
        this.baseMapper = baseMapper;
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    public void initBloom() {
        if (enableBloomFilter) {
            log.info("【开始初始化Person BloomFilter】");
            final List<Person> personList = baseMapper.selectList(null);
            bloomFilter = BloomFilter.create(Funnels.stringFunnel(StandardCharsets.UTF_8), personList.size() + 1000, 0.001);
            personList.forEach(person -> {
                bloomFilter.put(person.getId());
                redisTemplate.opsForValue().set(CACHE_KEY + person.getId(), person);
            });
            log.info("【结束始化Person BloomFilter，记录数{}】", personList.size());
        }
    }

    @Override
    public Person getEntityById(String id) {
        if (StringUtils.isBlank(id)) {
            throw new RuntimeException("id is null");
        }
        final LocalDateTime now = LocalDateTimeUtil.now();
        boolean flag = true;
        if (enableBloomFilter) {
            flag = bloomFilter.mightContain(id);
        }
        if (flag) {
            return Optional.ofNullable((Person) redisTemplate.opsForValue().get(id))
                    .orElseGet(() -> {
                        log.debug("load data person::{} from database ", id);
                        return baseMapper.getAllById(id);
                    });
        }
        return null;
    }

    @Override
    public boolean save(Person entity) {
        final boolean result = super.save(entity);
        if (result) {
            redisTemplate.opsForValue().set(CACHE_KEY + entity.getId(), entity);
            if (enableBloomFilter) {
                bloomFilter.put(entity.getId());
            }
        }
        return result;
    }

    @Override
    public boolean update(Person entity, Wrapper<Person> updateWrapper) {
        final boolean result = super.update(entity, updateWrapper);
        if (result) {
            redisTemplate.opsForValue().set(CACHE_KEY + entity.getId(), entity);
        }
        return result;
    }
}
