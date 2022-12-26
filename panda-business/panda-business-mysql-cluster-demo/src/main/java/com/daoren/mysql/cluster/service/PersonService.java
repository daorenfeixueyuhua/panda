package com.daoren.mysql.cluster.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.daoren.mysql.cluster.model.entity.Person;

/**
 * @author peng_da
 * @date 2022/12/26 14:38
 */
public interface PersonService extends IService<Person> {
    /**
     * query person by id
     *
     * @param id : id
     * @return com.daoren.mysql.cluster.model.entity.Person
     * @author peng_da
     * @since 2022/12/26 15:03
     */
    Person queryById(String id);
}
