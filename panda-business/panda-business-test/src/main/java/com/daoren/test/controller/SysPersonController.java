package com.daoren.test.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.daoren.graphql.model.entity.SysPerson;
import com.daoren.test.service.SysPersonService;
import com.daoren.web.annotation.ResponseResult;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 个人信息表 前端控制器
 * </p>
 *
 * @author daoren
 * @since 2022-03-03
 */
@RestController
@RequestMapping("/person")
public class SysPersonController {
    private final SysPersonService personService;

    public SysPersonController(SysPersonService personService) {
        this.personService = personService;
    }


    @ResponseResult
    @PostMapping("/list")
    public List<SysPerson> list(@Validated SysPerson entity) {
        return personService.list(new QueryWrapper<SysPerson>().setEntity(entity));
    }

    @Validated
    @ResponseResult
    @GetMapping("/{id}")
    public SysPerson one(@Length(min = 32, max = 32) @PathVariable(name = "id") String id) {
        return personService.cacheOne(id);
    }

}
