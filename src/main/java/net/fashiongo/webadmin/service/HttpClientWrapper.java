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
        return post(endpoint, request, null);
    }

    public ResponseEntity<JsonResponse> post(String endpoint, String payload, Map<String, String> headerMap) {
        return execute(endpoint, payload, headerMap, HttpMethod.POST);
    }

    private ResponseEntity<JsonResponse> execute(String endpoint, String payload, Map<String, String> headerMap, HttpMethod method) {
        HttpHeaders headers = new HttpHeaders();
        if(MapUtils.isNotEmpty(headerMap)) {
            headerMap.keySet().forEach(x -> headers.add(x, headerMap.get(x)));
        }

        log.debug("endpoint: {}, payload : {}", endpoint, payload);
        HttpEntity<String> requestEntity = new HttpEntity<>(payload, headers);
        ResponseEntity<JsonResponse> response = restTemplateWrapper.exchange(endpoint, method, requestEntity, JsonResponse.class);
        log.debug("status code:{}, body:{}", response.getStatusCode(), response.toString());
        if( response.getStatusCode() != HttpStatus.OK ) {
            log.warn("fail to call the api: {}, {}", endpoint, payload);
        }
        return response;
    }

    public ResponseEntity<JsonResponse> post(String endpoint, String payload) {
        return post(endpoint, payload, null);
    }

    public ResponseEntity<JsonResponse> put(String endpoint, String payload, Map<String, String> headerMap) {
        return execute(endpoint, payload, headerMap, HttpMethod.PUT);
    }

    public ResponseEntity<JsonResponse> put(String endpoint, Map<String, String> headerMap) {
        return execute(endpoint, null, headerMap, HttpMethod.PUT);
    }

    public ResponseEntity<JsonResponse> put(String endpoint, String payload) {
        return execute(endpoint, payload, null, HttpMethod.PUT);
    }

    public ResponseEntity<JsonResponse> put(String endpoint, Object request, Map<String, String> headerMap) {
        try {
            return put(endpoint, mapper.writeValueAsString(request), headerMap);
        } catch (JsonProcessingException e) {
            log.warn("fail to generate from object to json.");
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<JsonResponse> delete(String endpoint) {
        return delete(endpoint, null);
    }

    public ResponseEntity<JsonResponse> delete(String endpoint, Map<String, String> headerMap) {
        return execute(endpoint, null, headerMap, HttpMethod.DELETE);
    }
}
