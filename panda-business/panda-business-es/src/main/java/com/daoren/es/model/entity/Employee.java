package com.daoren.es.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 *
 * @author peng_da
 * @date  2022/8/1 14:44
 * @version :
 * @since :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private Long id;
    private String name;
    private String sex;
    private String occupation;
    private int salary;
}
