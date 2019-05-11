package net.fashiongo.webadmin.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.exception.BidAcceptAutoException;
import net.fashiongo.webadmin.model.pojo.bid.parameter.*;
import net.fashiongo.webadmin.model.pojo.bid.response.GetBidSettingLastRecordsResponse;
import net.fashiongo.webadmin.model.pojo.bid.response.GetBidSettingLastWeekResponse;
import net.fashiongo.webadmin.model.pojo.bid.response.GetBidSettingResponse;
import net.fashiongo.webadmin.model.pojo.common.ResultCode;
import net.fashiongo.webadmin.model.primary.AdBidSetting;
import net.fashiongo.webadmin.service.BidService;
import net.fashiongo.webadmin.utility.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 
 * @author JungHwan
 */
@Slf4j
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
	
	
	@RequestMapping(value = "setacceptbidsAuto", method = RequestMethod.GET)
	public JsonResponse<String> acceptBidsAuto() {
		JsonResponse<String> results = new JsonResponse<>(false, null, -1, null);

		List<AdBidSetting> adBidSettingList = bidService.getFinalizeAdBidSettingTargetList();
		if (adBidSettingList.size() == 0) {
			results.setSuccess(true);
			results.setMessage("No target exists");
			return results;
		}

		List<Integer> targetBidSettingIdList = adBidSettingList.stream().map(AdBidSetting::getBidSettingId).collect(Collectors.toList());

		JsonObject resultData = new JsonObject();
		resultData.add("targetBidSettingIds", new JsonArray());
		resultData.add("failedBidSettingIds", new JsonArray());

		for (int adBidSettingId : targetBidSettingIdList) {
			resultData.getAsJsonArray("targetBidSettingIds").add(adBidSettingId);
			try {
				bidService.acceptBid(adBidSettingId);
			} catch (Exception e) {
				if (!(e instanceof BidAcceptAutoException)) {
					log.error("acceptBidsAuto error", e);
				}
				resultData.getAsJsonArray("failedBidSettingIds").add(adBidSettingId);
			}
		}

		resultData.addProperty("target count", adBidSettingList.size());
		if (resultData.getAsJsonArray("failedBidSettingIds").size() == 0) {
			results.setSuccess(true);
			results.setMessage("Saved successfully!");
			results.setData(resultData.toString());
		} else {
			results.setSuccess(false);
			results.setMessage("Failed BidSettingId exists!");
			results.setData(resultData.toString());
		}

		return results;
	}
	
	@RequestMapping(value = "setacceptbids", method = RequestMethod.POST)
	public JsonResponse<String> acceptBids(@RequestBody SetBidAcceptParameter parameter) {
		JsonResponse<String> results = new JsonResponse<String>(false, null, -1, null);
		ResultCode result;
		try {
			result = bidService.editBid(parameter.getSpotId(), parameter.getAddate(), parameter.getBidids(), parameter.getAdminid());
		} catch (Exception e) {
			results.setMessage("Accept Bid Failed.");			
			return results;
		}

		results.setSuccess(result.getSuccess());
		results.setCode(result.getResultCode());
		results.setMessage(result.getResultMsg());
		
		return results;
	}
	
	@RequestMapping(value = "setcancelbid", method = RequestMethod.POST)
	public JsonResponse<String> cancelBid(@RequestBody SetBidCancelParameter parameter) {
		JsonResponse<String> results = new JsonResponse<String>(false, null, -1, null);
		ResultCode result;
		try {
			result = bidService.cancelBid(parameter.getBidid(), parameter.getAdminid());
		} catch (Exception e) {
			results.setMessage("Cancel Bid Failed.");			
			return results;
		}

		results.setSuccess(result.getSuccess());
		results.setCode(result.getResultCode());
		results.setMessage(result.getResultMsg());
		
		return results;
	}

	@RequestMapping(value = {"getListingAdBidCache", "getListingAdBidCache/{bidSettingId}"}, method = RequestMethod.GET)
	@ResponseBody
	public Object getListingAdBidSpotFromCache(@PathVariable(name = "bidSettingId", required = false) Integer bidSettingId) {
		return bidService.getListingAdBidSpotFromCache(bidSettingId);
	}
}
