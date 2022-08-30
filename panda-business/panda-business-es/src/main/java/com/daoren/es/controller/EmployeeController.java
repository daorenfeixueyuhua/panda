package com.daoren.es.controller;

import com.daoren.es.model.entity.Employee;
import com.daoren.es.servcie.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Iterator;

@RequestMapping("/employee")
@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping(value ="/allemployees")
    @ResponseBody
    public Iterator<Employee> getAllEmployees() {
        return employeeService.getAllEmployeeInfo();
    }

    @GetMapping(value ="/allemployees/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterator<Employee> getUserByName(@PathVariable String name){
        return employeeService.getEmployeesByName(name);
    }

    @GetMapping(value ="/allemployees/{name}/{address}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterator<Employee> getUserByNameAndAddress(@PathVariable String name, @PathVariable String address){
        return employeeService.getEmployeesByNameAndOccupation(name, address);
    }
}
