package net.fashiongo.webadmin.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.fashiongo.webadmin.dao.primary.AspnetMembershipRepository;
import net.fashiongo.webadmin.dao.primary.AspnetPersonalizationPerUserRepository;
import net.fashiongo.webadmin.dao.primary.AspnetProfileRepository;
import net.fashiongo.webadmin.dao.primary.AspnetUsersInRolesRepository;
import net.fashiongo.webadmin.dao.primary.AspnetUsersRepository;
import net.fashiongo.webadmin.dao.primary.MapWaUserVendorRepository;
import net.fashiongo.webadmin.dao.primary.SecurityGroupRepository;
import net.fashiongo.webadmin.dao.primary.SecurityLoginControlRepository;
import net.fashiongo.webadmin.dao.primary.SecurityMapUserGroupRepository;
import net.fashiongo.webadmin.dao.primary.SecurityPermissionGroupRepository;
import net.fashiongo.webadmin.dao.primary.SecurityPermissionRepository;
import net.fashiongo.webadmin.dao.primary.SecurityUserRepository;
import net.fashiongo.webadmin.model.pojo.AspnetMembershipGetUserByName;
import net.fashiongo.webadmin.model.pojo.AspnetUserRoles;
import net.fashiongo.webadmin.model.pojo.GroupData;
import net.fashiongo.webadmin.model.pojo.LoginControl;
import net.fashiongo.webadmin.model.pojo.MapUserGroup;
import net.fashiongo.webadmin.model.pojo.ResultCode;
import net.fashiongo.webadmin.model.pojo.SecurityGroupPermissions;
import net.fashiongo.webadmin.model.pojo.SecurityUserCreate;
import net.fashiongo.webadmin.model.pojo.SecurityUserPermission;
import net.fashiongo.webadmin.model.pojo.SecurityUserPermissionSub;
import net.fashiongo.webadmin.model.pojo.SecurityUsers;
import net.fashiongo.webadmin.model.pojo.SubGroupData;
import net.fashiongo.webadmin.model.pojo.UserMappingVendor;
import net.fashiongo.webadmin.model.pojo.UserMappingVendorAssigned;
import net.fashiongo.webadmin.model.pojo.UserMappingVendorResult;
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
import net.fashiongo.webadmin.model.pojo.response.GetUserMappingVendorResponse;
import net.fashiongo.webadmin.model.pojo.response.SetAspnetMembershipResponse;
import net.fashiongo.webadmin.model.pojo.response.SetCreateSecurityUserResponse;
import net.fashiongo.webadmin.model.pojo.response.SetUserMappingVendorResponse;
import net.fashiongo.webadmin.model.primary.AspnetUsers;
import net.fashiongo.webadmin.model.primary.SecurityGroup;
import net.fashiongo.webadmin.model.primary.SecurityLoginControl;
import net.fashiongo.webadmin.model.primary.SecurityMapUserGroup;
import net.fashiongo.webadmin.model.primary.SecurityPermission;
import net.fashiongo.webadmin.model.primary.SecurityPermissionGroup;
import net.fashiongo.webadmin.model.primary.SecurityUser;
import net.fashiongo.webadmin.utility.HttpClient;
import net.fashiongo.webadmin.utility.JsonResponse;
import net.fashiongo.webadmin.utility.Utility;
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
	
	@Autowired
	private SecurityLoginControlRepository securityLoginControlRepository;
	
	@Autowired
	private SecurityPermissionRepository securityPermissionRepository;
	
	@Autowired 
	private AspnetUsersRepository aspnetUsersRepository;
	
	@Autowired
	private AspnetMembershipRepository aspnetMembershipRepository;
	
	@Autowired
	private AspnetUsersInRolesRepository aspnetUsersInRolesRepository;
	
	@Autowired
	private AspnetProfileRepository aspnetProfileRepository;
	
	@Autowired
	private AspnetPersonalizationPerUserRepository aspnetPersonalizationPerUserRepository;
	
	private String appName = "FashionGo";
	
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
	public List<SecurityGroup> getSecurityGroup() {
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
	public List<SecurityGroup> getCommonSecurityGroup() {
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
	@SuppressWarnings("unchecked")
	public GetSecurityUserResponse getSecurityUsers(GetSecurityUserParameter parameters) {
		GetSecurityUserResponse result = new GetSecurityUserResponse();
		String spName = "up_wa_GetSecurityUserList_Migration";
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
	public GetSecurityGroupPermissionsResponse getSecurityUserPermissions(GetSecurityUserPermissionsParameter parameters) {
		GetSecurityGroupPermissionsResponse result = new GetSecurityGroupPermissionsResponse();
		String spName = "up_wa_Security_GetPermission";
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
		
		return result;
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
			
			List<Integer> filteredGroupIds = mapList.stream().map(group -> group.getGroupID()).distinct().collect(Collectors.toList());
			groupIds.removeAll(filteredGroupIds);
		}
		
		securityPermissionGroupRepository.deleteByGroupIDIn(groupIds);
		securityGroupRepository.deleteByGroupIDIn(groupIds);
		
		result.setResultCode(groupIds.size());
		
		return result;
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 10. 8.
	 * @author Incheol Jung
	 * @param GroupID
	 * @param Active
	 * @return
	 */
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
	@SuppressWarnings("unchecked")
	public GetUserMappingVendorResponse getUserMappingVendor(GetUserMappingVendorParameter parameters) {
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
	public Integer getUserMappingVendorCount(GetUserMappingVendorParameter parameters) {
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
	@SuppressWarnings("unchecked")
	public SetUserMappingVendorResponse setUserMappingVendor(SetUserMappingVendorParameter parameters) {
		SetUserMappingVendorResponse result = new SetUserMappingVendorResponse();
		String spName = "up_wa_SetUserMappingVendor";
		List<Object> params = new ArrayList<Object>();
		params.add(parameters.getUserID());
		params.add(parameters.getSaveWholeSalerIDs());
		params.add(parameters.getRemoveWholeSalerIDs());
		
		List<Object> _result = jdbcHelper.executeSP(spName, params, UserMappingVendorResult.class);
		result.setUserMappingVendorResultList((List<UserMappingVendorResult>) _result.get(0));
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
	@SuppressWarnings("unchecked")
	public GetSecurityUserGroupAccesstimeResponse getSecurityUserGroupAccessTimes(GetSecurityUserGroupParameter parameters) {
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
	@Transactional(value = "primaryTransactionManager")
	public ResultCode setDelSecurityUsers(DelSecurityUserParameter parameters) {	
		ResultCode result = new ResultCode(false, 0, null);
		List<Integer> securityUserID = parameters.getUserList();
		List<SecurityUser> securityUserList = securityUserRepository.findByUserIDIn(securityUserID);
		List<String> aspnetUserIdList = new ArrayList<String>();
		for(SecurityUser su: securityUserList) {
			AspnetUsers au = new AspnetUsers();
			au = aspnetUsersRepository.findByLoweredUserName(su.getUserName().toLowerCase().trim());
			aspnetUserIdList.add(au.getUserId());
		}
		
		securityLoginControlRepository.deleteByUserIDIn(parameters.getUserList());
		securityMapUserGroupRepository.deleteByUserIDIn(parameters.getUserList());
		securityPermissionRepository.deleteByUserIDIn(parameters.getUserList());
		securityUserRepository.deleteByUserIDIn(parameters.getUserList());
		
		aspnetMembershipRepository.deleteByUserIdIn(aspnetUserIdList);
		aspnetUsersInRolesRepository.deleteByUserIdIn(aspnetUserIdList);
		aspnetProfileRepository.deleteByUserIdIn(aspnetUserIdList);
		aspnetPersonalizationPerUserRepository.deleteByUserIdIn(aspnetUserIdList);
		aspnetUsersRepository.deleteByUserIdIn(aspnetUserIdList);

		result.setResultCode(1);
		result.setResultMsg(MSG_DELETE_SUCCESS);
		result.setSuccess(true);
//		for(SecurityUser su: securityUserList) {
//			String spName = "up_wa_DeleteSecurityUser";
//			
//			List<Object> params = new ArrayList<Object>();
//			params.add("Fashiongo");
//			params.add(su.getUserID());
//			
//			List<Object> outputParams = new ArrayList<Object>();
//			outputParams.add(0);
//			
//			@SuppressWarnings("unused")
//			List<Object> _result = jdbcHelper.executeSP(spName, params, outputParams);
//			result.setResultCode((Integer) outputParams.get(0));
//			result.setResultMsg(MSG_DELETE_SUCCESS);
//			result.setSuccess(true);
//		}
		
		return result;
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 10. 25.
	 * @author Reo
	 * @param userData
	 * @return
	 * @throws JsonProcessingException 
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@Transactional(value = "primaryTransactionManager")
	private SetAspnetMembershipResponse setSaveAspnetMembership(SecurityUserCreate userData) throws JsonProcessingException {
		SetAspnetMembershipResponse result = new SetAspnetMembershipResponse();
		Boolean userByNameSuccess = true;
		SecurityUser su = null;
		AspnetMembershipGetUserByName userByNameRes = new AspnetMembershipGetUserByName();
		
		if (userData.getUserName() == "" || userData.getFullName() == "" || userData.getRole() == "") {
			result.setSuccess(false);
			result.setResultCode(-1);
			result.setResultMsg("Please check the required field.");
		} else {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			
			Integer userID = userData.getID() != null ? (userData.getID() > 0 ? userData.getID() : 0) : 0;
			if (userID <= 0) {
				String uri = "/membership/createMembership";
				ObjectMapper mapper = new ObjectMapper();
				JsonResponse<?> ret = httpClient.postObject(uri, mapper.writeValueAsString(userData));
				
				if (ret.isSuccess()) {
					String guid = null;
					String userByNameSpName = "aspnet_Membership_GetUserByName";
					
					List<Object> userByNameParams = new ArrayList<Object>();
					userByNameParams.add(appName);
					userByNameParams.add(userData.getUserName());
					userByNameParams.add(dtf.format(now));
					userByNameParams.add(0);
					List<Object> _result = jdbcHelper.executeSP(userByNameSpName, userByNameParams, AspnetMembershipGetUserByName.class);
					userByNameRes = ((List<AspnetMembershipGetUserByName>) _result.get(0)).get(0);
					if (userByNameRes.getUserId() == null) {
						userByNameSuccess = false;
					} else {
						guid = userByNameRes.getUserId();
						String userRoleSpName = "aspnet_UsersInRoles_AddUsersToRoles";
						
						List<Object> userRoleParams = new ArrayList<Object>();
						userRoleParams.add(appName);
						userRoleParams.add(userData.getUserName());
						userRoleParams.add("Admin");
						userRoleParams.add(LocalDateTime.now());
						
						List<Object> _resultUserRole = jdbcHelper.executeSP(userRoleSpName,  userRoleParams, AspnetUserRoles.class);
					}
					
					if (userByNameSuccess) {
						su = new SecurityUser();
						su.setUserName(userData.getUserName());
						su.setUserGUID(guid);
						su.setCreatedBy(userData.getCreatedBy());
						su.setCreatedOn(userData.getCreatedOn());
						su.setModifiedBy(userData.getModifiedBy());
						su.setModifiedOn(userData.getModifiedOn());
					}
					
				} else {
					result.setSuccess(false);
					result.setResultCode(-1);
					result.setResultMsg(ret.getMessage());
					userByNameSuccess = false;
				}
			} else {
				String userByNameSpName = "aspnet_Membership_GetUserByName";
				
				List<Object> userByNameParams = new ArrayList<Object>();
				userByNameParams.add(appName);
				userByNameParams.add(userData.getUserName());
				userByNameParams.add(dtf.format(now));
				userByNameParams.add(0);
				List<Object> _result = jdbcHelper.executeSP(userByNameSpName, userByNameParams, AspnetMembershipGetUserByName.class);
				userByNameRes = ((List<AspnetMembershipGetUserByName>) _result.get(0)).get(0);
				
				su = securityUserRepository.findByUserID(userID);
				su.setModifiedBy(userData.getModifiedBy());
				su.setModifiedOn(now);
			}
			if (userByNameRes != null) {
				String membershipUpdateSpname = "aspnet_Membership_UpdateUser";
				
				List<Object> membershipParams = new ArrayList<Object>();
				membershipParams.add(appName);
				membershipParams.add(userData.getUserName());
				membershipParams.add(null);
				membershipParams.add("");
				membershipParams.add(userByNameRes.getIsApproved());
				membershipParams.add(dtf.format(userByNameRes.getLastLoginDate()));
				membershipParams.add(dtf.format(userByNameRes.getLastActivityDate()));
				membershipParams.add(0);
				membershipParams.add(dtf.format(now));
				
				List<Object> _resultMembership = jdbcHelper.executeSP(membershipUpdateSpname, membershipParams);
				
				su.setFullName(userData.getFullName());
				su.setRole(userData.getRole());
				su.setIpTimeExempt(userData.getExempt());
				su.setActive(userData.getActive());
				securityUserRepository.save(su);
				
				result.setUserID(su.getUserID());
				result.setSuccess(true);
				result.setResultCode(1);
				result.setResultMsg(MSG_SAVE_SUCCESS);
			}
		}
		return result;
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 10. 25.
	 * @author Reo
	 * @param userID
	 * @param groupNames
	 * @return
	 */
	private ResultCode setSaveSecurityGroup(Integer userID, List<String> groupNameList, List<String> delGroupNameList) {
		ResultCode result = new ResultCode(false, 0, null);
		//SecurityMapUserGroup Delete
		List<SecurityGroup> delSecurityGroupList = securityGroupRepository.findByGroupNameIn(delGroupNameList);
		List<SecurityMapUserGroup> delSecurityMapUserGroupList = new ArrayList<SecurityMapUserGroup>();
		for(SecurityGroup sc: delSecurityGroupList) {
			SecurityMapUserGroup newSecurityMapUserGroup = securityMapUserGroupRepository.findByUserIDAndGroupID(userID, sc.getGroupID());
			if (newSecurityMapUserGroup != null) {
			    delSecurityMapUserGroupList.add(newSecurityMapUserGroup);
			}
		}
		if (delSecurityMapUserGroupList.size() > 0) {
		    securityMapUserGroupRepository.deleteAll(delSecurityMapUserGroupList);
		}
		
		//SecurityMapUserGroup Insert
		List<SecurityGroup> securityGroupList = securityGroupRepository.findByGroupNameIn(groupNameList);
		List<SecurityMapUserGroup> newSecurityMapUserGroupList =  new ArrayList<SecurityMapUserGroup>();
	    for(SecurityGroup sg: securityGroupList) {
	    	SecurityMapUserGroup smu = new SecurityMapUserGroup();
	    	smu.setUserID(userID);
	    	smu.setGroupID(sg.getGroupID());
	    	newSecurityMapUserGroupList.add(smu);
	    }
	    if (newSecurityMapUserGroupList.size() > 0) {
	        securityMapUserGroupRepository.saveAll(newSecurityMapUserGroupList);
	        result.setResultCode(1);
	        result.setSuccess(true);
	        result.setResultMsg(MSG_SAVE_SUCCESS);
	    }
		return result;
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 10. 25.
	 * @author Reo
	 * @param userID
	 * @param dat
	 * @param at
	 * @return
	 * @throws ParseException
	 */
	@SuppressWarnings("unused")
	private ResultCode setSaveSecurityLoginControl(Integer userID, List<String> delAccesstimeList, List<String> accesstimeList) throws ParseException {
		ResultCode result = new ResultCode(false, 0, null);
		//SecurityLoginControl Delete
	    SimpleDateFormat transFormat = new SimpleDateFormat("HH:mm");
	    List<SecurityLoginControl> delSecurityLoginControlList = new ArrayList<SecurityLoginControl>();
	    for(String dat: delAccesstimeList) {
	    	Integer weekday = Utility.GetWeekday(dat.trim().substring(0, 3));
	    	Date timefrom = transFormat.parse(dat.trim().substring(4, 9));
			Date timeto = transFormat.parse(dat.trim().substring(12, 17));
	    	SecurityLoginControl slc = securityLoginControlRepository.findOneByUserIDAndWeekday(userID, weekday);
	    	if (slc != null) {
	    	    delSecurityLoginControlList.add(slc);
	    	}
	    }
	    if (delSecurityLoginControlList.size() > 0) {
	        securityLoginControlRepository.deleteAll(delSecurityLoginControlList);
	    }
		
	    //SecurityLoginControl Insert
	    List<SecurityLoginControl> newSecurityLoginControlList = new ArrayList<SecurityLoginControl>();
	    for(String at: accesstimeList) {
	    	Integer weekday = Utility.GetWeekday(at.trim().substring(0, 3));
	    	Date timefrom = transFormat.parse(at.trim().substring(4, 9));
			Date timeto = transFormat.parse(at.trim().substring(12, 17));
	    	SecurityLoginControl slc = securityLoginControlRepository.findOneByUserIDAndWeekday(userID, weekday);
	    	if(slc != null) {
	    		slc.setUserID(userID);
	    		slc.setWeekday(weekday);
	    		slc.setTimeFrom(timefrom);
	    		slc.setTimeTo(timeto);
	    		slc.setAllow(true);
	    		slc.setActive(true);
	    		newSecurityLoginControlList.add(slc);
	    	} else {
	    		SecurityLoginControl newSecurityLoginControl = new SecurityLoginControl();
	    		newSecurityLoginControl.setUserID(userID);
	    		newSecurityLoginControl.setWeekday(weekday);
	    		newSecurityLoginControl.setTimeFrom(timefrom);
	    		newSecurityLoginControl.setTimeTo(timeto);
	    		newSecurityLoginControl.setAllow(true);
	    		newSecurityLoginControl.setActive(true);
	    		newSecurityLoginControlList.add(newSecurityLoginControl);
	    	}
	    }
	    if (newSecurityLoginControlList.size() > 0) {
	        securityLoginControlRepository.saveAll(newSecurityLoginControlList);
	        result.setResultCode(1);
	        result.setSuccess(true);
	        result.setResultMsg(MSG_SAVE_SUCCESS);
	    }
	    
	    return result;
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 10. 25.
	 * @author Reo
	 * @param userID
	 * @param upData
	 * @return
	 */
	private ResultCode setSaveSecurityPermission(Integer userID, List<SecurityUserPermission> permissionList) {
		ResultCode result = new ResultCode(false, 0, null);
    	securityPermissionRepository.deleteByUserIDIn(userID);
    	
    	List<SecurityPermission> securityPermissionList = new ArrayList<SecurityPermission>();
    	for(SecurityUserPermission sup: permissionList) {
    		for(SecurityUserPermissionSub sups: sup.getSub()) {
    			SecurityPermission sp = new SecurityPermission();
    			sp.setUserID(userID);
    			sp.setResourceID(sups.getrId());
    			sp.setApplicationID(1);
    			sp.setAllow(sups.getV());
    			sp.setAllowEdit(sups.getE());
    			sp.setAllowDelete(sups.getD());
    			sp.setAllowAdd(sups.getA());
    			securityPermissionList.add(sp);
    		}
    	}
    	securityPermissionRepository.saveAll(securityPermissionList);
    	result.setSuccess(true);
    	result.setResultCode(1);
    	result.setResultMsg(MSG_SAVE_SUCCESS);
    	return result;
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 10. 22.
	 * @author Reo
	 * @param jsonParameters
	 * @return
	 * @throws JsonProcessingException
	 * @throws ParseException 
	 */
	public SetCreateSecurityUserResponse setCreateSecurityUser(SetSecurityUserParameter jsonParameters) throws JsonProcessingException, ParseException {
		SetAspnetMembershipResponse resultMembership = new SetAspnetMembershipResponse();
		ResultCode resultSecurityGroup = new ResultCode(false, 0, null);
		ResultCode resultLoginControl = new ResultCode(false, 0, null);
		ResultCode resultPermission = new ResultCode(false, 0, null);
		//Get Json Data
		SecurityUserCreate userData = jsonParameters.getData().getUser();
		List<SecurityUserPermission> permissionList = jsonParameters.getUpdata();
		List<String> delAccesstimeList = jsonParameters.getData().getDelaccesstimes();
		List<String> accesstimeList = jsonParameters.getData().getAccesstimes();
		List<String> groupNameList = jsonParameters.getData().getGroupnames();
		List<String> delGroupNameList = jsonParameters.getData().getDelgroupnames();
		
		Integer userID = userData.getID() != null ? (userData.getID() > 0 ? userData.getID() : 0) : 0;
		Integer userPK = 0;
		//Save AspnetMembership
		resultMembership = this.setSaveAspnetMembership(userData);
		if (resultMembership.getSuccess()) {
			userID = resultMembership.getUserID();
			//Save SecurityGroup
			resultSecurityGroup = this.setSaveSecurityGroup(userID, groupNameList, delGroupNameList);
			
			if (resultSecurityGroup.getSuccess()) {
				//Save SecurityLoginControl
			    resultLoginControl = this.setSaveSecurityLoginControl(userID, delAccesstimeList, accesstimeList);
			}
		     
			userPK = userID;    
		    if(resultLoginControl.getSuccess()) {
		    	if(permissionList == null || permissionList.isEmpty()) {
		    		userID = 0;
		    	} else {
		    		if (userID == 0) {
		    			userID = userPK;
		    		}
		    	}
		    	
		    	if (userID != 0 && permissionList != null) {
		    		//Save SecurityPermission
		    		resultPermission = this.setSaveSecurityPermission(userID, permissionList);
		    	}
		    } else {
		    	resultLoginControl.setSuccess(false);
		    	resultLoginControl.setResultCode(-1);
		    	resultLoginControl.setResultMsg("No data selected!");
		    }
		}
	    SetCreateSecurityUserResponse createUserRes = new SetCreateSecurityUserResponse();
	    createUserRes.setResultCode(resultLoginControl);
	    createUserRes.setResultCode2(resultPermission);
		return createUserRes;
	}
	
}
