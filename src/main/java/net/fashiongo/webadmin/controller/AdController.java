package net.fashiongo.webadmin.controller;


import net.fashiongo.webadmin.data.model.ad.response.GetAdPageSettingResponse;
import net.fashiongo.webadmin.data.model.ad.response.GetCategoryAdDetailResponse;
import net.fashiongo.webadmin.model.pojo.ad.parameter.*;
import net.fashiongo.webadmin.model.pojo.ad.response.*;
import net.fashiongo.webadmin.model.pojo.common.ResultCode;
import net.fashiongo.webadmin.model.pojo.parameter.GetCategoryAdItemForBidVendorParameter;
import net.fashiongo.webadmin.model.primary.CodeBodySize;
import net.fashiongo.webadmin.service.AdService;
import net.fashiongo.webadmin.service.renewal.RenewalAdService;
import net.fashiongo.webadmin.utility.JsonResponse;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/ad", produces = "application/json")
public class AdController {
	@Autowired
	AdService adService;

	@Autowired
	RenewalAdService renewalAdService;

	/**
	 * 
	 * Get AD Setting
	 * 
	 * @since 2018. 10. 02.
	 * @author Nayeon Kim
	 * @return GetADSettingResponse
	 */
	@RequestMapping(value = "getadsetting", method = RequestMethod.POST)
	public JsonResponse<GetAdPageSettingResponse> getAdsetting(@RequestBody JSONObject parameter) {
		boolean showAll = (boolean) parameter.get("showAll");
		GetAdPageSettingResponse result = renewalAdService.getAdSetting(showAll);
		return new JsonResponse<GetAdPageSettingResponse>(true, null, 0, result);
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
	public JsonResponse<net.fashiongo.webadmin.data.model.ad.response.GetCategoryAdCalendarResponse> GetCategoryAdCalendar(@RequestBody GetCategoryAdCalendarParameter parameters) {
		JsonResponse<net.fashiongo.webadmin.data.model.ad.response.GetCategoryAdCalendarResponse> results = new JsonResponse<net.fashiongo.webadmin.data.model.ad.response.GetCategoryAdCalendarResponse>(false, null, 0, null);
		net.fashiongo.webadmin.data.model.ad.response.GetCategoryAdCalendarResponse categoryAdCalendar = renewalAdService.getCategoryAdCalendar(parameters);

		results.setData(categoryAdCalendar);
		results.setSuccess(true);

		return results;
	}

	@RequestMapping(value = "getfgcategoryadcalendar", method = RequestMethod.POST)
	public JsonResponse<GetFGCategoryListAdCountResponse> GetFGCategoryAdCalendar(@RequestBody GetFGCategoryListAdCountParameter parameters) {
		JsonResponse<GetFGCategoryListAdCountResponse> results = new JsonResponse<GetFGCategoryListAdCountResponse>(false, null, 0, null);
		GetFGCategoryListAdCountResponse result = adService.GetFGCategoryAdCount(parameters);
		
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

		GetCategoryAdDetailResponse categoryAdDetail = renewalAdService.getCategoryAdDetail(parameters);

		results.setData(categoryAdDetail);
		results.setSuccess(true);
		return results;
	}
	
	
	/**
	 * 
	 * Get Category Ad Item For Bid Vendor
	 * 
	 * @since 2018. 10. 25.Pa
	 * @author Jiwon Kim
	 * @param AdID
	 * @return GetCategoryAdItemForBidVendor
	 */
	@RequestMapping(value = "getcategoryaditemforbidvendor", method = RequestMethod.POST)
	public JsonResponse<net.fashiongo.webadmin.data.model.ad.response.GetCategoryAdItemForBidVendorResponse> GetCategoryAdItemForBidVendor(@RequestBody GetCategoryAdItemForBidVendorParameter parameters) {
		JsonResponse<net.fashiongo.webadmin.data.model.ad.response.GetCategoryAdItemForBidVendorResponse> results = new JsonResponse<net.fashiongo.webadmin.data.model.ad.response.GetCategoryAdItemForBidVendorResponse>(false, null, 0, null);

		net.fashiongo.webadmin.data.model.ad.response.GetCategoryAdItemForBidVendorResponse result = renewalAdService.getCategoryAdItemForBidVendor(parameters);

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
	
	/**
	 * 
	 * Get Category Ad List
	 * 
	 * @since 2018. 10. 30.
	 * @author Jiwon Kim
	 * @param GetCategoryAdListParameter
	 * @return GetCategoryAdList
	 */
	@RequestMapping(value = "getcategoryadlist", method = RequestMethod.POST)
	public JsonResponse<net.fashiongo.webadmin.data.model.ad.response.GetCategoryAdListResponse> GetCategoryAdList(@RequestBody GetCategoryAdListParameter parameters) {
		JsonResponse<net.fashiongo.webadmin.data.model.ad.response.GetCategoryAdListResponse> results = new JsonResponse<net.fashiongo.webadmin.data.model.ad.response.GetCategoryAdListResponse>(false, null, 0, null);
		net.fashiongo.webadmin.data.model.ad.response.GetCategoryAdListResponse result = renewalAdService.getCategoryAdList(parameters);
		results.setData(result);
		results.setSuccess(true);
		return results;
	}
	
	@RequestMapping(value = "getfgcategoryadlist", method = RequestMethod.POST)
	public JsonResponse<GetFGCategoryAdListResponse> GetFGCategoryAdList(@RequestBody GetFGCategoryAdListParameter parameters) {
		JsonResponse<GetFGCategoryAdListResponse> results = new JsonResponse<GetFGCategoryAdListResponse>(false, null, 0, null);
		GetFGCategoryAdListResponse result = adService.GetFGCategoryAdList(parameters);
		results.setData(result);
		results.setSuccess(true);
		return results;
	}
	
	
	/**
	 * 
	 * Save Category Ad Item For Bid Vendor
	 * 
	 * @since 2018. 10. 31.
	 * @author Jiwon Kim
	 * @param SaveCategoryAdItemForBidVendorParameter
	 * @return SaveCategoryAdItemForBidVendor
	 */
	@RequestMapping(value = "savecategoryaditemforbidvendor", method = RequestMethod.POST)
	public JsonResponse<String> SaveCategoryAdItemForBidVendor(@RequestBody SaveCategoryAdItemForBidVendorParameter parameters) {
		JsonResponse<String> results = new JsonResponse<String>(false, null, -1, null);
		ResultCode result = adService.SaveCategoryAdItemForBidVendor(parameters);

		results.setSuccess(result.getSuccess());
		results.setCode(result.getResultCode());
		results.setMessage(result.getResultMsg());
		
		return results;
	}
	
	
	/**
	 * 
	 * Set Category Ad Item
	 * 
	 * @since 2018. 11. 01.
	 * @author Jiwon Kim
	 * @param SetCategoryAdItemParameter
	 * @return SetCategoryAdItem
	 */
	@RequestMapping(value = "setcategoryaditem", method = RequestMethod.POST)
	public JsonResponse<String> SetCategoryAdItem(@RequestBody SetCategoryAdItemParameter parameters) {
		JsonResponse<String> results = new JsonResponse<String>(false, null, -1, null);
		ResultCode result = adService.SetCategoryAdItem(parameters);

		results.setSuccess(result.getSuccess());
		results.setCode(result.getResultCode());
		results.setMessage(result.getResultMsg());

		return results;
	}

	@PostMapping("getbanneradsListvendorcategory")
	public BannerAdsListVendorCategoryResponse getBannerAdsListVendorCategory(@RequestBody AdVendorCategoryParameter parameter) {

		List<AdVendorCategoryResponse> responses = adService.getBannerAdsListVendorCategory(parameter.getWholesalerId());

		return BannerAdsListVendorCategoryResponse.of(responses);
	}
}
