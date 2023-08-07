package com.daoren.test.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.daoren.test.model.entity.Person;
import com.daoren.test.service.PersonService;
import com.daoren.web.annotation.ResponseResult;
import lombok.extern.slf4j.Slf4j;
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
 * @since 2022-07-20
 */
@Slf4j
@RestController
@RequestMapping("/person")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }


    @ResponseResult
    @PostMapping("/list")
    public List<Person> list(@Validated Person entity) {
        return personService.list(new QueryWrapper<Person>().setEntity(entity));
    }

    @ResponseResult
    @PostMapping("/page")
    public IPage<Person> page(Page<Person> page, Person entity) {
        final LambdaQueryWrapper<Person> queryWrapper = new QueryWrapper<Person>().lambda();
        queryWrapper.setEntity(entity);
        return personService.page(page, queryWrapper);
    }

    @Validated
    @ResponseResult
    @GetMapping("/{id}")
    public Person one(@Length(min = 32, max = 32) @PathVariable(name = "id") String id) {
        return personService.getEntityById(id);
    }

    @ResponseResult
    @PostMapping("/")
    public Boolean save(@RequestBody Person entity) {
        return personService.save(entity);
    }
}
