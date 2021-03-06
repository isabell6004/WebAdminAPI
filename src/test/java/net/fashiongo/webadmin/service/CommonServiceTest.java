package net.fashiongo.webadmin.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.fashiongo.webadmin.model.pojo.admin.parameter.GetSecurityResourcesParameter;
import net.fashiongo.webadmin.model.pojo.admin.response.GetSecurityResourcesResponse;
import net.fashiongo.webadmin.model.pojo.common.parameter.GetBidAdPageSpotsParameter;
import net.fashiongo.webadmin.model.pojo.common.response.GetBidAdPagesResponse;
import net.fashiongo.webadmin.model.pojo.common.response.GetCountryStatesResponse;
import net.fashiongo.webadmin.model.primary.AdPageSpot;
import net.fashiongo.webadmin.model.primary.SecurityUser;
import net.fashiongo.webadmin.model.primary.TopCategories;
import net.fashiongo.webadmin.utility.JsonResponse;
/**
 * 
 * @author DAHYE
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CommonServiceTest {

	@Autowired
	CommonService commonService;
	
	@Autowired
	AdminService adminService;
	
	/**
     * 
     * testGetMenuID
     * 
     * @since 2018. 10. 18.
     * @author Dahye
     */
	@Test
	public void testGetMenuID() {
		String pageName = "vendor-list";
		Integer result = commonService.getMenuID(pageName);
		assertEquals(result.toString(), "143");
	}

	/**
     * 
     * testGetServerHeartBeat
     * 
     * @since 2018. 10. 18.
     * @author Dahye
     */
	@Test
	public void testGetServerHeartBeat() {
		Long q = 1539669765324l;
		String result = commonService.getServerHeartBeat(q);
		assertEquals(result.toString(), "Spring Boot");
	}

	/**
     * 
     * testGetCountryStates
     * 
     * @since 2018. 10. 18.
     * @author Dahye
     */
	@Test
	public void testGetCountryStates() {
		String countryAbbrev = "us";
		JsonResponse<GetCountryStatesResponse> result = commonService.getCountryStates(countryAbbrev);
		assertNotNull(result.getData());	
	}

	/**
     * 
     * testGetTopCategories
     * 
     * @since 2018. 10. 18.
     * @author Dahye
     */
	@Test
	public void testGetTopCategories() {
		List<TopCategories> result = commonService.getTopCategories();
		assertNotNull(result);	
	}

	/**
	 * 
	 * Get Bid AdPage Spots
	 * 
	 * @since 2018. 10. 22.
	 * @author Junghwan Lee
	 */
	@Test
	public void testGetBidAdPageSpots() {
		GetBidAdPageSpotsParameter parameter = new GetBidAdPageSpotsParameter();
		parameter.setPageId(1);
		
		List<AdPageSpot> result = commonService.getBidAdPageSpots(parameter.getPageId());
		assertNotNull(result);
	}
	
	/**
	 * 
	 * Get Bid AdPage Spots Combined 
	 * 
	 * @since 2018. 10. 22.
	 * @author Junghwan Lee
	 */
	@Deprecated
	@Test
	public void testGetBidAdPageSpotsCombined() {
		
	}

	/**
	 * 
	 * testGetSecurityUser
	 * 
	 * @since 2018. 10. 19.
	 * @author Nayeon Kim
	 */
	@Test
	public void testGetSecurityUser() {
		List<SecurityUser> result = commonService.getSecurityUser();
		assertNotNull(result);	
	}
}
