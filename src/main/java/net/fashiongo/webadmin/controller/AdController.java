package net.fashiongo.webadmin.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.fashiongo.webadmin.model.pojo.ResultCode;
import net.fashiongo.webadmin.model.pojo.parameter.DelCategoryAdItemParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetCategoryAdCalendarParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetCategoryAdDetailParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetCategoryAdItemForBidVendorParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetCategoryAdItemSearchParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetCategoryAdItemSearchVendorParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetAddPageParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetAddSpotSettingParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SpotIDParameter;
import net.fashiongo.webadmin.model.pojo.response.GetADSettingResponse;
import net.fashiongo.webadmin.model.pojo.response.GetCategoryAdCalendarResponse;
import net.fashiongo.webadmin.model.pojo.response.GetCategoryAdDetailResponse;
import net.fashiongo.webadmin.model.pojo.response.GetCategoryAdItemForBidVendorResponse;
import net.fashiongo.webadmin.model.pojo.response.GetCategoryAdItemSearchResponse;
import net.fashiongo.webadmin.model.pojo.response.GetCategoryAdItemSearchVendorResponse;
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
	public JsonResponse<GetSpotCheckResponse> getSpotCheck(@RequestBody SpotIDParameter parameters) {
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
	public JsonResponse<String> delSpotSetting(@RequestBody SpotIDParameter parameters) {
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
	
	/**
	 * 
	 * Get Category Ad Calendar
	 * 
	 * @since 2018. 10. 23.
	 * @author Jiwon Kim
	 * @param categoryDate
	 * @return GetCategoryAdCalendar
	 */
	@RequestMapping(value = "getcategoryadcalendar", method = RequestMethod.POST)
	public JsonResponse<GetCategoryAdCalendarResponse> GetCategoryAdCalendar(@RequestBody GetCategoryAdCalendarParameter parameters) {
		JsonResponse<GetCategoryAdCalendarResponse> results = new JsonResponse<GetCategoryAdCalendarResponse>(false, null, 0, null);
		GetCategoryAdCalendarResponse result = adService.GetCategoryAdCalendar(parameters);
		results.setData(result);
		results.setSuccess(true);
		
		return results;
	}
	
	/**
	 * 
	 * Del Category Ad Item
	 * 
	 * @since 2018. 10. 24.
	 * @author Jiwon Kim
	 * @param ccitemid
	 * @return DelCategoryAdItem
	 */
	@RequestMapping(value = "delcategoryaditem", method = RequestMethod.POST)
	public JsonResponse<String> delSpotSetting(@RequestBody DelCategoryAdItemParameter parameters) {
		JsonResponse<String> results = new JsonResponse<String>(false, null, -1, null);
		ResultCode result = adService.DelCategoryAdItem(parameters.getCcItemID());

		results.setSuccess(result.getSuccess());
		results.setCode(result.getResultCode());
		results.setMessage(result.getResultMsg());

		return results;
	}
	
	
	/**
	 * 
	 * Get Category Ad Detail
	 * 
	 * @since 2018. 10. 24.
	 * @author Jiwon Kim
	 * @param spotid,categorydate
	 * @return GetCategoryAdDetail
	 */
	@RequestMapping(value = "getcategoryaddetail", method = RequestMethod.POST)
	public JsonResponse<GetCategoryAdDetailResponse> GetCategoryAdDetail(@RequestBody GetCategoryAdDetailParameter parameters) {
		JsonResponse<GetCategoryAdDetailResponse> results = new JsonResponse<GetCategoryAdDetailResponse>(false, null, 0, null);
		GetCategoryAdDetailResponse result = adService.GetCategoryAdDetail(parameters);
		results.setData(result);
		results.setSuccess(true);
		return results;
	}
	
	
	/**
	 * 
	 * Get Category Ad Item For Bid Vendor
	 * 
	 * @since 2018. 10. 25.
	 * @author Jiwon Kim
	 * @param AdID
	 * @return GetCategoryAdItemForBidVendor
	 */
	@RequestMapping(value = "getcategoryaditemforbidvendor", method = RequestMethod.POST)
	public JsonResponse<GetCategoryAdItemForBidVendorResponse> GetCategoryAdItemForBidVendor(@RequestBody GetCategoryAdItemForBidVendorParameter parameters) {
		JsonResponse<GetCategoryAdItemForBidVendorResponse> results = new JsonResponse<GetCategoryAdItemForBidVendorResponse>(false, null, 0, null);
		GetCategoryAdItemForBidVendorResponse result = adService.GetCategoryAdItemForBidVendor(parameters);
		results.setData(result);
		results.setSuccess(true);
		return results;
	}
	
	
	/**
	 * 
	 * Get Category Ad Item Search
	 * 
	 * @since 2018. 10. 26.
	 * @author Jiwon Kim
	 * @param GetCategoryAdItemSearchParameter
	 * @return GetCategoryAdItemSearch
	 */
	@RequestMapping(value = "getcategoryaditemsearch", method = RequestMethod.POST)
	public JsonResponse<GetCategoryAdItemSearchResponse> GetCategoryAdItemSearch(@RequestBody GetCategoryAdItemSearchParameter parameters) {
		JsonResponse<GetCategoryAdItemSearchResponse> results = new JsonResponse<GetCategoryAdItemSearchResponse>(false, null, 0, null);
		GetCategoryAdItemSearchResponse result = adService.GetCategoryAdItemSearch(parameters);
		results.setData(result);
		results.setSuccess(true);
		return results;
	}
	
	
	/**
	 * 
	 * Get Category Ad Item Search Vendor
	 * 
	 * @since 2018. 10. 29.
	 * @author Jiwon Kim
	 * @param GetCategoryAdItemSearchVendorParameter
	 * @return GetCategoryAdItemSearchVendor
	 */
	@RequestMapping(value = "getcategoryaditemsearchvendor", method = RequestMethod.POST)
	public JsonResponse<GetCategoryAdItemSearchVendorResponse> GetCategoryAdItemSearchVendor(@RequestBody GetCategoryAdItemSearchVendorParameter parameters) {
		JsonResponse<GetCategoryAdItemSearchVendorResponse> results = new JsonResponse<GetCategoryAdItemSearchVendorResponse>(false, null, 0, null);
		GetCategoryAdItemSearchVendorResponse result = adService.GetCategoryAdItemSearchVendor(parameters);
		results.setData(result);
		results.setSuccess(true);
		return results;
	}
}
