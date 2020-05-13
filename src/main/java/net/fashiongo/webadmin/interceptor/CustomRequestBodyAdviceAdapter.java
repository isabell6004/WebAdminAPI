package net.fashiongo.webadmin.interceptor;

import java.lang.reflect.Type;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.utility.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import net.fashiongo.webadmin.service.LoggingService;

@Slf4j
@ControllerAdvice
public class CustomRequestBodyAdviceAdapter extends RequestBodyAdviceAdapter {
    
    @Autowired
    LoggingService loggingService;
    
    @Autowired
    HttpServletRequest httpServletRequest;
    
    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> aClass) {

        return true;
    }

    
    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
            Class<? extends HttpMessageConverter<?>> converterType) {
        loggingService.logRequest(httpServletRequest, body);
        
        return super.afterBodyRead(body, inputMessage, parameter, targetType, converterType);
    }

    @ExceptionHandler(InvalidFormatException.class)
    @ResponseBody
    public JsonResponse<String> handleInvalidFormatException(InvalidFormatException e) {
        log.error("InvalidFormatException={}", e.getMessage());
        return new JsonResponse<>(false, "Invalid format",  null);
    }
}