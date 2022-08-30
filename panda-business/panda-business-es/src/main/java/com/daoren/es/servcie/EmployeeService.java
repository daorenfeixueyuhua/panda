package com.daoren.es.servcie;

import com.daoren.es.model.entity.Employee;

import java.util.Iterator;

/**
 *
 * @author peng_da
 * @date  2022/8/1 14:45
 * @version :
 * @since :
 */
public interface EmployeeService {

    Iterator<Employee> getAllEmployeeInfo();
    Iterator<Employee> getEmployeesByName(String name);
    Iterator<Employee> getEmployeesByNameAndOccupation(String name, String occupation);
}
