package net.fashiongo.webadmin.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.utility.JsonResponse;
import org.apache.commons.collections.MapUtils;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Created by jinwoo on 2019-12-12.
 */
@Service
@Slf4j
public class HttpClientWrapper {

    private RestTemplate restTemplateWrapper;

    public HttpClientWrapper (RestTemplate restTemplateWrapper) {
        this.restTemplateWrapper = restTemplateWrapper;
    }

    private final ObjectMapper mapper = new ObjectMapper();

    public ResponseEntity<JsonResponse> post(String endpoint, Object request, Map<String, String> headerMap) {
        try {
            return post(endpoint, mapper.writeValueAsString(request), headerMap);
        } catch (JsonProcessingException e) {
            log.warn("fail to generate from object to json.");
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<JsonResponse> post(String endpoint, Object request) {
        try {
            return post(endpoint, mapper.writeValueAsString(request));
        } catch (JsonProcessingException e) {
            log.warn("fail to generate from object to json.");
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<JsonResponse> post(String endpoint, String payload, Map<String, String> headerMap) {

        HttpHeaders headers = new HttpHeaders();
        if(MapUtils.isNotEmpty(headerMap)) {
            headerMap.keySet().forEach(x -> headers.add(x, headerMap.get(x)));
        }

        HttpEntity<String> requestEntity = new HttpEntity<>(payload, headers);
        return restTemplateWrapper.exchange(endpoint, HttpMethod.POST, requestEntity, JsonResponse.class);
    }

    public ResponseEntity<JsonResponse> post(String endpoint, String payload) {
        return post(endpoint, payload, null);
    }
}
