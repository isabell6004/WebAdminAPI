package net.fashiongo.webadmin.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.fashiongo.webadmin.model.pojo.ResultCode;
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
	AdService adService;
	
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
		GetADSettingResponse result = adService.getAdsetting();
		return new JsonResponse<GetADSettingResponse>(true, null, 0, result);
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
		ResultCode result = adService.setAdPage(parameters);
		return new JsonResponse<String>(result.getSuccess(), result.getResultMsg(), result.getResultCode(), null);
	}
	
	/**
	 * 
	 * Get Body Size Code
	 * 
	 * @since 2018. 10. 05.
	 * @author Nayeon Kim
	 * @return List<CodeBodySize>
	 */
	@RequestMapping(value = "getbodysizelist", method = RequestMethod.POST)
	public JsonResponse<List<CodeBodySize>> getBodySizeCode() {
		List<CodeBodySize> result = adService.getBodySizeCode();
		return new JsonResponse<List<CodeBodySize>>(true, null, 0, result);
	}
	
	/**
	 * 
	 * Get Spot Check
	 * 
	 * @since 2018. 10. 05.
	 * @author Nayeon Kim
	 * @param spotID
	 * @return GetSpotCheckResponse
	 */
	@RequestMapping(value = "getspotcheck", method = RequestMethod.POST)
	public JsonResponse<GetSpotCheckResponse> getSpotCheck(@RequestBody GetSpotCheckParameter parameters) {
		GetSpotCheckResponse result = adService.getSpotCheck(parameters.getSpotID());
		return new JsonResponse<GetSpotCheckResponse>(true, null, 0, result);
	}
	
	/**
	 * 
	 * Delete Spot Setting
	 * 
	 * @since 2018. 10. 05.
	 * @author Nayeon Kim
	 * @param spotID
	 * @return 
	 */
	@RequestMapping(value = "delspotsetting", method = RequestMethod.POST)
	public JsonResponse<String> delSpotSetting(@RequestBody DelSpotSettingParameter parameters) {
		ResultCode result = adService.delSpotSetting(parameters.getSpotID());
		return new JsonResponse<String>(result.getSuccess(), result.getResultMsg(), result.getResultCode(), null);
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
		ResultCode result = adService.setAddSpotSetting(parameters);
		return new JsonResponse<String>(result.getSuccess(), result.getResultMsg(), result.getResultCode(), null);
	}
}
