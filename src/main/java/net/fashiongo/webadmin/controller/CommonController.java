package net.fashiongo.webadmin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.fashiongo.webadmin.model.pojo.response.GetBidAdPagesResponse;
import net.fashiongo.webadmin.model.pojo.response.GetCountryStatesResponse;
import net.fashiongo.webadmin.model.primary.AdPageSpot;
import net.fashiongo.webadmin.model.primary.SecurityGroup;
import net.fashiongo.webadmin.model.primary.SecurityUser;
import net.fashiongo.webadmin.model.primary.TopCategories;
import net.fashiongo.webadmin.service.CommonService;
import net.fashiongo.webadmin.service.SecurityGroupService;
import net.fashiongo.webadmin.utility.JsonResponse;

/**
 * 
 * @author JungHwan
 */
@RestController
@RequestMapping(value = "/common", produces = "application/json")
public class CommonController {
	@Autowired
	CommonService commonService;
	
	@Autowired
	SecurityGroupService securityGroupService;
	
	@RequestMapping(value="getsecuritygroups", method=RequestMethod.POST)
	public JsonResponse<List<SecurityGroup>> GetSecurityGroups() {
		JsonResponse<List<SecurityGroup>> results = new JsonResponse<List<SecurityGroup>>();
		List<SecurityGroup> result  = securityGroupService.GetCommonSecurityGroup();
		results.setData(result);
		return results;
	}

	/**
	 * Get Bid AdPage
	 * 
	 * @since 2018. 10. 11.
	 * @author Junghwan Lee
	 * @return JsonResponse<GetBidAdPagesResponse>
	 */
	@RequestMapping(value = "getbidadpages", method = RequestMethod.POST)
	public JsonResponse<GetBidAdPagesResponse> GetBidAdPages() {
		JsonResponse<GetBidAdPagesResponse> results = new JsonResponse<GetBidAdPagesResponse>();
		GetBidAdPagesResponse result = commonService.GetBidAdPages();

		results.setSuccess(true);
		results.setData(result);

		return results;
	}

	/**
	 * Get Bid AdPage Spots
	 * 
	 * @since 2018. 10. 11.
	 * @author Junghwan Lee
	 * @param pageId
	 * @return JsonResponse<List<AdPageSpot>>
	 */
	@RequestMapping(value = "getbidadpagespots", method = RequestMethod.POST)
	public JsonResponse<List<AdPageSpot>> GetBidAdPageSpots(@RequestBody Integer pageId) {
		JsonResponse<List<AdPageSpot>> results = new JsonResponse<List<AdPageSpot>>();
		List<AdPageSpot> result = commonService.GetBidAdPageSpots(pageId);
		
		results.setSuccess(true);
		results.setData(result);
		
		return results;
	}
	
	/**
	 * Get Bid AdPage Spots Combined 
	 * 
	 * @since 2018. 10. 11.
	 * @author Junghwan Lee
	 * @return JsonResponse<String>
	 */
	@Deprecated
	@RequestMapping(value = "getbidadpagespotscombined", method = RequestMethod.POST)
	public JsonResponse<String> GetBidAdPageSpotsCombined() {
		JsonResponse<String> results = new JsonResponse<String>();
		
		return results;
	}
	
	/**
	 * Get Menu ID
	 * 
	 * @since 2018. 10. 05.
	 * @author DAHYE
	 * @param Page Name
	 * @return Page id
	 * @throws Exception
	 */
	@RequestMapping(value = "getmenuid", method = RequestMethod.POST)
	public JsonResponse<Integer> GetMenuID(@RequestBody String pageName) {
		pageName = pageName != null ? pageName : "";
		JsonResponse<Integer> results = new JsonResponse<Integer>();
		Integer result = commonService.GetMenuID(pageName);
		results.setData(result);
		return results;
	}
	
	/**
	 * Get Server Heart Beat
	 * 
	 * @since 2018. 10. 12.
	 * @author DAHYE
	 * @param q
	 * @return "Spring Boot"
	 */
	@RequestMapping(value = "getserverheartbeat", method = RequestMethod.POST)
	public JsonResponse<String> GetServerHeartBeat(@RequestBody Long q) {
		JsonResponse<String> results = new JsonResponse<String>();
		String result = commonService.GetServerHeartBeat(q);
		results.setMessage(result);
		return results;
	}
	
	/**
	 * Get Country States
	 * 
	 * @since 2018. 10. 15.
	 * @author DAHYE
	 * @param countryAbbrev
	 * @return JsonResponse<GetCountryStatesResponse>
	 */
	@RequestMapping(value = "getcountrystates", method = RequestMethod.POST)
	public JsonResponse<GetCountryStatesResponse> GetCountryStates(@RequestBody String countryAbbrev) {
		JsonResponse<GetCountryStatesResponse> results = commonService.GetCountryStates(countryAbbrev);
		
		return results;
	}
	
	/**
	 * Get Top Categories
	 * 
	 * @since 2018. 10. 11.
	 * @author DAHYE
	 * @param countryAbbrev
	 * @return JsonResponse<GetCountryStatesResponse>
	 */
	@RequestMapping(value = "gettopcategories", method = RequestMethod.POST)
	public JsonResponse<List<TopCategories>> GetTopCategories() {
		JsonResponse<List<TopCategories>> results = new JsonResponse<List<TopCategories>>(false, null, null, null);
		List<TopCategories> result = commonService.GetTopCategories();
		results.setSuccess(true);
		results.setData(result);
		return results;
	}
	
	/**
	 * 
	 * Get Security Users
	 * 
	 * @since 2018. 10. 10.
	 * @author Nayeon Kim
	 * @return GetSecurityUserResponse
	 */
	@RequestMapping(value="getsecurityusers", method=RequestMethod.POST)
	public JsonResponse<List<SecurityUser>> GetSecurityUser() {
		JsonResponse<List<SecurityUser>> results = new JsonResponse<List<SecurityUser>>(false, null, 0, null);
		
		List<SecurityUser> result = commonService.GetSecurityUser();
		results.setData(result);
		results.setSuccess(true);
		
		return results;
	}
}
