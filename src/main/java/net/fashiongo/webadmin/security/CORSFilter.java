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

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.stereotype.Component;

@Component(AbstractSecurityWebApplicationInitializer.DEFAULT_FILTER_NAME)
public class CORSFilter implements Filter{
    static Logger logger = LoggerFactory.getLogger(CORSFilter.class);
    @Value("${spring.profiles.active}")
    String activeProfile;
    @Value("{client.uri}")
    String clientUri;
    

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
    	logger.info("current active profile:" + activeProfile);
    	
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        String origin = request.getHeader("origin");
        logger.info("access control allow origin from request:" + origin);
        if (StringUtils.isNotEmpty(origin)) {
            response.setHeader("Access-Control-Allow-Origin", origin);
        } else {
            response.setHeader("Access-Control-Allow-Origin", "*");
        }
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Access-Control-Allow-Headers, Origin, Accept, X-Requested-With, Access-Control-Request-Method, Access-Control-Request-Headers, Authorization, Content-Type, Content-Length, Accept-Encoding, X-CSRF-Token, X-Custom-Header, Access-Control-Allow-Credentials, Access-Control-Allow-Methods, Access-Control-Allow-Origin");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        chain.doFilter(request, response);
    }

    public void destroy() {}
}