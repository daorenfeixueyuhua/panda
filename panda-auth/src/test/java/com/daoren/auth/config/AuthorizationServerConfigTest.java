package com.daoren.auth.config;


import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AuthorizationServerConfigTest {
    @Test
    public void run(){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        final String encode = passwordEncoder.encode("123456");
        System.out.println(encode);

    }
}