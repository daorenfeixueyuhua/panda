package com.daoren.test.gen;

import com.daoren.mybatis.config.MybatisPlusConfig;
import com.daoren.mybatis.generator.MybatisPlusGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = MybatisPlusConfig.class)
public class GenTest {
    @Autowired
    private MybatisPlusGenerator generator;

    @Test
    public void runGen() {
        String[] include = new String[]{
                "sys_file_info"
        };
        generator.run(include);
    }
}
