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
import net.fashiongo.webadmin.model.pojo.ResultCode;
import net.fashiongo.webadmin.model.pojo.parameter.GetCategoryListParameters;
import net.fashiongo.webadmin.model.pojo.parameter.GetVendorListParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetPaidCampaignParameter;
import net.fashiongo.webadmin.model.pojo.response.GetCategoryListResponse;
import net.fashiongo.webadmin.model.pojo.response.GetPaidCampaignResponse;
import net.fashiongo.webadmin.model.pojo.response.GetTodaydealResponse;
import net.fashiongo.webadmin.model.pojo.response.GetTrendReportCategoryResponse;
import net.fashiongo.webadmin.model.pojo.response.GetVendorListResponse;
import net.fashiongo.webadmin.model.primary.GetTodaydealParameter;
import net.fashiongo.webadmin.model.primary.SocialMedia;
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
		JsonResponse<GetPaidCampaignResponse> results = new JsonResponse<GetPaidCampaignResponse>(false, null, 0, null);

		GetPaidCampaignResponse result = sitemgmtService.getPaidCampaign();
		results.setData(result);
		results.setSuccess(true);

		return results;
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
		JsonResponse<String> results = new JsonResponse<String>(false, null, -1, null);
		ResultCode result = sitemgmtService.setPaidCampaign(parameters);

		results.setSuccess(result.getSuccess());
		results.setCode(result.getResultCode());
		results.setMessage(result.getResultMsg());

		return results;
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
	public void GetPolicyManagement () {

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
	public void SetAddDelPolicyManagement () {

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
	public void GetPolicyDetail () {

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
	public void GetPolicyManagementDetail () {

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
	public void GetCommunicationReasonAll () {

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
	public void DeleteCommunicationReason () {

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
	public void SetCommunicationReasonActive () {

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
	public void SetCommunicationReason () {

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
		return new JsonResponse<List<SocialMedia>>(true, null, socialMediaList);

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
	public JsonResponse<GetTrendReportCategoryResponse> GetTrendReportCategory() {
		JsonResponse<GetTrendReportCategoryResponse> results = new JsonResponse<GetTrendReportCategoryResponse>(true, null, null);
		GetTrendReportCategoryResponse result = sitemgmtService.GetTrendReportCategory();
		results.setData(result);

		return results;
	}
}
