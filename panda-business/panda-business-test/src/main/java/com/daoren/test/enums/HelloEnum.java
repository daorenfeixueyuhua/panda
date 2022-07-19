package com.daoren.test.enums;

import com.daoren.test.service.Hello;

/**
 * @author peng_da
 * @version :
 * @date 2022/4/29 15:56
 * @since :
 */
public enum HelloEnum {
    /**
     * HELLO
     */
    HELLOE1(() -> {
        System.out.println("HELLE1");
    });


    public Hello hello;

    HelloEnum(Hello hello) {
        this.hello = hello;
    }
}
