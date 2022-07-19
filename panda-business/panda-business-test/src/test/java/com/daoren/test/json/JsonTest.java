package com.daoren.test.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JsonTest {
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void run() throws JsonProcessingException {
        C1 c1 = new C1();
        c1.name = "panda";
        C2 c2 = new C2();
        c2.c1 = c1;
//        final ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance,
//                ObjectMapper.DefaultTyping.NON_FINAL,
//                JsonTypeInfo.As.WRAPPER_ARRAY);
        final String s = objectMapper.writeValueAsString(c2);
        System.out.println(s);
    }
}

class C1 {
    public String name;
}

class C2 {
    public Object c1;
}

