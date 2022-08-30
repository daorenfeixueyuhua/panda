package com.daoren.dbagent.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * 线程池配置
 * @author peng_da
 * @date  2022/8/11 14:20
 */

@Configuration
@Data
@ConfigurationProperties(prefix = "thread-properties")
public class ThreadProperties {
    private Integer corePoolSize;
    private Integer maxPoolSize;
    private Long keepTime;
    private TimeUnit unit;
}
