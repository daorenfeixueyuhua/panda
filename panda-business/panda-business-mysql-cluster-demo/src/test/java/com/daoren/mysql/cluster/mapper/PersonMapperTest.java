package com.daoren.mysql.cluster.mapper;

import com.daoren.mysql.cluster.model.entity.Person;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootTest
public class PersonMapperTest {
    @Autowired
    private PersonMapper personMapper;

    @Test
    public void insertTest() {
        final Person entity = Person.builder()
                .name("panda")
                .birth(LocalDateTime.parse("1998-12-27 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .build();
        personMapper.insert(entity);

    }
}