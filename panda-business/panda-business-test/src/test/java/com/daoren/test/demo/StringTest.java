package com.daoren.test.demo;

import org.junit.Test;

public class StringTest {
    @Test
    public void run1() throws InterruptedException {
        long start = System.currentTimeMillis();
        int capacity = 100000;
        String a = "";
        for (int i = 0; i < capacity; i++) {
            a = a + "a";
        }
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms"); // 耗时：3866 ms
    }

    @Test
    public void run2() {
        long start = System.currentTimeMillis();
        int capacity = 100000;
        StringBuilder a = new StringBuilder();
        for (int i = 0; i < capacity; i++) {
            a.append("a");
        }
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms"); //耗时：7 ms
    }
}
