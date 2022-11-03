package com.daoren.dynamic.controller;

import com.daoren.dynamic.model.entity.DynamicTable;
import com.daoren.dynamic.service.DynamicTableService;
import com.daoren.web.annotation.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author peng_da
 * @date 2022/11/3 15:50
 */
@RestController
@RequestMapping("/dynamic/table")
public class DynamicTableController {
    private DynamicTableService baseService;

    @Autowired
    public void setBaseService(DynamicTableService baseService) {
        this.baseService = baseService;
    }

    @PostMapping
    @ResponseResult
    public int save(@RequestBody DynamicTable entity) {
        return this.baseService.insert(entity);
    }
}
