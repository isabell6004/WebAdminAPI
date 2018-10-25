package net.fashiongo.webadmin.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import net.fashiongo.webadmin.model.pojo.MapUserGroup;
import net.fashiongo.webadmin.model.pojo.ResultCode;
import net.fashiongo.webadmin.model.pojo.SecurityGroupPermissions;
import net.fashiongo.webadmin.model.pojo.SecurityUsers;
import net.fashiongo.webadmin.model.pojo.UserMappingVendor;
import net.fashiongo.webadmin.model.pojo.UserMappingVendorAssigned;
import net.fashiongo.webadmin.model.pojo.parameter.DelSecurityUserParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetSecurityGroupPermissionsParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetSecurityUserGroupParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetSecurityUserParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetUserMappingVendorParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetActiveGroupParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetUserMappingVendorParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetdeletesecuritygroupsParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetsecuritygroupParameter;
import net.fashiongo.webadmin.model.pojo.response.GetSecurityGroupPermissionsResponse;
import net.fashiongo.webadmin.model.pojo.response.GetSecurityUserGroupAccesstimeResponse;
import net.fashiongo.webadmin.model.pojo.response.GetSecurityUserResponse;
import net.fashiongo.webadmin.model.pojo.response.GetUserMappingVendorResponse;
import net.fashiongo.webadmin.model.pojo.response.SetUserMappingVendorResponse;
import net.fashiongo.webadmin.model.primary.SecurityGroup;
import net.fashiongo.webadmin.model.primary.SecurityUser;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SecurityGroupServiceTest {
	@Autowired
    SecurityGroupService securityGroupService;
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 10. 22.
	 * @author Reo
	 */
	@Test
	public void testGetSecurityUserGroupAccessTimes() {
		GetSecurityUserGroupParameter parameters = new GetSecurityUserGroupParameter();
		parameters.setUsrId(11);
		
		GetSecurityUserGroupAccesstimeResponse result = securityGroupService.GetSecurityUserGroupAccessTimes(parameters);
		if (result != null) {
			for(MapUserGroup mug : result.getMapUserGroupList()) {
				assertNotNull(mug.getGroupID());
				assertNotNull(mug.getGroupName());
				assertNotNull(mug.getUserID());
				assertNotNull(mug.getMapID());
			}
		}
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 10. 22.
	 * @author Reo
	 */
	@Test
	public void testGetSecurityUsers() {
		GetSecurityUserParameter parameters = new GetSecurityUserParameter();
		parameters.setUserName("niki");
		parameters.setGroup("Design");
		parameters.setRole("G");
		parameters.setVendorID(3064);
		
		GetSecurityUserResponse result = securityGroupService.GetSecurityUsers(parameters);
		if (result != null) {
			for(SecurityUsers su: result.getSecurityUserList()) {
				assertNotNull(su.getUserID());
				assertNotNull(su.getUserName());
				assertNotNull(su.getFullName());
				assertNotNull(su.getRole());
				assertNotNull(su.getGroupName());
				assertNotNull(su.getCreatedBy());
				assertNotNull(su.getCreatedOn());
			}
		}
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 10. 22.
	 * @author Reo
	 */
	@Test
	public void testGetUserMappingVendor() {
		GetUserMappingVendorParameter parameters = new GetUserMappingVendorParameter();
		parameters.setUserID(11);
		parameters.setAlphabet(null);
		parameters.setCategorys(null);
		parameters.setCompanyType(null);
		parameters.setVendorType(null);
		parameters.setVendorKeyword(null);
		
		GetUserMappingVendorResponse result = securityGroupService.GetUserMappingVendor(parameters);
		if (result != null) {
			for(UserMappingVendor umv: result.getUserMappingVendorList()) {
				assertNotNull(umv.getWholeSalerID());
				assertNotNull(umv.getMapID());
				assertNotNull(umv.getCompanyName());
			}
			
			for(UserMappingVendorAssigned umva: result.getUserMappingVendorAssigned())
			{
			    assertNotNull(umva.getAssigned());
			}
			
		}
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 10. 22.
	 * @author Reo
	 */
	@Test
	public void testGetUserMappingVendorCount() {
		GetUserMappingVendorParameter parameters = new GetUserMappingVendorParameter();
		parameters.setUserID(11);
		
		Integer result = securityGroupService.GetUserMappingVendorCount(parameters);
		if (result > 0) {
			assertNotNull(result);
			assertTrue(result > 0);
		}
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 10. 22.
	 * @author Reo
	 */
	@Test
	public void testSetSecurityUserActive() {
		GetSecurityUserParameter parameters = new GetSecurityUserParameter();
		parameters.setUserID(11);
		parameters.setActive(true);
		
		ResultCode result = securityGroupService.SetSecurityUserActive(parameters.getUserID(), parameters.getActive());
		if (result != null) {
			assertTrue(result.getSuccess());
		}
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 10. 22.
	 * @author Reo
	 */
	@Test
	public void testSetUserMappingVendor() {
		SetUserMappingVendorParameter parameters = new SetUserMappingVendorParameter();
		parameters.setUserID(11);
		parameters.setSaveWholeSalerIDs(null);
		parameters.setRemoveWholeSalerIDs(null);
		
		SetUserMappingVendorResponse result = securityGroupService.SetUserMappingVendor(parameters);
		if (result != null) {
			assertTrue(result.isSuccess());
		}
	}
	
	/**
     * 
     * Test GetSecurityGroupPermissions
     * 
     * @since 2018. 10. 22.
     * @author Incheol Jung
     */
	@Test
    public void testGetSecurityGroupPermissions() {
    	GetSecurityGroupPermissionsParameter param = new GetSecurityGroupPermissionsParameter();
    	param.setAppid(1);
    	param.setGroupid(1);
    	
    	GetSecurityGroupPermissionsResponse result = securityGroupService.GetSecurityGroupPermissions(param);
    	if(result != null) {
    		List<SecurityGroupPermissions> permissions = result.getSecurityGroupsPermissions();
    		if(!CollectionUtils.isEmpty(permissions)) {
    			assertNotNull(permissions.get(0).getMenuID());
    		}
    	}
    }
    
	/**
	 * 
	 * Test GetSecurityGroups
	 * 
	 * @since 2018. 10. 22.
	 * @author Incheol Jung
	 */
	@Test
    public void testGetSecurityGroups() {
		List<SecurityGroup> result  = securityGroupService.GetSecurityGroup();
		if(!CollectionUtils.isEmpty(result)) {
			assertNotNull(result.get(0).getGroupID());
		}
    }
	
	/**
	 * 
	 * Test setActiveGroup
	 * 
	 * @since 2018. 10. 22.
	 * @author Incheol Jung
	 */
	@Ignore
	@Test
	public void testsetActiveGroup() {
		SetActiveGroupParameter param = new SetActiveGroupParameter();
		param.setActive("1");
		param.setGroupID("17");
		
		ResultCode result = securityGroupService.setActiveGroup(param.getGroupID(), param.getActive());
		assertTrue(result.getResultCode().equals(0));
	}
	
	/**
	 * 
	 * Test SetSecurityGroup
	 * 
	 * @since 2018. 10. 22.
	 * @author Incheol Jung
	 */
	@Ignore
	@Test
	public void testSetSecurityGroup() {
		SetsecuritygroupParameter param = new SetsecuritygroupParameter();
		param.setApplyall(true);
		param.setDescription("Marketing Team");
		param.setGid(1);
		param.setGroupactive(true);
		param.setGroupname("Margeting Team");
		
		ResultCode result = securityGroupService.setSecurityGroup(param.getGid(), param.getGroupname(), param.getDescription(), param.getGroupactive());
		assertTrue(result.getResultCode().equals(0));
	}
	
	/**
	 * 
	 * Test SetDeleteSecurityGroups
	 * 
	 * @since 2018. 10. 22.
	 * @author Incheol Jung
	 * @throws IOException
	 */
	@Ignore
	@Test
	public void testSetDeleteSecurityGroups() throws IOException {
		SetdeletesecuritygroupsParameter param = new SetdeletesecuritygroupsParameter();
		param.setData("[17,18,19]");
		
		ResultCode result = securityGroupService.setdeletesecuritygroups(param.getData());
		assertTrue(result.getResultCode().equals(0) || result.getResultCode().equals(1));
	}
}