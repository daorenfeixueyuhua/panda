package com.daoren.test.rest.template;

import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author peng_da
 * @date 2022/12/29 16:09
 */
public class RestTemplateTest {
    @Test
    public void getTest() {
        final String url = "http://daoren.top/hello?username=panda";
        final RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        System.out.println(response.getBody());

    }

    @Test
    public void postTest() {
        final String url = "http://daoren.top/api/test/person/";
        final RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> body = new HashMap<>();
        body.put("name", "ex");
        restTemplate.postForObject(url, body, String.class);
    }
}
