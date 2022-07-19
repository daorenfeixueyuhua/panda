package com.daoren.test.gen;

import com.daoren.mybatis.generator.MybatisPlusGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GenTest {
    @Autowired
    private MybatisPlusGenerator generator;

    @Test
    public void runGen() {
        String[] include = new String[]{
                "sys_operate_log"
        };
        generator.run(include);
    }
}
