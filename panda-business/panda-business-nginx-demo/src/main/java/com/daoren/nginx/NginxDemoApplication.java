package com.daoren.nginx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author peng_da
 * @date  2022/12/20 16:32
 */
@RestController
@SpringBootApplication
public class NginxDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(NginxDemoApplication.class, args);
    }

    private int requestCount = 0;

    @GetMapping("/")
    public String index(String uuid, String datetime){
        requestCount+=1;
        System.out.println("uuid: " + uuid + " datetime: " + datetime + "requestCount: " + requestCount);

        return "Index";
    }
}
