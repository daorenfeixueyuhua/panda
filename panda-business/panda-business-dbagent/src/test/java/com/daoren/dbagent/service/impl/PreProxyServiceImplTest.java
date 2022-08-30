package com.daoren.dbagent.service.impl;

import com.daoren.dbagent.enums.SqlType;
import com.daoren.dbagent.model.dto.RequestParams;
import com.daoren.dbagent.model.dto.SqlParams;
import com.daoren.dbagent.service.PreProxyService;
import com.daoren.dbagent.util.DbAgentUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PreProxyServiceImplTest {
    @Resource
    private PreProxyService baseService;
    @Resource
    private ObjectMapper objectMapper;

    @SneakyThrows
    @Test
    public void printRequestParamsTest(){
        String sql = "select * from sys_person where name = #{name};";
        SqlParams params = new SqlParams();
        params.put("name", "234");
        RequestParams requestParams = RequestParams.builder()
                .db("api")
                .tip("127.0.0.1")
                .type(SqlType.SELECT)
                .code(DbAgentUtil.sqlEncode(sql))
                .params(params)
                .build();
        System.out.println(objectMapper.writeValueAsString(requestParams));
    }

    @Test
    public void preProxy() {
        String sql = "select * from sys_person where name = #{name};";
        SqlParams params = new SqlParams();
        params.put("name", "234");
        RequestParams requestParams = RequestParams.builder()
                .db("api")
                .tip("127.0.0.1")
                .type(SqlType.SELECT)
                .code(DbAgentUtil.sqlEncode(sql))
                .params(params)
                .build();
        final Object result = baseService.preProxy(requestParams);
        System.out.println(result);
    }

    @Test
    public void preProxyList() {
    }
}