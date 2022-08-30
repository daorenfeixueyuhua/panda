package com.daoren.dbagent.util;

import com.daoren.dbagent.model.dto.SqlParams;
import org.junit.Assert;
import org.junit.Test;

public class DbAgentUtilTest {
    @Test
    public void run(){
        String sql = "select * from sys_person;";
        final String code = DbAgentUtil.sqlEncode(sql);
        final String newSql = DbAgentUtil.sqlDecode(code);
        System.out.println("src sql " + sql);
        System.out.println("code sql " + code );
        System.out.println("new sql " + newSql);
        Assert.assertEquals(sql, newSql);
    }

    @Test
    public void getSqlParamsTest(){
        String sql = "select * from sys_person where name = #{name};";
        System.out.println(DbAgentUtil.getSqlParams(sql));
    }

    @Test
    public void fetchSqlTest(){
        String sql = "select * from sys_person where name = #{name};";
        SqlParams params = new SqlParams();
        params.put("name", "彭达");
        final String result = DbAgentUtil.fetchSql(sql, params);
        System.out.println(result);

    }

}