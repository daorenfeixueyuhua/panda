package com.daoren.mongodb.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author peng_da
 * @date 2022/11/14 10:34
 */
@Document("user")
public class User {
    @Id
    private String id;
    private Integer age;
    private String name;
}
