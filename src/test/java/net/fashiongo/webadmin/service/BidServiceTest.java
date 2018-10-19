package net.fashiongo.webadmin.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.fashiongo.webadmin.model.pojo.ResultCode;
import net.fashiongo.webadmin.model.pojo.parameter.GetBidSettingLastRecordsParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetBidSettingLastWeekParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetBidSettingParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetBidSettingParameter;
import net.fashiongo.webadmin.model.pojo.response.GetBidSettingLastRecordsResponse;
import net.fashiongo.webadmin.model.pojo.response.GetBidSettingLastWeekResponse;
import net.fashiongo.webadmin.model.pojo.response.GetBidSettingResponse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BidServiceTest {
	@Autowired
	BidService bidService;
	
	@Test
	public void testGetBidSettingLastRecords() {
		GetBidSettingLastRecordsParameter parameters = new GetBidSettingLastRecordsParameter();
		parameters.setSpotId(0);
		parameters.setMth("201610");
		parameters.setWeekDay(0);
		parameters.setFromDate("8/1/2018");
		parameters.setToDate("8/31/2018");
		
		GetBidSettingLastRecordsResponse _result = bidService.GetBidSettingLastRecords(parameters);
		assertNotNull(_result.getBidSettingLastRecords());
	}
	
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
		
		ResultCode _result = bidService.SetBidSetting(parameters);
		assertTrue(_result.getSuccess());
	}
	
	@Test
	public void testGetBidSetting() {
		GetBidSettingParameter parameters = new GetBidSettingParameter();
		parameters.setPageId(1);
		parameters.setSpotId(0);
		parameters.setYearMonth("201612");
		parameters.setWeekDay(0);
		parameters.setFromDate("10/28/2018");
		parameters.setToDate("11/03/2018");
		
		GetBidSettingResponse _result = bidService.GetBidSetting(parameters);
		assertNotNull(_result.getBidSetting());
	}
	
	@Test
	public void testGetBidSettingLastWeek() {
		GetBidSettingLastWeekParameter parameters = new GetBidSettingLastWeekParameter();
		parameters.setTop(120);
		
		GetBidSettingLastWeekResponse _result = bidService.GetBidSettingLastWeek(parameters.getTop());
		assertNotNull(_result.getBidSettingLastWeek());
	}
}
