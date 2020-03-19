package net.fashiongo.webadmin.service;

import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import net.fashiongo.webadmin.utility.Utility;


@Component
@Slf4j
public class LoggingServiceImpl implements LoggingService {

    //info logger
    private final static Logger logger = LoggerFactory.getLogger("webadminAccessPayloadLogger");

    @Override
    public void logRequest(HttpServletRequest httpServletRequest, Object body) {
        StringBuilder stringBuilder = new StringBuilder();
        Map<String, String> parameters = buildParametersMap(httpServletRequest);

        stringBuilder.append("REQUEST ");
        stringBuilder.append("username=[").append(Utility.getUsername()).append("] ");
        stringBuilder.append("ipaddress=[").append(Utility.getIpAddress(httpServletRequest)).append("] ");
        stringBuilder.append("method=[").append(httpServletRequest.getMethod()).append("] ");
        stringBuilder.append("path=[").append(httpServletRequest.getRequestURI()).append("] ");
        stringBuilder.append("headers=[").append(buildHeadersMap(httpServletRequest)).append("] ");
        
        if (!parameters.isEmpty()) {
            stringBuilder.append("parameters=[").append(parameters).append("] ");
        }
        
        if (body != null) {
            stringBuilder.append("body=[" + body + "]");
        }

        if (!httpServletRequest.getRequestURI().contains("getserverheartbeat")) {
            log.info(stringBuilder.toString());
            logger.info(stringBuilder.toString());
        }
    }
    
    @Override
    public void logResponse(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object body) {

            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append("RESPONSE ");
            stringBuilder.append("username=[").append(Utility.getUsername()).append("] ");
            stringBuilder.append("ipaddress=[").append(Utility.getIpAddress(httpServletRequest)).append("] ");
            stringBuilder.append("method=[").append(httpServletRequest.getMethod()).append("] ");
            stringBuilder.append("path=[").append(httpServletRequest.getRequestURI()).append("] ");
            stringBuilder.append("responseHeaders=[").append(buildHeadersMap(httpServletResponse)).append("] ");
            stringBuilder.append("responseBody=[").append(body).append("] ");

        if (!httpServletRequest.getRequestURI().contains("getserverheartbeat")) {
            log.info(stringBuilder.toString());
            logger.info(stringBuilder.toString());
        }
    }
    
    private Map<String, String> buildParametersMap(HttpServletRequest httpServletRequest) {
        Map<String, String> resultMap = new HashMap<>();
        Enumeration<String> parameterNames = httpServletRequest.getParameterNames();
        
        while (parameterNames.hasMoreElements()) {
            String key = parameterNames.nextElement();
            String value = httpServletRequest.getParameter(key);
            resultMap.put(key, value);
        }
        
        return resultMap;
    }

    private Map<String, String> buildHeadersMap(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        
        return map;
    }
    
    private Map<String, String> buildHeadersMap(HttpServletResponse response) {
        Map<String, String> map = new HashMap<>();
        
        Collection<String> headerNames = response.getHeaderNames();
        for (String header : headerNames) {
            map.put(header, response.getHeader(header));
        }
        
        return map;
    }
}