package net.fashiongo.webadmin.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.fashiongo.webadmin.model.pojo.ResultCode;
import net.fashiongo.webadmin.model.pojo.parameter.GetSecurityResourcesParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetSecurityAccessIpParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetSecurityResourceParameter;
import net.fashiongo.webadmin.model.pojo.response.GetSecurityAccessIpsResponse;
import net.fashiongo.webadmin.model.pojo.response.GetSecurityResourcesResponse;
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
	public void testSetResource() {
		Integer resourceID = 161;
		boolean active = false;
		
		JsonResponse result = adminService.SetResource(resourceID, active);
		assertTrue(result.isSuccess());
	}

	@Test
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

}
