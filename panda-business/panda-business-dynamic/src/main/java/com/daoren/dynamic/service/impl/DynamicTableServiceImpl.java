package com.daoren.dynamic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.daoren.dynamic.mapper.DynamicTableMapper;
import com.daoren.dynamic.model.entity.DynamicTable;
import com.daoren.dynamic.service.DynamicTableService;
import com.daoren.mybatis.annotation.dynamic.DynamicDatabase1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author peng_da
 * @date 2022/11/3 15:46
 */
@Service
public class DynamicTableServiceImpl implements DynamicTableService {
    private DynamicTableMapper baseMapper;

    @Autowired
    public void setBaseMapper(DynamicTableMapper baseMapper) {
        this.baseMapper = baseMapper;
    }

    @DynamicDatabase1
    @Override
    public int insert(DynamicTable entity) {
        return this.baseMapper.insert(entity);
    }

    @Override
    public Page<DynamicTable> page(Page<DynamicTable> page, DynamicTable entity) {
        final LambdaQueryWrapper<DynamicTable> queryWrapper = new QueryWrapper<DynamicTable>().lambda();
        queryWrapper.setEntity(entity);
        return this.baseMapper.selectPage(page, queryWrapper);
    }
}
