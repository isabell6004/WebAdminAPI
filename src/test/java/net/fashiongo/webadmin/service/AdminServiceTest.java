package net.fashiongo.webadmin.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.fashiongo.webadmin.model.pojo.admin.parameter.GetSecurityAccessCodesParameters;
import net.fashiongo.webadmin.model.pojo.admin.parameter.GetSecurityLogsParameter;
import net.fashiongo.webadmin.model.pojo.admin.parameter.GetSecurityMenus2Parameter;
import net.fashiongo.webadmin.model.pojo.admin.parameter.GetSecurityResourcesParameter;
import net.fashiongo.webadmin.model.pojo.admin.parameter.SetActiveSecurityMenusParameter;
import net.fashiongo.webadmin.model.pojo.admin.parameter.SetDeleteSecurityAccessCodesParameter;
import net.fashiongo.webadmin.model.pojo.admin.parameter.SetDeleteSecurityMenusParameter;
import net.fashiongo.webadmin.model.pojo.admin.parameter.SetSecurityAccessCodeParameters;
import net.fashiongo.webadmin.model.pojo.admin.parameter.SetSecurityAccessIpParameter;
import net.fashiongo.webadmin.model.pojo.admin.parameter.SetSecurityMenuParameter;
import net.fashiongo.webadmin.model.pojo.admin.parameter.SetSecurityResourceParameter;
import net.fashiongo.webadmin.model.pojo.admin.response.GetSecurityAccessCodesResponse;
import net.fashiongo.webadmin.model.pojo.admin.response.GetSecurityAccessIpsResponse;
import net.fashiongo.webadmin.model.pojo.admin.response.GetSecurityLogsResponse;
import net.fashiongo.webadmin.model.pojo.admin.response.GetSecurityMenus2Response;
import net.fashiongo.webadmin.model.pojo.admin.response.GetSecurityParentMenusResponse;
import net.fashiongo.webadmin.model.pojo.admin.response.GetSecurityResourcesResponse;
import net.fashiongo.webadmin.model.pojo.common.ResultCode;


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
	
	/**
     * 
     * testGetSecurityResources
     * 
     * @since 2018. 10. 18.
     * @author Dahye
     */
	@Test
	public void testGetSecurityResources() {
		GetSecurityResourcesParameter parameters = new GetSecurityResourcesParameter();
		parameters.setApplication("Web Admin");
		parameters.setResourceName(null);
		parameters.setResourceParent("All");
		parameters.setResourceType("All");
		
		GetSecurityResourcesResponse result = adminService.getSecurityResources(parameters);
		assertNotNull(result.getResource());
	}

	/**
     * 
     * testGetSecurityAccessIps
     * 
     * @since 2018. 10. 18.
     * @author Dahye
     */
	@Test
	public void testGetSecurityAccessIps() {
		GetSecurityAccessIpsResponse result = adminService.getSecurityAccessIps();
		assertNotNull(result.getIps());
	}

	/**
     * 
     * testSetSecurityAccessIp
     * 
     * @since 2018. 10. 18.
     * @author Dahye
     */
	@Test
	@Ignore
	public void testSetSecurityAccessIp() throws Exception {
		SetSecurityAccessIpParameter parameters = new SetSecurityAccessIpParameter();
		parameters.setIpid(18);
		parameters.setIp("103.243.200.12");
		parameters.setDescription("Seoul");
		parameters.setOffice(null);
		
		ResultCode result = adminService.setSecurityAccessIp(parameters);
		assertTrue(result.getSuccess());
	}

	/**
     * 
     * testSetDeleteSecurityAccessIps
     * 
     * @since 2018. 10. 18.
     * @author Dahye
     */
	@Test
	@Ignore
	public void testSetDeleteSecurityAccessIps() {
		List<Integer> idList = new ArrayList();
		idList.add(3);
		idList.add(5);
		
		ResultCode result = adminService.setDeleteSecurityAccessIps(idList);
		assertTrue(result.getSuccess());
	}
	
	/**
     * 
     * testSetResource
     * 
     * @since 2018. 10. 18.
     * @author Dahye
     */
	@Test
	@Ignore
	public void testSetResource() {
		Integer resourceID = 161;
		boolean active = false;
		
		ResultCode result = adminService.setResource(resourceID, active);
		assertTrue(result.getSuccess());
	}

	/**
     * 
     * testSetSecurityResource
     * 
     * @since 2018. 10. 18.
     * @author Dahye
     */
	@Test
	@Ignore
	public void testSetSecurityResource() {
		SetSecurityResourceParameter parameters = new SetSecurityResourceParameter();
		parameters.setActive(true);
		parameters.setApplicationid(1);
		parameters.setResourceID(113);
		parameters.setResourceName("User");
		parameters.setResourceType("Page");
		parameters.setResourceUrl("url");
		
		ResultCode result = adminService.setSecurityResource(parameters);
		assertTrue(result.getSuccess());
	}

	/**
     * 
     * testSetDeleteSecurityResources
     * 
     * @since 2018. 10. 18.
     * @author Dahye
     */
	@Test
	@Ignore
	public void testSetDeleteSecurityResources() {
		List<Integer> idList = new ArrayList();
		
		ResultCode result = adminService.setDeleteSecurityResources(idList);
		assertTrue(result.getSuccess());
	}

	/**
	 * 
	 * Get Security Access Code
	 * 
	 * @since 2018. 10. 22.
	 * @author Junghwan Lee
	 */
	@Test
	public void testGetSecurityAccessCodes() {
		GetSecurityAccessCodesParameters parameters = new GetSecurityAccessCodesParameters();
		parameters.setAccessCode(" ");
		parameters.setsDate("2015-01-01");
		parameters.seteDate("2020-01-01");
		
		GetSecurityAccessCodesResponse _result = adminService.getSecurityAccessCodes(parameters);
		
		assertNotNull(_result.getSecurityAccessCodes());
	}
	
	/**
	 * 
	 * Set Security Access Code
	 * 
	 * @since 2018. 10. 22.
	 * @author Junghwan Lee
	 */
	@Ignore
	@Test
	public void testSetSecurityAccessCode() {
		SetSecurityAccessCodeParameters parameters = new SetSecurityAccessCodeParameters();
		parameters.setCodeID(0);
		parameters.setAccessCode("hgfhgf");
		parameters.setExpiredOn("09/23/2019");
		
		//ResultCode _result = adminService.SetSecurityAccessCode(parameters);
		//assertTrue(_result.getSuccess());
	}
	
	/**
	 * 
	 * Delete Security Access Code
	 * 
	 * @since 2018. 10. 22.
	 * @author Junghwan Lee
	 */
	@Ignore
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

	/**
	 * 
	 * testGetSecurityLogs
	 * 
	 * @since 2018. 10. 19.
	 * @author Nayeon Kim
	 */
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
	
	
	/**
     * 
     * testGetSecurityParentMenus
     * 
     * @since 2018. 10. 22.
     * @author Jiwon
     */
    @Test
	public void testGetSecurityParentMenus() {
    	GetSecurityParentMenusResponse result = adminService.GetSecurityParentMenus();
    	assertNotNull(result);
	}
    
    /**
     * 
     * testGetSecurityMenus2
     * 
     * @since 2018. 10. 22.
     * @author Jiwon
     */
    @Test
	public void testGetSecurityMenus2() {
    	GetSecurityMenus2Parameter parameters = new GetSecurityMenus2Parameter();
        parameters.setMenuname("");
        parameters.setParentmenuid("0");
        parameters.setApplicationid("0");
        parameters.setActive(1);
        
    	GetSecurityMenus2Response result = adminService.GetSecurityMenus2(parameters);
    	assertNotNull(result);
	}
    
    /**
     * 
     * testSetSecurityMenu
     * 
     * @since 2018. 10. 22.
     * @author Jiwon
     */
    @Test
	@Ignore
	public void testSetSecurityMenu() {
    	SetSecurityMenuParameter parameters = new SetSecurityMenuParameter();
        parameters.setMenuid(0);
        parameters.setParentid(1);
        parameters.setResourceid(2);
        parameters.setApplicationid(3);
        parameters.setMenuname("menutest");
        parameters.setRoutepath("RoutePath Test");
        parameters.setMenuicon("menuicon Test");
        parameters.setListorder(0);
        parameters.setVisible(true);
        parameters.setActive(true);
        
        ResultCode result = adminService.setSecurityMenu(parameters);
        assertTrue(result.getSuccess());
	}
    
    
    /**
     * 
     * testSetDeleteSecurityMenus
     * 
     * @since 2018. 10. 22.
     * @author Jiwon
     */
    @Test
	@Ignore
	public void testSetDeleteSecurityMenus() {
    	SetDeleteSecurityMenusParameter parameters = new SetDeleteSecurityMenusParameter();
    	String data = "";
    	List<Integer> idList= new ArrayList<Integer>();
    	idList.add(3);
    	idList.add(4);
    	parameters.setIdList(idList);
        
        ResultCode result = adminService.SetDeleteSecurityMenus(parameters);
        assertTrue(result.getSuccess());
	}
    
    
    
    /**
     * 
     * testSetActiveSecurityMenus
     * 
     * @since 2018. 10. 22.
     * @author Jiwon
     */
    @Test
	@Ignore
	public void testSetActiveSecurityMenus() {
    	SetActiveSecurityMenusParameter parameters = new SetActiveSecurityMenusParameter();
    	parameters.setMenuID(1);
    	parameters.setActive(true);
    	
        ResultCode result = adminService.SetActiveSecurityMenus(parameters);
        assertTrue(result.getSuccess());
	}
    
    
    
    
}