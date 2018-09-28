/**
 * 
 */
package net.fashiongo.webadmin.utility;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author kcha
 *
 */
public class Utility {
	static Logger logger = LoggerFactory.getLogger(Utility.class);
	
	/* return logined username */
	public static String getWebAdminUserName() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession s = attr.getRequest().getSession();
		logger.info("username: " + s.getAttribute("username"));
		return (String) s.getAttribute("username");
	}
	
	/* return [security.user].UserID */
	public static Integer getWebAdminUserId() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession s = attr.getRequest().getSession();
		return (Integer) s.getAttribute("userId");
	}
	
	public static String getWebAdminUserFullName() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession s = attr.getRequest().getSession();
		return (String) s.getAttribute("fullName");
	}
	
	
	public static String getRole() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession s = attr.getRequest().getSession();
		return (String) s.getAttribute("role");
	}
}