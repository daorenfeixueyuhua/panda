package com.daoren.test.mapper;


import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.defaults.DefaultSqlSession;
import org.junit.Test;

public class Demo1 {
    @Test
    public void run1() {
        SqlSession sqlSession = new DefaultSqlSession(null, null, true);
    }
}
