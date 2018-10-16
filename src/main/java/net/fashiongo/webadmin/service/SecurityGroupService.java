package net.fashiongo.webadmin.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.fashiongo.webadmin.dao.primary.MapWaUserVendorRepository;
import net.fashiongo.webadmin.dao.primary.SecurityGroupRepository;
import net.fashiongo.webadmin.dao.primary.SecurityMapUserGroupRepository;
import net.fashiongo.webadmin.dao.primary.SecurityPermissionGroupRepository;
import net.fashiongo.webadmin.dao.primary.SecurityUserRepository;
import net.fashiongo.webadmin.model.pojo.GroupData;
import net.fashiongo.webadmin.model.pojo.LoginControl;
import net.fashiongo.webadmin.model.pojo.MapUserGroup;
import net.fashiongo.webadmin.model.pojo.ResultCode;
import net.fashiongo.webadmin.model.pojo.SecurityGroupPermissions;
import net.fashiongo.webadmin.model.pojo.SecurityUserCreate;
import net.fashiongo.webadmin.model.pojo.SecurityUserData;
import net.fashiongo.webadmin.model.pojo.SecurityUsers;
import net.fashiongo.webadmin.model.pojo.SubGroupData;
import net.fashiongo.webadmin.model.pojo.UserMappingVendor;
import net.fashiongo.webadmin.model.pojo.UserMappingVendorAssigned;
import net.fashiongo.webadmin.model.pojo.parameter.DelSecurityUserParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetSecurityGroupPermissionsParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetSecurityUserGroupParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetSecurityUserParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetSecurityUserPermissionsParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetUserMappingVendorParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetSecurityUserParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetUserMappingVendorParameter;
import net.fashiongo.webadmin.model.pojo.response.GetSecurityGroupPermissionsResponse;
import net.fashiongo.webadmin.model.pojo.response.GetSecurityUserGroupAccesstimeResponse;
import net.fashiongo.webadmin.model.pojo.response.GetSecurityUserResponse;
import net.fashiongo.webadmin.model.pojo.response.GetUserByNameResponse;
import net.fashiongo.webadmin.model.pojo.response.GetUserMappingVendorResponse;
import net.fashiongo.webadmin.model.primary.SecurityGroup;
import net.fashiongo.webadmin.model.primary.SecurityMapUserGroup;
import net.fashiongo.webadmin.model.primary.SecurityPermissionGroup;
import net.fashiongo.webadmin.model.primary.SecurityUser;
import net.fashiongo.webadmin.utility.HttpClient;
import net.fashiongo.webadmin.utility.JsonResponse;
/**
 * 
 * @author Reo
 *
 */
@Service
public class SecurityGroupService extends ApiService {
	@Autowired
	private SecurityGroupRepository securityGroupRepository;
	
	@Autowired
	private SecurityUserRepository securityUserRepository;
	
	@Autowired
	private SecurityPermissionGroupRepository securityPermissionGroupRepository;
	
	@Autowired
	private SecurityMapUserGroupRepository securityMapUserGroupRepository;
	
	@Autowired
	private MapWaUserVendorRepository mapWaUserVendorRepository;
	
	@Value("${spring.application.name}")
	private String appName;
	
	@Autowired
	@Qualifier("serviceJsonClient")
	private HttpClient httpClient;

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
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 10. 5.
	 * @author Reo
	 * @return
	 */
	public List<SecurityGroup> GetCommonSecurityGroup() {
		List<SecurityGroup> securityGroupList = (List<SecurityGroup>) securityGroupRepository.findAllByOrderByGroupNameAsc();
		
		return securityGroupList;
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 10. 8.
	 * @author Incheol Jung
	 * @param parameters
	 * @return
	 */
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
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 10. 8.
	 * @author Reo
	 * @param parameters
	 * @return
	 */
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
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 10. 8.
	 * @author Incheol Jung
	 * @param parameters
	 * @return
	 */
	public GetSecurityGroupPermissionsResponse GetSecurityUserPermissions(GetSecurityUserPermissionsParameter parameters) {
		GetSecurityGroupPermissionsResponse result = new GetSecurityGroupPermissionsResponse();
		String spName = "up_wa_Security_GetPermissionGroup";
        List<Object> params = new ArrayList<Object>();
        params.add(parameters.getAppid());
        params.add(parameters.getUserid());
        params.add(parameters.getGroupid());
        
        List<Object> _result = jdbcHelper.executeSP(spName, params, SecurityGroupPermissions.class);
        result.setSecurityGroupsPermissions((List<SecurityGroupPermissions>) _result.get(0));
		
		return result;
	}
	
	/**
	 * 
	 * Set SecurityGroup
	 * 
	 * @since 2018. 10. 8.
	 * @author Incheol Jung
	 * @param groupID
	 * @param groupName
	 * @param description
	 * @param active
	 * @return
	 */
	public ResultCode setSecurityGroup(Integer groupID, String groupName, String description, boolean active) {
		ResultCode result = new ResultCode(true, 0, MSG_SAVE_SUCCESS);
		boolean isDuplicated = false;
		SecurityGroup securityGroup = null;
		
		try {
			if(groupID.equals(0)) {
				isDuplicated = securityGroupRepository.existsByGroupName(groupName);
			} else {
				isDuplicated = securityGroupRepository.existsByGroupIDAndGroupName(groupID, groupName);
			}
			
			if(isDuplicated) {
				result.setSuccess(false);
				result.setResultCode(0);
				result.setResultMsg("group name is duplicated!");
				
				return result;
			}
			
			if(!groupID.equals(0)) {
				securityGroup = securityGroupRepository.findOneByGroupID(groupID);
				securityGroupRepository.delete(securityGroup);
			}
			
			securityGroup = new SecurityGroup();
			securityGroup.setGroupName(groupName);
			securityGroup.setDescription(description);
			securityGroup.setActive(active);
			
			securityGroupRepository.save(securityGroup);
			
			result.setResultMsg(securityGroup.getGroupID().toString());
			
		} catch(Exception ex) {
			result = new ResultCode(false, 0, ex.getMessage());
			return result;
		}
		
		return null;
	}
	
	/**
	 * 
	 * Set SecurityGroupPermissionsAllUsers
	 * 
	 * @since 2018. 10. 8.
	 * @author Incheol Jung
	 * @param groupID
	 * @param p
	 * @return
	 */
	public ResultCode setSecurityGroupPermissionsAllUsers(Integer groupID, List<GroupData> p) {
		ResultCode result = new ResultCode(true, 1, MSG_SAVE_SUCCESS);
		
		if(groupID.equals(0) || CollectionUtils.isEmpty(p)) {
			result.setSuccess(false);
			result.setResultCode(-1);
			result.setResultMsg("No data selected!");
			
			return result;
		}
		try {
			securityPermissionGroupRepository.deleteByGroupID(groupID);
			this.saveSecurityPermissionGroup(groupID, p);
			this.callProcSetPermission(groupID);
		} catch(Exception ex) {
			result = new ResultCode(false, 0, ex.getMessage());
			return result;
		}
		
		return result;
	}
	
	
	/**
	 * 
	 * Description Example
	 * 
	 * @since 2018. 10. 8.
	 * @author Incheol Jung
	 * @param groupID
	 */
	private void callProcSetPermission(Integer groupID) {
		String spName = "up_wa_SetGroupPermission";
        List<Object> params = new ArrayList<Object>();
        List<Object> outputParams = new ArrayList<Object>();
        
        params.add(groupID);
        List<Object> _result = jdbcHelper.executeSP(spName, params, outputParams);
	}
	
	/**
	 * 
	 * Save SecurityPermissionGroups
	 * 
	 * @since 2018. 10. 8.
	 * @author Incheol Jung
	 * @param groupID
	 * @param p
	 */
	@Transactional
	private void saveSecurityPermissionGroup(Integer groupID, List<GroupData> p) {
		List<SecurityPermissionGroup> permissions = new ArrayList<SecurityPermissionGroup>();
		
		for(GroupData groupData : p) {
			for(SubGroupData subGroupData : groupData.getSub()) {
				SecurityPermissionGroup permission = new SecurityPermissionGroup();
				permission.setGroupID(groupID);
				permission.setResourceID(subGroupData.getrId());
				permission.setAllow(subGroupData.getV());
				permission.setAllowEdit(subGroupData.getE());
				permission.setAllowDelete(subGroupData.getD());
				permission.setAllowAdd(subGroupData.getA());
				
				permissions.add(permission);
			}
		}
		
		if(!CollectionUtils.isEmpty(permissions)) {
			securityPermissionGroupRepository.saveAll(permissions);
		}
	}
	
	/**
	 * 
	 * Set deletesecuritygroups
	 * 
	 * @since 2018. 10. 8.
	 * @author Incheol Jung
	 * @param groupIds
	 * @return
	 */
	public ResultCode setdeletesecuritygroups(List<Integer> groupIds) {
		ResultCode result = new ResultCode(true, 0, MSG_DELETE_SUCCESS);
		
		if(CollectionUtils.isEmpty(groupIds)) {
			return result;
		}
		
		List<SecurityMapUserGroup> mapList = securityMapUserGroupRepository.findByGroupIDIn(groupIds);
		if(!CollectionUtils.isEmpty(mapList)) {
			result.setResultMsg("Check the mapped user");
		}

		List<Integer> filteredGroupIds = mapList.stream().map(group -> group.getGroupID()).distinct().collect(Collectors.toList());
		groupIds.removeAll(filteredGroupIds);
		
		securityPermissionGroupRepository.deleteByGroupIDIn(groupIds);
		securityGroupRepository.deleteByGroupIDIn(groupIds);
		
		result.setResultCode(groupIds.size());
		
		return result;
	}
	
	@Transactional("primaryTransactionManager")
	public ResultCode setActiveGroup(Integer GroupID, boolean Active) {
		ResultCode result = new ResultCode(true, 0, MSG_UPDATE_SUCCESS);
		
		SecurityGroup securityGroup = securityGroupRepository.findOneByGroupID(GroupID);
		if(securityGroup != null) {
			securityGroup.setActive(Active);
			securityGroupRepository.save(securityGroup);
		}
		
		return result;
	}
	
	/**
	 * 
	 * Set SecurityGroupPermissions
	 * 
	 * @since 2018. 10. 8.
	 * @author Incheol Jung
	 * @param groupID
	 * @param p
	 * @return
	 */
	@Transactional
	public ResultCode setSecurityGroupPermissions(Integer groupID, List<GroupData> p) {
		return null;
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 10. 10.
	 * @author Reo
	 * @param userID
	 * @param active
	 * @return
	 */
	@Transactional("primaryTransactionManager")
	public ResultCode setSecurityUserActive(Integer userID, boolean active) {
		ResultCode result = new ResultCode(true, 0, MSG_UPDATE_SUCCESS);
		SecurityUser securityUser = securityUserRepository.findByUserID(userID);
		if (securityUser != null) {
			securityUser.setActive(active);
			securityUserRepository.save(securityUser);
		}
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
	public GetUserMappingVendorResponse GetUserMappingVendor(GetUserMappingVendorParameter parameters) {
		GetUserMappingVendorResponse result = new GetUserMappingVendorResponse();
		String spName = "up_wa_GetUserMappingVendor";
		List<Object> params = new ArrayList<Object>();
		params.add(parameters.getUserID());
		params.add(parameters.getAlphabet());
		params.add(parameters.getCompanyType());
		params.add(parameters.getCategorys());
		params.add(parameters.getVendorType());
		params.add(parameters.getVendorKeyword());
		
		List<Object> _result = jdbcHelper.executeSP(spName, params, UserMappingVendor.class, UserMappingVendorAssigned.class);
		result.setUserMappingVendorList((List<UserMappingVendor>) _result.get(0));
		result.setUserMappingVendorAssigned((List<UserMappingVendorAssigned>) _result.get(1));
		
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
	public Integer GetUserMappingVendorCount(GetUserMappingVendorParameter parameters) {
		Integer userMappginVendorCount = mapWaUserVendorRepository.countByUserID(parameters.getUserID());
		
		return userMappginVendorCount;
	}
	
    /**
     * 
     * Description Example
     * @since 2018. 10. 10.
     * @author Reo
     * @param parameters
     * @return
     */
	public ResultCode SetUserMappingVendor(SetUserMappingVendorParameter parameters) {
		ResultCode result = new ResultCode(false, 0, null);
		String spName = "up_wa_SetUserMappingVendor";
		List<Object> params = new ArrayList<Object>();
		
		List<Object> _result = jdbcHelper.executeSP(spName, params, UserMappingVendor.class);
		result.setResultCode((Integer) _result.get(0));
		result.setSuccess(true);
		
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
	public GetSecurityUserGroupAccesstimeResponse GetSecurityUserGroupAccessTimes(GetSecurityUserGroupParameter parameters) {
		GetSecurityUserGroupAccesstimeResponse result = new GetSecurityUserGroupAccesstimeResponse();
		String spName = "up_wa_GetSecurityUserGroupAccesstimes";
		List<Object> params = new ArrayList<Object>();
		params.add(parameters.getUsrId());
		
		List<Object> _result = jdbcHelper.executeSP(spName, params, MapUserGroup.class, LoginControl.class);
		result.setMapUserGroupList((List<MapUserGroup>) _result.get(0));
		result.setLoginControlList((List<LoginControl>) _result.get(1));
		result.setSuccess(true);
		
		return result;
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 10. 11.
	 * @author Reo
	 * @param parameters
	 * @return
	 */
	public ResultCode SetDelSecurityUsers(DelSecurityUserParameter parameters) {	
		ResultCode result = new ResultCode(false, 0, null);
		for(Integer delUserID: parameters.getUserList()) {
			SecurityUser securityUser = securityUserRepository.findByUserID(delUserID);
			String spName = "up_wa_DeleteSecurityUser";
			
			List<Object> params = new ArrayList<Object>();
			params.add("Fashiongo");
			params.add(securityUser.getUserID());
			
			List<Object> outputParams = new ArrayList<Object>();
			outputParams.add(0);
			
			List<Object> _result = jdbcHelper.executeSP(spName, params, outputParams);
			result.setResultCode((Integer) outputParams.get(0));
			result.setResultMsg(MSG_DELETE_SUCCESS);
			result.setSuccess(true);
		}
		
		return result;
	}
	
	public ResultCode SetCreateSecurityUser(SetSecurityUserParameter jsonParameters) throws JsonProcessingException {
		ResultCode result = new ResultCode(false, 0, null);
		SecurityUserCreate userData = jsonParameters.getData().getUser();
		String uri = "/membership/createMembership";
		ObjectMapper mapper = new ObjectMapper();
		JsonResponse<?> ret = httpClient.postObject(uri, mapper.writeValueAsString(userData));
		
		if (ret.isSuccess()) {
			Boolean userByNameSuccess = true;
			String guid = null;
			String userByNameSpName = "aspnet_Membership_GetUserByName";
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			
			List<Object> userByNameParams = new ArrayList<Object>();
			userByNameParams.add(appName);
			userByNameParams.add(userData.getUserName());
			userByNameParams.add(0);
			userByNameParams.add(dtf.format(now));
			List<Object> _result = jdbcHelper.executeSP(userByNameSpName, userByNameParams);
			GetUserByNameResponse userByNameRes = (GetUserByNameResponse) _result.get(0);
			
			if (userByNameRes.getUserId() != null) {
				userByNameSuccess = false;
			} else {
				guid = userByNameRes.getUserId();
				String userRoleSpName = "aspnet_UsersInRoles_AddUsersToRoles";
				
				List<Object> userRoleParams = new ArrayList<Object>();
				userRoleParams.add(appName);
			}
			
		}
		return result;
	}
}
