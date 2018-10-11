package net.fashiongo.webadmin.config.security.filter;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.filter.GenericFilterBean;

import net.fashiongo.webadmin.config.security.TokenAuthenticationService;
import net.fashiongo.webadmin.utility.JsonResponse;
import net.fashiongo.webadmin.utility.Utility;


public class JWTAuthenticationFilter extends GenericFilterBean {
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		Authentication authentication;
		try {
			authentication = TokenAuthenticationService.getAuthentication((HttpServletRequest)request);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			filterChain.doFilter(request,response);
		} catch (Exception e) {
			try {
				Utility.HttpResponse(e.getMessage());
			} catch (Throwable e1) {
				e1.printStackTrace();
			}
		}
	}

}