package net.fashiongo.webadmin.service.renewal;

import net.fashiongo.webadmin.data.model.admin.SecurityGroupPermissions;
import net.fashiongo.webadmin.data.model.admin.response.GetSecurityGroupPermissionsResponse;
import net.fashiongo.webadmin.data.repository.primary.SecurityGroupProcedureRepository;
import net.fashiongo.webadmin.model.pojo.admin.parameter.GetSecurityGroupPermissionsParameter;
import net.fashiongo.webadmin.model.pojo.admin.parameter.GetSecurityUserPermissionsParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RenewalSecurityGroupService {

	@Autowired
	private SecurityGroupProcedureRepository securityGroupProcedureRepository;

	public GetSecurityGroupPermissionsResponse GetSecurityGroupPermissions(GetSecurityGroupPermissionsParameter parameters) {

		Integer appid = parameters.getAppid();
		Integer groupid = parameters.getGroupid();
		List<SecurityGroupPermissions> securityGroupPermissions = securityGroupProcedureRepository.up_wa_Security_GetPermissionGroup(appid, groupid);

		return GetSecurityGroupPermissionsResponse.builder()
				.securityGroupsPermissions(securityGroupPermissions)
				.build();
	}

	public GetSecurityGroupPermissionsResponse getSecurityUserPermissions(GetSecurityUserPermissionsParameter parameters) {

		Integer appId = parameters.getAppid();
		Integer userId = parameters.getUserid();
		Integer groupId = parameters.getGroupid();

		List<SecurityGroupPermissions> securityGroupPermissions = securityGroupProcedureRepository.up_wa_Security_GetPermission(appId, userId, groupId);

		return GetSecurityGroupPermissionsResponse.builder()
				.securityGroupsPermissions(securityGroupPermissions)
				.build();
	}

}
