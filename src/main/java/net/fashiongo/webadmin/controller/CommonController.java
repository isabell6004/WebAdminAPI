package net.fashiongo.webadmin.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.fashiongo.webadmin.model.pojo.admin.Resource;
import net.fashiongo.webadmin.model.pojo.admin.parameter.GetSecurityResourcesParameter;
import net.fashiongo.webadmin.model.pojo.admin.response.GetSecurityResourcesResponse;
import net.fashiongo.webadmin.model.pojo.common.parameter.GetBidAdPageSpotsParameter;
import net.fashiongo.webadmin.model.pojo.common.parameter.GetBidAdSpotCategory;
import net.fashiongo.webadmin.model.pojo.common.parameter.GetCountryStatesParameter;
import net.fashiongo.webadmin.model.pojo.common.parameter.GetMenuIDParameter;
import net.fashiongo.webadmin.model.pojo.common.parameter.GetServerHeartBeatParameter;
import net.fashiongo.webadmin.model.pojo.common.response.GetBidAdPagesResponse;
import net.fashiongo.webadmin.model.pojo.common.response.GetCountryStatesResponse;
import net.fashiongo.webadmin.model.primary.AdPageSpot;
import net.fashiongo.webadmin.model.primary.Category;
import net.fashiongo.webadmin.model.primary.SecurityGroup;
import net.fashiongo.webadmin.model.primary.SecurityUser;
import net.fashiongo.webadmin.model.primary.TopCategories;
import net.fashiongo.webadmin.service.AdminService;
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
	AdminService adminService;
	
	@Autowired
	CommonService commonService;
	
	@Autowired
	SecurityGroupService securityGroupService;
	
	@RequestMapping(value="getsecuritygroups", method=RequestMethod.POST)
	public JsonResponse<List<SecurityGroup>> GetSecurityGroups() {
		JsonResponse<List<SecurityGroup>> results = new JsonResponse<List<SecurityGroup>>();
		List<SecurityGroup> result  = securityGroupService.getCommonSecurityGroup();
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
	public JsonResponse<GetBidAdPagesResponse> getBidAdPages() {
		JsonResponse<GetBidAdPagesResponse> results = new JsonResponse<GetBidAdPagesResponse>();
		GetBidAdPagesResponse result = commonService.getBidAdPages();

		results.setSuccess(true);
		results.setData(result);

		return results;
	}

	/**
	 * Get Bid AdPage Spots
	 * 
	 * @since 2018. 10. 11.
	 * @author Junghwan Lee
	 * @param parameter
	 * @return JsonResponse<List<AdPageSpot>>
	 */
	@RequestMapping(value = "getbidadpagespots", method = RequestMethod.POST)
	public JsonResponse<List<AdPageSpot>> getBidAdPageSpots(@RequestBody GetBidAdPageSpotsParameter parameter) {
		JsonResponse<List<AdPageSpot>> results = new JsonResponse<List<AdPageSpot>>();
		List<AdPageSpot> result = commonService.getBidAdPageSpots(parameter.getPageId());
		
		results.setSuccess(true);
		results.setData(result);
		
		return results;
	}
	
	@RequestMapping(value = "getbidadpagespotCategory", method = RequestMethod.POST)
	public JsonResponse<List<Category>> getBidAdPageSpotCategry(@RequestBody GetBidAdSpotCategory parameter) {
		JsonResponse<List<Category>> results = new JsonResponse<List<Category>>();
		List<Category> result = commonService.getBidAdPageSpotCategory(parameter.getParentCategoryId(), parameter.getCategoryLevel());
		
		results.setSuccess(true);
		results.setData(result);
		
		return results;
	}
	
	@RequestMapping(value = "getAllCategoryListbyParentID", method = RequestMethod.POST)
	public JsonResponse<List<Category>> getAllCategoryListbyParentID(@RequestBody GetBidAdSpotCategory parameter) {
		JsonResponse<List<Category>> results = new JsonResponse<List<Category>>();
		List<Category> result = commonService.getAllCategoryListbyParentID(parameter.getParentCategoryId(),parameter.getParentCategoryId());
		
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
	public JsonResponse<String> getBidAdPageSpotsCombined() {
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
	public JsonResponse<Integer> getMenuID(@RequestBody GetMenuIDParameter parameters) {
		Integer menuID = commonService.getMenuID(parameters.getPageName());
		return new JsonResponse<Integer>(true, null, 0, menuID);
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
	public JsonResponse<String> getServerHeartBeat(@RequestBody GetServerHeartBeatParameter parameters) {
		String result = commonService.getServerHeartBeat(parameters.getQ());
		return new JsonResponse<String>(true, null, 0, result);
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
	public JsonResponse<GetCountryStatesResponse> getCountryStates(@RequestBody GetCountryStatesParameter parameters) {
		JsonResponse<GetCountryStatesResponse> results = commonService.getCountryStates(parameters.getCountryAbbrev());
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
	public JsonResponse<List<TopCategories>> getTopCategories() {
		List<TopCategories> categories = commonService.getTopCategories();
		return new JsonResponse<List<TopCategories>>(true, null, 0, categories);
	}
	
	/**
	 * 
	 * Get Security Users
	 * 
	 * @since 2018. 10. 10.
	 * @author Nayeon Kim
	 * @return List<SecurityUser>
	 */
	@RequestMapping(value="getsecurityusers", method=RequestMethod.POST)
	public JsonResponse<List<SecurityUser>> getSecurityUser() {
		List<SecurityUser> result = commonService.getSecurityUser();
		return new JsonResponse<List<SecurityUser>>(true, null, 0, result);
	}
	
	
	
	/**
	 * 
	 * Get Security Resources
	 * 
	 * @since 2018. 10. 15.
	 * @author Jiwon Kim
	 * @return GetSecurityResources
	 */
	@RequestMapping(value="getsecurityresources", method=RequestMethod.POST)
	public JsonResponse<GetSecurityResourcesResponse> GetSecurityResources (@RequestBody GetSecurityResourcesParameter parameters) {
		JsonResponse<GetSecurityResourcesResponse> results = new JsonResponse<GetSecurityResourcesResponse>(true, null, 0, null);
		GetSecurityResourcesResponse result = adminService.getSecurityResources(parameters);
		List<Resource> rs = result.getResource();
		rs = rs.stream().sorted((o1,o2) -> o1.getResourceName().toLowerCase().compareTo(o2.getResourceName().toLowerCase())).collect(Collectors.toList());
		result.setResource(rs);
		results.setData(result);
		return results;
	}
}
