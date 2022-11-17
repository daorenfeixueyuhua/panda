package com.daoren.mybatisS.config;

import com.daoren.mybatisS.mapper.TestMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MybatisConfig.class})
public class MybatisConfigTest {

    @Resource
    private TestMapper testMapper;

    @Test
    public void run1() {
        System.out.println(testMapper.getUuid());
    }
}