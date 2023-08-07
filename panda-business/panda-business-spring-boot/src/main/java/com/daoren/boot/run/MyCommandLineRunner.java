package com.daoren.boot.run;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author peng_da
 * @date 2022/12/7 10:58
 */
@Component
public class MyCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("MyCommandLineRunner.run");
        System.out.println("args = " + Arrays.deepToString(args));
    }
}
