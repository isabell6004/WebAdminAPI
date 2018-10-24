package net.fashiongo.webadmin.config.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.fashiongo.webadmin.utility.JsonResponse;

@Component
public class WebAdminAuthFailureHandler implements AuthenticationFailureHandler {
	private final ObjectMapper mapper;
    
    @Autowired
    public WebAdminAuthFailureHandler(ObjectMapper mapper) {
        this.mapper = mapper;
    }	
    
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		
		JsonResponse<String> res = new JsonResponse<String>();
		
		if(exception instanceof BadCredentialsException){
			BadCredentialsException badException = (BadCredentialsException)exception;
			res = new JsonResponse<String>(false, badException.getMessage(), null);
		}else{
			res = new JsonResponse<String>(false, exception.getMessage(), null);
		}

		mapper.writeValue(response.getWriter(), res);
	}
}