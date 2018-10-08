package net.fashiongo.webadmin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.fashiongo.webadmin.model.pojo.parameter.GetBidSettingLastRecordsParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetBidSettingParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetBidSettingParameter;
import net.fashiongo.webadmin.model.pojo.response.GetBidSettingLastRecordsResponse;
import net.fashiongo.webadmin.model.pojo.response.GetBidSettingLastWeekResponse;
import net.fashiongo.webadmin.model.pojo.response.GetBidSettingResponse;
import net.fashiongo.webadmin.model.pojo.response.SetResultResponse;
import net.fashiongo.webadmin.service.BidService;
import net.fashiongo.webadmin.utility.JsonResponse;

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
	 */
	@RequestMapping(value = "getbidsettinglastrecords", method = RequestMethod.POST)
	public GetBidSettingLastRecordsResponse GetBidSettingLastRecords(
			@RequestBody GetBidSettingLastRecordsParameter parameters) {
		GetBidSettingLastRecordsResponse result = new GetBidSettingLastRecordsResponse();

		result = bidService.GetBidSettingLastRecords(parameters);
		return result;
	}

	/**
	 * Set BidSetting
	 * 
	 * @since 2018. 10. 04.
	 * @author Junghwan Lee
	 * @param parameters
	 * @return
	 */
	@RequestMapping(value = "setbidsetting", method = RequestMethod.POST)
	public SetResultResponse SetBidSetting(@RequestBody SetBidSettingParameter parameters) {
		SetResultResponse result = new SetResultResponse(false, 0, null);

		result = bidService.SetBidSetting(parameters);
		return result;

	}

	/**
	 * Get BidSetting
	 * 
	 * @since 2018. 10. 08.
	 * @author Junghwan Lee
	 * @param parameters
	 * @return
	 */
	@RequestMapping(value = "getbidsetting", method = RequestMethod.POST)
	public GetBidSettingResponse GetBidSetting(@RequestBody GetBidSettingParameter parameters) {
		GetBidSettingResponse result = new GetBidSettingResponse();

		result = bidService.GetBidSetting(parameters);
		return result;
	}

	/**
	 * Get BidSetting Last Week
	 * 
	 * @since 2018. 10. 08.
	 * @author Junghwan Lee
	 * @param top
	 * @return
	 */
	@RequestMapping(value = "getbidsettinglastweek", method = RequestMethod.POST)
	public JsonResponse<GetBidSettingLastWeekResponse> GetBidSettingLastWeek(@RequestBody Integer top) {
		JsonResponse<GetBidSettingLastWeekResponse> result = new JsonResponse<GetBidSettingLastWeekResponse>();

		GetBidSettingLastWeekResponse responseData = bidService.GetBidSettingLastWeek(top);
		result.setData(responseData);

		return result;
	}
}
