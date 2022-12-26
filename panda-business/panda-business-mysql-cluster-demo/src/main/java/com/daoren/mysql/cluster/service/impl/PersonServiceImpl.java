package com.daoren.mysql.cluster.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.daoren.mysql.cluster.mapper.PersonMapper;
import com.daoren.mysql.cluster.model.entity.Person;
import com.daoren.mysql.cluster.service.PersonService;
import org.springframework.stereotype.Service;

/**
 * @author peng_da
 * @date 2022/12/26 14:39
 */
@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements PersonService {
    private final PersonMapper personMapper;

    public PersonServiceImpl(PersonMapper personMapper) {
        this.personMapper = personMapper;
    }

    @Override
    public Person queryById(String id) {
        return personMapper.selectById(id);
    }
}
