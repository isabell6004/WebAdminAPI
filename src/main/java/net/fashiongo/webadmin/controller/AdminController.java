package net.fashiongo.webadmin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.fashiongo.webadmin.model.pojo.parameter.GetSecurityAccessCodesParameters;
import net.fashiongo.webadmin.model.pojo.parameter.GetSecurityGroupPermissionsParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetSecurityLogsParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetSecurityUserParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetSecurityUserPermissionsParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetSecurityAccessCodeParameters;
import net.fashiongo.webadmin.model.pojo.response.GetSecurityAccessCodesResponse;
import net.fashiongo.webadmin.model.pojo.response.GetSecurityGroupPermissionsResponse;
import net.fashiongo.webadmin.model.pojo.response.GetSecurityLogsResponse;
import net.fashiongo.webadmin.model.pojo.response.GetSecurityUserResponse;
import net.fashiongo.webadmin.model.pojo.response.SetResultResponse;
import net.fashiongo.webadmin.model.primary.SecurityGroup;
import net.fashiongo.webadmin.service.AdminService;
import net.fashiongo.webadmin.service.SecurityGroupService;
import net.fashiongo.webadmin.utility.JsonResponse;

/**
 * 
 * @author JungHwan
 */
@RestController
@RequestMapping(value = "/admin", produces = "application/json")
public class AdminController {
	@Autowired
	AdminService adminService;
	
	@Autowired
	SecurityGroupService securityGroupService;

	/**
	 * Get Security Access Code
	 * 
	 * @since 2018. 10. 01.
	 * @author Junghwan Lee
	 * @param parameters
	 * @return
	 */
	@RequestMapping(value = "getsecurityaccesscodes", method = RequestMethod.POST)
	public GetSecurityAccessCodesResponse GetSecurityAccessCodes(@RequestBody GetSecurityAccessCodesParameters parameters) {
		GetSecurityAccessCodesResponse result = adminService.GetSecurityAccessCodes(parameters);
		
		return result;
	}

	/**
	 * Set Security Access Code
	 * 
	 * @since 2018. 10. 02.
	 * @author Junghwan Lee
	 * @param parameters
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "setsecurityaccesscode", method = RequestMethod.POST)
	public SetResultResponse SetSecurityAccessCode(@RequestBody SetSecurityAccessCodeParameters parameters) throws Exception {
		SetResultResponse result = adminService.SetSecurityAccessCode(parameters);
		
		return result;
	}
	
	/**
	 * Delete Security Access Code
	 * 
	 * @since 2018. 10. 02.
	 * @author Junghwan Lee
	 * @param idList
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "setdeletesecurityaccesscodes", method = RequestMethod.POST)
	public SetResultResponse SetDeleteSecurityAccessCodes(@RequestBody List<Integer> idList) throws Exception {
		SetResultResponse result = adminService.SetDeleteSecurityAccessCodes(idList);
		
		return result;
	}
	
	/**
	 * 
	 * Get Security Log
	 * 
	 * @since 2018. 10. 02.
	 * @author nayeon
	 * @param GetSecurityLogsParameter
	 * @return GetSecurityLogsResponse
	 */
	@RequestMapping(value = "getsecuritylogs", method = RequestMethod.POST)
	public GetSecurityLogsResponse getSecuritylogs(@RequestBody GetSecurityLogsParameter parameters) throws Exception {		
		GetSecurityLogsResponse result = adminService.getSecuritylogs(parameters);

		return result;
	}
	
	@RequestMapping(value="getsecuritygroups", method=RequestMethod.POST)
	public JsonResponse<List<SecurityGroup>> GetSecurityGroups() {
		JsonResponse<List<SecurityGroup>> results = new JsonResponse<List<SecurityGroup>>(false, null, null);
		List<SecurityGroup> result  = securityGroupService.GetSecurityGroup();
		
		results.setData(result);
		results.setSuccess(true);
	
		return results;
	}
	
	@RequestMapping(value="getsecuritygrouppermissions", method=RequestMethod.POST)
	public GetSecurityGroupPermissionsResponse GetSecurityGroupPermissions(@RequestBody GetSecurityGroupPermissionsParameter parameters) {
		JsonResponse<GetSecurityGroupPermissionsResponse> results = new JsonResponse<GetSecurityGroupPermissionsResponse>(false, "", null);
		GetSecurityGroupPermissionsResponse result = securityGroupService.GetSecurityGroupPermissions(parameters);
		
		results.setData(result);
		results.setSuccess(true);
		
		return results.getData();
	}
	
	@RequestMapping(value="getsecurityusers", method=RequestMethod.POST)
	public GetSecurityUserResponse GetSecurityUsers(@RequestBody GetSecurityUserParameter parameters) {
		JsonResponse<GetSecurityUserResponse> results = new JsonResponse<GetSecurityUserResponse>(false, "", null);
		GetSecurityUserResponse result = securityGroupService.GetSecurityUsers(parameters);
		results.setData(result);
		results.setSuccess(true);
		
		return results.getData();
	}
	
	@RequestMapping(value="getsecurityuserpermissions", method=RequestMethod.POST)
	public GetSecurityGroupPermissionsResponse GetSecurityUserPermissions(@RequestBody GetSecurityUserPermissionsParameter parameters) {
		JsonResponse<GetSecurityGroupPermissionsResponse> results = new JsonResponse<GetSecurityGroupPermissionsResponse>(false, "", null);
		GetSecurityGroupPermissionsResponse result = securityGroupService.GetSecurityUserPermissions(parameters);
		
		results.setData(result);
		results.setSuccess(true);
		
		return results.getData();
	}
}
