/**
 * 
 */
package net.fashiongo.webadmin.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

/**
 * @author kjback
 *
 */
@Component
public class SessionFilter implements Filter {
	static Logger logger = LoggerFactory.getLogger(SessionFilter.class);
	
	private String secretKey = "fgwav2^^9070";
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    	HttpServletRequest req = (HttpServletRequest) request;
    	String method = req.getMethod().toLowerCase();
//    	logger.info("RequestURI: " + req.getRequestURI());
    	if (method.equals("options") || req.getRequestURI().startsWith("/swagger") || req.getRequestURI().startsWith("/webjars") ||
    			req.getRequestURI().startsWith("/v2/api") || req.getRequestURI().startsWith("/webhook/")) {
    		chain.doFilter(request, response);
    		return;
    	}
    	
   	
		HttpServletResponse res = (HttpServletResponse) response;
		String authorization = req.getHeader("Authorization");
		if(StringUtils.isEmpty(authorization)) {
//			logger.info("SessionFilter: Empty Token");
			res.setStatus(499);
			return;
		}
		
		String[] tokens = authorization.split(" ");
		String accessToken = "";
		if (tokens.length > 1) accessToken = tokens[1];
//		logger.info("SessionFilter - access token: " + accessToken);
		
		if (StringUtils.isEmpty(accessToken)) {
//			logger.info("SessionFilter: Empty Access Token");
			res.setStatus(499);
			return;
		}
		
		try {
			Claims claims = Jwts.parser()
				.setSigningKey(secretKey.getBytes("UTF-8"))
				.parseClaimsJws(accessToken)
				.getBody();
			
//			logger.info("SessionFilter - claims: {}", claims);
			Integer userId = (Integer) claims.get("userId");
			String username = (String) claims.get("username");
			String fullName = (String) claims.get("fullname");
			String role = (String)claims.get("roleid");
			
			HttpSession s = ((HttpServletRequest) request).getSession(false);
			if (s == null) { // set session
				s = ((HttpServletRequest) request).getSession(true);
				s.setAttribute("token", accessToken);
				s.setAttribute("userId", userId);
				s.setAttribute("username", username);
				s.setAttribute("fullName", fullName);
				s.setAttribute("role", role);
//				logger.info("SessionFilter - sessionId: " + s.getId());
//				logger.info("SessionFilter - userId: " + s.getAttribute("userId"));
			} 
//			else { // check token
//				if (!accessToken.equals(s.getAttribute("token"))) {
//					logger.info("SessionFilter - sessionId: " + s.getId());
//					logger.info("SessionFilter - token in session:" + s.getAttribute("token"));
//					logger.info("SessionFilter: Token doesn't match");
//					res.setStatus(499);
//					return;
//				}
//			}
			
		} catch (SignatureException e) {
//			logger.info("SessionFilter - SignatureException: ", e.getMessage());
			res.setStatus(499);
			return;
		} catch (ExpiredJwtException e) {
//			logger.info("SessionFilter - ExpiredJwtException: ", e.getMessage());
			res.setStatus(499);
			return;
		} catch (Exception e) {
//			logger.info("SessionFilter - Exception: ", e.getMessage());
			res.setStatus(499);
			return;
		}
        chain.doFilter(request, response);
	}
	
	public void destroy() {}
}
