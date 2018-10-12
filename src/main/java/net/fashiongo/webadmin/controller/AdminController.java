package net.fashiongo.webadmin.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.fashiongo.webadmin.model.pojo.ResultCode;
import net.fashiongo.webadmin.model.pojo.parameter.GetSecurityAccessCodesParameters;
import net.fashiongo.webadmin.model.pojo.parameter.GetSecurityGroupPermissionsParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetSecurityLogsParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetSecurityResourcesParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetSecurityUserParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetSecurityUserPermissionsParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetActiveGroupParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetResourceParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetSecurityAccessCodeParameters;
import net.fashiongo.webadmin.model.pojo.parameter.SetSecurityAccessIpParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetSecurityResourceParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetdeletesecuritygroupsParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetsecuritygroupParameter;
import net.fashiongo.webadmin.model.pojo.response.GetSecurityAccessCodesResponse;
import net.fashiongo.webadmin.model.pojo.response.GetSecurityAccessIpsResponse;
import net.fashiongo.webadmin.model.pojo.response.GetSecurityGroupPermissionsResponse;
import net.fashiongo.webadmin.model.pojo.response.GetSecurityLogsResponse;
import net.fashiongo.webadmin.model.pojo.response.GetSecurityResourcesResponse;
import net.fashiongo.webadmin.model.pojo.response.GetSecurityUserResponse;
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
	public JsonResponse<GetSecurityAccessCodesResponse> GetSecurityAccessCodes(@RequestBody GetSecurityAccessCodesParameters parameters) {
		JsonResponse<GetSecurityAccessCodesResponse> result = new JsonResponse<GetSecurityAccessCodesResponse>(false, null, null);
		GetSecurityAccessCodesResponse _result = adminService.GetSecurityAccessCodes(parameters);

		result.setSuccess(true);
		result.setData(_result);

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
	public JsonResponse<String> SetSecurityAccessCode(@RequestBody SetSecurityAccessCodeParameters parameters) throws Exception {
		JsonResponse<String> result = new JsonResponse<String>(false, null, -1, null);
		ResultCode _result = adminService.SetSecurityAccessCode(parameters);
		
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
	public JsonResponse<String> SetDeleteSecurityAccessCodes(@RequestBody List<Integer> idList) throws Exception {
		JsonResponse<String> result = new JsonResponse<String>(false, null, -1, null);
		ResultCode _result = adminService.SetDeleteSecurityAccessCodes(idList);
		
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
	public JsonResponse<GetSecurityLogsResponse> getSecuritylogs(@RequestBody GetSecurityLogsParameter parameters) {		
		JsonResponse<GetSecurityLogsResponse> results = new JsonResponse<GetSecurityLogsResponse>(false, null, 0, null);
		GetSecurityLogsResponse result = adminService.getSecuritylogs(parameters);
		
		results.setData(result);
		results.setSuccess(true);
		
		return results;
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
		List<SecurityGroup> result  = securityGroupService.GetSecurityGroup();
		
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
	public JsonResponse<GetSecurityGroupPermissionsResponse> GetSecurityGroupPermissions(@RequestBody GetSecurityGroupPermissionsParameter parameters) {
		JsonResponse<GetSecurityGroupPermissionsResponse> results = new JsonResponse<GetSecurityGroupPermissionsResponse>(false, null, 0, null);
		GetSecurityGroupPermissionsResponse result = securityGroupService.GetSecurityGroupPermissions(parameters);
		
		results.setData(result);
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
	@RequestMapping(value="setSecurityGroup", method=RequestMethod.POST)
	public JsonResponse<List<ResultCode>> SetSecurityGroup(@RequestBody SetsecuritygroupParameter parameters) {
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
	 */
	@RequestMapping(value="setdeletesecuritygroups", method=RequestMethod.POST)
	public JsonResponse<Integer> setdeletesecuritygroups(@RequestBody SetdeletesecuritygroupsParameter parameters) {
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
	public GetSecurityUserResponse GetSecurityUsers(@RequestBody GetSecurityUserParameter parameters) {
		JsonResponse<GetSecurityUserResponse> results = new JsonResponse<GetSecurityUserResponse>(false, null, 0, null);
		GetSecurityUserResponse result = securityGroupService.GetSecurityUsers(parameters);
		results.setData(result);
		results.setSuccess(true);
		
		return results.getData();
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
	public GetSecurityGroupPermissionsResponse GetSecurityUserPermissions(@RequestBody GetSecurityUserPermissionsParameter parameters) {
		JsonResponse<GetSecurityGroupPermissionsResponse> results = new JsonResponse<GetSecurityGroupPermissionsResponse>(false, null, 0, null);
		GetSecurityGroupPermissionsResponse result = securityGroupService.GetSecurityUserPermissions(parameters);
		
		results.setData(result);
		results.setSuccess(true);
		
		return results.getData();
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
	public JsonResponse<GetSecurityResourcesResponse> GetSecurityResources (@RequestBody GetSecurityResourcesParameter parameters) {
		JsonResponse<GetSecurityResourcesResponse> results = new JsonResponse<GetSecurityResourcesResponse>(true, null, 0, null);
		GetSecurityResourcesResponse result = adminService.GetSecurityResources(parameters);
		results.setData(result);
		return results;
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
	public JsonResponse<GetSecurityAccessIpsResponse> GetSecurityAccessIps () {
		JsonResponse<GetSecurityAccessIpsResponse> results = new JsonResponse<GetSecurityAccessIpsResponse>(true, null, 0, null);
		GetSecurityAccessIpsResponse result = adminService.GetSecurityAccessIps();
		results.setData(result);
		return results;
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
	public JsonResponse<ResultCode> SetSecurityAccessIp (@RequestBody SetSecurityAccessIpParameter parameters) throws Exception {
		JsonResponse<ResultCode> results = new JsonResponse<ResultCode>(true, null, 0, null);
		ResultCode result = adminService.SetSecurityAccessIp(parameters);
		results.setData(result);
		return results;
	}
	
	/**
	 * 
	 * Set Delete Security Access Ips
	 * @since 2018. 10. 10.
	 * @author Dahye Jeong
	 * @param SetSecurityAccessIpParameter
	 * @return ResultCode
	 */
	@RequestMapping(value = "setdeletesecurityaccessips", method = RequestMethod.POST)
	public JsonResponse<String> SetDeleteSecurityAccessIps(@RequestBody List<Integer> idList) throws Exception {
		JsonResponse<String> results = new JsonResponse<String>(true, null, 0, null);
		ResultCode result = adminService.SetDeleteSecurityAccessIps(idList);
		
		results.setCode(result.getResultCode());
		results.setMessage(result.getResultMsg());
		
		return results;
	}
	
	/**
	 * 
	 * Set Resource
	 * @since 2018. 10. 12.
	 * @author Dahye Jeong
	 * @param SetResourceParameter
	 * @return ResultCode
	 */
	@RequestMapping(value = "setactiveresource", method = RequestMethod.POST)
	public JsonResponse<Integer> SetResource(@RequestBody SetResourceParameter parameters) {
		JsonResponse<Integer> results = new JsonResponse<Integer>(true, null, 0, null);
		ResultCode result = adminService.SetResource(parameters.getResourceId(), parameters.getActive());
		results.setCode(result.getResultCode());
		results.setMessage(result.getResultMsg());
		return results;
	}
	
	/**
	 * 
	 * Set Security Resource
	 * @since 2018. 10. 12.
	 * @author Dahye Jeong
	 * @param SetSecurityResourceParameter
	 * @return ResultCode
	 */
	@RequestMapping(value = "setsecurityresource", method = RequestMethod.POST)
	public JsonResponse<Integer> SetSecurityResource(@RequestBody SetSecurityResourceParameter parameters) {
		JsonResponse<Integer> results = new JsonResponse<Integer>(true, null, 0, null);
		ResultCode result = adminService.SetSecurityResource(parameters);
		results.setCode(result.getResultCode());
		results.setMessage(result.getResultMsg());
		return results;
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
	public JsonResponse<Integer> SetDeleteSecurityResources(@RequestBody List<Integer> idList) throws Exception {
		JsonResponse<Integer> results = new JsonResponse<Integer>(true, null, 0, null);
		ResultCode result = adminService.SetDeleteSecurityResources(idList);
		results.setCode(result.getResultCode());
		results.setMessage(result.getResultMsg());
		return results;
	}
}
