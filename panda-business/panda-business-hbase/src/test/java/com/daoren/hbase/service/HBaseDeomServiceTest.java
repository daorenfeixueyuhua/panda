package com.daoren.hbase.service;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Connection;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HBaseDeomServiceTest {

    @Test
    public void singleConnection() {
        Configuration configuration = new Configuration();
        configuration.set("", "");
        Connection connection = null;
    }
}