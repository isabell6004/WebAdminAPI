package net.fashiongo.webadmin.controller.ads;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(value = "product-api")
public class AdProductApiController {

    private static final String HEADER_NAME_SERVICE_TYPE = "Referer-Service-Type";
    private static final String HEADER_NAME_APPLICATION_TYPE = "Referer-Application-Type";
    private static final String HEADER_NAME_REQUEST_ID = "Request-Id";
    private static final String HEADER_VALUE_SERVICE_TYPE = "FG";
    private static final String HEADER_VALUE_APPLICATION_TYPE = "AD";

    @Value("${webAdminapi.productApi.endpoint}")
    private String endpoint;
    private RestTemplate restTemplate;

    public AdProductApiController(@Qualifier("adApiRestTemplate") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping(value = "**")
    public ResponseEntity proxyProductApi(@RequestBody(required = false) Object body, org.apache.catalina.servlet4preview.http.HttpServletRequest request) {

        String url = convertToProductApi(request);
        HttpMethod method = HttpMethod.valueOf(request.getMethod());

        // TODO: temporary Request Id
        String requestId = UUID.randomUUID().toString();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HEADER_NAME_SERVICE_TYPE, HEADER_VALUE_SERVICE_TYPE);
        httpHeaders.add(HEADER_NAME_APPLICATION_TYPE, HEADER_VALUE_APPLICATION_TYPE);
        httpHeaders.add(HEADER_NAME_REQUEST_ID, requestId);

        HttpEntity httpEntity = new HttpEntity<>(body, httpHeaders);

        ResponseEntity<Map> response = null;
        try {
            return this.restTemplate.exchange(URI.create(url), method, httpEntity, Map.class);
        } catch (HttpClientErrorException ex) {
            response = new ResponseEntity<>(ex.getStatusCode());
        } catch (Exception e) {
            log.error("API Exception", e);
        }

        if (response == null)
            return null;

        HttpHeaders headers = response.getHeaders();
        if (headers != null && headers.size() > 0) {
            // 헤더가 있다면, 헤더 없이 리턴
            return new ResponseEntity<>(response.getBody(), response.getStatusCode());
        }

        return response;
    }

    private String convertToProductApi(HttpServletRequest request) {

        String urlPathDelimiter = "/";

        String requestUri = null;
        try {
            requestUri = URLDecoder.decode(request.getRequestURI(), StandardCharsets.UTF_8.name());
        } catch (Exception e) {
            log.error("UTF8 Encoding Error", request.getRequestURI());
            throw new ResourceNotFoundException("Encoding Error", e, request.getRequestURI());
        }

        if (StringUtils.isEmpty(requestUri))
            throw new ResourceNotFoundException("Uri is null", request.getRequestURI());

        String[] urlParts = requestUri.split("/");
        if (urlParts == null || urlParts.length <= 2)
            throw new ResourceNotFoundException("Invalid uri format. ", requestUri);

        String url = Arrays.stream(urlParts)
                .filter(urlPart -> !StringUtils.isEmpty(urlPart))
                .skip(2L)
                .collect(Collectors.joining(urlPathDelimiter));

        url = UriComponentsBuilder.fromPath(url).toUriString();

        if (!StringUtils.isEmpty(request.getQueryString())) {
            url += "?" + request.getQueryString();
        }

        return String.format("%s/v1.0/%s", endpoint, url);
    }
}
