package net.fashiongo.webadmin.controller;


import io.netty.util.internal.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.model.pojo.common.PagedResult;
import net.fashiongo.webadmin.model.pojo.common.ResultCode;
import net.fashiongo.webadmin.model.pojo.common.ResultResponse;
import net.fashiongo.webadmin.model.pojo.sitemgmt.BannerOrMedia;
import net.fashiongo.webadmin.model.pojo.sitemgmt.CategoryListOrder;
import net.fashiongo.webadmin.model.pojo.sitemgmt.EditorsPick;
import net.fashiongo.webadmin.model.pojo.sitemgmt.TrendReportKmmImage;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.*;
import net.fashiongo.webadmin.model.pojo.sitemgmt.response.*;
import net.fashiongo.webadmin.model.primary.CommunicationReason;
import net.fashiongo.webadmin.model.primary.SocialMedia;
import net.fashiongo.webadmin.model.primary.Vendor;
import net.fashiongo.webadmin.service.CacheService;
import net.fashiongo.webadmin.service.SitemgmtService;
import net.fashiongo.webadmin.service.SocialMediaService;
import net.fashiongo.webadmin.service.VendorService;
import net.fashiongo.webadmin.service.renewal.RenewalSitemgmtService;
import net.fashiongo.webadmin.utility.JsonResponse;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/*
 * @author Sanghyup Kim
 */
@Api(description = "Site Management", tags = { "sitemgmt" })
@RestController
@Consumes(MediaType.APPLICATION_JSON)
@RequestMapping(value = "/sitemgmt", produces = "application/json")
@Slf4j
public class SitemgmtController {

	@Autowired
	SitemgmtService sitemgmtService;

	@Autowired
	SocialMediaService socialMediaService;
	
    @Autowired
    private CacheService cacheService;
    
	@Autowired
	VendorService vendorService;

	@Autowired
	private RenewalSitemgmtService renewalSitemgmtService;

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
	public JsonResponse<net.fashiongo.webadmin.data.model.sitemgmt.response.GetCategoryListResponse> getCategoryList(@RequestBody GetCategoryListParameters parameters) {

		JsonResponse<net.fashiongo.webadmin.data.model.sitemgmt.response.GetCategoryListResponse> results = new JsonResponse<net.fashiongo.webadmin.data.model.sitemgmt.response.GetCategoryListResponse>();

//		GetCategoryListResponse result = sitemgmtService.getCategoryList(parameters);
		net.fashiongo.webadmin.data.model.sitemgmt.response.GetCategoryListResponse result = renewalSitemgmtService.getCategoryList(parameters);
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
	public JsonResponse<net.fashiongo.webadmin.data.model.sitemgmt.response.GetPolicyDetailResponse> getPolicyDetail (@RequestBody GetPolicyDetailParameter parameters) {
		net.fashiongo.webadmin.data.model.sitemgmt.response.GetPolicyDetailResponse policyDetail = renewalSitemgmtService.getPolicyDetail(parameters);

		return new JsonResponse<net.fashiongo.webadmin.data.model.sitemgmt.response.GetPolicyDetailResponse>(true, null, 0, policyDetail);
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
	public JsonResponse<net.fashiongo.webadmin.data.model.sitemgmt.response.GetTodaydealResponse> getTodaydeal(@RequestBody GetTodaydealParameter parameters) throws ParseException {
		JsonResponse<net.fashiongo.webadmin.data.model.sitemgmt.response.GetTodaydealResponse> results = new JsonResponse<net.fashiongo.webadmin.data.model.sitemgmt.response.GetTodaydealResponse>(true, null, null);
		net.fashiongo.webadmin.data.model.sitemgmt.response.GetTodaydealResponse todaydeal = renewalSitemgmtService.getTodaydeal(parameters);
		results.setData(todaydeal);

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
	public JsonResponse<net.fashiongo.webadmin.data.model.sitemgmt.response.GetProductAttributesResponse> getProductAttributes(@RequestBody GetProductAttributesParameter parameters) {
		JsonResponse<net.fashiongo.webadmin.data.model.sitemgmt.response.GetProductAttributesResponse> results = new JsonResponse<net.fashiongo.webadmin.data.model.sitemgmt.response.GetProductAttributesResponse>(false, null, 0, null);

		net.fashiongo.webadmin.data.model.sitemgmt.response.GetProductAttributesResponse productAttributes = renewalSitemgmtService.getProductAttributes(parameters);

		results.setData(productAttributes);
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
	 * Get curated item
	 * @since 2018. 11. 06.
	 * @author Junghwan Lee
	 * @param parameter
	 * @return
	 */
	@RequestMapping(value = "getitems2", method = RequestMethod.POST)
	public JsonResponse<GetItemsResponse> getItems2(@RequestBody GetItemsParameter parameters) {
		JsonResponse<GetItemsResponse> results = new JsonResponse<GetItemsResponse>(true, null, null);
		
		GetItemsResponse _result = sitemgmtService.GetItems2(parameters);
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
		cacheService.GetRedisCacheEvict("BodySizeVendors", null);
		
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
	
	/**
	 * 
	 * Set FeaturedItem
	 * 
	 * @since 2018. 11. 1.
	 * @author Junghwan Lee
	 * @param parameters
	 * @return
	 */
	@RequestMapping(value = "setfeatureditem", method = RequestMethod.POST)
	public JsonResponse<String> setFeaturedItem(@RequestBody SetFeaturedItemParameter parameters) {
		ResultCode _result = sitemgmtService.setFeaturedItem(parameters);
		
		return new JsonResponse<String>(_result.getSuccess(), _result.getResultMsg(), _result.getResultCode(), null);
	}
	
	/**
	 *
	 * delete featured item
	 *
	 * @since 2018. 11. 05.
	 * @author Sanghyup Kim
	 * @param 
	 * @return 
	 */
	@RequestMapping(value = "delfeatureditem", method = RequestMethod.POST)
	public JsonResponse<Integer> delFeaturedItem(@RequestBody DelFeaturedItemParameter parameters) {
		ResultResponse<Integer> result = sitemgmtService.delFeaturedItem(parameters);
		
		JsonResponse<Integer> results = new JsonResponse<>(true, null, result.getData());
		return results;
	}
	
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
	
	/**
	 *
	 * Get TrendReport Item
	 *
	 * @since 2018. 11. 05.
	 * @author Nayeon Kim
	 * @param GetTrendReportItemParameter
	 * @return GetTrendReportItemResponse
	 */
	@RequestMapping(value = "gettrendreportitem", method = RequestMethod.POST)
	public JsonResponse<GetTrendReportItemResponse> getTrendReportItem(@RequestBody GetTrendReportItemParameter parameters) {
		GetTrendReportItemResponse result = sitemgmtService.getTrendReportItem(parameters);
		return new JsonResponse<GetTrendReportItemResponse>(true, null, result);
	}
	
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
	 * Set TrendReport
	 *
	 * @since 2018. 11. 06.
	 * @author Junghwan Lee
	 * @param SetTrendReportParameter
	 * @return JsonResponse<String
	 */
	@RequestMapping(value = "settrendreport", method = RequestMethod.POST)
	public JsonResponse<String> setTrendReport(@RequestBody SetTrendReportParameter parameters) {
		return sitemgmtService.setTrendReport(parameters);
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
	public JsonResponse<TrendReportKmmImage> getLastKMMData() {
		TrendReportKmmImage result = sitemgmtService.getLastKMMData();
		return new JsonResponse<TrendReportKmmImage>(true, null, result);
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
	 * Get DMRequestSendList
	 * 
	 * @since 2018. 10. 29.
	 * @author Incheol Jung
	 * @param parameters
	 * @return
	 */
	@RequestMapping(value = "getdmrequestsendlistOrigin", method = RequestMethod.POST)
	public JsonResponse<JSONObject> getDMRequestSendListOrigin(@RequestBody GetDMRequestSendListParameter parameters) {
		JsonResponse<JSONObject> results = new JsonResponse<JSONObject>(true, null, null);
		
		JSONObject _result = sitemgmtService.getDMRequestSendListOrigin(parameters);
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
		cacheService.GetRedisCacheEvict("BodySizeVendors", null);
		results.setData(result);
		results.setSuccess(true);
		
		return results;
	}
	

	/**
	 *
	 * Set Trend Report Map -add/del
	 *
	 * @since 2018. 11. 05.
	 * @author Nayeon Kim
	 * @param SetTrendReportMapParameter
	 * @return 
	 */
	@RequestMapping(value = "settrendreportmap", method = RequestMethod.POST)
	public JsonResponse<Integer> setTrendreportMap(@RequestBody SetTrendReportMapParameter parameters) {
		ResultCode result = sitemgmtService.setTrendReportMap(parameters);
		return new JsonResponse<Integer>(result.getSuccess(), result.getResultMsg(), result.getResultCode(), null);
	}
	
    /**
     * @author Kenny/Kyungwoo
     * @since 2019-04-29
     */
    @GetMapping(value = "editorsPicks")
    public JsonResponse<PagedResult<EditorsPick>> getEditorsPicks(
    		@RequestParam(value="pagenum", required=false) String pagenum,
    		@RequestParam(value="pagesize", required=false) String pagesize,
    		@RequestParam(value="title", required=false) String title,
    		@RequestParam(value="vendor", required=false) String vendor,
    		@RequestParam(value="startDate", required=false) String startDate,
    		@RequestParam(value="endDate", required=false) String endDate,
    		@RequestParam(value="orderBy", required=false) String orderBy) {
        JsonResponse<PagedResult<EditorsPick>> response = new JsonResponse<>(false, null, null);
        try {
        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy HH:mm:ss");
            PagedResult<EditorsPick> result = sitemgmtService.getEditorPickVendorContents(
            		StringUtil.isNullOrEmpty(pagenum) ? null : Integer.parseInt(pagenum),
            		StringUtil.isNullOrEmpty(pagesize) ? null : Integer.parseInt(pagesize),
            		title,
            		vendor,
            		StringUtil.isNullOrEmpty(startDate) ? null : LocalDateTime.parse(startDate, formatter),
            		StringUtil.isNullOrEmpty(endDate) ? null : LocalDateTime.parse(endDate, formatter),
            		orderBy);
            
            response.setSuccess(true);
            response.setData(result);
        } catch (Exception ex) {
            log.error("Exception Error: ", ex);
            response.setMessage(ex.getMessage());
        }
        return response;
    }
    
    /**
     * @author Kenny/Kyungwoo
     * @since 2019-04-29
     */
    @GetMapping(value = "editorsPick/{id}")
    public JsonResponse<EditorsPick> getEditorsPick(@PathVariable("id") String id){
    	JsonResponse<EditorsPick> response = new JsonResponse<>(false, null, null);
    	try {
    		EditorsPick result = sitemgmtService.getEditorPickVendorContent(StringUtil.isNullOrEmpty(id) ? null : Integer.parseInt(id));
    		response.setSuccess(true);
            response.setData(result);
        } catch (Exception ex) {
            log.error("Exception Error: ", ex);
            response.setMessage(ex.getMessage());
        }
    	return response;
    }
    
    @GetMapping(value = "editorsPick/vendors")
    public JsonResponse<List<Vendor>> getEditorsPickVendors(){
    	JsonResponse<List<Vendor>> response = new JsonResponse<>(false, null, null);
    	try {
    		List<Vendor> result = vendorService.getEditorsPickVendors();
    		response.setSuccess(true);
            response.setData(result);
        } catch (Exception ex) {
            log.error("Exception Error: ", ex);
            response.setMessage(ex.getMessage());
        }
    	return response;
    }
    
    @GetMapping(value = "editorsPick/vendor/{vendorId}")
    public JsonResponse<List<BannerOrMedia>> getEditorsPickVendorBannerOrMedia(@PathVariable("vendorId") Integer vendorId){
    	JsonResponse<List<BannerOrMedia>> response = new JsonResponse<>(false, null, null);
    	try {
    		List<BannerOrMedia> result = sitemgmtService.getBannerOrMedias(vendorId);
    		response.setSuccess(true);
            response.setData(result);
        } catch (Exception ex) {
            log.error("Exception Error: ", ex);
            response.setMessage(ex.getMessage());
        }
    	return response;
    }
    
	@PostMapping(value = "editorsPick")
	public JsonResponse<String> saveEditorsPick(@RequestBody EditorsPick editorsPick) {
		ResultCode result = sitemgmtService.saveEditorsPick(editorsPick);
		return new JsonResponse<>(result.getSuccess(), result.getResultMsg(), result.getResultCode(), "");
	}
	
	@DeleteMapping(value = "editorsPick/{id}")
	public JsonResponse<String> deleteEditorsPick(@PathVariable("id") String id) {
		ResultCode result = sitemgmtService.deleteEditorPickVendorContent(StringUtil.isNullOrEmpty(id) ? null : Integer.parseInt(id));
		return new JsonResponse<>(result.getSuccess(), result.getResultMsg(), result.getResultCode(), "");
	}

	@PostMapping(value = "gettrendkeywordcalendar")
	public JsonResponse<GetTrendDailyKeywordResponse> getTrendKeywordCalendar(@RequestBody GetTrendDailyKeywordParameter parameter) {
		JsonResponse<GetTrendDailyKeywordResponse> results = new JsonResponse<GetTrendDailyKeywordResponse>(true, null, null);

		GetTrendDailyKeywordResponse _result = sitemgmtService.getTrendDailyKeywords(parameter);
		results.setData(_result);
		results.setSuccess(true);

		return results;
	}

	@PostMapping(value = "settrendkeywords")
	public JsonResponse<Integer> setTrendKeywords(@RequestBody SetTrendDailyKeywordParameter parameter) {
		ResultCode result = sitemgmtService.setTrendDailyKeywords(parameter);
		return new JsonResponse<Integer>(result.getSuccess(), result.getResultMsg(), result.getResultCode(), null);
	}

	@PostMapping(value = "deltrendkeywords")
	public JsonResponse<Integer> delTrendKeyword(@RequestBody DelTrendDailyKeywordParameter parameter) {
		ResultCode result = sitemgmtService.delTrendDailyKeyword(parameter);
		return new JsonResponse<Integer>(result.getSuccess(), result.getResultMsg(), result.getResultCode(), null);
	}
}
