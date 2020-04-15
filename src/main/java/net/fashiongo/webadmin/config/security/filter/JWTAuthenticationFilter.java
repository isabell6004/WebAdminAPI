package net.fashiongo.webadmin.config.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.exception.TokenInvalidException;
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
		} catch (TokenInvalidException e) {
			try {
				//FGM/118
				//Token 검증 실패 시. 401 return.
				Utility.sendUnAuthorized(e.getMessage());
			} catch (Exception ex) {
				log.error("Utility.sendUnAuthorized : {0}", ex);
			}
		} catch (Exception ex) {
			//FGM/532
			try {
				//그 외 exception 발생 시.
				Utility.HttpResponse(ex.getMessage());
			} catch (Throwable throwable) {
				log.error("Utility.HttpResponse : {0}", throwable);
			}
		}
	}
}
