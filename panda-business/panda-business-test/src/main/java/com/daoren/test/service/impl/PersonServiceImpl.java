package com.daoren.test.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.daoren.test.mapper.PersonMapper;
import com.daoren.test.model.entity.Person;
import com.daoren.test.service.PersonService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 个人信息表 服务实现类
 * </p>
 *
 * @author daoren
 * @since 2022-07-20
 */
@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements PersonService {
    private final PersonMapper baseMapper;

    public PersonServiceImpl(PersonMapper baseMapper) {
        this.baseMapper = baseMapper;
    }

    /**
     * 从缓存获取数据
     *
     * @param id : id
     * @return com.daoren.test.model.entity.Person
     * @author peng_da
     * @since 2022/7/20 14:43
     */
    @Override
    public Person cacheOne(String id) {
        if ("1".equals(id)) {
            throw new RuntimeException("ID不能为空！");
        }
        return this.baseMapper.selectById(id);
    }
}
