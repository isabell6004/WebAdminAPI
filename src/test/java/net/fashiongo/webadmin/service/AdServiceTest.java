package net.fashiongo.webadmin.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.fashiongo.webadmin.model.pojo.ad.parameter.SetAddPageParameter;
import net.fashiongo.webadmin.model.pojo.ad.parameter.SetAddSpotSettingParameter;
import net.fashiongo.webadmin.model.pojo.ad.response.GetADSettingResponse;
import net.fashiongo.webadmin.model.pojo.ad.response.GetSpotCheckResponse;
import net.fashiongo.webadmin.model.pojo.common.ResultCode;
import net.fashiongo.webadmin.model.primary.CodeBodySize;

/**
 * @author Nayeon Kim
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AdServiceTest {

	@Autowired
	AdService adService;

	/**
	 * 
	 * testGetAdsetting
	 * 
	 * @since 2018. 10. 19.
	 * @author Nayeon Kim
	 */
	@Test
	public void testGetAdsetting() {
		GetADSettingResponse result = adService.getAdsetting();
		assertNotNull(result.getAdSettingSubList());
		assertNotNull(result.getAdSettingList());
	}
	
	/**
	 * 
	 * testSetAdPage
	 * 
	 * @since 2018. 10. 19.
	 * @author Nayeon Kim
	 */
	@Ignore
	@Test
	public void testSetAdPage() {
		SetAddPageParameter parameters = new SetAddPageParameter();
		
		// insert
		parameters.setPageID(null);
		parameters.setPageName("Test insert");
		ResultCode result = adService.setAdPage(parameters);
		assertTrue(result.getSuccess());
		
		// update
		parameters.setPageID("1");
		parameters.setPageName("Test update");
		ResultCode result2 = adService.setAdPage(parameters);
		assertTrue(result2.getSuccess());
	}

	/**
	 * 
	 * testGetBodySizeCode
	 * 
	 * @since 2018. 10. 19.
	 * @author Nayeon Kim
	 */
	@Test
	public void testGetBodySizeCode() {
		List<CodeBodySize> result = adService.getBodySizeCode();
		assertNotNull(result);	
	}

	/**
	 * 
	 * testGetSpotCheck
	 * 
	 * @since 2018. 10. 19.
	 * @author Nayeon Kim
	 */
	@Test
	public void testGetSpotCheck() {
		Integer spotID = 10;
		GetSpotCheckResponse result = adService.getSpotCheck(spotID);
		assertNotNull(result.getSpotID());
	}

	/**
	 * 
	 * testDelSpotSetting
	 * 
	 * @since 2018. 10. 19.
	 * @author Nayeon Kim
	 */
	@Ignore
	@Test
	public void testDelSpotSetting() {
		Integer spotID = 81;
		ResultCode result = adService.delSpotSetting(spotID);
		assertTrue(result.getSuccess());
	}
	
	/**
	 * 
	 * testSetAddSpotSetting
	 * 
	 * @since 2018. 10. 19.
	 * @author Nayeon Kim
	 */
	@Ignore
	@Test
	public void testSetAddSpotSetting() {
		SetAddSpotSettingParameter parameters = new SetAddSpotSettingParameter();
		
		// insert
		parameters.setPageID("102");
		parameters.setCategoryID(null);
		parameters.setBodySizeID(null);
		parameters.setSpotName("Test insert");
		parameters.setPrice1(null);
		parameters.setPrice2(null);
		parameters.setPrice3(null);
		parameters.setPrice4(null);
		parameters.setPrice5(null);
		parameters.setPrice6(null);
		parameters.setPrice7(null);
		parameters.setActive(true);
		parameters.setIncludeVendorCategory(true);
		parameters.setSpotInstanceCount("1");
		parameters.setMaxPurchasable("1");
		parameters.setSpotItemCount("5");
		parameters.setBidEffectiveOn2(null);
		ResultCode result = adService.setAddSpotSetting(parameters);
		assertTrue(result.getSuccess());
		
		// update
		parameters.setSpotID("1");
		parameters.setPageID("3");
		parameters.setCategoryID(null);
		parameters.setBodySizeID(null);
		parameters.setSpotName("Test update");
		parameters.setPrice1(null);
		parameters.setPrice2(null);
		parameters.setPrice3(null);
		parameters.setPrice4(null);
		parameters.setPrice5(null);
		parameters.setPrice6(null);
		parameters.setPrice7(null);
		parameters.setActive(true);
		parameters.setIncludeVendorCategory(true);
		parameters.setSpotInstanceCount("3");
		parameters.setMaxPurchasable("1");
		parameters.setSpotItemCount("5");
		parameters.setBidEffectiveOn2(null);
		ResultCode result2 = adService.setAddSpotSetting(parameters);
		assertTrue(result2.getSuccess());
	}
}
