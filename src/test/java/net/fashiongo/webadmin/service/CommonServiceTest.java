package net.fashiongo.webadmin.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.fashiongo.webadmin.model.pojo.parameter.GetBidAdPageSpotsParameter;
import net.fashiongo.webadmin.model.pojo.response.GetBidAdPagesResponse;
import net.fashiongo.webadmin.model.pojo.response.GetCountryStatesResponse;
import net.fashiongo.webadmin.model.primary.AdPageSpot;
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
	
	@Test
	public void testGetMenuID() {
		String pageName = "vendor-list";
		Integer result = commonService.GetMenuID(pageName);
		assertEquals(result.toString(), "143");
	}

	@Test
	public void testGetServerHeartBeat() {
		Long q = 1539669765324l;
		String result = commonService.GetServerHeartBeat(q);
		assertEquals(result.toString(), "Spring Boot");
	}

	@Test
	public void testGetCountryStates() {
		String countryAbbrev = "us";
		JsonResponse<GetCountryStatesResponse> result = commonService.GetCountryStates(countryAbbrev);
		assertNotNull(result.getData());	
	}

	@Test
	public void testGetTopCategories() {
		List<TopCategories> result = commonService.GetTopCategories();
		assertNotNull(result);	
	}
	
	@Test
	public void testGetBidAdPages() {
		GetBidAdPagesResponse result = commonService.GetBidAdPages();
		assertNotNull(result.getAdPage());
	}
	
	@Test
	public void testGetBidAdPageSpots() {
		GetBidAdPageSpotsParameter parameter = new GetBidAdPageSpotsParameter();
		parameter.setPageId(1);
		
		List<AdPageSpot> result = commonService.GetBidAdPageSpots(parameter.getPageId());
		assertNotNull(result);
	}
	
	@Deprecated
	@Test
	public void testGetBidAdPageSpotsCombined() {
		
	}
}
