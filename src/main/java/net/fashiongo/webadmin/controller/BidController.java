package net.fashiongo.webadmin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.fashiongo.webadmin.model.pojo.parameter.GetBidSettingLastRecordsParameter;
import net.fashiongo.webadmin.model.pojo.response.GetBidSettingLastRecordsResponse;
import net.fashiongo.webadmin.service.BidService;

/**
 * 
 * @author JungHwan
 */
@RestController
@RequestMapping(value = "/bid", produces = "application/json")
public class BidController {
	@Autowired
	BidService bidService;

	/**
	 * Get BidSetting LastRecords
	 * 
	 * @since 2018. 10. 03.
	 * @author Junghwan Lee
	 * @param parameters
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getbidsettinglastrecords", method = RequestMethod.POST)
	public GetBidSettingLastRecordsResponse GetBidSettingLastRecords(@RequestBody GetBidSettingLastRecordsParameter parameters) {
		GetBidSettingLastRecordsResponse result = bidService.GetBidSettingLastRecords(parameters);
		return result;
	}
	
	@RequestMapping(value = "setbidsetting", method = RequestMethod.POST)
	public void SetBidSetting() {
		
	}
	
	@RequestMapping(value = "getbidsetting", method = RequestMethod.POST)
	public void GetBidSetting() {
		
	}
	
	@RequestMapping(value = "getbidsettinglastweek", method = RequestMethod.POST)
	public void GetBidSettingLastWeek() {
		
	}
}
