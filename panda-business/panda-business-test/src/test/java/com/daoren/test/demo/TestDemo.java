package com.daoren.test.demo;

import org.junit.Test;

public class TestDemo {
    @Test
    public void run1() {
        method1();
    }

    public void method1() {
        System.out.println("method1 start");
        method2();
        System.out.println("method1 finish");
    }

    private void method2() {
        System.out.println("method2 start");
        method3();
        System.out.println("method2 finish");
    }

    private void method3() {
        System.out.println("method3 start");
        method4();
        System.out.println("method3 finish");
    }

    private void method4() {
        System.out.println("method4 start");
        System.out.println("method4 finish");
    }
}
