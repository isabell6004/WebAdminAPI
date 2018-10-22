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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import net.fashiongo.webadmin.model.pojo.ResultCode;
import net.fashiongo.webadmin.model.pojo.SecurityGroupPermissions;
import net.fashiongo.webadmin.model.pojo.parameter.GetSecurityAccessCodesParameters;
import net.fashiongo.webadmin.model.pojo.parameter.GetSecurityGroupPermissionsParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetSecurityLogsParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetSecurityResourcesParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetActiveGroupParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetDeleteSecurityAccessCodesParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetSecurityAccessCodeParameters;
import net.fashiongo.webadmin.model.pojo.parameter.SetSecurityAccessIpParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetSecurityResourceParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetdeletesecuritygroupsParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetsecuritygroupParameter;
import net.fashiongo.webadmin.model.pojo.response.GetSecurityAccessCodesResponse;
import net.fashiongo.webadmin.model.pojo.response.GetSecurityAccessIpsResponse;
import net.fashiongo.webadmin.model.pojo.response.GetSecurityGroupPermissionsResponse;
import net.fashiongo.webadmin.model.pojo.response.GetSecurityLogsResponse;
import net.fashiongo.webadmin.model.pojo.response.GetSecurityResourcesResponse;
import net.fashiongo.webadmin.model.primary.SecurityGroup;
import net.fashiongo.webadmin.utility.JsonResponse;


/**
 * 
 * @author DAHYE
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminServiceTest {

	@Autowired
	AdminService adminService;
	
	@Autowired
	SecurityGroupService securityGroupService;
	
	@Test
	public void testGetSecurityResources() {
		GetSecurityResourcesParameter parameters = new GetSecurityResourcesParameter();
		parameters.setApplication("Web Admin");
		parameters.setResourceName(null);
		parameters.setResourceParent("All");
		parameters.setResourceType("All");
		
		GetSecurityResourcesResponse result = adminService.GetSecurityResources(parameters);
		assertNotNull(result.getResource());
	}

	@Test
	public void testGetSecurityAccessIps() {
		GetSecurityAccessIpsResponse result = adminService.GetSecurityAccessIps();
		assertNotNull(result.getIps());
	}

	@Test
	@Ignore("Not yet implemented")
	public void testSetSecurityAccessIp() throws Exception {
		SetSecurityAccessIpParameter parameters = new SetSecurityAccessIpParameter();
		parameters.setIpid(18);
		parameters.setIp("103.243.200.12");
		parameters.setDescription("Seoul");
		parameters.setOffice(null);
		
		ResultCode result = adminService.SetSecurityAccessIp(parameters);
		assertTrue(result.getSuccess());
	}

/*	@Test
	public void testSetDeleteSecurityAccessIps() {
		List<Integer> idList = new ArrayList();
		idList.add(3);
		idList.add(5);
		
		ResultCode result = adminService.SetDeleteSecurityAccessIps(idList);
		assertTrue(result.getSuccess());
	}*/

	@Test
	@Ignore("Not yet implemented")
	public void testSetResource() {
		Integer resourceID = 161;
		boolean active = false;
		
		ResultCode result = adminService.SetResource(resourceID, active);
		assertTrue(result.getSuccess());
	}

	@Test
	@Ignore("Not yet implemented")
	public void testSetSecurityResource() {
		SetSecurityResourceParameter parameters = new SetSecurityResourceParameter();
		parameters.setActive(true);
		parameters.setApplicationid(1);
		parameters.setResourceID(113);
		parameters.setResourceName("User");
		parameters.setResourceType("Page");
		parameters.setResourceUrl("url");
		
		ResultCode result = adminService.SetSecurityResource(parameters);
		assertTrue(result.getSuccess());
	}

/*	@Test
	public void testSetDeleteSecurityResources() {
		List<Integer> idList = new ArrayList();
		
		ResultCode result = adminService.SetDeleteSecurityResources(idList);
		assertTrue(result.getSuccess());
	}*/

	@Test
	public void testGetSecurityAccessCodes() {
		GetSecurityAccessCodesParameters parameters = new GetSecurityAccessCodesParameters();
		parameters.setAccessCode(" ");
		parameters.setsDate("2015-01-01");
		parameters.seteDate("2020-01-01");
		
		GetSecurityAccessCodesResponse _result = adminService.GetSecurityAccessCodes(parameters);
		
		assertNotNull(_result.getSecurityAccessCodes());
	}
	
	@Test
	public void testSetSecurityAccessCode() {
		SetSecurityAccessCodeParameters parameters = new SetSecurityAccessCodeParameters();
		parameters.setCodeID(0);
		parameters.setAccessCode("hgfhgf");
		parameters.setExpiredOn("09/23/2019");
		
		//ResultCode _result = adminService.SetSecurityAccessCode(parameters);
		//assertTrue(_result.getSuccess());
	}
	
	@Test
	public void testSetDeleteSecurityAccessCodes() {
		SetDeleteSecurityAccessCodesParameter parameters = new SetDeleteSecurityAccessCodesParameter();
		List<Integer> idList= new ArrayList<Integer>();
		idList.add(3);
		idList.add(4);
		parameters.setIdList(idList);
		
		//ResultCode _result = adminService.SetDeleteSecurityAccessCodes(parameters.getIdList());
		//assertTrue(_result.getSuccess());
	}

	@Test
	public void testGetSecurityLogs() {
		GetSecurityLogsParameter parameters = new GetSecurityLogsParameter();
		parameters.setPagenum(1);
		parameters.setPagesize(30);
		parameters.setUsrid(1);
		parameters.setIp(null);
		parameters.setSdate("10/01/2018");
		parameters.setEdate("10/31/2018");

		GetSecurityLogsResponse result = adminService.getSecuritylogs(parameters);
		assertNotNull(result.getSecurityLogs());
		assertNotNull(result.getSecurityLogsColumn());
	}
    
    
    
    
}