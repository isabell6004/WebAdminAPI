package net.fashiongo.webadmin.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.fashiongo.webadmin.model.pojo.response.GetADSettingResponse;
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
	 * @since 2018. 10. 2.
	 * @author Nayeon Kim
	 * @return GetADSettingResponse
	 */
	@RequestMapping(value = "getAdsetting", method = RequestMethod.POST)
	public GetADSettingResponse getAdsetting() {
		
		GetADSettingResponse result = AdService.getAdsetting();

		return result;
	}
}
