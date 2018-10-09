package net.fashiongo.webadmin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.fashiongo.webadmin.model.primary.SecurityGroup;
import net.fashiongo.webadmin.service.SecurityGroupService;
import net.fashiongo.webadmin.utility.JsonResponse;

@RestController
@RequestMapping(value = "/common", produces = "application/json")
public class CommonController {
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
}
