package net.fashiongo.webadmin.service.renewal;

import net.fashiongo.webadmin.data.model.admin.ResultGetSecurityUserGroupAccesstimes;
import net.fashiongo.webadmin.data.model.admin.SecurityGroupPermissions;
import net.fashiongo.webadmin.data.model.admin.response.GetSecurityGroupPermissionsResponse;
import net.fashiongo.webadmin.data.model.admin.response.GetSecurityUserGroupAccesstimeResponse;
import net.fashiongo.webadmin.data.model.admin.response.GetUserMappingVendorResponse;
import net.fashiongo.webadmin.data.repository.primary.SecurityGroupProcedureRepository;
import net.fashiongo.webadmin.data.repository.primary.procedure.PrimaryProcedureRepository;
import net.fashiongo.webadmin.data.model.sitemgmt.ResultGetUserMappingVendor;
import net.fashiongo.webadmin.model.pojo.admin.parameter.GetSecurityGroupPermissionsParameter;
import net.fashiongo.webadmin.model.pojo.admin.parameter.GetSecurityUserGroupParameter;
import net.fashiongo.webadmin.model.pojo.admin.parameter.GetSecurityUserPermissionsParameter;
import net.fashiongo.webadmin.model.pojo.admin.parameter.GetUserMappingVendorParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RenewalSecurityGroupService {

	@Autowired
	private SecurityGroupProcedureRepository securityGroupProcedureRepository;

	@Autowired
	private PrimaryProcedureRepository primaryProcedureRepository;

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

	public GetSecurityUserGroupAccesstimeResponse getSecurityUserGroupAccessTimes(GetSecurityUserGroupParameter parameters) {

		Integer userID = parameters.getUsrId();

		ResultGetSecurityUserGroupAccesstimes userGroupAccessTimes = securityGroupProcedureRepository.up_wa_GetSecurityUserGroupAccesstimes(userID);

		return GetSecurityUserGroupAccesstimeResponse.builder()
				.mapUserGroupList(userGroupAccessTimes.getMapUserGroups())
				.loginControlList(userGroupAccessTimes.getLoginControls())
				.success(true)
				.build();
	}

	public GetUserMappingVendorResponse getUserMappingVendor(GetUserMappingVendorParameter parameters) {

		Integer userID = parameters.getUserID();
		String alphabet = parameters.getAlphabet();
		String companyType = parameters.getCompanyType();
		String categorys = parameters.getCategorys();
		String vendorType = parameters.getVendorType();
		String vendorKeyword = parameters.getVendorKeyword();
		ResultGetUserMappingVendor resultGetUserMappingVendor = primaryProcedureRepository.up_wa_GetUserMappingVendor(userID, alphabet, companyType, categorys, vendorType, vendorKeyword);

		return GetUserMappingVendorResponse.builder()
				.userMappingVendorAssigned(resultGetUserMappingVendor.getUserMappingVendorAssignedList())
				.userMappingVendorList(resultGetUserMappingVendor.getUserMappingVendorList())
				.build();
	}

}
