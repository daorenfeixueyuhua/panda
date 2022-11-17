package com.daoren.mybatis;

import com.daoren.mybatis.dao.TestMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class Demo1 {
    @Test
    public void run1() throws IOException {
        String resource = "mybatis-config.xml";
        final InputStream inputStream = Resources.getResourceAsStream(resource);
        final SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        final Configuration configuration = sqlSessionFactory.getConfiguration();
        TestMapper testMapper = configuration.getMapper(TestMapper.class, sqlSessionFactory.openSession());
        System.out.println(testMapper.getUuidAnn());
        System.out.println(configuration);
    }
}
