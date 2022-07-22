package com.daoren.camunda;

import com.daoren.json.config.JacksonConfig;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.event.EventListener;

/**
 * Main
 *
 * @author peng_da
 * @version :
 * @date 2022/7/21 16:39
 * @since :
 */
@MapperScan("com.daoren.**.mapper")
@EnableDiscoveryClient
@EnableProcessApplication
@SpringBootApplication(exclude = {JacksonConfig.class})
public class CamundaApplication {
    @Autowired
    private RuntimeService runtimeService;

    public static void main(String[] args) {
        SpringApplication.run(CamundaApplication.class, args);
    }

    @EventListener
    public void processPostDeploy(PostDeployEvent event) {
        runtimeService.startProcessInstanceByKey("Process_07i921h");
    }
}
