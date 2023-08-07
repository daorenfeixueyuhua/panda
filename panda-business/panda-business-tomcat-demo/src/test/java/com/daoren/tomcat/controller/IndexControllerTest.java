package com.daoren.tomcat.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class IndexControllerTest {

    @Autowired
    private IndexController controller;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void indexTest() {
        final String index = controller.index();
    }
}