package com.daoren.test.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.daoren.graphsqlql.mapper.SysPersonMapper;
import com.daoren.graphsqlql.model.entity.SysPerson;
import com.daoren.test.service.SysPersonService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 个人信息表 服务实现类
 * </p>
 *
 * @author daoren
 * @since 2022-03-03
 */
@Service
public class SysPersonServiceImpl extends ServiceImpl<SysPersonMapper, SysPerson> implements SysPersonService {
    private final SysPersonMapper baseMapper;

    public SysPersonServiceImpl(SysPersonMapper baseMapper) {
        this.baseMapper = baseMapper;
    }

    /**
     * 从缓存获取数据
     *
     * @param id :
     * @return com.daoren.graphsqlql.model.entity.SysPerson
     * @author peng_da
     * @since 2022/3/10 13:59
     */
    @Override
    public SysPerson cacheOne(String id) {
        if ("1".equals(id)) {
            throw new RuntimeException("ID不能为空！");
        }
        return this.baseMapper.selectById(id);
    }
}
