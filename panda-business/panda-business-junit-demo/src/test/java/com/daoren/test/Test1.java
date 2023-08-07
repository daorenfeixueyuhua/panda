package com.daoren.test;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class Test1 {

    @BeforeClass
    public static void beforeClass() {
        System.out.println("this is static before");
    }

    @Before
    public void before() {
        System.out.println("before");
    }

    @Test
    public void run() {
        System.out.println("this is a test");
    }

    @Test
    public void run1() {
        System.out.println("Test1.run1");
    }
}
