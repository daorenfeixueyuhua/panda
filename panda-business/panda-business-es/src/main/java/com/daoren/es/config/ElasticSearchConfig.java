package com.daoren.es.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author peng_da
 * @date  2022/8/1 16:26
 * @version :
 * @since :
 */
@Configuration
public class ElasticSearchConfig {
    @Bean
    public ElasticsearchClient elasticsearchClient(){
        final RestClient client = RestClient
                .builder(new HttpHost("127.0.0.1", 9200, "http")).build();
        final RestClientTransport restClientTransport = new RestClientTransport(client, new JacksonJsonpMapper());
        return new ElasticsearchClient(restClientTransport);
    }

}
