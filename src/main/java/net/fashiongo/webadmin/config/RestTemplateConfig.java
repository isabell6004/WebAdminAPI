package net.fashiongo.webadmin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean(name = "adApiRestTemplate")
    public RestTemplate adApiRestTemplate() {

        // SimpleClientHttpRequestFactory 는 connection pool 이 없다.
        SimpleClientHttpRequestFactory httpRequestFactory = new SimpleClientHttpRequestFactory();
        httpRequestFactory.setConnectTimeout(3000);
        httpRequestFactory.setReadTimeout(3000);
        httpRequestFactory.setBufferRequestBody(false);

        return new RestTemplate();
    }

    @Bean("rewardApiRestTemplate")
    public RestTemplate rewardApiRestTemplate() {
        SimpleClientHttpRequestFactory httpRequestFactory = new SimpleClientHttpRequestFactory();
        httpRequestFactory.setConnectTimeout(5000);
        httpRequestFactory.setReadTimeout(5000);
        httpRequestFactory.setBufferRequestBody(false);
        return new RestTemplate();
    }
}
