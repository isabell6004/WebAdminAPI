package net.fashiongo.webadmin.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.fashiongo.webadmin.model.pojo.admin.MapUserGroup;
import net.fashiongo.webadmin.model.pojo.admin.SecurityGroupPermissions;
import net.fashiongo.webadmin.model.pojo.admin.SecurityUserData;
import net.fashiongo.webadmin.model.pojo.admin.SecurityUserPermission;
import net.fashiongo.webadmin.model.pojo.admin.SecurityUsers;
import net.fashiongo.webadmin.model.pojo.admin.UserMappingVendor;
import net.fashiongo.webadmin.model.pojo.admin.UserMappingVendorAssigned;
import net.fashiongo.webadmin.model.pojo.admin.parameter.DelSecurityUserParameter;
import net.fashiongo.webadmin.model.pojo.admin.parameter.GetSecurityGroupPermissionsParameter;
import net.fashiongo.webadmin.model.pojo.admin.parameter.GetSecurityUserGroupParameter;
import net.fashiongo.webadmin.model.pojo.admin.parameter.GetSecurityUserParameter;
import net.fashiongo.webadmin.model.pojo.admin.parameter.GetUserMappingVendorParameter;
import net.fashiongo.webadmin.model.pojo.admin.parameter.SetActiveGroupParameter;
import net.fashiongo.webadmin.model.pojo.admin.parameter.SetSecurityUserParameter;
import net.fashiongo.webadmin.model.pojo.admin.parameter.SetUserMappingVendorParameter;
import net.fashiongo.webadmin.model.pojo.admin.parameter.SetdeletesecuritygroupsParameter;
import net.fashiongo.webadmin.model.pojo.admin.parameter.SetsecuritygroupParameter;
import net.fashiongo.webadmin.model.pojo.admin.response.GetSecurityGroupPermissionsResponse;
import net.fashiongo.webadmin.model.pojo.admin.response.GetSecurityUserGroupAccesstimeResponse;
import net.fashiongo.webadmin.model.pojo.admin.response.GetSecurityUserResponse;
import net.fashiongo.webadmin.model.pojo.admin.response.GetUserMappingVendorResponse;
import net.fashiongo.webadmin.model.pojo.admin.response.SetCreateSecurityUserResponse;
import net.fashiongo.webadmin.model.pojo.admin.response.SetUserMappingVendorResponse;
import net.fashiongo.webadmin.model.pojo.common.ResultCode;
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
	public void testGetSecurityUsers() {
		GetSecurityUserParameter parameters = new GetSecurityUserParameter();
		parameters.setUserName("niki");
		parameters.setGroup(35);
		parameters.setRole("G");
		parameters.setVendorID(3064);
		
		GetSecurityUserResponse result = securityGroupService.getSecurityUsers(parameters);
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
	public void testGetUserMappingVendorCount() {
		GetUserMappingVendorParameter parameters = new GetUserMappingVendorParameter();
		parameters.setUserID(11);
		
		Integer result = securityGroupService.getUserMappingVendorCount(parameters);
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
		
		ResultCode result = securityGroupService.setSecurityUserActive(parameters.getUserID(), parameters.getActive());
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
		
		SetUserMappingVendorResponse result = securityGroupService.setUserMappingVendor(parameters);
		if (result != null) {
			assertTrue(result.isSuccess());
		}
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 10. 22.
	 * @author Reo
	 */
	@Ignore
	@Test
	public void testSetDelSecurityUsers() {
		DelSecurityUserParameter parameters = new DelSecurityUserParameter();
		List<Integer> userList = new ArrayList<Integer>();
		userList.add(0,  900);
		parameters.setUserList(userList);
        ResultCode result = new ResultCode(false, 0, null);
		
		result = securityGroupService.setDelSecurityUsers(parameters);
		
		if (result != null) {
			assertTrue(result.getSuccess());
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
		List<SecurityGroup> result  = securityGroupService.getSecurityGroup();
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
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 10. 31.
	 * @author Reo
	 * @throws ParseException
	 * @throws org.json.simple.parser.ParseException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	@Ignore
	@Test
	public void testsetCreateSecurityUsers() throws ParseException, FileNotFoundException, IOException, org.json.simple.parser.ParseException
	{
		SetSecurityUserParameter parameters = new SetSecurityUserParameter();
		
		JSONParser parser = new JSONParser();
		String path = SecurityGroupServiceTest.class.getResource("").getPath();
		Object obj = parser.parse(new FileReader(path + "SecurityGroupJson.json"));  //read user infomation from json file
		JSONObject jsonObj = (JSONObject) obj;
		
		//convert json string to model
		ObjectMapper mapper = new ObjectMapper();
		mapper.findAndRegisterModules();
		SecurityUserData sud = mapper.convertValue(jsonObj.get("data"),  SecurityUserData.class);
		List<SecurityUserPermission> lsud = Arrays.asList(mapper.convertValue(jsonObj.get("updata"), SecurityUserPermission[].class));
		
		parameters.setData(sud);
		parameters.setUpdata(lsud);
		SetCreateSecurityUserResponse result = securityGroupService.setCreateSecurityUser(parameters);
		if (result != null) {
			assertTrue(result.getResultCode().getSuccess());
			assertTrue(result.getResultCode2().getSuccess());
		}
	}
}
