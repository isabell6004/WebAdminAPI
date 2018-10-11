package net.fashiongo.webadmin.utility;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import net.fashiongo.webadmin.model.pojo.WebAdminLoginUser;


public class Utility {
	public static WebAdminLoginUser getUserInfo() {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication auth = context.getAuthentication();
		return (WebAdminLoginUser)auth.getDetails();
	}
	
	public static String getIpAddress(HttpServletRequest request) {
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) ipAddress = request.getRemoteAddr();
        
		return ipAddress;
	}
}