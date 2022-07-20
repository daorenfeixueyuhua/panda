package com.daoren.test.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.daoren.graphql.model.entity.SysPerson;

/**
 * <p>
 * 个人信息表 服务类
 * </p>
 *
 * @author daoren
 * @since 2022-03-03
 */
public interface SysPersonService extends IService<SysPerson> {

    /**
     * 从缓存获取数据
     *
     * @param id :
     * @return com.daoren.graphsqlql.model.entity.SysPerson
     * @author peng_da
     * @since 2022/3/10 13:59
     */
    SysPerson cacheOne(String id);
}
