package net.fashiongo.webadmin.controller;


import java.text.ParseException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;
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
import net.fashiongo.webadmin.model.pojo.TrendReportKmmImage;
import net.fashiongo.webadmin.model.pojo.parameter.DelSocialMediaParameter;
import net.fashiongo.webadmin.model.pojo.parameter.DeleteCommunicationReasonParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetCategoryListParameters;
import net.fashiongo.webadmin.model.pojo.parameter.GetCategoryVendorListParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetDMRequestParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetDMRequestSendListParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetFeaturedItemSearchParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetFeaturedItemSearchVendorParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetPolicyDetailParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetPolicyManagementDetailParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetProductAttributesParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetProductDetailParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetTodayDealCalendarListParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetTodayDealCanlendarParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetTodaydealParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetTrendReport2Parameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetTrendReportDefaultParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetVendorCategoryParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetVendorListParameter;
import net.fashiongo.webadmin.model.pojo.parameter.PageSizeParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetAddDelPolicyManagementParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetCategoryListOrderParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetCategoryParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetCommunicationReasonActiveParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetCommunicationReasonParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetFGCatalogParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetNewTodayDealParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetPaidCampaignParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetProductAttributesMappingParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetProductAttributesParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetTodayDealCalendarParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetTrendReportSortParameter;
import net.fashiongo.webadmin.model.pojo.parameter.StartDateParameter;
import net.fashiongo.webadmin.model.pojo.response.DeleteCommunicationReasonResponse;
import net.fashiongo.webadmin.model.pojo.response.GetCategoryListResponse;
import net.fashiongo.webadmin.model.pojo.response.GetCategoryVendorListResponse;
import net.fashiongo.webadmin.model.pojo.response.GetDMRequestResponse;
import net.fashiongo.webadmin.model.pojo.response.GetFeaturedItemCountResponse;
import net.fashiongo.webadmin.model.pojo.response.GetFeaturedItemListDayResponse;
import net.fashiongo.webadmin.model.pojo.response.GetFeaturedItemSearchResponse;
import net.fashiongo.webadmin.model.pojo.response.GetFeaturedItemSearchVendorResponse;
import net.fashiongo.webadmin.model.pojo.response.GetPaidCampaignResponse;
import net.fashiongo.webadmin.model.pojo.response.GetPolicyDetailResponse;
import net.fashiongo.webadmin.model.pojo.response.GetPolicyManagementDetailResponse;
import net.fashiongo.webadmin.model.pojo.response.GetPolicyManagementResponse;
import net.fashiongo.webadmin.model.pojo.response.GetProductAttributesResponse;
import net.fashiongo.webadmin.model.pojo.response.GetProductAttributesTotalResponse;
import net.fashiongo.webadmin.model.pojo.response.GetProductDetailResponse;
import net.fashiongo.webadmin.model.pojo.response.GetTodayDealCalendarListResponse;
import net.fashiongo.webadmin.model.pojo.response.GetTodayDealCalendarResponse;
import net.fashiongo.webadmin.model.pojo.response.GetTodaydealResponse;
import net.fashiongo.webadmin.model.pojo.response.GetTrendReport2Response;
import net.fashiongo.webadmin.model.pojo.response.GetTrendReportCategoryResponse;
import net.fashiongo.webadmin.model.pojo.response.GetTrendReportDefaultResponse;
import net.fashiongo.webadmin.model.pojo.response.GetVendorCategoryResponse;
import net.fashiongo.webadmin.model.pojo.response.GetVendorListResponse;
import net.fashiongo.webadmin.model.primary.CommunicationReason;
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
	 *getPolicyManagement
	 *
	 * @since 2018. 10. 29.
	 * @author Dahye
	 * @param PageSizeParameter
	 * @return GetPolicyManagementResponse
	 */
	@RequestMapping(value="getpolicymanagement", method=RequestMethod.POST)
	public JsonResponse<GetPolicyManagementResponse> getPolicyManagement (@RequestBody PageSizeParameter parameters) {
		GetPolicyManagementResponse result = sitemgmtService.getPolicyManagement(parameters);
		return new JsonResponse<GetPolicyManagementResponse>(true, null, 0, result);
	}

	/**
	 *
	 *setAddDelPolicyManagement
	 *
	 * @since 2018. 10. 30.
	 * @author Dahye
	 * @param SetAddDelPolicyManagementParameter
	 * @return ResultCode
	 */
	@RequestMapping(value="setpolicymanagement", method=RequestMethod.POST)
	public JsonResponse<String> setAddDelPolicyManagement (@RequestBody SetAddDelPolicyManagementParameter parameters) {
		ResultCode result = sitemgmtService.setAddDelPolicyManagement(parameters.getSetType(), parameters.getPolicy());
		return new JsonResponse<String>(true, result.getResultMsg(), result.getResultCode(), null);
	}

	/**
	 *
	 * getPolicyDetail
	 *
	 * @since 2018. 10. 30.
	 * @author Dahye
	 * @param GetPolicyDetailParameter
	 * @return GetPolicyDetailResponse
	 */
	@RequestMapping(value="getpolicydetail", method=RequestMethod.POST)
	public JsonResponse<GetPolicyDetailResponse> getPolicyDetail (@RequestBody GetPolicyDetailParameter parameters) {
		GetPolicyDetailResponse result = sitemgmtService.getPolicyDetail(parameters);
		return new JsonResponse<GetPolicyDetailResponse>(true, null, 0, result);
	}

	/**
	 *
	 * getPolicyManagementDetail
	 *
	 * @since 2018. 10. 31.
	 * @author Dahye
	 * @param GetPolicyManagementDetailParameter
	 * @return GetPolicyManagementDetailResponse
	 */
	@RequestMapping(value="getpolicymanagementdetail", method=RequestMethod.POST)
	public JsonResponse<GetPolicyManagementDetailResponse> getPolicyManagementDetail (@RequestBody GetPolicyManagementDetailParameter parameters) {
		GetPolicyManagementDetailResponse result = sitemgmtService.getPolicyManagementDetail(parameters);
		return new JsonResponse<GetPolicyManagementDetailResponse>(true, null, 0, result);
	}

	/**
	 *
	 * getCommunicationReasonAll
	 *
	 * @since 2018. 10. 31.
	 * @author Dahye
	 * @param GetcommunicationreasonallParameter
	 * @return CommunicationReason
	 */
	@RequestMapping(value="getcommunicationreasonall", method=RequestMethod.POST)
	public JsonResponse<List<CommunicationReason>> getCommunicationReasonAll () {
		List<CommunicationReason> result = sitemgmtService.getCommunicationReasonAll();
		return new JsonResponse<List<CommunicationReason>>(true, null, 0, result);
	}

	/**
	 *
	 * deleteCommunicationReason
	 *
	 * @since 2018. 10. 31.
	 * @author Dahye
	 * @param DeleteCommunicationReasonParameter
	 * @return DeleteCommunicationReasonResponse
	 */
	@RequestMapping(value="deletecommunicationreason", method=RequestMethod.POST)
	public JsonResponse<DeleteCommunicationReasonResponse> deleteCommunicationReason (@RequestBody DeleteCommunicationReasonParameter parameters) {
		DeleteCommunicationReasonResponse result = sitemgmtService.deleteCommunicationReason(parameters);
		return new JsonResponse<DeleteCommunicationReasonResponse>(true, null, 0, result);
	}

	/**
	 *
	 * setCommunicationReasonActive
	 *
	 * @since 2018. 10. 22.
	 * @author Dahye
	 * @param SetCommunicationReasonActiveParameter
	 * @return Integer
	 */
	@RequestMapping(value="setcommunicationreasonactive", method=RequestMethod.POST)
	public JsonResponse<String> setCommunicationReasonActive (@RequestBody SetCommunicationReasonActiveParameter parameters) {
		Integer result = sitemgmtService.setCommunicationReasonActive(parameters);
		return new JsonResponse<String>(true, null, result, null);
	}

	/**
	 *
	 * setCommunicationReason
	 *
	 * @since 2018. 10. 22.
	 * @author Dahye
	 * @param SetCommunicationReasonParameter
	 * @return Integer
	 */
	@RequestMapping(value="setcommunicationreason", method=RequestMethod.POST)
	public JsonResponse<String> setCommunicationReason (@RequestBody SetCommunicationReasonParameter parameters) {
		Integer result = sitemgmtService.setCommunicationReason(parameters);
		return new JsonResponse<String>(true, null, result, null);
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
	@RequestMapping(value = "setcategorylistorder", method = RequestMethod.POST)
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
	 * @return StartDateParameter
	 * @return GetFeaturedItemCountResponse
	 */
	@RequestMapping(value = "getfeatureditemcount", method = RequestMethod.POST)
	public JsonResponse<GetFeaturedItemCountResponse> getFeaturedItemCount(@RequestBody StartDateParameter parameters) {
		GetFeaturedItemCountResponse result = sitemgmtService.getFeaturedItemCount(parameters.getsDate());	
		return new JsonResponse<GetFeaturedItemCountResponse>(true, null, result);
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 10. 29.
	 * @author Reo
	 * @param parameters
	 * @return
	 */
	@RequestMapping(value="getproductattributes", method=RequestMethod.POST)
	public JsonResponse<GetProductAttributesResponse> getProductAttributes(@RequestBody GetProductAttributesParameter parameters) {
		JsonResponse<GetProductAttributesResponse> results = new JsonResponse<GetProductAttributesResponse>(false, null, 0, null);
		
		GetProductAttributesResponse result = sitemgmtService.getProductAttributes(parameters);
		results.setData(result);
		results.setSuccess(true);
		
		return results;
	}
	/**
	 * 
	 * @since 2018. 10. 26.
	 * @author Incheol Jung
	 * @param parameters
	 * @return
	 */
	@RequestMapping(value = "settodaydealcalendar", method = RequestMethod.POST)
	public JsonResponse<String> setTodayDealCalendar(@RequestBody SetTodayDealCalendarParameter parameters) {
		JsonResponse<String> results = new JsonResponse<String>(true, null, null);
		
		ResultCode _result = sitemgmtService.setTodayDealCalendar(parameters);
		results.setCode(_result.getResultCode());
		results.setMessage(_result.getResultMsg());
		
		return results;
	}
	

	/**
	 * 
	 * Get VendorCategory
	 * 
	 * @since 2018. 10. 29.
	 * @author Incheol Jung
	 * @param parameters
	 * @return
	 */
	@RequestMapping(value = "getvendorcategory", method = RequestMethod.POST)
	public JsonResponse<GetVendorCategoryResponse> getVendorCategory(@RequestBody GetVendorCategoryParameter parameters) {
		JsonResponse<GetVendorCategoryResponse> results = new JsonResponse<GetVendorCategoryResponse>(true, null, null);
		
		GetVendorCategoryResponse _result = sitemgmtService.getVendorCategory(parameters.getWholesalerid());
		results.setData(_result);
		results.setSuccess(true);
		
		return results;
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 10. 29.
	 * @author Reo
	 * @param parameter
	 * @return
	 */
	@RequestMapping(value="setproductattributes", method=RequestMethod.POST)
	public JsonResponse<ResultCode> setProductAttributes(@RequestBody SetProductAttributesParameter parameter) {
		JsonResponse<ResultCode> results = new JsonResponse<ResultCode>(false, null, 0, null);
		
		ResultCode result = sitemgmtService.setProductAttributes(parameter);
		results.setData(result);
		results.setSuccess(true);
		
		return results;
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 10. 29.
	 * @author Reo
	 * @param parameter
	 * @return
	 */
	@RequestMapping(value="setproductattributesactive", method=RequestMethod.POST)
	public JsonResponse<ResultCode> setProductAttributesActive(@RequestBody SetProductAttributesParameter parameter) {
		JsonResponse<ResultCode> results = new JsonResponse<ResultCode>(false, null, 0, null);
		
		ResultCode result = sitemgmtService.setProductAttributesActive(parameter);
		results.setData(result);
		results.setSuccess(true);
		
        return results;
	}
	
	/**
	 * Set NewTodayDeal
	 * 
	 * @since 2018. 10. 29.
	 * @author Incheol Jung
	 * @param parameters
	 * @return
	 */
	@RequestMapping(value = "setnewtodaydeal", method = RequestMethod.POST)
	public JsonResponse<Integer> setNewTodayDeal(@RequestBody SetNewTodayDealParameter parameters) {
		JsonResponse<Integer> results = new JsonResponse<Integer>(true, null, null);
		
		Integer _result = sitemgmtService.setNewTodayDeal(parameters);
		results.setCode(_result);
		
		return results;
	}

	/**
	 * Get FeaturedItemSearch
	 * 
	 * @since 2018. 11. 01.
	 * @author Junghwan Lee
	 * @param parameters
	 * @return
	 */
	@RequestMapping(value = "getfeatureditemsearch", method = RequestMethod.POST)
	public JsonResponse<GetFeaturedItemSearchResponse> getFeaturedItemSearch(@RequestBody GetFeaturedItemSearchParameter parameters) {
		JsonResponse<GetFeaturedItemSearchResponse> results = new JsonResponse<GetFeaturedItemSearchResponse>(true, null, null);
		
		GetFeaturedItemSearchResponse _result = sitemgmtService.getFeaturedItemSearch(parameters);
		results.setData(_result);
		
		return results;
	}
	
	/**
	 * Get FeaturedItemSearch Vendor
	 * 
	 * @since 2018. 11. 01.
	 * @author Junghwan Lee
	 * @param parameters
	 * @return
	 */
	@RequestMapping(value = "getfeatureditemsearchvendor", method = RequestMethod.POST)
	public JsonResponse<GetFeaturedItemSearchVendorResponse> getFeaturedItemSearchVendor(@RequestBody GetFeaturedItemSearchVendorParameter parameters) {
		JsonResponse<GetFeaturedItemSearchVendorResponse> results = new JsonResponse<GetFeaturedItemSearchVendorResponse>(true, null, null);
		
		GetFeaturedItemSearchVendorResponse _result = sitemgmtService.getFeaturedItemSearchVendor(parameters);
		results.setData(_result);
		
		return results;
	}
	
	@RequestMapping(value = "setfeatureditem", method = RequestMethod.POST)
	public void getFeaturedItem() {}
	
	@RequestMapping(value = "delfeatureditem", method = RequestMethod.POST)
	public void gelFeaturedItem() {}
	
	/**
	 *
	 * Get Featured Item List Day
	 *
	 * @since 2018. 10. 30.
	 * @author Nayeon Kim
	 * @param StartDateParameter
	 * @return GetFeaturedItemListDayResponse
	 */
	@RequestMapping(value = "getfeatureditemlistday", method = RequestMethod.POST)
    public JsonResponse<GetFeaturedItemListDayResponse> getFeaturedItemListDay(@RequestBody StartDateParameter parameters) {
			GetFeaturedItemListDayResponse result = sitemgmtService.getFeaturedItemListDay(parameters.getsDate());  
           return new JsonResponse<GetFeaturedItemListDayResponse>(true, null, result);
    }
	
	/**
	 *
	 * Get Product Detail
	 *
	 * @since 2018. 10. 31.
	 * @author Nayeon Kim
	 * @param GetProductDetailParameter
	 * @return GetProductDetailResponse
	 */
	@RequestMapping(value = "getproductdetail", method = RequestMethod.POST)
	public JsonResponse<GetProductDetailResponse> getProductDetail(@RequestBody GetProductDetailParameter parameters) {
		GetProductDetailResponse result = sitemgmtService.getProductDetail(parameters);
		return new JsonResponse<GetProductDetailResponse>(true, null, result);
	}
	
	/**
	 *
	 * Get TrendReport Default
	 *
	 * @since 2018. 10. 31.
	 * @author Nayeon Kim
	 * @param GetTrendReportDefaultParameter
	 * @return GetTrendReportDefaultResponse
	 */
	@RequestMapping(value = "gettrendreportdefault", method = RequestMethod.POST)
	public JsonResponse<GetTrendReportDefaultResponse> getTrendReportDefault(@RequestBody GetTrendReportDefaultParameter parameters) {
		GetTrendReportDefaultResponse result = sitemgmtService.getTrendReportDefault(parameters);
		return new JsonResponse<GetTrendReportDefaultResponse>(true, null, result);
	}
	
	/**
	 *
	 * Get TrendReport V2
	 *
	 * @since 2018. 10. 31.
	 * @author Nayeon Kim
	 * @param GetTrendReport2Parameter
	 * @return GetTrendReport2Response
	 */
	@RequestMapping(value = "gettrendreport2", method = RequestMethod.POST)
	public JsonResponse<GetTrendReport2Response> getTrendReport2(@RequestBody GetTrendReport2Parameter parameters) {
		GetTrendReport2Response result = sitemgmtService.getTrendReport2(parameters);
		return new JsonResponse<GetTrendReport2Response>(true, null, result);
	}
	
	@RequestMapping(value = "getitems2", method = RequestMethod.POST)
	public void getItems2() {}
	
	@RequestMapping(value = "gettrendreportitem", method = RequestMethod.POST)
	public void getTrendReportItem() {}
	
//	@Deprecated
//	@RequestMapping(value = "getproductattributestotal", method = RequestMethod.POST)
//	public JsonResponse<String>  setAddDelTrendReportMap() { return null; }
	
	/**
	 *
	 * Set Trend Report Sort
	 *
	 * @since 2018. 10. 29.
	 * @author Nayeon Kim
	 * @param SetTrendReportSortParameter
	 * @return ResultCode
	 */
	@RequestMapping(value = "settrendreportsort", method = RequestMethod.POST)
	public JsonResponse<String> setTrendReportSort(@RequestBody SetTrendReportSortParameter parameters) {
		ResultCode result = sitemgmtService.setTrendReportSort(parameters.getxMLDatas());
		return new JsonResponse<String>(result.getSuccess(), result.getResultMsg(), result.getResultCode(), null);
	}
	
	/**
	 *
	 * Get Last KMM Data
	 *
	 * @since 2018. 10. 29.
	 * @author Nayeon Kim
	 * @return List<TrendReportKmmImage>
	 */
	@RequestMapping(value = "getlastkmmdata", method = RequestMethod.POST)
	public JsonResponse<List<TrendReportKmmImage>> getLastKMMData() {
		List<TrendReportKmmImage> result = sitemgmtService.getLastKMMData();
		return new JsonResponse<List<TrendReportKmmImage>>(true, null, result);
	}
	
	/**
	 * 
	 * Get DMRequest
	 * 
	 * @since 2018. 10. 29.
	 * @author Incheol Jung
	 * @param parameters
	 * @return
	 */
	@RequestMapping(value = "getdmrequest", method = RequestMethod.POST)
	public JsonResponse<GetDMRequestResponse> getDMRequest(@RequestBody GetDMRequestParameter parameters) {
		JsonResponse<GetDMRequestResponse> results = new JsonResponse<GetDMRequestResponse>(true, null, null);
		
		GetDMRequestResponse _result = sitemgmtService.getDMRequest(parameters);
		results.setData(_result);
		
		return results;
	}
	
	/**
	 * 
	 * Get DMRequestSendList
	 * 
	 * @since 2018. 10. 29.
	 * @author Incheol Jung
	 * @param parameters
	 * @return
	 */
	@RequestMapping(value = "getdmrequestsendlist", method = RequestMethod.POST)
	public JsonResponse<JSONObject> getDMRequestSendList(@RequestBody GetDMRequestSendListParameter parameters) {
		JsonResponse<JSONObject> results = new JsonResponse<JSONObject>(true, null, null);
		
		JSONObject _result = sitemgmtService.getDMRequestSendList(parameters);
		results.setData(_result);
		
		return results;
	}
	
	/**
	 * 
	 * Set FGCatalog
	 * 
	 * @since 2018. 10. 29.
	 * @author Incheol Jung
	 * @param parameters
	 * @return
	 */
	@RequestMapping(value = "setfgcatalog", method = RequestMethod.POST)
	public JsonResponse<String> setFGCatalog(@RequestBody SetFGCatalogParameter parameters) {
		JsonResponse<String> results = new JsonResponse<String>(true, null, null);
		
		ResultCode _result = sitemgmtService.setFGCatalog(parameters);
		results.setCode(_result.getResultCode());
		results.setMessage(_result.getResultMsg());
		
		return results;
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 10. 31.
	 * @author Reo
	 * @param parameter
	 * @return
	 */
	@RequestMapping(value = "setproductattributesmapping", method = RequestMethod.POST)
	public JsonResponse<ResultCode> setProductAttributesMapping(@RequestBody SetProductAttributesMappingParameter parameter) {
		JsonResponse<ResultCode> results = new JsonResponse<ResultCode>(false, null, 0, null);
		
		ResultCode result = sitemgmtService.setProductAttributesMapping(parameter);
		results.setData(result);
		results.setSuccess(true);
		
		return results;
	}
}
