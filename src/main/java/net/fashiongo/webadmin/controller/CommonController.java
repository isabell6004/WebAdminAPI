package net.fashiongo.webadmin.controller;

import net.fashiongo.webadmin.data.model.common.CodeOrderStatus;
import net.fashiongo.webadmin.data.model.vendor.SendEmailParameter;
import net.fashiongo.webadmin.model.pojo.admin.parameter.GetSecurityResourcesParameter;
import net.fashiongo.webadmin.model.pojo.common.parameter.*;
import net.fashiongo.webadmin.model.pojo.common.response.GetCountryStatesResponse;
import net.fashiongo.webadmin.model.primary.*;
import net.fashiongo.webadmin.service.AdminService;
import net.fashiongo.webadmin.service.CommonService;
import net.fashiongo.webadmin.service.SecurityGroupService;
import net.fashiongo.webadmin.service.renewal.RenewalAdminService;
import net.fashiongo.webadmin.service.renewal.RenewalCommonService;
import net.fashiongo.webadmin.utility.JsonResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

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
	RenewalAdminService renewalAdminService;

	@Autowired
	CommonService commonService;
	
	@Autowired
	SecurityGroupService securityGroupService;

	@Autowired
	RenewalCommonService renewalCommonService;
	
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
	public JsonResponse<net.fashiongo.webadmin.data.model.ad.response.GetBidAdPagesResponse> getBidAdPages() {
		JsonResponse<net.fashiongo.webadmin.data.model.ad.response.GetBidAdPagesResponse> results = new JsonResponse<net.fashiongo.webadmin.data.model.ad.response.GetBidAdPagesResponse>();

		net.fashiongo.webadmin.data.model.ad.response.GetBidAdPagesResponse result = renewalCommonService.getBidAdPages();

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
	public JsonResponse<net.fashiongo.webadmin.data.model.admin.response.GetSecurityResourcesResponse> GetSecurityResources (@RequestBody GetSecurityResourcesParameter parameters) {
		JsonResponse<net.fashiongo.webadmin.data.model.admin.response.GetSecurityResourcesResponse> results = new JsonResponse<net.fashiongo.webadmin.data.model.admin.response.GetSecurityResourcesResponse>(true, null, 0, null);
		net.fashiongo.webadmin.data.model.admin.response.GetSecurityResourcesResponse securityResources = renewalAdminService.getSecurityResources(parameters);

		List<net.fashiongo.webadmin.data.model.admin.Resource> resourceList = securityResources.getResource()
				.stream()
				.sorted((o1, o2) -> o1.getResourceName().toLowerCase().compareTo(o2.getResourceName().toLowerCase())).collect(Collectors.toList());

		net.fashiongo.webadmin.data.model.admin.response.GetSecurityResourcesResponse responseData = net.fashiongo.webadmin.data.model.admin.response.GetSecurityResourcesResponse.builder()
				.resource(resourceList)
				.build();

		results.setData(responseData);
		return results;
	}

	@RequestMapping(value = "getcountries",method = RequestMethod.POST)
	public JsonResponse getCountries() {
		return renewalCommonService.getCountries();
	}

	@RequestMapping(value = "getorderstatus",method = RequestMethod.POST)
	public JsonResponse<List<CodeOrderStatus>> getOrderstatus() {
		JsonResponse<List<CodeOrderStatus>> jsonResponse = new JsonResponse();
		List<CodeOrderStatus> orderstatus = renewalCommonService.getOrderstatus();
		jsonResponse.setSuccess(true);
		jsonResponse.setData(orderstatus);

		return jsonResponse;
	}

	@PostMapping(value = "sendemail")
	public JsonResponse sendemail(@RequestBody SendEmailParameter param) {
		String title = StringUtils.isEmpty(param.getTitle()) ? "" : param.getTitle();
		String sender = StringUtils.isEmpty(param.getSender()) ? "" : param.getSender();
		String senderName = StringUtils.isEmpty(param.getSendername()) ? "" : param.getSendername();
		String recipient = StringUtils.isEmpty(param.getRecipient()) ? "" : param.getRecipient();
		String recipientName = StringUtils.isEmpty(param.getRecipientname()) ? "" : param.getRecipientname();
		String message = StringUtils.isEmpty(param.getMessage()) ? "" : param.getMessage();

		JsonResponse response = renewalCommonService.sendEmail(title, sender, senderName, recipient, recipientName, message);

		return response;
	}
}
