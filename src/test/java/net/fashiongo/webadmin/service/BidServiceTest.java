package net.fashiongo.webadmin.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import net.fashiongo.webadmin.model.primary.AdBid;
import net.fashiongo.webadmin.model.primary.AdBidSetting;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.fashiongo.webadmin.model.pojo.bid.parameter.GetBidSettingLastRecordsParameter;
import net.fashiongo.webadmin.model.pojo.bid.parameter.GetBidSettingLastWeekParameter;
import net.fashiongo.webadmin.model.pojo.bid.parameter.GetBidSettingParameter;
import net.fashiongo.webadmin.model.pojo.bid.parameter.SetBidSettingParameter;
import net.fashiongo.webadmin.model.pojo.bid.response.GetBidSettingLastRecordsResponse;
import net.fashiongo.webadmin.model.pojo.bid.response.GetBidSettingLastWeekResponse;
import net.fashiongo.webadmin.model.pojo.bid.response.GetBidSettingResponse;
import net.fashiongo.webadmin.model.pojo.common.ResultCode;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BidServiceTest {
	@Autowired
	BidService bidService;
	
	/**
	 * 
	 * Get BidSetting LastRecords
	 * 
	 * @since 2018. 10. 22.
	 * @author Junghwan Lee
	 */
	@Test
	public void testGetBidSettingLastRecords() {
		GetBidSettingLastRecordsParameter parameters = new GetBidSettingLastRecordsParameter();
		parameters.setSpotId(0);
		parameters.setMth("201610");
		parameters.setWeekDay(0);
		parameters.setFromDate("8/1/2018");
		parameters.setToDate("8/31/2018");
		
		GetBidSettingLastRecordsResponse _result = bidService.getBidSettingLastRecords(parameters);
		assertNotNull(_result.getBidSettingLastRecords());
	}
	
	/**
	 * 
	 * Set BidSetting
	 * 
	 * @since 2018. 10. 22.
	 * @author Junghwan Lee
	 */
	@Ignore
	@Test
	public void testSetBidSetting() {
		SetBidSettingParameter parameters = new SetBidSettingParameter();
		parameters.setDataFlag(" ");
		parameters.setFromData("11/02/2018,11/03/2018");
		parameters.setSpotId("82,82");
		parameters.setStartedOn("08/13/2018 09:00,08/06/2018 09:00");
		parameters.setEndedOn("08/17/2018 14:20,08/10/2018 14:25");
		parameters.setStartingPrice("40,40");
		parameters.setPriceUnit("3,3");
		parameters.setBuyItNowPrice(",");
		
		ResultCode _result = bidService.setBidSetting(parameters);
		assertTrue(_result.getSuccess());
	}
	
	/**
	 * 
	 * Get BidSetting
	 * 
	 * @since 2018. 10. 22.
	 * @author Junghwan Lee
	 */
	@Test
	public void testGetBidSetting() {
		GetBidSettingParameter parameters = new GetBidSettingParameter();
		parameters.setPageId(1);
		parameters.setSpotId(0);
		parameters.setYearMonth("201612");
		parameters.setWeekDay(0);
		parameters.setFromDate("10/28/2018");
		parameters.setToDate("11/03/2018");
		
		GetBidSettingResponse _result = bidService.getBidSetting(parameters);
		assertNotNull(_result.getBidSetting());
	}
	
	/**
	 * 
	 * Get BidSetting Last Week
	 * 
	 * @since 2018. 10. 22.
	 * @author Junghwan Lee
	 */
	@Test
	public void testGetBidSettingLastWeek() {
		GetBidSettingLastWeekParameter parameters = new GetBidSettingLastWeekParameter();
		parameters.setTop(120);
		
		GetBidSettingLastWeekResponse _result = bidService.getBidSettingLastWeek(parameters.getTop());
		assertNotNull(_result.getBidSettingLastWeek());
	}
	
	@Test
	public void testAcceptBid() {
		List<AdBidSetting> adBidSettingList = bidService.getFinalizeAdBidSettingTargetList();
		for (AdBidSetting adBidSetting : adBidSettingList) {
			ResultCode resultCode = bidService.acceptBid(adBidSetting.getBidSettingId());
			assertEquals(Integer.valueOf(1), resultCode.getResultCode());
		}
	}
	
	@Test
	public void testEditBids() {
		ResultCode resultCode = new ResultCode(true, 1, "SUCCESS");
		try {
			resultCode = bidService.editBid("124", "20190410", "86093,86088,86090,86092,86087", "developer");
		} catch (Exception e) {
			e.printStackTrace();
			resultCode = new ResultCode(true, -1, "FAILURE");
		}
		assertEquals(Integer.valueOf(1), resultCode.getResultCode());
	}
}
