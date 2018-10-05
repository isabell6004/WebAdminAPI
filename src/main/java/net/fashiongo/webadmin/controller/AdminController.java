package net.fashiongo.webadmin.controller;
/**
 * Reo
 */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.fashiongo.common.JsonResponse;
import net.fashiongo.webadmin.model.pojo.SecurityGroupPermissions;
import net.fashiongo.webadmin.model.pojo.SecurityUsers;
import net.fashiongo.webadmin.model.pojo.parameter.GetMessageParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetSecurityGroupPermissionsParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetSecurityUserGroupParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetSecurityUserParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetSecurityUserPermissionsParameter;
import net.fashiongo.webadmin.model.pojo.response.GetMessageResponse;
import net.fashiongo.webadmin.model.pojo.response.GetSecurityGroupPermissionsResponse;
import net.fashiongo.webadmin.model.pojo.response.GetSecurityGroupResponse;
import net.fashiongo.webadmin.model.pojo.response.GetSecurityUserResponse;
import net.fashiongo.webadmin.model.primary.SecurityGroup;
import net.fashiongo.webadmin.service.SecurityGroupService;

@RestController
@RequestMapping(value="/admin", produces = "application/json")
public class AdminController {
	

	@Autowired
	SecurityGroupService securityGroupService;
	
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
