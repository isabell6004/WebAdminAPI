package net.fashiongo.webadmin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.fashiongo.webadmin.model.primary.SecurityGroup;
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
		JsonResponse<List<SecurityGroup>> results = new JsonResponse<List<SecurityGroup>>(false, null, null);
		List<SecurityGroup> result  = securityGroupService.GetCommonSecurityGroup();
		
		results.setData(result);
		results.setSuccess(true);
	
		return results;
	}

	@RequestMapping(value = "getbidadpages", method = RequestMethod.POST)
	public void GetBidAdPages() {
		
	}
	
	@RequestMapping(value = "getbidadpagespots", method = RequestMethod.POST)
	public void GetBidAdPageSpots() {
		
	}
	
	@RequestMapping(value = "getbidadpagespotscombined", method = RequestMethod.POST)
	public void GetBidAdPageSpotsCombined() {
		
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
	public JsonResponse<Integer> GetMenuID(@RequestParam("PageName") String pageName) {
		JsonResponse<Integer> results = new JsonResponse<Integer>(false, null, null, null);
		Integer result = commonService.GetMenuID(pageName);
		results.setSuccess(true);
		results.setData(result);
		return results;
	}
	
	@RequestMapping(value = "getserverheartbeat", method = RequestMethod.POST)
	public void GetServerHeartBeat() {
		
	}
	
	@RequestMapping(value = "getcountrystates", method = RequestMethod.POST)
	public void GetCountryStates() {
		
	}
	
	@RequestMapping(value = "gettopcategories", method = RequestMethod.POST)
	public JsonResponse<List<TopCategories>> GetTopCategories() {
		JsonResponse<List<TopCategories>> results = new JsonResponse<List<TopCategories>>(false, null, null, null);
		List<TopCategories> result = commonService.GetTopCategories();
		results.setSuccess(true);
		results.setData(result);
		return results;
	}
}
