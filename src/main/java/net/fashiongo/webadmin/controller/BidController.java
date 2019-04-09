package net.fashiongo.webadmin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.fashiongo.webadmin.model.pojo.bid.parameter.GetBidSettingLastRecordsParameter;
import net.fashiongo.webadmin.model.pojo.bid.parameter.GetBidSettingLastWeekParameter;
import net.fashiongo.webadmin.model.pojo.bid.parameter.GetBidSettingParameter;
import net.fashiongo.webadmin.model.pojo.bid.parameter.SetBidSettingParameter;
import net.fashiongo.webadmin.model.pojo.bid.response.GetBidSettingLastRecordsResponse;
import net.fashiongo.webadmin.model.pojo.bid.response.GetBidSettingLastWeekResponse;
import net.fashiongo.webadmin.model.pojo.bid.response.GetBidSettingResponse;
import net.fashiongo.webadmin.model.pojo.common.ResultCode;
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
	public JsonResponse<GetBidSettingLastRecordsResponse> getBidSettingLastRecords(@RequestBody GetBidSettingLastRecordsParameter parameters) {
		JsonResponse<GetBidSettingLastRecordsResponse> result = new JsonResponse<GetBidSettingLastRecordsResponse>(
				false, null, null);
		GetBidSettingLastRecordsResponse _result = bidService.getBidSettingLastRecords(parameters);

		result.setSuccess(true);
		result.setData(_result);

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
	public JsonResponse<String> setBidSetting(@RequestBody SetBidSettingParameter parameters) {
		JsonResponse<String> result = new JsonResponse<String>(false, null, -1, null);
		ResultCode _result = bidService.setBidSetting(parameters);

		result.setSuccess(_result.getSuccess());
		result.setCode(_result.getResultCode());

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
	public JsonResponse<GetBidSettingResponse> getBidSetting(@RequestBody GetBidSettingParameter parameters) {
		JsonResponse<GetBidSettingResponse> result = new JsonResponse<GetBidSettingResponse>(false, null, null);
		GetBidSettingResponse _result = bidService.getBidSetting(parameters);

		result.setSuccess(true);
		result.setData(_result);

		return result;
	}

	/**
	 * Get BidSetting Last Week
	 * 
	 * @since 2018. 10. 08.
	 * @author Junghwan Lee
	 * @param parameters
	 * @return
	 */
	@RequestMapping(value = "getbidsettinglastweek", method = RequestMethod.POST)
	public JsonResponse<GetBidSettingLastWeekResponse> getBidSettingLastWeek(@RequestBody GetBidSettingLastWeekParameter parameters) {
		JsonResponse<GetBidSettingLastWeekResponse> result = new JsonResponse<GetBidSettingLastWeekResponse>(false,
				null, null);

		GetBidSettingLastWeekResponse _result = bidService.getBidSettingLastWeek(parameters.getTop());
		result.setSuccess(true);
		result.setData(_result);

		return result;
	}
	
	
	@RequestMapping(value = "setacceptbids", method = RequestMethod.POST)
	public JsonResponse<String> acceptBids() {
		JsonResponse<String> results = new JsonResponse<String>(false, null, -1, null);
		ResultCode result = bidService.acceptBids();

		results.setSuccess(result.getSuccess());
		results.setCode(result.getResultCode());
		results.setMessage(result.getResultMsg());
		
		return results;
	}
}
