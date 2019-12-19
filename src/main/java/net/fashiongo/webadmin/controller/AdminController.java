package net.fashiongo.webadmin.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import net.fashiongo.webadmin.data.model.admin.GetUserLoginTrackingParameter;
import net.fashiongo.webadmin.data.model.admin.response.GetSecurityAccessCodesResponse;
import net.fashiongo.webadmin.data.model.admin.response.GetUserLoginTrackingResponse;
import net.fashiongo.webadmin.model.pojo.common.ResultCode;
import net.fashiongo.webadmin.model.primary.SecurityGroup;
import net.fashiongo.webadmin.service.AdminService;
import net.fashiongo.webadmin.service.SecurityGroupService;
import net.fashiongo.webadmin.service.renewal.RenewalAdminService;
import net.fashiongo.webadmin.service.renewal.RenewalSecurityGroupService;
import net.fashiongo.webadmin.utility.DateUtils;
import net.fashiongo.webadmin.utility.JsonResponse;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.fashiongo.webadmin.model.pojo.admin.parameter.DelSecurityUserParameter;
import net.fashiongo.webadmin.model.pojo.admin.parameter.GetSecurityAccessCodesParameters;
import net.fashiongo.webadmin.model.pojo.admin.parameter.GetSecurityGroupPermissionsParameter;
import net.fashiongo.webadmin.model.pojo.admin.parameter.GetSecurityLogsParameter;
import net.fashiongo.webadmin.model.pojo.admin.parameter.GetSecurityMenus2Parameter;
import net.fashiongo.webadmin.model.pojo.admin.parameter.GetSecurityResourcesParameter;
import net.fashiongo.webadmin.model.pojo.admin.parameter.GetSecurityUserGroupParameter;
import net.fashiongo.webadmin.model.pojo.admin.parameter.GetSecurityUserParameter;
import net.fashiongo.webadmin.model.pojo.admin.parameter.GetSecurityUserPermissionsParameter;
import net.fashiongo.webadmin.model.pojo.admin.parameter.GetUserMappingVendorParameter;
import net.fashiongo.webadmin.model.pojo.admin.parameter.SetActiveGroupParameter;
import net.fashiongo.webadmin.model.pojo.admin.parameter.SetActiveSecurityMenusParameter;
import net.fashiongo.webadmin.model.pojo.admin.parameter.SetDeleteSecurityAccessCodesParameter;
import net.fashiongo.webadmin.model.pojo.admin.parameter.SetDeleteSecurityAccessIpsParameter;
import net.fashiongo.webadmin.model.pojo.admin.parameter.SetDeleteSecurityMenusParameter;
import net.fashiongo.webadmin.model.pojo.admin.parameter.SetResourceParameter;
import net.fashiongo.webadmin.model.pojo.admin.parameter.SetSecurityAccessCodeParameters;
import net.fashiongo.webadmin.model.pojo.admin.parameter.SetSecurityAccessIpParameter;
import net.fashiongo.webadmin.model.pojo.admin.parameter.SetSecurityMenuParameter;
import net.fashiongo.webadmin.model.pojo.admin.parameter.SetSecurityResourceParameter;
import net.fashiongo.webadmin.model.pojo.admin.parameter.SetSecurityUserParameter;
import net.fashiongo.webadmin.model.pojo.admin.parameter.SetUserMappingVendorParameter;
import net.fashiongo.webadmin.model.pojo.admin.parameter.SetdeletesecuritygroupsParameter;
import net.fashiongo.webadmin.model.pojo.admin.parameter.SetsecuritygroupParameter;
import net.fashiongo.webadmin.model.pojo.admin.response.GetSecurityAccessIpsResponse;
import net.fashiongo.webadmin.model.pojo.admin.response.GetSecurityGroupPermissionsResponse;
import net.fashiongo.webadmin.model.pojo.admin.response.GetSecurityMenus2Response;
import net.fashiongo.webadmin.model.pojo.admin.response.GetSecurityParentMenusResponse;
import net.fashiongo.webadmin.model.pojo.admin.response.GetSecurityResourcesResponse;
import net.fashiongo.webadmin.model.pojo.admin.response.GetSecurityUserGroupAccesstimeResponse;
import net.fashiongo.webadmin.model.pojo.admin.response.GetSecurityUserResponse;
import net.fashiongo.webadmin.model.pojo.admin.response.GetUserMappingVendorResponse;
import net.fashiongo.webadmin.model.pojo.admin.response.SetCreateSecurityUserResponse;
import net.fashiongo.webadmin.model.pojo.admin.response.SetUserMappingVendorResponse;

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

	@Autowired
	RenewalSecurityGroupService renewalSecurityGroupService;

	@Autowired
	RenewalAdminService renewalAdminService;

	/**
	 * Get Security Access Code
	 * 
	 * @since 2018. 10. 01.
	 * @author Junghwan Lee
	 * @param parameters
	 * @return
	 */
	@RequestMapping(value = "getsecurityaccesscodes", method = RequestMethod.POST)
	public JsonResponse<GetSecurityAccessCodesResponse> getSecurityAccessCodes(@RequestBody GetSecurityAccessCodesParameters parameters) {
		JsonResponse<GetSecurityAccessCodesResponse> result = new JsonResponse<GetSecurityAccessCodesResponse>(false, null, null);
//		GetSecurityAccessCodesResponse _result = adminService.getSecurityAccessCodes(parameters);
		GetSecurityAccessCodesResponse securityAccessCodeList = renewalAdminService.getSecurityAccessCodes(parameters);

		result.setSuccess(true);
		result.setData(securityAccessCodeList);

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
	public JsonResponse<String> setSecurityAccessCode(@RequestBody SetSecurityAccessCodeParameters parameters) {
		JsonResponse<String> result = new JsonResponse<String>(false, null, -1, null);
		ResultCode _result = adminService.setSecurityAccessCode(parameters);
		
		result.setSuccess(_result.getSuccess());
		result.setCode(_result.getResultCode());
		result.setMessage(_result.getResultMsg());
		
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
	public JsonResponse<String> setDeleteSecurityAccessCodes(@RequestBody SetDeleteSecurityAccessCodesParameter parameters) {
		JsonResponse<String> result = new JsonResponse<String>(false, null, -1, null);
		
		ResultCode _result = adminService.setDeleteSecurityAccessCodes(parameters.getIdList());
		
		result.setSuccess(_result.getSuccess());
		result.setCode(_result.getResultCode());
		result.setMessage(_result.getResultMsg());
		
		return result;
	}
	
	/**
	 * 
	 * Get Security Log
	 * 
	 * @since 2018. 10. 02.
	 * @author Nayeon Kim
	 * @param GetSecurityLogsParameter
	 * @return GetSecurityLogsResponse
	 */
	@RequestMapping(value = "getsecuritylogs", method = RequestMethod.POST)
	public JsonResponse<net.fashiongo.webadmin.data.model.admin.response.GetSecurityLogsResponse> getSecuritylogs(@RequestBody GetSecurityLogsParameter parameters) {
		net.fashiongo.webadmin.data.model.admin.response.GetSecurityLogsResponse result = renewalAdminService.getSecurityLoginLogs(parameters);
		return new JsonResponse<net.fashiongo.webadmin.data.model.admin.response.GetSecurityLogsResponse>(true, null, 0, result);
	}
	
	/**
	 * 
	 * Get Security Group
	 * 
	 * @since 2018. 10. 8.
	 * @author Incheol Jung
	 * @return
	 */
	@RequestMapping(value="getsecuritygroups", method=RequestMethod.POST)
	public JsonResponse<List<SecurityGroup>> GetSecurityGroups() {
		JsonResponse<List<SecurityGroup>> results = new JsonResponse<List<SecurityGroup>>(false, null, 0, null);
		List<SecurityGroup> result  = securityGroupService.getSecurityGroup();
		
		results.setData(result);
		results.setSuccess(true);
	
		return results;
	}
	
	/**
	 * 
	 * Get Security Group Permissions
	 * 
	 * @since 2018. 10. 8.
	 * @author Incheol Jung
	 * @param parameters
	 * @return
	 */
	@RequestMapping(value="getsecuritygrouppermissions", method=RequestMethod.POST)
	public JsonResponse<net.fashiongo.webadmin.data.model.admin.response.GetSecurityGroupPermissionsResponse> GetSecurityGroupPermissions(@RequestBody GetSecurityGroupPermissionsParameter parameters) {
		JsonResponse<net.fashiongo.webadmin.data.model.admin.response.GetSecurityGroupPermissionsResponse> results = new JsonResponse<net.fashiongo.webadmin.data.model.admin.response.GetSecurityGroupPermissionsResponse>(false, null, 0, null);
		net.fashiongo.webadmin.data.model.admin.response.GetSecurityGroupPermissionsResponse getSecurityGroupPermissionsResponse = renewalSecurityGroupService.GetSecurityGroupPermissions(parameters);

		results.setData(getSecurityGroupPermissionsResponse);
		results.setSuccess(true);
		
		return results;
	}
	
	/**
	 * 
	 * Description Example
	 * 
	 * @since 2018. 10. 8.
	 * @author Incheol Jung
	 * @param parameters
	 * @return
	 */
	@RequestMapping(value="setsecuritygroup", method=RequestMethod.POST)
	public JsonResponse<List<ResultCode>> setSecurityGroup(@RequestBody SetsecuritygroupParameter parameters) {
		JsonResponse<List<ResultCode>> results = new JsonResponse<List<ResultCode>>(true, null, 0, null);
		List<ResultCode> data = new ArrayList<ResultCode>();
		
		data.add(securityGroupService.setSecurityGroup(parameters.getGid(), parameters.getGroupname(), parameters.getDescription(), parameters.getGroupactive()));
		
		if(CollectionUtils.isEmpty(parameters.getGpdata())) {
			parameters.setGid(0);
		}
		
		if(parameters.getApplyall()) {
			data.add(securityGroupService.setSecurityGroupPermissionsAllUsers(parameters.getGid(), parameters.getGpdata()));
		} else{
			data.add(securityGroupService.setSecurityGroupPermissions(parameters.getGid(), parameters.getGpdata()));
		}
		
		results.setData(data);
		
		return results;
	}
	
	/**
	 * 
	 * Set deletesecuritygroups
	 * 
	 * @since 2018. 10. 8.
	 * @author Incheol Jung
	 * @param parameters
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="setdeletesecuritygroups", method=RequestMethod.POST)
	public JsonResponse<Integer> setdeletesecuritygroups(@RequestBody SetdeletesecuritygroupsParameter parameters) throws IOException {
		JsonResponse<Integer> results = new JsonResponse<Integer>(true, null, 0, null);
		
		ResultCode result = securityGroupService.setdeletesecuritygroups(parameters.getData());
		results.setCode(result.getResultCode());
		results.setMessage(result.getResultMsg());
		
		return results;
	}
	
	/**
	 * 
	 * Description Example
	 * 
	 * @since 2018. 10. 8.
	 * @author Incheol Jung
	 * @param parameters
	 * @return
	 */
	@RequestMapping(value="setactivegroup", method=RequestMethod.POST)
	public JsonResponse<Integer> setActiveGroup(@RequestBody SetActiveGroupParameter parameters) {
		JsonResponse<Integer> results = new JsonResponse<Integer>(true, null, 0);
		
		ResultCode result = securityGroupService.setActiveGroup(parameters.getGroupID(), parameters.getActive());
		results.setCode(result.getResultCode());
		results.setMessage(result.getResultMsg());
		
		return results;
	}
	
	/**
	 * 
	 * GetSecurityUsers
	 * 
	 * @since 2018. 10. 8.
	 * @author Reo
	 * @param parameters
	 * @return
	 */
	@RequestMapping(value="getsecurityusers", method=RequestMethod.POST)
	public JsonResponse<GetSecurityUserResponse> getSecurityUsers(@RequestBody GetSecurityUserParameter parameters) {
		JsonResponse<GetSecurityUserResponse> results = new JsonResponse<GetSecurityUserResponse>(false, null, 0, null);
		GetSecurityUserResponse result = securityGroupService.getSecurityUsers(parameters);
		results.setData(result);
		results.setSuccess(true);
		
		return results;
	}
	
	/**
	 * 
	 * Get SecurityUserPermissions
	 * 
	 * @since 2018. 10. 8.
	 * @author Reo
	 * @param parameters
	 * @return
	 */
	@RequestMapping(value="getsecurityuserpermissions", method=RequestMethod.POST)
	public JsonResponse<net.fashiongo.webadmin.data.model.admin.response.GetSecurityGroupPermissionsResponse> getSecurityUserPermissions(@RequestBody GetSecurityUserPermissionsParameter parameters) {
		JsonResponse<net.fashiongo.webadmin.data.model.admin.response.GetSecurityGroupPermissionsResponse> results = new JsonResponse<net.fashiongo.webadmin.data.model.admin.response.GetSecurityGroupPermissionsResponse>(false, null, 0, null);

		net.fashiongo.webadmin.data.model.admin.response.GetSecurityGroupPermissionsResponse securityUserPermissions = renewalSecurityGroupService.getSecurityUserPermissions(parameters);

		results.setData(securityUserPermissions);
		results.setSuccess(true);
		
		return results;
	}
	
	/**
	 * 
	 * Get Security Resources
	 * @since 2018. 10. 8.
	 * @author Dahye Jeong
	 * @param GetSecurityResourcesParameter
	 * @return GetSecurityResourcesResponse
	 */
	@RequestMapping(value="getsecurityresources", method=RequestMethod.POST)
	public JsonResponse<net.fashiongo.webadmin.data.model.admin.response.GetSecurityResourcesResponse> getSecurityResources (@RequestBody GetSecurityResourcesParameter parameters) {
		net.fashiongo.webadmin.data.model.admin.response.GetSecurityResourcesResponse securityResources = renewalAdminService.getSecurityResources(parameters);

		return new JsonResponse<net.fashiongo.webadmin.data.model.admin.response.GetSecurityResourcesResponse>(true, null, 0, securityResources);
	}
	
	/**
	 * 
	 * Get Security Access Ips
	 * @since 2018. 10. 10.
	 * @author Dahye Jeong
	 * @param null
	 * @return GetSecurityAccessIpsResponse
	 */
	@RequestMapping(value="getsecurityaccessips", method=RequestMethod.POST)
	public JsonResponse<net.fashiongo.webadmin.data.model.admin.response.GetSecurityAccessIpsResponse> getSecurityAccessIps () {
		net.fashiongo.webadmin.data.model.admin.response.GetSecurityAccessIpsResponse result = renewalAdminService.getSecurityAccessIps();
		return new JsonResponse<net.fashiongo.webadmin.data.model.admin.response.GetSecurityAccessIpsResponse>(true, null, 0, result);
	}
	
	/**
	 * 
	 * Set Security Access Ips
	 * @since 2018. 10. 10.
	 * @author Dahye Jeong
	 * @param SetSecurityAccessIpParameter
	 * @return ResultCode
	 */
	@RequestMapping(value="setsecurityaccessip", method=RequestMethod.POST)
	public JsonResponse<String> setSecurityAccessIp (@RequestBody SetSecurityAccessIpParameter parameters) throws Exception {
		ResultCode result = adminService.setSecurityAccessIp(parameters);
		return new JsonResponse<String>(result.getSuccess(), result.getResultMsg(), result.getResultCode(), null);
	}
	
	/**
	 * 
	 * Set Delete Security Access Ips
	 * @since 2018. 10. 10.
	 * @author Dahye Jeong
	 * @param idList
	 * @return ResultCode
	 */
	@RequestMapping(value = "setdeletesecurityaccessips", method = RequestMethod.POST)
	public JsonResponse<String> setDeleteSecurityAccessIps(@RequestBody SetDeleteSecurityAccessIpsParameter data) throws Exception {
		ResultCode result = adminService.setDeleteSecurityAccessIps(data.getIdList());
		return new JsonResponse<String>(result.getSuccess(), result.getResultMsg(), result.getResultCode(), null);
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 10. 10.
	 * @author Reo
	 * @param parameters
	 * @return
	 */
	@RequestMapping(value = "setsecurityusersactive", method=RequestMethod.POST)
	public JsonResponse<String> setSecurityUserActive(@RequestBody GetSecurityUserParameter parameters) {
		JsonResponse<String> results = new JsonResponse<String>(true, null, null);
		ResultCode result = securityGroupService.setSecurityUserActive(parameters.getUserID(), parameters.getActive());
		results.setCode(result.getResultCode());
		results.setMessage(result.getResultMsg());
		return results;
	}
	/**
	 * Set Resource
	 * @since 2018. 10. 12.
	 * @author Dahye Jeong
	 * @param SetResourceParameter
	 * @return ResultCode
	 */
	@RequestMapping(value = "setactiveresource", method = RequestMethod.POST)
	public JsonResponse<String> setResource(@RequestBody SetResourceParameter parameters) {
		ResultCode result = adminService.setResource(parameters.getResourceId(), parameters.getActive());
		return new JsonResponse<String>(result.getSuccess(), result.getResultMsg(), result.getResultCode(), null);
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 10. 10.
	 * @author Reo
	 * @param parameters
	 * @return
	 */
	@RequestMapping(value = "getusermappingvendor", method=RequestMethod.POST)
	public JsonResponse<net.fashiongo.webadmin.data.model.admin.response.GetUserMappingVendorResponse> getUserMappingVendor(@RequestBody GetUserMappingVendorParameter parameters) {
		JsonResponse<net.fashiongo.webadmin.data.model.admin.response.GetUserMappingVendorResponse> results = new JsonResponse<net.fashiongo.webadmin.data.model.admin.response.GetUserMappingVendorResponse>(false, null, 0, null);
		net.fashiongo.webadmin.data.model.admin.response.GetUserMappingVendorResponse userMappingVendor = renewalSecurityGroupService.getUserMappingVendor(parameters);

		results.setData(userMappingVendor);
		results.setSuccess(true);
		
		return results;
	}

	/**
	 * 
	 * Description Example
	 * @since 2018. 10. 10.
	 * @author Reo
	 * @param parameters
	 * @return
	 */
	@RequestMapping(value = "getusermappingvendorcount", method=RequestMethod.POST)
	public JsonResponse<Integer> getUserMappingVendorCount (@RequestBody GetUserMappingVendorParameter parameters) {
		JsonResponse<Integer> results = new JsonResponse<Integer>(false, null, 0, null);
		Integer result = securityGroupService.getUserMappingVendorCount(parameters);
		results.setData(result);
		results.setSuccess(true);
		
		return results;
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 10. 10.
	 * @author Reo
	 * @param parameters
	 * @return
	 */
	@RequestMapping(value = "setusermappingvendor", method=RequestMethod.POST)
	public SetUserMappingVendorResponse setUserMappingVendor(@RequestBody SetUserMappingVendorParameter parameters) {
		JsonResponse<SetUserMappingVendorResponse> results = new JsonResponse<SetUserMappingVendorResponse>(false, null, 0, null);
		
		SetUserMappingVendorResponse result = securityGroupService.setUserMappingVendor(parameters);
		
		results.setData(result);
		results.setSuccess(true);
		return result;
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 10. 10.
	 * @author Reo
	 * @param parameters
	 * @return
	 */
	@RequestMapping(value="getsecurityusergroupaccesstimes", method=RequestMethod.POST)
	public JsonResponse<net.fashiongo.webadmin.data.model.admin.response.GetSecurityUserGroupAccesstimeResponse> getSecurityUserGroupAccessTimes(@RequestBody GetSecurityUserGroupParameter parameters) {
		JsonResponse<net.fashiongo.webadmin.data.model.admin.response.GetSecurityUserGroupAccesstimeResponse> results = new JsonResponse<net.fashiongo.webadmin.data.model.admin.response.GetSecurityUserGroupAccesstimeResponse>(false, null, 0, null);

		net.fashiongo.webadmin.data.model.admin.response.GetSecurityUserGroupAccesstimeResponse result = renewalSecurityGroupService.getSecurityUserGroupAccessTimes(parameters);

		results.setData(result);
		results.setSuccess(true);

		return results;
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 10. 11.
	 * @author Reo
	 * @param parameters
	 * @return
	 */
	@RequestMapping(value="setdeletesecurityusers", method=RequestMethod.POST)
	public JsonResponse<String> setDelSecurityUsers(@RequestBody DelSecurityUserParameter parameters) {
		JsonResponse<String> results = new JsonResponse<String>(true, null, 0, null);
		ResultCode result = new ResultCode(false, 0, null);
		
		result = securityGroupService.setDelSecurityUsers(parameters);
		results.setCode(result.getResultCode());
		results.setSuccess(result.getSuccess());
		results.setMessage(result.getResultMsg());
		
		return results;
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 10. 19.
	 * @author Reo
	 * @param jsonParameters
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 * @throws java.text.ParseException 
	 */
	@RequestMapping(value="setsecurityuser", method=RequestMethod.POST)
	public JsonResponse<SetCreateSecurityUserResponse> setCreateSecurityUsers(@RequestBody SetSecurityUserParameter jsonParameters) throws ParseException, IOException, java.text.ParseException {
		JsonResponse<SetCreateSecurityUserResponse> results = new JsonResponse<SetCreateSecurityUserResponse>(true, null, 0, null);
		SetCreateSecurityUserResponse result = securityGroupService.setCreateSecurityUser(jsonParameters);
		results.setData(result);
		
		return results;
	}
	
	/**
	 * Set Security Resource
	 * @since 2018. 10. 12.
	 * @author Dahye Jeong
	 * @param SetSecurityResourceParameter
	 * @return ResultCode
	 */
	@RequestMapping(value = "setsecurityresource", method = RequestMethod.POST)
	public JsonResponse<String> setSecurityResource(@RequestBody SetSecurityResourceParameter parameters) {
		ResultCode result = adminService.setSecurityResource(parameters);
		return new JsonResponse<String>(result.getSuccess(), result.getResultMsg(), result.getResultCode(), null);
	}
	
	
	/**
	 * 
	 * Set Delete Security Resources
	 * @since 2018. 10. 12.
	 * @author Dahye Jeong
	 * @param ID List
	 * @return ResultCode
	 */
	@RequestMapping(value = "setdeletesecurityresources", method = RequestMethod.POST)
	public JsonResponse<String> setDeleteSecurityResources(@RequestBody SetDeleteSecurityAccessIpsParameter data) throws Exception {
		ResultCode result = adminService.setDeleteSecurityResources(data.getIdList());
		return new JsonResponse<String>(result.getSuccess(), result.getResultMsg(), result.getResultCode(), null);
	}
	
	/**
	 * 
	 * Get Security Parent Menus
	 * 
	 * @since 2018. 10. 16.
	 * @author Jiwon Kim
	 * @return GetSecurityParentMenus
	 */
	@RequestMapping(value="getsecurityparentmenus", method=RequestMethod.POST)
	public JsonResponse<GetSecurityParentMenusResponse> GetSecurityParentMenus () {
		JsonResponse<GetSecurityParentMenusResponse> results = new JsonResponse<GetSecurityParentMenusResponse>(true, null, 0, null);
		GetSecurityParentMenusResponse result = adminService.GetSecurityParentMenus();
		results.setData(result);
		return results;
	}
	
	/**
	 * 
	 * Get Security Menus2
	 * 
	 * @since 2018. 10. 16.
	 * @author Jiwon Kim
	 * @return GetSecurityMenus2
	 */
	@RequestMapping(value="getsecuritymenus2", method=RequestMethod.POST)
	public JsonResponse<net.fashiongo.webadmin.data.model.admin.response.GetSecurityMenus2Response> GetSecurityMenus2 (@RequestBody GetSecurityMenus2Parameter parameters) {
		JsonResponse<net.fashiongo.webadmin.data.model.admin.response.GetSecurityMenus2Response> results = new JsonResponse<net.fashiongo.webadmin.data.model.admin.response.GetSecurityMenus2Response>(true, null, 0, null);
		net.fashiongo.webadmin.data.model.admin.response.GetSecurityMenus2Response getSecurityMenus2Response = renewalAdminService.GetSecurityMenus2(parameters);
		results.setData(getSecurityMenus2Response);
		return results;
	}
	
	
	/**
	 * 
	 * Set Security Menu
	 * 
	 * @since 2018. 10. 16.
	 * @author Jiwon Kim
	 * @return SetSecurityMenu
	 */
	@RequestMapping(value="setsecuritymenu", method=RequestMethod.POST)
	public JsonResponse<Integer> SetSecurityMenu(@RequestBody SetSecurityMenuParameter parameters) {
		JsonResponse<Integer> results = new JsonResponse<Integer>(true, null, 0, null);
		ResultCode result = adminService.setSecurityMenu(parameters);
		results.setSuccess(result.getSuccess());
		results.setCode(result.getResultCode());
		results.setMessage(result.getResultMsg());
		return results;
	}
	
	/**
	 * 
	 * Set Delete Security Menus
	 * 
	 * @since 2018. 10. 16.
	 * @author Jiwon Kim
	 * @return SetDeleteSecurityMenus
	 */
	@RequestMapping(value="setdeletesecuritymenus", method=RequestMethod.POST)
	public JsonResponse<Integer> SetDeleteSecurityMenus(@RequestBody SetDeleteSecurityMenusParameter parameters) {
		JsonResponse<Integer> results = new JsonResponse<Integer>(true, null, 0, null);
		ResultCode result = adminService.SetDeleteSecurityMenus(parameters);
		results.setSuccess(result.getSuccess());
		results.setCode(result.getResultCode());
		results.setMessage(result.getResultMsg());
		return results;
	}
	
	/**
	 * 
	 * Set Active Security Menus
	 * 
	 * @since 2018. 10. 16.
	 * @author Jiwon Kim
	 * @return SetActiveSecurityMenus
	 */
	@RequestMapping(value="setactivesecuritymenus", method=RequestMethod.POST)
	public JsonResponse<Integer> SetActiveSecurityMenus(@RequestBody SetActiveSecurityMenusParameter parameters) {
		JsonResponse<Integer> results = new JsonResponse<Integer>(true, null, 0, null);
		ResultCode result = adminService.SetActiveSecurityMenus(parameters);
		results.setSuccess(result.getSuccess());
		results.setCode(result.getResultCode());
		results.setMessage(result.getResultMsg());
		return results;
	}

	@RequestMapping(value="getuserlogintracking", method=RequestMethod.POST)
	public JsonResponse<GetUserLoginTrackingResponse> getUserLoginTracking(@RequestBody GetUserLoginTrackingParameter parameters) {
		JsonResponse<GetUserLoginTrackingResponse> results = new JsonResponse<GetUserLoginTrackingResponse>(true, null, 0, null);


		String userType = parameters.getUsertype();
		String userName = parameters.getUsername();
		String companyName = parameters.getCompanyname();
		String ip = parameters.getIp();
		LocalDateTime sDate = DateUtils.convertToLocalDateTime(parameters.getSdate(),"F");
		LocalDateTime eDate = DateUtils.convertToLocalDateTime(parameters.getEdate(),"T");
		int pageNum = Optional.ofNullable(parameters.getPagenum()).orElse(0);
		int pageSize = Optional.ofNullable(parameters.getPagesize()).orElse(0);
		String sortField = parameters.getSortfield();
		String sortDir = parameters.getSortdir();

		GetUserLoginTrackingResponse result = renewalAdminService.getUserLoginTracking(pageNum,
				pageSize,
				sortField,
				sortDir,
				userType,
				userName,
				companyName,
				ip,
				sDate,
				eDate);
		results.setSuccess(true);
		results.setCode(0);
		results.setMessage("");
		results.setData(result);
		return results;
	}
}
