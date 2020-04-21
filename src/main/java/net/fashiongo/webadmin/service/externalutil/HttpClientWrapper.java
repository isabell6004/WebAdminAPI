package net.fashiongo.webadmin.service.externalutil;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@Slf4j
public class HttpClientWrapper {

    // debug logger
    private final static Logger logger = LoggerFactory.getLogger("fashiongoApiPayloadLogger");
    private RestTemplate restTemplateWrapper;

    public HttpClientWrapper (RestTemplate restTemplateWrapper) {
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

    private String execute(String endpoint, String payload, Map<String, String> headerMap, HttpMethod method) {
        HttpHeaders headers = new HttpHeaders();
        if(MapUtils.isNotEmpty(headerMap)) {
            headerMap.keySet().forEach(x -> headers.add(x, headerMap.get(x)));
        }

        try {
            logger.info("endpoint: {}, payload : {}", endpoint, payload); // temporary
            log.debug("endpoint: {}, payload : {}", endpoint, payload);
            HttpEntity<String> requestEntity = new HttpEntity<>(payload, headers);
            ResponseEntity<String> response = restTemplateWrapper.exchange(endpoint, method, requestEntity, String.class);
            log.debug("status code:{}, body:{}", response.getStatusCode(), response.toString());
            if( response.getStatusCode() != HttpStatus.OK ) {
                log.warn("fail to call the api: {}, {}, {}", endpoint, payload, headers);
                return StringUtils.EMPTY;
            } else {
                log.debug("response : {}, {}", response, response.getBody());
                return response.getBody();
            }
        } catch (Throwable t) {
            log.error("fail to call the api : {}", t.getMessage(), t);
        }
        return StringUtils.EMPTY;
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
}
