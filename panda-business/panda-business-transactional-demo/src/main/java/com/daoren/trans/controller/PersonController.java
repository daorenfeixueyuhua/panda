package com.daoren.trans.controller;

import com.baomidou.dynamic.datasource.annotation.Slave;
import com.daoren.trans.model.entity.Person;
import com.daoren.trans.service.PersonService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
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
        personService.saveAndQuery(entity);
       return "success";
    }

    @Slave
    @GetMapping("/{id}")
    public Person queryById(@PathVariable String id) {
        return personService.queryById(id);
    }

    @GetMapping("/propagation")
    public String propagation(){
        this.personService.methodA();
        return "hello";
    }
    @GetMapping("/methods/{methodName}")
    public String method(@PathVariable String methodName){
        switch (methodName){
            case "A":
                personService.methodA();
                return "exec methodA";
            case "B":
                personService.methodB();
                return "exec methodB";
            case "A_B":
                personService.methodA();
                personService.methodB();
                return "exec methodA and methodB";
            default:
                return "nothing exec";
        }
    }
}
