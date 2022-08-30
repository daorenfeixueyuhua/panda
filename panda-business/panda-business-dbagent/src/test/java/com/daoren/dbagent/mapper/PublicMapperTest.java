package com.daoren.dbagent.mapper;

import com.daoren.dbagent.model.dto.SqlParams;
import com.daoren.dbagent.util.DbAgentUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.LinkedHashMap;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PublicMapperTest {

    @Autowired
    private PublicMapper publicMapper;

    @Test
    public void getPublicItems() {
        String sql = "select * from sys_person;";
        final List<LinkedHashMap<String, Object>> result =
                publicMapper.getPublicItems(sql);
        System.out.println(result);
    }

    @Test
    public void getItems() {
        String sql = "select * from sys_person where name = #{name};";
        SqlParams params = new SqlParams();
        params.put("name", "234");
        final String sql1 = DbAgentUtil.fetchSql(sql, params);
        final List<LinkedHashMap<String, Object>> result =
                publicMapper.getItems(sql1, params);
        System.out.println(result);
    }

    @Test
    public void updateItem() {
    }

    @Test
    public void count() {
    }
}