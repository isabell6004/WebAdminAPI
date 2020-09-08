package net.fashiongo.webadmin.service.externalutil;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class HttpClientWrapper {

    // debug logger
    private final static Logger logger = LoggerFactory.getLogger("fashiongoApiPayloadLogger");
    private RestTemplate restTemplateWrapper;

    public HttpClientWrapper(RestTemplate restTemplateWrapper) {
        this.restTemplateWrapper = restTemplateWrapper;
    }

    private final ObjectMapper mapper = new ObjectMapper();

    public String post(String endpoint, Object request, Map<String, String> headerMap) {
        try {
            return post(endpoint, mapper.writeValueAsString(request), headerMap);
        } catch (JsonProcessingException e) {
            log.warn("fail to generate from object to json.");
            return StringUtils.EMPTY;
        }
    }

    public String post(String endpoint, Object request) {
        return post(endpoint, request, null);
    }

    public String post(String endpoint, String payload, Map<String, String> headerMap) {
        return execute(endpoint, payload, headerMap, HttpMethod.POST);
    }

    public String get(String endpoint, Map<String, String> headerMap) {
        return execute(endpoint, null, headerMap, HttpMethod.GET);
    }

    public String post(String endpoint, String payload) {
        return post(endpoint, payload, null);
    }

    public String put(String endpoint, String payload, Map<String, String> headerMap) {
        return execute(endpoint, payload, headerMap, HttpMethod.PUT);
    }

    public String put(String endpoint, Map<String, String> headerMap) {
        return execute(endpoint, null, headerMap, HttpMethod.PUT);
    }

    public String put(String endpoint, String payload) {
        return execute(endpoint, payload, null, HttpMethod.PUT);
    }

    public String put(String endpoint, Object request, Map<String, String> headerMap) {
        try {
            return put(endpoint, mapper.writeValueAsString(request), headerMap);
        } catch (JsonProcessingException e) {
            log.warn("fail to generate from object to json.");
        }
        return StringUtils.EMPTY;
    }

    public String delete(String endpoint) {
        return delete(endpoint, null);
    }

    public String delete(String endpoint, Map<String, String> headerMap) {
        return execute(endpoint, null, headerMap, HttpMethod.DELETE);
    }

    public String patch(String endpoint, Object request, Map<String, String> headerMap) {
        try {
            return patch(endpoint, mapper.writeValueAsString(request), headerMap);
        } catch (JsonProcessingException e) {
            log.warn("fail to generate from object to json.");
            return StringUtils.EMPTY;
        }
    }

    public String patch(String endpoint, String payload, Map<String, String> headerMap) {
        return execute(endpoint, payload, headerMap, HttpMethod.PATCH);
    }

    public <T> T get(String endpoint, Map<String, String> headerMap, Class<T> type) {
        return execute(endpoint, null, headerMap, HttpMethod.GET, type);
    }

    public <T> T get(String endpoint, Map<String, String> headerMap, ParameterizedTypeReference<T> type) {
        return execute(endpoint, null, headerMap, HttpMethod.GET, type);
    }

    public <T> T post(String endpoint, Map<String, String> headerMap, Object payload, ParameterizedTypeReference<T> type) {
        try {
            return execute(endpoint, mapper.writeValueAsString(payload), headerMap, HttpMethod.POST, type);
        } catch (JsonProcessingException e) {
            log.warn("fail to generate from object to json.");
            return null;
        }
    }

    public <T> T patch(String endpoint, Map<String, String> headerMap, Object payload, ParameterizedTypeReference<T> type) {
        try {
            return execute(endpoint, mapper.writeValueAsString(payload), headerMap, HttpMethod.PATCH, type);
        } catch (JsonProcessingException e) {
            log.warn("fail to generate from object to json.");
            return null;
        }
    }

    public String execute(String endpoint, String payload, Map<String, String> headerMap, HttpMethod method) {
        return Optional.ofNullable(execute(endpoint, payload, headerMap, method, String.class))
                .orElse(StringUtils.EMPTY);
    }

    public <T> T execute(String endpoint, String payload, Map<String, String> headerMap, HttpMethod method, Class<T> type) {
        HttpHeaders headers = new HttpHeaders();
        if (MapUtils.isNotEmpty(headerMap)) {
            headerMap.keySet().forEach(x -> headers.add(x, headerMap.get(x)));
        }

        try {
            logger.info("endpoint: {}, payload : {}", endpoint, payload); // temporary
            log.debug("endpoint: {}, payload : {}", endpoint, payload);
            HttpEntity<String> requestEntity = new HttpEntity<>(payload, headers);
            ResponseEntity<T> response = restTemplateWrapper.exchange(endpoint, method, requestEntity, type);
            log.debug("status code:{}, body:{}", response.getStatusCode(), response.toString());
            if (response.getStatusCode() != HttpStatus.OK) {
                log.warn("fail to call the api: {}, {}, {}", endpoint, payload, headers);
                return null;
            } else {
                log.debug("response : {}, {}", response, response.getBody());
                return response.getBody();
            }
        } catch (Throwable t) {
            log.error("fail to call the api : {}", t.getMessage(), t);
        }
        return null;
    }

    public <T> T execute(String endpoint, String payload, Map<String, String> headerMap, HttpMethod method, ParameterizedTypeReference<T> type) {
        HttpHeaders headers = new HttpHeaders();
        if (MapUtils.isNotEmpty(headerMap)) {
            headerMap.keySet().forEach(x -> headers.add(x, headerMap.get(x)));
        }

        try {
            logger.info("endpoint: {}, payload : {}", endpoint, payload); // temporary
            log.debug("endpoint: {}, payload : {}", endpoint, payload);
            HttpEntity<String> requestEntity = new HttpEntity<>(payload, headers);
            ResponseEntity<T> response = restTemplateWrapper.exchange(endpoint, method, requestEntity, type);
            log.debug("status code:{}, body:{}", response.getStatusCode(), response.toString());
            if (response.getStatusCode() != HttpStatus.OK) {
                log.warn("fail to call the api: {}, {}, {}", endpoint, payload, headers);
                return null;
            } else {
                log.debug("response : {}, {}", response, response.getBody());
                return response.getBody();
            }
        } catch (Throwable t) {
            log.error("fail to call the api : {}", t.getMessage(), t);
        }
        return null;
    }
}
