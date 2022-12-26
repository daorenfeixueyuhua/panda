package com.daoren.mysql.cluster.controller;

import com.baomidou.dynamic.datasource.annotation.Slave;
import com.daoren.mysql.cluster.model.entity.Person;
import com.daoren.mysql.cluster.service.PersonService;
import org.springframework.web.bind.annotation.*;

/**
 * @author peng_da
 * @date 2022/12/26 14:41
 */
@RestController
@RequestMapping("/persons")
public class PersonController {
    private final PersonService personService;


    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/")
    public String save(@RequestBody Person entity) {
        final boolean save = this.personService.save(entity);
        return entity.getId();
    }

    @Slave
    @GetMapping("/{id}")
    public Person queryById(@PathVariable String id) {
        return personService.queryById(id);
    }
}
