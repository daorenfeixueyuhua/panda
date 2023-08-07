package com.daoren.test.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.daoren.test.model.entity.Person;

/**
 * <p>
 * 个人信息表 服务类
 * </p>
 *
 * @author daoren
 * @since 2022-07-20
 */
public interface PersonService extends IService<Person> {

    /**
     * 通过id进行查询
     *
     * @param id : id
     * @return com.daoren.test.model.entity.Person
     * @author peng_da
     * @since 2022/12/23 9:42
     */
    Person getEntityById(String id);
}
