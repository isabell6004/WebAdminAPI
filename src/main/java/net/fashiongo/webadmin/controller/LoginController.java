package net.fashiongo.webadmin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.fashiongo.webadmin.service.LoginService;
import net.fashiongo.webadmin.utility.JsonResponse;
import net.fashiongo.webadmin.utility.Utility;

/**
 * 
 * @author Incheol Jung
 */
@RestController
@RequestMapping(value = "/authuser", produces = "application/json")
public class LoginController {
	
	@Autowired
	LoginService loginService;

	/**
	 * 
	 * Get Security Group
	 * 
	 * @since 2018. 10. 8.
	 * @author Incheol Jung
	 * @return
	 */
	@RequestMapping(value="checkip", method=RequestMethod.POST)
	public JsonResponse<String> CheckIP(HttpServletRequest request) {
		JsonResponse<String> results = new JsonResponse<String>(false, null, 0, null);
        String ipAddress = Utility.getIpAddress(request);
        
		boolean result  = loginService.CheckIP(ipAddress);
		results.setSuccess(result);
	
		return results;
	}
	
	/**
	 * 
	 * Log out
	 * 
	 * @since 2018. 10. 8.
	 * @author Incheol Jung
	 * @return
	 */
	@RequestMapping(value="logout", method=RequestMethod.POST)
	public JsonResponse<String> LogOut(HttpSession session) {
		JsonResponse<String> results = new JsonResponse<String>(true, null, 0, null);
		
		session.invalidate();
		return results;
	}
}