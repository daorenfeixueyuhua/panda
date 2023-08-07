package com.daoren.test.demo;


import com.daoren.test.enums.HelloEnum;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class HelleTest {
    @Test
    public void run() {
        Map map = new HashMap();
        map.put(null, null);
        HelloEnum.HELLOE1.hello.hello();
        System.out.println(anInt());

        Thread thread = new thread1();
        thread.start();

    }

    public int anInt() {
        try {
            System.out.println("try");
            return -1;
        } catch (Exception e) {


        } finally {
            System.out.println("finally");
            return 1;
        }
    }
}

class thread1 extends Thread {
    @Override
    public void run() {
        super.run();
    }
}
