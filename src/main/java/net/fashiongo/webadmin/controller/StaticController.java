/**
 * 
 */
package net.fashiongo.webadmin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.fashiongo.webadmin.model.pojo.statics.response.GetDashboardResponse;
import net.fashiongo.webadmin.service.StaticService;
import net.fashiongo.webadmin.utility.HttpClient;
import net.fashiongo.webadmin.utility.JsonResponse;

/**
 * @author Incheol Jung
 */
@RestController
@RequestMapping(value="/stat", produces = "application/json")
public class StaticController {
	
	@Autowired
	StaticService staticService;
	
	@Autowired
	@Qualifier("vendorApiJsonClient")
	HttpClient jsonClient;
	
	
	/**
	 * 
	 * Get Dashboard
	 * 
	 * @since 2018. 10. 8.
	 * @author Incheol Jung
	 * @return
	 */
	@RequestMapping(value="getdashboard", method=RequestMethod.POST)
	public JsonResponse<GetDashboardResponse> getDashboard() {
		JsonResponse<GetDashboardResponse> results = new JsonResponse<GetDashboardResponse>(true, null, 0, null);
		
		GetDashboardResponse result = staticService.getDashboard();
		results.setData(result);
		
		return results;
	}
	
	@RequestMapping(value="vpi", method=RequestMethod.GET)
	public JsonResponse<String> getVpi(
			HttpServletRequest request) {
		String url = "vpi/" + "?" + request.getQueryString();
		
		return jsonClient.get(url);
	}
}