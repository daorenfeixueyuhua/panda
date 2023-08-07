package com.daoren.nacos;

import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;

public class PubTest {
    public static void main(String[] args) throws Exception {
        String serviceName = "hello.service";
        final NamingService namingService = NamingFactory.createNamingService("daoren:30048");
        namingService.registerInstance(serviceName, "127.0.0.1", 8888);
        Thread.sleep(Integer.MAX_VALUE);
    }
}