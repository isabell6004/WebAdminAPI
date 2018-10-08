package net.fashiongo.webadmin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import net.fashiongo.webadmin.dao.primary.SecurityGroupRepository;
import net.fashiongo.webadmin.dao.primary.SecurityMapUserGroupRepository;
import net.fashiongo.webadmin.dao.primary.SecurityPermissionGroupRepository;
import net.fashiongo.webadmin.model.pojo.GroupData;
import net.fashiongo.webadmin.model.pojo.ResultCode;
import net.fashiongo.webadmin.model.pojo.SecurityGroupPermissions;
import net.fashiongo.webadmin.model.pojo.SecurityUsers;
import net.fashiongo.webadmin.model.pojo.SubGroupData;
import net.fashiongo.webadmin.model.pojo.parameter.GetSecurityGroupPermissionsParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetSecurityUserParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetSecurityUserPermissionsParameter;
import net.fashiongo.webadmin.model.pojo.response.GetSecurityGroupPermissionsResponse;
import net.fashiongo.webadmin.model.pojo.response.GetSecurityUserResponse;
import net.fashiongo.webadmin.model.primary.SecurityGroup;
import net.fashiongo.webadmin.model.primary.SecurityMapUserGroup;
import net.fashiongo.webadmin.model.primary.SecurityPermissionGroup;
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
	private SecurityPermissionGroupRepository securityPermissionGroupRepository;
	
	@Autowired
	private SecurityMapUserGroupRepository securityMapUserGroupRepository;

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
	 * 
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
	 * 
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
	 * 
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
	
	@Transactional
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
}
