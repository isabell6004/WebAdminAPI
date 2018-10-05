package net.fashiongo.webadmin.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.fashiongo.webadmin.model.pojo.ResultResponse;
import net.fashiongo.webadmin.model.pojo.parameter.DelSpotSettingParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetSpotCheckParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetAddPageParameter;
import net.fashiongo.webadmin.model.pojo.response.GetADSettingResponse;
import net.fashiongo.webadmin.model.pojo.response.GetBodySizeCodeResponse;
import net.fashiongo.webadmin.model.pojo.response.GetSpotCheckResponse;
import net.fashiongo.webadmin.service.AdService;

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
	public GetADSettingResponse getAdsetting() {
		GetADSettingResponse result = AdService.getAdsetting();

		return result;
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
	public ResultResponse<Object> setAddPage(@RequestBody SetAddPageParameter parameters) {
		ResultResponse<Object> result = AdService.setAdPage(parameters);

		return result;
	}
	
	/**
	 * 
	 * Get Body Size Code
	 * 
	 * @since 2018. 10. 05.
	 * @author Nayeon Kim
	 * @return GetBodySizeCodeResponse
	 */
	@RequestMapping(value = "getbodysizelist", method = RequestMethod.POST)
	public GetBodySizeCodeResponse getBodySizeList() {
		GetBodySizeCodeResponse result = AdService.getBodySizeList();

		return result;
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
	public GetSpotCheckResponse getSpotCheck(GetSpotCheckParameter parameters) {
		GetSpotCheckResponse result = AdService.getSpotCheck(parameters);

		return result;
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
	public ResultResponse<Object> delSpotSetting(@RequestBody DelSpotSettingParameter parameters) {
		ResultResponse<Object> result = AdService.delSpotSetting(parameters);

		return result;
	}
}
