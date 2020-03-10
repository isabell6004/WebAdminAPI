package net.fashiongo.webadmin.config.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import net.fashiongo.webadmin.config.security.TokenAuthenticationService;
import net.fashiongo.webadmin.utility.Utility;


@Slf4j
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
				//FGM/118
				Utility.sendUnAuthorized(e.getMessage());
			} catch (Exception ex) {
				log.error("Utility.sendUnAuthorized : {0}", ex);
			}
		}
	}
}
