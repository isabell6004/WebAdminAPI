package net.fashiongo.webadmin.utility;

import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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
        
		return ipAddress.equals("0:0:0:0:0:0:0:1") ? "::1" : ipAddress;
	}
	
	public static String getUserAgent(HttpServletRequest request) {
		return request.getHeader("User-Agent");
	}
	
	public static void HttpResponse(String exceptionMsg) throws Throwable {
		HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getResponse();
	    
		JsonResponse<String> res = new JsonResponse<String>();
		res.setSuccess(false);
		res.setCode(-1);
		res.setMessage(exceptionMsg);
		res.setData(null);
		
	    ObjectMapper om = new ObjectMapper();
		String returnStr = om.writeValueAsString(res);
		OutputStream ostr = response.getOutputStream();
		ostr.write(returnStr.getBytes());
		ostr.flush();
		ostr.close();
	}
}