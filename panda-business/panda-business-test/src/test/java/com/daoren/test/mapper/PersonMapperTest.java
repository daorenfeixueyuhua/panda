package com.daoren.test.mapper;

import com.daoren.test.model.entity.Person;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PersonMapperTest {

    @Resource
    private PersonMapper personMapper;
    @Test
    public void dataScopeTest(){
        String id = "07ad52cf8382fc371d6405d5a8cddc84";
        final List<Person> person = personMapper.getAllById(id);
        Assert.assertTrue(person.size()!=0);
    }


    @Test
    public void updateBatchTest(){
        List<Person> personList = new ArrayList<Person>();
        Person p1 = new Person();
        p1.setId("1043f0217270b9b9b662fd7f2af073a6");
        p1.setName("xxxxx");
        p1.setIdCard("fhaiqfiiwe");
        personList.add(p1);
        Person p2 = new Person();
        p2.setId("1c887641c939cdf9f77d630ad5e8dde7");
        p2.setName("xsdc");
        p2.setIdCard("qwqwe");
        personList.add(p2);

        final int i = personMapper.updateBatch(personList);

    }
}