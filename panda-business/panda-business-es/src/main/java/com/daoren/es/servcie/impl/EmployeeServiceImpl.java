package com.daoren.es.servcie.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.indices.GetIndexResponse;
import com.daoren.es.model.entity.Employee;
import com.daoren.es.servcie.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;

/**
 *
 * @author peng_da
 * @date  2022/8/1 15:06
 * @version :
 * @since :
 */
@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private ElasticsearchClient client;

    @Override
    public Iterator<Employee> getAllEmployeeInfo() {
        try{
            final GetIndexResponse employees = client.indices().get(i -> i.index("employees"));
            System.out.println(employees.get("result"));
        }catch (Exception e){
            log.error("获取数据错误", e);
            throw new RuntimeException("获取数据错误");
        }
        return null;
    }

    @Override
    public Iterator<Employee> getEmployeesByName(String name) {
        return null;
    }

    @Override
    public Iterator<Employee> getEmployeesByNameAndOccupation(String name, String occupation) {
        return null;
    }
}
