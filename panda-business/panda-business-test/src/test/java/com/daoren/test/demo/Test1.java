package com.daoren.test.demo;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

interface DemoService<T> {
    T getData(T data);
}

public class Test1 {

    @Test
    public void run() {
        final Class<DemoServiceImpl> aClass = DemoServiceImpl.class;
        final Method[] declaredMethods = aClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod);
        }
    }

}

class DemoServiceImpl<T> implements DemoService<T> {

    @Override
    public T getData(T data) {
        return data;
    }
}
