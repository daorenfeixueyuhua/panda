package com.daoren.mongodb.model.controller;

import com.daoren.mongodb.model.entity.User;
import com.daoren.web.annotation.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * @author peng_da
 * @date 2022/11/14 10:37
 */
@RestController
@RequestMapping("/mongo/db/users")
public class MongoDbController {
    private MongoTemplate mongoTemplate;

    @Autowired
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @ResponseResult
    @GetMapping
    public List<User> list() {
        return mongoTemplate.findAll(User.class);
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable String id) {
        return mongoTemplate.findById(id, User.class);
    }

    @ResponseResult
    @GetMapping(value = {
            "/name/{name}",
            "/name/{name}/{page}",
            "/name/{name}/{page}/{pageSize}",
    })
    public List<User> findByName(@PathVariable String name,
                                 @PathVariable(required = false) Integer page,
                                 @PathVariable(required = false) Integer pageSize) {
        page = Optional.ofNullable(page).orElse(1);
        pageSize = Optional.ofNullable(pageSize).orElse(10);
        // 模糊查询
        final String regex = String.format("%s%s%s", "^.*", name, ".*$");
        final Pattern pattern = Pattern.compile(regex);
        final Query query = new Query();
        final Criteria nameCriteria = Criteria.where("name").regex(pattern);
        query.addCriteria(nameCriteria);
        final List<User> users = mongoTemplate.find(query.skip((page - 1) * pageSize).limit(pageSize), User.class);
        return users;
    }
}
