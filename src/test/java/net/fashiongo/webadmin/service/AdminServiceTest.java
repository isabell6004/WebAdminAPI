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
