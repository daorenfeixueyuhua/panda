package com.daoren.trans.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.daoren.trans.model.entity.Person;

/**
 * @author peng_da
 * @date 2022/12/26 14:38
 */
public interface PersonService extends IService<Person> {
    /**
     * query person by id
     *
     * @param id : id
     * @return com.daoren.trans.model.entity.Person
     * @author peng_da
     * @since 2022/12/26 15:03
     */
    Person queryById(String id);

    /**
     * 保存并插入
     * @author peng_da
     * @since 2022/12/28 14:59
     * @param entity :
     * @return com.daoren.trans.model.entity.Person
     */
    Person saveAndQuery(Person entity);
    /**
     * 通过事务进行保存
     * @author peng_da
     * @since 2022/12/27 10:15
     * @param person : entity
     * @return boolean
     */
    boolean saveByTran(Person person);
    /**
     * A
     * @author peng_da
     * @since 2022/12/27 15:55
     */
    void methodA();

    /**
     * B
     * @author peng_da
     * @since 2022/12/27 15:55
     */
    void methodB();
}
