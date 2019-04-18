/**
 * 
 */
package net.fashiongo.webadmin.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.model.pojo.statics.response.GetDashboardResponse;
import net.fashiongo.webadmin.service.StaticService;
import net.fashiongo.webadmin.utility.HttpClient;
import net.fashiongo.webadmin.utility.JsonResponse;

/**
 * @author Incheol Jung
 */
@Slf4j
@RestController
@RequestMapping(value="/stat", produces = "application/json")
public class StaticController {
	
	@Autowired
	StaticService staticService;
	
	@Autowired
	@Qualifier("vendorApiJsonClient")
	HttpClient jsonClient;
	
	@Autowired
	@Qualifier("statsApiJsonClient")
	HttpClient statsJsonClient;
	
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
	
	/**
	 * @author Kenny/Kyungwoo
	 * @since 2019-04-17
	 */
	@GetMapping(value="vendorkpi")
	public JsonResponse<String> getVendorKpi(
			@RequestParam(value="pn") Integer pn,
			@RequestParam(value="ps") Integer ps,
			@RequestParam(value="unit") Integer unit,
			@RequestParam(value="df") String df,
			@RequestParam(value="dt") String dt,
			@RequestParam(value="so") String so,
			@RequestParam(value="sq") String sq,
			@RequestParam(value="vendorStatus") Integer vendorStatus,
			@RequestParam(value="vendorCategory") Integer vendorCategory,
			@RequestParam(value="vendorType") String vendorType,
			@RequestParam(value="location") String location,
			@RequestParam(value="state") String state,
			@RequestParam(value="assignedUser") String assignedUser,
			@RequestParam(value="orderBy", required=false) String orderBy) {
		//log.debug("===========================getVendorKpi");
		
		//1. Process params
		ArrayList<String> params = new ArrayList<>();		
		if(pn!=null && pn!=0) params.add("pn="+pn);
		if(ps!=null && ps!=0) params.add("ps="+ps);
		if(unit!=null && unit!=0) params.add("unit="+unit);
		if(df!=null && !df.isEmpty()) params.add("df="+df);
		if(dt!=null && !dt.isEmpty()) params.add("dt="+dt);
		if(so!=null && !so.isEmpty()) params.add("so="+so);
		if(sq!=null && !sq.isEmpty()) params.add("sq="+sq);
		if(vendorStatus!=null && vendorStatus!=0) params.add("vendorStatus="+vendorStatus);
		if(vendorCategory!=null && vendorCategory!=0) params.add("vendorCategory="+vendorCategory);
		if(vendorType!=null && !vendorType.isEmpty()) params.add("vendorType="+vendorType);
		if(location!=null && !location.isEmpty()) params.add("location="+location);
		if(state!=null && !state.isEmpty()) params.add("state="+state);
		if(assignedUser!=null && !assignedUser.isEmpty()) params.add("assignedUser="+assignedUser);
		if(orderBy!=null && !orderBy.isEmpty()) params.add("orderBy="+orderBy);
		
		//2. Call StatsAPI
		return statsJsonClient.get("kpi/vendor?" + String.join("&", params));
	}
}