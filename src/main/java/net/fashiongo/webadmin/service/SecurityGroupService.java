package net.fashiongo.webadmin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fashiongo.webadmin.dao.primary.SecurityGroupRepository;
import net.fashiongo.webadmin.model.pojo.Message;
import net.fashiongo.webadmin.model.pojo.SecurityGroupPermissions;
import net.fashiongo.webadmin.model.pojo.SecurityUsers;
import net.fashiongo.webadmin.model.pojo.Total;
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
/**
 * 
 * @author Reo
 *
 */
@Service
public class SecurityGroupService extends ApiService {
	@Autowired
	private SecurityGroupRepository securityGroupRepository;

	/**
	 * 
	 * Description Example
	 * @since 2018. 10. 1.
	 * @author Reo
	 * @return
	 */
	public List<SecurityGroup> GetSecurityGroup() {
		List<SecurityGroup> securityGroupList = (List<SecurityGroup>) securityGroupRepository.findAll();
		
		return securityGroupList;
	}
	
	public GetSecurityGroupPermissionsResponse GetSecurityGroupPermissions(GetSecurityGroupPermissionsParameter parameters) {
		GetSecurityGroupPermissionsResponse result = new GetSecurityGroupPermissionsResponse();
		String spName = "up_wa_Security_GetPermissionGroup";
        List<Object> params = new ArrayList<Object>();
        
        params.add(parameters.getAppid());
        params.add(parameters.getGroupid());
        List<Object> _result = jdbcHelper.executeSP(spName, params, SecurityGroupPermissions.class);
        
        result.setSecurityGroupsPermissions((List<SecurityGroupPermissions>) _result.get(0));
		
		return result;
	}
	
	public GetSecurityUserResponse GetSecurityUsers(GetSecurityUserParameter parameters) {
		GetSecurityUserResponse result = new GetSecurityUserResponse();
		String spName = "up_wa_GetSecurityUserList";
        List<Object> params = new ArrayList<Object>();
        
        params.add(parameters.getUserName());
        params.add(parameters.getGroup());
        params.add(parameters.getRole());
        params.add(parameters.getVendorID());
        List<Object> _result = jdbcHelper.executeSP(spName, params, SecurityUsers.class);
        
        result.setSecurityUserList((List<SecurityUsers>) _result.get(0));
		
		return result;
	}
	
	public GetSecurityGroupPermissionsResponse GetSecurityUserPermissions(GetSecurityUserPermissionsParameter parameters) {
		GetSecurityGroupPermissionsResponse result = new GetSecurityGroupPermissionsResponse();
		String spName = "up_wa_Security_GetPermissionGroup";
        List<Object> params = new ArrayList<Object>();
        
        params.add(parameters.getAppid());
        params.add(parameters.getGroupid());
        List<Object> _result = jdbcHelper.executeSP(spName, params, SecurityGroupPermissions.class);
        
        result.setSecurityGroupsPermissions((List<SecurityGroupPermissions>) _result.get(0));
		
		return result;
	}
}
