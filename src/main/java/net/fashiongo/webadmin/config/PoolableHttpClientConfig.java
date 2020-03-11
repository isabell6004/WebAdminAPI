package net.fashiongo.webadmin.config;

import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

/**
 * Created by jinwoo on 2019-12-12.
 */
@Configuration
public class PoolableHttpClientConfig {

    @Bean
    public RestTemplate restTemplateWrapper() {
        return new RestTemplate(clientHttpRequestFactoryWithPoolConfig());
    }

    private ClientHttpRequestFactory clientHttpRequestFactoryWithPoolConfig() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectionRequestTimeout(2000);
        factory.setConnectTimeout(3000);  // 3 seconds
        factory.setReadTimeout(10000); // 10 seconds
        org.apache.http.client.HttpClient httpClient = HttpClientBuilder.create()
                .evictIdleConnections(5000L, TimeUnit.MILLISECONDS)
                .setMaxConnTotal(200)
                .setMaxConnPerRoute(50)
                .build();
        factory.setHttpClient(httpClient);
        return factory;
    }

}
