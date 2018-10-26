package net.fashiongo.webadmin.controller;


import java.text.ParseException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fashiongo.webadmin.model.pojo.CategoryListOrder;
import net.fashiongo.webadmin.model.pojo.ResultCode;
import net.fashiongo.webadmin.model.pojo.ResultResponse;
import net.fashiongo.webadmin.model.pojo.parameter.DelSocialMediaParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetCategoryListParameters;
import net.fashiongo.webadmin.model.pojo.parameter.GetCategoryVendorListParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetFeaturedItemCountParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetTodayDealCalendarListParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetTodayDealCanlendarParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetTodaydealParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetVendorListParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetCategoryListOrderParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetCategoryParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetPaidCampaignParameter;
import net.fashiongo.webadmin.model.pojo.response.GetCategoryListResponse;
import net.fashiongo.webadmin.model.pojo.response.GetCategoryVendorListResponse;
import net.fashiongo.webadmin.model.pojo.response.GetFeaturedItemCountResponse;
import net.fashiongo.webadmin.model.pojo.response.GetPaidCampaignResponse;
import net.fashiongo.webadmin.model.pojo.response.GetProductAttributesTotalResponse;
import net.fashiongo.webadmin.model.pojo.response.GetTodayDealCalendarListResponse;
import net.fashiongo.webadmin.model.pojo.response.GetTodayDealCalendarResponse;
import net.fashiongo.webadmin.model.pojo.response.GetTodaydealResponse;
import net.fashiongo.webadmin.model.pojo.response.GetTrendReportCategoryResponse;
import net.fashiongo.webadmin.model.pojo.response.GetVendorListResponse;
import net.fashiongo.webadmin.model.primary.SocialMedia;
import net.fashiongo.webadmin.service.CacheService;
import net.fashiongo.webadmin.service.SitemgmtService;
import net.fashiongo.webadmin.service.SocialMediaService;
import net.fashiongo.webadmin.utility.JsonResponse;

/*
 * @author Sanghyup Kim
 */
@Api(description = "Site Management", tags = { "sitemgmt" })
@RestController
@Consumes(MediaType.APPLICATION_JSON)
@RequestMapping(value = "/sitemgmt", produces = "application/json")
public class SitemgmtController {

	@Autowired
	SitemgmtService sitemgmtService;

	@Autowired
	SocialMediaService socialMediaService;
	
    @Autowired
    private CacheService cacheService;

	// ----------------------------------------------------
	// collection category setting

	/**
	 *
	 * get Category List
	 *
	 * @since 2018. 9. 28.
	 * @author Sanghyup Kim
	 * @param GetCategoryListParameters
	 * @return GetCategoryListResponse
	 */
	@RequestMapping(value = "getcategorylist", method = RequestMethod.POST)
	@ApiOperation("site management > collection category setting - get Category List")
	public JsonResponse<GetCategoryListResponse> getCategoryList(@RequestBody GetCategoryListParameters parameters) {

		JsonResponse<GetCategoryListResponse> results = new JsonResponse<GetCategoryListResponse>();

		GetCategoryListResponse result = sitemgmtService.getCategoryList(parameters);
		results.setData(result);
		results.setSuccess(true);

		return results;
	}

	// collection category setting
	// ----------------------------------------------------

	/**
	 *
	 * Get Paid Campaign
	 *
	 * @since 2018. 10. 08.
	 * @author Nayeon Kim
	 * @return GetPaidCampaignResponse
	 */
	@RequestMapping(value = "getpaidcampaign", method = RequestMethod.POST)
	public JsonResponse<GetPaidCampaignResponse> getPaidCampaign() {
		GetPaidCampaignResponse result = sitemgmtService.getPaidCampaign();
		return new JsonResponse<GetPaidCampaignResponse>(true, null, 0, result);
	}

	/**
	 *
	 * Set Paid Campaign
	 *
	 * @since 2018. 10. 11.
	 * @author Nayeon Kim
	 * @param SetPaidCampaignParameter
	 * @return
	 */
	@RequestMapping(value = "setpaidcampaign", method = RequestMethod.POST)
	public JsonResponse<String> setPaidCampaign(@RequestBody SetPaidCampaignParameter parameters) {
		ResultCode result = sitemgmtService.setPaidCampaign(parameters);
		return new JsonResponse<String>(result.getSuccess(), result.getResultMsg(), result.getResultCode(), null);
	}

	/**
	 *
	 *
	 *
	 * @since 2018. 10. 22.
	 * @author Dahye
	 * @param
	 * @return
	 */
	@RequestMapping(value="getpolicymanagement", method=RequestMethod.POST)
	public void getPolicyManagement () {

	}

	/**
	 *
	 *
	 *
	 * @since 2018. 10. 22.
	 * @author Dahye
	 * @param
	 * @return
	 */
	@RequestMapping(value="setpolicymanagement", method=RequestMethod.POST)
	public void setAddDelPolicyManagement () {

	}

	/**
	 *
	 *
	 *
	 * @since 2018. 10. 22.
	 * @author Dahye
	 * @param
	 * @return
	 */
	@RequestMapping(value="getpolicydetail", method=RequestMethod.POST)
	public void getPolicyDetail () {

	}

	/**
	 *
	 *
	 *
	 * @since 2018. 10. 22.
	 * @author Dahye
	 * @param
	 * @return
	 */
	@RequestMapping(value="getpolicymanagementdetail", method=RequestMethod.POST)
	public void getPolicyManagementDetail () {

	}

	/**
	 *
	 *
	 *
	 * @since 2018. 10. 22.
	 * @author Dahye
	 * @param
	 * @return
	 */
	@RequestMapping(value="getcommunicationreasonall", method=RequestMethod.POST)
	public void getCommunicationReasonAll () {

	}

	/**
	 *
	 *
	 *
	 * @since 2018. 10. 22.
	 * @author Dahye
	 * @param
	 * @return
	 */
	@RequestMapping(value="deletecommunicationreason", method=RequestMethod.POST)
	public void deleteCommunicationReason () {

	}

	/**
	 *
	 *
	 *
	 * @since 2018. 10. 22.
	 * @author Dahye
	 * @param
	 * @return
	 */
	@RequestMapping(value="setcommunicationreasonactive", method=RequestMethod.POST)
	public void setCommunicationReasonActive () {

	}

	/**
	 *
	 *
	 *
	 * @since 2018. 10. 22.
	 * @author Dahye
	 * @param
	 * @return
	 */
	@RequestMapping(value="setcommunicationreason", method=RequestMethod.POST)
	public void setCommunicationReason () {

	}

	/**
	 *
	 * Get VendorList
	 *
	 * @since 2018. 10. 22.
	 * @author Incheol Jung
	 * @param parameters
	 * @return
	 */
	@RequestMapping(value = "getvendorlist", method = RequestMethod.POST)
	public JsonResponse<GetVendorListResponse> getVendorList(@RequestBody GetVendorListParameter parameters) {
		JsonResponse<GetVendorListResponse> results = new JsonResponse<GetVendorListResponse>(true, null, null);
		GetVendorListResponse result = sitemgmtService.getVendorList();
		results.setData(result);

		return results;
	}

	/**
	 *
	 * Get Todaydeal
	 *
	 * @since 2018. 10. 23.
	 * @author Incheol Jung
	 * @param parameters
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "gettodaydeal", method = RequestMethod.POST)
	public JsonResponse<GetTodaydealResponse> getTodaydeal(@RequestBody GetTodaydealParameter parameters) throws ParseException {
		JsonResponse<GetTodaydealResponse> results = new JsonResponse<GetTodaydealResponse>(true, null, null);
		GetTodaydealResponse result = sitemgmtService.getTodaydeal(parameters);
		results.setData(result);

		return results;
	}
	/**
	 * Get social media list
	 * @since Oct 23, 2018.
	 * @author roy
	 * @return JsonResponse<List<SocialMedia>>
	 */
	@RequestMapping(value = "getsocialmedialist", method = RequestMethod.GET)
	public JsonResponse<List<SocialMedia>> getSocialMediaList() {
		List<SocialMedia> socialMediaList = socialMediaService.getSocialMedias();
		return new JsonResponse<>(true, null, socialMediaList);
	}
	
	/**
	 * Delete social media
	 * @since Oct 25, 2018.
	 * @author roy
	 * @param DelSocialMediaParameter
	 * @return JsonResponse<String>
	 */
	@RequestMapping(value = "delsocialmedia", method = RequestMethod.POST)
	public JsonResponse<String> deleteSocialMedias(@RequestBody DelSocialMediaParameter delSocialMediaParameter) {
		boolean result = socialMediaService.deleteSocialMedias(delSocialMediaParameter.getSocialMediaIds());
		return new JsonResponse<>(result, null, "");
	}
	
	/**
	 * Save social media
	 * @since Oct 26, 2018.
	 * @author roy
	 * @param SocialMedia
	 * @return JsonResponse<String>
	 */
	@RequestMapping(value = "setsocialmedialist", method = RequestMethod.POST)
	public JsonResponse<String> saveSocialMedia(@RequestBody SocialMedia socialMedia) {
		ResultCode result = socialMediaService.saveSocialMedia(socialMedia);
		return new JsonResponse<>(result.getSuccess(), result.getResultMsg(), result.getResultCode(), "");

	}

	/**
	 *
	 * Get TrendReportCategory
	 *
	 * @since 2018. 10. 23.
	 * @author Incheol Jung
	 * @return
	 */
	@RequestMapping(value = "gettrendcategory", method = RequestMethod.POST)
	public JsonResponse<GetTrendReportCategoryResponse> getTrendReportCategory() {
		JsonResponse<GetTrendReportCategoryResponse> results = new JsonResponse<GetTrendReportCategoryResponse>(true, null, null);
		GetTrendReportCategoryResponse result = sitemgmtService.getTrendReportCategory();
		results.setData(result);

		return results;
	}
	
	/**
	 * 
	 * Get TodayDealCalendar
	 * 
	 * @since 2018. 10. 24.
	 * @author Incheol Jung
	 * @param parameters
	 * @return
	 */
	@RequestMapping(value = "gettodaydealcalendar", method = RequestMethod.POST)
	public JsonResponse<GetTodayDealCalendarResponse> getTodayDealCalendar(@RequestBody GetTodayDealCanlendarParameter parameters) {
		JsonResponse<GetTodayDealCalendarResponse> results = new JsonResponse<GetTodayDealCalendarResponse>(true, null, null);
		GetTodayDealCalendarResponse result = sitemgmtService.getTodayDealCalendar(parameters);
		results.setData(result);

		return results;
	}
	
	/**
	 * 
	 * Get TodayDealCalendarList
	 * 
	 * @since 2018. 10. 26.
	 * @author Incheol Jung
	 * @param parameters
	 * @return
	 */
	@RequestMapping(value = "gettodaydealcalendarlist", method = RequestMethod.POST)
	public JsonResponse<GetTodayDealCalendarListResponse> getTodayDealCalendarList(@RequestBody GetTodayDealCalendarListParameter parameters) {
		JsonResponse<GetTodayDealCalendarListResponse> results = new JsonResponse<GetTodayDealCalendarListResponse>(true, null, null);
		GetTodayDealCalendarListResponse result = sitemgmtService.getTodayDealCalendarList(parameters);
		results.setData(result);
		
		return results;
	}
	
	
	/**
	 * 
	 * Set Category
	 * 
	 * @since 2018. 10. 23.
	 * @author Nayeon Kim
	 * @param SetCategoryParameter
	 * @return
	 */
	@RequestMapping(value = "setcategory", method = RequestMethod.POST)
	public JsonResponse<Integer> setCategory(@RequestBody SetCategoryParameter parameters) {
		ResultResponse<Integer> result = sitemgmtService.setCategory(parameters);
		
		cacheService.GetRedisCacheEvict("CategoryVendors", null); // When a vendor is activated or deactivated
		cacheService.GetRedisCacheEvict("Category", null); // When FashionGo categories is changed (delete, add, modify)
		
		return new JsonResponse<Integer>(result.getSuccess(), result.getMessage(), result.getCode(), result.getPk(),result.getData());
	}

	/**
	 * 
	 * Set Category List Order
	 * 
	 * @since 2018. 10. 23.
	 * @author Nayeon Kim
	 * @param SetCategoryListOrderParameter
	 * @return
	 */
	@RequestMapping(value = "setcategorylistOrder", method = RequestMethod.POST)
	public JsonResponse<List<CategoryListOrder>> setCategoryListOrder(@RequestBody SetCategoryListOrderParameter parameters) {
		List<CategoryListOrder> result = sitemgmtService.setCategoryListOrder(parameters);
		
		cacheService.GetRedisCacheEvict("CategoryVendors", null); // When a vendor is activated or deactivated
		cacheService.GetRedisCacheEvict("Category", null); // When FashionGo categories is changed (delete, add, modify)
		
		return new JsonResponse<List<CategoryListOrder>>(true, "Updated successfully!", null, result);
	}
	
    /**
    *
    * Get Category Vendor List
    *
    * @since 2018. 10. 25.
    * @author Nayeon Kim
    * @param GetCategoryVendorListParameter
    * @return GetCategoryVendorListResponse
    */
	@RequestMapping(value = "getcategoryvendorlist", method = RequestMethod.POST)
	public JsonResponse<GetCategoryVendorListResponse> getCategoryVendorList(@RequestBody GetCategoryVendorListParameter parameters) {
		GetCategoryVendorListResponse result = sitemgmtService.getCategoryVendorList(parameters);
		return new JsonResponse<GetCategoryVendorListResponse>(true, null, result);
	}
	
	/**
	 *
	 * Get Product Attributes Total
	 *
	 * @since 2018. 10. 25.
	 * @author Nayeon Kim
	 * @return GetProductAttributesTotalResponse
	 */
	@RequestMapping(value = "getproductattributestotal", method = RequestMethod.POST)
	public JsonResponse<GetProductAttributesTotalResponse> getProductAttributesTotal() {
		GetProductAttributesTotalResponse result = sitemgmtService.getProductAttributesTotal();
		return new JsonResponse<GetProductAttributesTotalResponse>(true, null, result);
	}
	
	/**
	 *
	 * Get Featured Item Count
	 *
	 * @since 2018. 10. 25.
	 * @author Nayeon Kim
	 * @return GetFeaturedItemCountParameter
	 * @return GetFeaturedItemCountResponse
	 */
	@RequestMapping(value = "getfeatureditemcount", method = RequestMethod.POST)
	public JsonResponse<GetFeaturedItemCountResponse> getFeaturedItemCount(@RequestBody GetFeaturedItemCountParameter parameters) {
		GetFeaturedItemCountResponse result = sitemgmtService.getFeaturedItemCount(parameters.getsDate());	
		return new JsonResponse<GetFeaturedItemCountResponse>(true, null, result);
	}
}
