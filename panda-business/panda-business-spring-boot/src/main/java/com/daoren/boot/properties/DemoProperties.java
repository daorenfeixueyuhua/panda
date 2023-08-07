package com.daoren.boot.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author peng_da
 * @date 2022/12/6 14:53
 */
@ConfigurationProperties(prefix = "demo")
public class DemoProperties {
    static {
        System.out.println("DemoProperties.static initializer");
    }

    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
