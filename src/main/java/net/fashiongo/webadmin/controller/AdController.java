package net.fashiongo.webadmin.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.fashiongo.webadmin.model.pojo.ResultCode;
import net.fashiongo.webadmin.model.pojo.ResultResponse;
import net.fashiongo.webadmin.model.pojo.parameter.DelSpotSettingParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetSpotCheckParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetAddPageParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetAddSpotSettingParameter;
import net.fashiongo.webadmin.model.pojo.response.GetADSettingResponse;
import net.fashiongo.webadmin.model.pojo.response.GetSpotCheckResponse;
import net.fashiongo.webadmin.model.primary.CodeBodySize;
import net.fashiongo.webadmin.service.AdService;
import net.fashiongo.webadmin.utility.JsonResponse;

@RestController
@RequestMapping(value="/ad", produces = "application/json")
public class AdController {
	@Autowired
	AdService AdService;
	
	/**
	 * 
	 * Get AD Setting
	 * 
	 * @since 2018. 10. 02.
	 * @author Nayeon Kim
	 * @return GetADSettingResponse
	 */
	@RequestMapping(value = "getadsetting", method = RequestMethod.POST)
	public JsonResponse<GetADSettingResponse> getAdsetting() {
		JsonResponse<GetADSettingResponse> results = new JsonResponse<GetADSettingResponse>(false, null, 0, null);
		
		GetADSettingResponse result = AdService.getAdsetting();
		results.setData(result);
		results.setSuccess(true);
		
		return results;
	}
	
	/**
	 * 
	 * Set Add Page
	 * 
	 * @since 2018. 10. 05.
	 * @author Nayeon Kim
	 * @param SetAddPageParameter
	 * @return 
	 */
	@RequestMapping(value = "setaddpage", method = RequestMethod.POST)
	public JsonResponse<String> setAddPage(@RequestBody SetAddPageParameter parameters) {
		JsonResponse<String> results = new JsonResponse<String>(false, null, -1, null);
		ResultCode result = AdService.setAdPage(parameters);

		results.setSuccess(result.getSuccess());
		results.setCode(result.getResultCode());
		results.setMessage(result.getResultMsg());

		return results;
	}
	
	/**
	 * 
	 * Get Body Size Code
	 * 
	 * @since 2018. 10. 05.
	 * @author Nayeon Kim
	 * @return GetBodySizeCodeResponse
	 */
	@RequestMapping(value = "getbodysizecode", method = RequestMethod.POST)
	public JsonResponse<List<CodeBodySize>> getBodySizeCode() {
		JsonResponse<List<CodeBodySize>> results = new JsonResponse<List<CodeBodySize>>(false, null, 0, null);
		
		List<CodeBodySize> result = AdService.getBodySizeCode();
		results.setData(result);
		results.setSuccess(true);
		
		return results;
	}
	
	/**
	 * 
	 * Get Spot Check
	 * 
	 * @since 2018. 10. 05.
	 * @author Nayeon Kim
	 * @param GetSpotCheckParameter
	 * @return GetSpotCheckResponse
	 */
	@RequestMapping(value = "getspotcheck", method = RequestMethod.POST)
	public JsonResponse<GetSpotCheckResponse> getSpotCheck(GetSpotCheckParameter parameters) {
		JsonResponse<GetSpotCheckResponse> results = new JsonResponse<GetSpotCheckResponse>(false, null, 0, null);
		
		GetSpotCheckResponse result = AdService.getSpotCheck(parameters);
		if(result.getSpotID() != null) {
			results.setData(result);
		}
		results.setSuccess(true);
		
		return results;
	}
	
	/**
	 * 
	 * Delete Spot Setting
	 * 
	 * @since 2018. 10. 05.
	 * @author Nayeon Kim
	 * @param DelSpotSettingParameter
	 * @return 
	 */
	@RequestMapping(value = "delspotsetting", method = RequestMethod.POST)
	public JsonResponse<String> delSpotSetting(@RequestBody DelSpotSettingParameter parameters) {
		JsonResponse<String> results = new JsonResponse<String>(false, null, -1, null);
		ResultCode result = AdService.delSpotSetting(parameters);

		results.setSuccess(result.getSuccess());
		results.setCode(result.getResultCode());
		results.setMessage(result.getResultMsg());

		return results;
	}
	
	/**
	 * 
	 * Set Add Spot Setting
	 * 
	 * @since 2018. 10. 08.
	 * @author Nayeon Kim
	 * @param SetAddSpotSettingParameter
	 * @return 
	 */
	@RequestMapping(value = "setaddspotsetting", method = RequestMethod.POST)
	public JsonResponse<String> setAddSpotSetting(@RequestBody SetAddSpotSettingParameter parameters) {
		JsonResponse<String> results = new JsonResponse<String>(false, null, -1, null);
		ResultCode result = AdService.setAddSpotSetting(parameters);
		
		results.setSuccess(result.getSuccess());
		results.setCode(result.getResultCode());
		results.setMessage(result.getResultMsg());
		
		return results;
	}
}
