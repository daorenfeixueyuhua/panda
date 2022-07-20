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
     * 从缓存获取数据
     *
     * @param id :
     * @return com.daoren.graphsqlql.model.entity.SysPerson
     * @author peng_da
     * @since 2022/3/10 13:59
     */
    Person cacheOne(String id);

}
