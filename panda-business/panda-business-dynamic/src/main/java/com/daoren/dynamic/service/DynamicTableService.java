package com.daoren.dynamic.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.daoren.dynamic.model.entity.DynamicTable;

/**
 * @author peng_da
 * @date 2022/11/3 15:44
 */
public interface DynamicTableService {
    /**
     * 保存数据
     *
     * @param entity :
     * @return int
     * @author peng_da
     * @since 2022/11/3 15:45
     */
    int insert(DynamicTable entity);

    /**
     * 分页查询
     *
     * @param page   :
     * @param entity :
     * @return com.baomidou.mybatisplus.extension.plugins.pagination.Page<com.daoren.dynamic.model.entity.DynamicTable>
     * @author peng_da
     * @since 2022/11/3 15:46
     */
    Page<DynamicTable> page(Page<DynamicTable> page, DynamicTable entity);
}
