package com.daoren.mq.message;

import lombok.Data;

@Data
public class Demo01Message {
    public static final String TOPIC = "DEMO_01";

    private Integer id;
}
