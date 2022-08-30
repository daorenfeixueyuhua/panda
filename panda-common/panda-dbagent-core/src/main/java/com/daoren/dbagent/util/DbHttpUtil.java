package com.daoren.dbagent.util;

import com.daoren.dbagent.model.dto.RequestParams;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * 数据库代理工具类
 *
 * @author peng_da
 * @date 2022/8/11 15:31
 */
public class DbHttpUtil {
    private DbHttpUtil() {

    }

    /**
     * 发送请求
     * @author peng_da
     * @since 2022/8/11 15:47
     * @param url : 请求Url
     * @param params : 请求参数
     * @return com.daoren.dbagent.model.vo.DbResult
     */
    @SuppressWarnings({"unehcked"})
    public static Map post(String url, RequestParams params) {
        final WebClient client = WebClient.builder()
                .build();
        final Mono<Map> resultMono = client
                .post()
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(params)
                .retrieve()
                .bodyToMono(Map.class);
        return resultMono.block();
    }

}
