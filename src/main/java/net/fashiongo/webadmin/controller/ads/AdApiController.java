package net.fashiongo.webadmin.controller.ads;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.model.ads.ApiResponse;
import net.fashiongo.webadmin.model.ads.ErrorCode;
import net.fashiongo.webadmin.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(value = "adapi") // context-path = "/api" 가 있습니다. 실제 RequestMapping(value = "api/adapi")
public class AdApiController {

    private static final String URL_PATH_DELIMITER = "/";
    private static final String CHANNEL_VENDOR_API = "admin-api";

    private static final String HEADER_NAME_CHANNEL = "FG-Channel";
    private static final String HEADER_NAME_USER_ID = "FG-User-Id";
    private static final String HEADER_NAME_USER_NAME = "FG-User-Name";
    private static final String HEADER_NAME_REQUEST_ID = "X-Request-ID";
    private static final String HEADER_NAME_VENDOR_ID = "FG-Vendor-Id";

    @Autowired
    @Qualifier("adApiRestTemplate")
    private RestTemplate restTemplate;

    @Value("${webAdminapi.adapi.endpoint}")
    private String adApiEndpoint;

    @RequestMapping(
            value = {
                    "spots/**/slots/**/items/**"
            })
    public ResponseEntity proxyWithVendorId(@RequestParam(value = "vendorId") Long vendorId,
                                            @RequestBody Object body,
                                            HttpServletRequest request) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HEADER_NAME_CHANNEL, CHANNEL_VENDOR_API);
        httpHeaders.add(HEADER_NAME_USER_ID, Utility.getUserInfo().getUserId().toString());
        httpHeaders.add(HEADER_NAME_USER_NAME, Utility.getUsername());
        httpHeaders.add(HEADER_NAME_REQUEST_ID, request.getHeader(HEADER_NAME_REQUEST_ID));
        httpHeaders.add(HEADER_NAME_VENDOR_ID, vendorId.toString());

        return proxyAdApi(body, request, httpHeaders);
    }

    @RequestMapping(value = "*")
    public ResponseEntity proxy(@RequestBody(required = false) Object body, HttpServletRequest request) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HEADER_NAME_CHANNEL, CHANNEL_VENDOR_API);
        httpHeaders.add(HEADER_NAME_USER_ID, Utility.getUserInfo().getUserId().toString());
        httpHeaders.add(HEADER_NAME_USER_NAME, Utility.getUsername());
        httpHeaders.add(HEADER_NAME_REQUEST_ID, request.getHeader(HEADER_NAME_REQUEST_ID));

        return proxyAdApi(body, request, httpHeaders);
    }

    private ResponseEntity proxyAdApi(Object body, HttpServletRequest request, HttpHeaders httpHeaders) {

        String apiUrl = convertToAdApi(request);
        HttpMethod method = HttpMethod.valueOf(request.getMethod());

        HttpEntity httpEntity = new HttpEntity<>(body, httpHeaders);

        ResponseEntity<Map> response = null;
        try {
            return this.restTemplate.exchange(URI.create(apiUrl), method, httpEntity, Map.class);
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

    private String convertToAdApi(HttpServletRequest request) {

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

        String apiUrl = Arrays.stream(urlParts)
                .filter(urlPart -> !StringUtils.isEmpty(urlPart))
                .skip(2L)                                           // remove `api/adapi`
                .collect(Collectors.joining(URL_PATH_DELIMITER));

        apiUrl = UriComponentsBuilder.fromPath(apiUrl).toUriString();

        if (!StringUtils.isEmpty(request.getQueryString())) {
            apiUrl += "?" + request.getQueryString();
        }

        return String.format("%s/v2/%s", adApiEndpoint, apiUrl);
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ApiResponse<Void> handleException(Exception ex) {

        log.error("internal system error", ex);
        return new ApiResponse<>(ErrorCode.COMMON_ERROR, "internal system error", null);
    }
}
