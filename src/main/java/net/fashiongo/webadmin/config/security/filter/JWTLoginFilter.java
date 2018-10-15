package net.fashiongo.webadmin.config.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.fashiongo.webadmin.config.security.AccountCredentials;
import net.fashiongo.webadmin.config.security.TokenAuthenticationService;
import net.fashiongo.webadmin.config.security.WebAdminUserAuthenticationToken;
import net.fashiongo.webadmin.model.pojo.WebAdminLoginUser;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {
	
	private final AuthenticationFailureHandler failureHandler;
  
	public JWTLoginFilter(
		String url, 
		AuthenticationManager authenticationManager, 
		AuthenticationFailureHandler failureHandler) {
		super(new AntPathRequestMatcher(url));
			
		this.failureHandler = failureHandler;
		setAuthenticationManager(authenticationManager);
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException{
		AccountCredentials credentials = new ObjectMapper().readValue(httpServletRequest.getInputStream(), AccountCredentials.class);
		WebAdminUserAuthenticationToken token = new WebAdminUserAuthenticationToken(credentials.getUsername(), credentials);
		token.setDetails(httpServletRequest);
		return getAuthenticationManager().authenticate(token);
	}
		
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
		WebAdminLoginUser user = ((WebAdminUserAuthenticationToken)authentication).getUserInfo();
		TokenAuthenticationService.addAuthentication(request, response, user);
	}
	
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
		failureHandler.onAuthenticationFailure(request, response, failed);
	}
}