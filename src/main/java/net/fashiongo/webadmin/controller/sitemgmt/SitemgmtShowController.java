package net.fashiongo.webadmin.controller.sitemgmt;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.model.sitemgmt.show.*;
import net.fashiongo.webadmin.model.pojo.sitemgmt.ShowInfoDto;
import net.fashiongo.webadmin.model.pojo.sitemgmt.response.*;
import net.fashiongo.webadmin.service.renewal.RenewalSiteManagementShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fashiongo.webadmin.model.pojo.common.ResultResponse;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.DelShowParameter;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.GetShowListParameters;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.GetShowParameter;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.GetShowScheduleListParameters;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.SetShowInfoParameters;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.SetShowParameters;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.SetShowParticipatingVendorParameters;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.SetShowPromotionPlanParameters;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.SetShowScheduleParameters;
import net.fashiongo.webadmin.model.primary.show.ListShow;
import net.fashiongo.webadmin.model.primary.show.MapShowSchedulePromotionPlanVendor;
import net.fashiongo.webadmin.model.primary.show.ShowSchedule;
import net.fashiongo.webadmin.model.primary.show.ShowSchedulePromotionPlan;
import net.fashiongo.webadmin.service.SitemgmtShowService;
import net.fashiongo.webadmin.utility.JsonResponse;

/*
 * @author Sanghyup Kim
 */
@Api(description = "Site Management Show", tags = { "sitemgmt-show" })
@RestController
@Consumes(MediaType.APPLICATION_JSON)
@RequestMapping(value = "/sitemgmt", produces = "application/json")
@Slf4j
public class SitemgmtShowController {

	@Autowired
	SitemgmtShowService siteMgmtShowService;

	@Autowired
	private RenewalSiteManagementShowService renewalSiteManagementShowService;

	/**
	 * 
	 * get Show List
	 * 
	 * @since 2018. 10. 15.
	 * @author Sanghyup Kim
	 * @param GetShowListParameters
	 * @return JsonResponse<AdminShowResponse<ListShowResponse>>
	 */
	@RequestMapping(value = "getshowlist", method = RequestMethod.POST)
	@ApiOperation("site management > show info. - get show list")
	public JsonResponse<AdminShowResponse<ListShowResponse>> getShowList(@RequestBody GetShowListParameters parameters) {
		AdminShowResponse<ListShowResponse> data = renewalSiteManagementShowService.getShowList(parameters);
		return new JsonResponse<>(true, null, data);
	}

	/**
	 * 
	 * set ShowInfo
	 * 
	 * @since 2018. 10. 15.
	 * @author Sanghyup Kim
	 * @param SetShowInfoParameters
	 * @return JsonResponse<Integer>
	 */
	@RequestMapping(value = "setshowinfo", method = RequestMethod.POST)
	@ApiOperation("site management > show info. - set show info")
	public JsonResponse<Integer> setShowInfo(@RequestBody SetShowInfoParameters parameters) {

		ResultResponse<Integer> result = siteMgmtShowService.setShowInfo(parameters);

		JsonResponse<Integer> results = new JsonResponse<Integer>(result.getSuccess(), result.getMessage(),
				result.getCode(), result.getPk(), result.getData());
//		results.setCode(result.getCode());
//		results.setData(result.getData());
//		results.setMessage(result.getMessage());
//		results.setPk(result.getPk());
//		results.setSuccess(result.getSuccess());

		return results;
	}

	/**
	 * 
	 * get Show
	 * 
	 * @since 2018. 10. 15.
	 * @author Sanghyup Kim
	 * @param showId
	 * @return JsonResponse<ListShow>
	 */
	@RequestMapping(value = "show/{showId}", method = RequestMethod.POST)
	@ApiOperation("site management > show info. - get show")
	public JsonResponse<ListShow> getShow(@PathVariable("showId") Integer showID) {

		ResultResponse<ListShow> result = siteMgmtShowService.getShowDetail(showID);

		JsonResponse<ListShow> results = new JsonResponse<ListShow>(true, null, result.getData());
//		results.setData(result.getData());
		return results;
	}

	/**
	 * 
	 * get ShowSchedules
	 * 
	 * @since 2018. 10. 15.
	 * @author Sanghyup Kim
	 * @param GetShowScheduleListParameters
	 * @return JsonResponse<AdminShowResponse<ShowScheduleResponse>>
	 */
	@RequestMapping(value = "show/schedule", method = RequestMethod.POST)
	@ApiOperation("site management > show info. - get show schedule list")
	public JsonResponse<AdminShowResponse<ShowScheduleResponse>> getShowSchedules(@RequestBody GetShowScheduleListParameters parameters) {
		AdminShowResponse<ShowScheduleResponse> response = renewalSiteManagementShowService.getShowScheduleList(parameters);
		return new JsonResponse<>(true, null, response);
	}

	/**
	 * 
	 * set Show
	 * 
	 * @since 2018. 10. 16.
	 * @author Sanghyup Kim
	 * @param SetShowParameters
	 * @return JsonResponse<SetShowParameters>
	 */
	@RequestMapping(value = "show/save", method = RequestMethod.POST)
	@ApiOperation("site management > show info. - set show save")
	public JsonResponse<SetShowParameters> setShow(@RequestBody SetShowParameters parameters) {

		ResultResponse<Integer> result = siteMgmtShowService.setShow(parameters);
		parameters.setShowId(result.getData());

		JsonResponse<SetShowParameters> results = new JsonResponse<SetShowParameters>(true, null, parameters);
//		results.setData(parameters);
		return results;
	}

	/**
	 * 
	 * delete showschedule
	 * 
	 * @since 2018. 10. 17.
	 * @author Sanghyup Kim
	 * @param DelShowParameter
	 * @return JsonResponse<Integer>
	 */
	@RequestMapping(value = "show/schedule/delete", method = RequestMethod.POST)
	@ApiOperation("site management > show info. - set show schedule delete")
	public JsonResponse<Integer> deleteShowSchedule(@RequestBody DelShowParameter parameters) {

		ResultResponse<Integer> result = siteMgmtShowService.setDeleteShowSchedule(parameters);

		JsonResponse<Integer> results = new JsonResponse<Integer>(result.getSuccess(), result.getMessage(),
				result.getData());
//		results.setSuccess(result.getSuccess());
//		results.setMessage(result.getMessage());

		return results;
	}

	/**
	 * 
	 * delete show
	 * 
	 * @since 2018. 10. 17.
	 * @author Sanghyup Kim
	 * @param DelShowParameter
	 * @return JsonResponse<Integer>
	 */
	@RequestMapping(value = "show/delete", method = RequestMethod.POST)
	@ApiOperation("site management > show info. - set show delete")
	public JsonResponse<Integer> deleteShow(@RequestBody DelShowParameter parameters) {

		ResultResponse<Integer> result = siteMgmtShowService.setDeleteShow(parameters);

		JsonResponse<Integer> results = new JsonResponse<Integer>(result.getSuccess(), result.getMessage(),
				result.getData());
		results.setSuccess(result.getSuccess());
		results.setMessage(result.getMessage());

		return results;
	}

	/**
	 * 
	 * set ShowSchedule
	 * 
	 * @since 2018. 10. 11.
	 * @author Sanghyup Kim
	 * @param SetShowScheduleParameters
	 * @return JsonResponse<SetShowScheduleParameters>
	 */
	@RequestMapping(value = "show/schedule/save", method = RequestMethod.POST)
	@ApiOperation("site management > show info. - set show schedule save")
	public JsonResponse<SetShowScheduleParameters> setShowSchedule(@RequestBody SetShowScheduleParameters parameters)
			throws Exception {

		ResultResponse<Integer> result = siteMgmtShowService.setShowSchedule(parameters);

		parameters.setShowScheduleId(result.getData());

		JsonResponse<SetShowScheduleParameters> results = new JsonResponse<SetShowScheduleParameters>(
				result.getSuccess(), result.getMessage(), parameters);
//		results.setSuccess(result.getSuccess());
//		results.setMessage(result.getMessage());
//		results.setData(parameters);

		return results;
	}

	/**
	 * 
	 * getshowcategorylist (for comobobox)
	 * 
	 * @since 2018. 10. 11.
	 * @author Sanghyup Kim
	 * @param GetShowParameter
	 * @return JsonResponse<GetShowCategoriesResponse>
	 */
	@RequestMapping(value = "getshowcategorylist", method = RequestMethod.POST)
	@ApiOperation("site management > show info. - get show category list")
	public JsonResponse<GetShowCategoriesResponse> getShowCategories(@RequestBody GetShowParameter parameters) {

		GetShowCategoriesResponse getShowCategoriesResponse = siteMgmtShowService.getShowCategories(parameters);

		JsonResponse<GetShowCategoriesResponse> results = new JsonResponse<GetShowCategoriesResponse>(true, null,
				getShowCategoriesResponse);
//		results.setData(getShowCategoriesResponse);

		return results;
	}

	/**
	 * 
	 * get ShowPromotionPlans
	 * 
	 * @since 2018. 10. 18.
	 * @author Sanghyup Kim
	 * @param GetShowParameter
	 * @return JsonResponse<List<ShowSchedulePromotionPlan>>
	 */
	@RequestMapping(value = "show/promotion-plan", method = RequestMethod.POST)
	@ApiOperation("site management > show info. - get show promotion-plans")
	public JsonResponse<List<ShowSchedulePromotionPlan>> getShowPromotionPlans(
			@RequestBody GetShowParameter parameters) {

		List<ShowSchedulePromotionPlan> showSchedulePromotionPlanList = siteMgmtShowService
				.getShowPromotionPlans(parameters);

		JsonResponse<List<ShowSchedulePromotionPlan>> results = new JsonResponse<List<ShowSchedulePromotionPlan>>(true,
				null, showSchedulePromotionPlanList);
//		results.setData(showSchedulePromotionPlanList);
		return results;
	}

	/**
	 * 
	 * get ShowParticipating Vendors
	 * 
	 * @since 2018. 10. 11.
	 * @author Sanghyup Kim
	 * @param GetShowParameter
	 * @return JsonResponse<AdminShowResponse<ShowPromotionVendorResponse>>
	 */
	@RequestMapping(value = "show/participating-vendor", method = RequestMethod.POST)
	@ApiOperation("site management > show info. - get show participating vendors")
	public JsonResponse<AdminShowResponse<ShowPromotionVendorResponse>> getShowParticipatingVendors(@RequestBody GetShowParameter parameters) {
		AdminShowResponse<ShowPromotionVendorResponse> response = renewalSiteManagementShowService.getShowParticipatingVendors(parameters);
		return new JsonResponse<>(true, null, response);
	}

	/**
	 * 
	 * set ShowParticipating Vendor
	 * 
	 * @since 2018. 10. 11.
	 * @author Sanghyup Kim
	 * @param SetShowParticipatingVendorParameters
	 * @return JsonResponse<Integer>
	 */
	@RequestMapping(value = "show/participating-vendor/save", method = RequestMethod.POST)
	@ApiOperation("site management > show info. - set show participating-vendor save")
	public JsonResponse<Integer> setShowParticipatingVendor(
			@RequestBody SetShowParticipatingVendorParameters parameters) {

		ResultResponse<Integer> result = siteMgmtShowService.setShowParticipatingVendor(parameters);

		JsonResponse<Integer> results = new JsonResponse<Integer>(true, null, result.getData());
//		results.setData(result.getData());
		return results;
	}

	/**
	 * 
	 * get ShowPromotionPlan
	 * 
	 * @since 2018. 10. 11.
	 * @author Sanghyup Kim
	 * @param planId
	 * @return JsonResponse<GetShowPromotionPlanResponse>
	 */
	@RequestMapping(value = "show/promotion-plan/{planId}", method = RequestMethod.POST)
	@ApiOperation("site management > show info. - get show promotion-plan")
	public JsonResponse<GetShowPromotionPlanResponse> getShowPromotionPlan(@PathVariable("planId") Integer planId) {

		GetShowPromotionPlanResponse result = siteMgmtShowService.getShowPromotionPlan(planId);

		JsonResponse<GetShowPromotionPlanResponse> results = new JsonResponse<GetShowPromotionPlanResponse>(true, null,
				result);
//		results.setData(result);
		return results;
	}

	/**
	 * 
	 * set ShowPromotionPlan
	 * 
	 * @since 2018. 10. 11.
	 * @author Sanghyup Kim
	 * @param SetShowPromotionPlanParameters
	 * @return JsonResponse<Integer>
	 */
	@RequestMapping(value = "show/promotion-plan/save", method = RequestMethod.POST)
	@ApiOperation("site management > show info. - set show promotion-plan save")
	public JsonResponse<Integer> setShowPromotionPlan(@RequestBody SetShowPromotionPlanParameters parameters) {

		ResultResponse<Integer> result = siteMgmtShowService.setShowPromotionPlan(parameters);

		JsonResponse<Integer> results = new JsonResponse<Integer>(result.getSuccess(), result.getMessage(),
				result.getData());
//		results.setSuccess(result.getSuccess());
//		results.setMessage(result.getMessage());
//		results.setData(result.getData());

		return results;
	}

	/**
	 * 
	 * get ShowSchedule
	 * 
	 * @since 2018. 10. 11.
	 * @author Sanghyup Kim
	 * @param showScheduleId
	 * @return JsonResponse<ShowSchedule>
	 */
	@RequestMapping(value = "show/schedule/{showScheduleId}", method = RequestMethod.POST)
	@ApiOperation("site management > show info. - get show schedule")
	public JsonResponse<ShowSchedule> getShowSchedule(@PathVariable("showScheduleId") Integer showScheduleId) {

		ResultResponse<ShowSchedule> result = siteMgmtShowService.getShowScheduleDetail(showScheduleId);

		JsonResponse<ShowSchedule> results = new JsonResponse<ShowSchedule>(true, null, result.getData());
//		results.setData(result.getData());
		return results;
	}

	/**
	 * 
	 * get ShowParticipating Vendor
	 * 
	 * @since 2018. 10. 11.
	 * @author Sanghyup Kim
	 * @param mapId
	 * @return JsonResponse<MapShowSchedulePromotionPlanVendor>
	 */
	@RequestMapping(value = "show/participating-vendor/{mapId}", method = RequestMethod.POST)
	@ApiOperation("site management > show info. - get show participating vendor")
	public JsonResponse<MapShowSchedulePromotionPlanVendor> getShowParticipatingVendor(
			@PathVariable("mapId") Integer mapId) {

		ResultResponse<MapShowSchedulePromotionPlanVendor> result = siteMgmtShowService
				.getShowParticipatingVendor(mapId);

		JsonResponse<MapShowSchedulePromotionPlanVendor> results = new JsonResponse<MapShowSchedulePromotionPlanVendor>(
				true, null, result.getData());
//		results.setData(result.getData());
		return results;
	}

	/**
	 * 
	 * delete ShowParticipating Vendor
	 * 
	 * @since 2018. 10. 11.
	 * @author Sanghyup Kim
	 * @param DelShowParameter
	 * @return JsonResponse<Integer>
	 */
	@RequestMapping(value = "show/participating-vendor/delete", method = RequestMethod.POST)
	@ApiOperation("site management > show info. - set show participating-vendor delete")
	public JsonResponse<Integer> deleteShowParticipatingVendor(@RequestBody DelShowParameter parameters) {

		ResultResponse<Integer> result = siteMgmtShowService.setDeleteShowParticipatingVendor(parameters);

		JsonResponse<Integer> results = new JsonResponse<Integer>(result.getSuccess(), result.getMessage(),
				result.getData());
//		results.setSuccess(result.getSuccess());
//		results.setMessage(result.getMessage());

		return results;
	}
	

	@RequestMapping(value = "show/participating-vendor/cancel", method = RequestMethod.POST)
	@ApiOperation("site management > show info. - set show participating-vendor cancel")
	public JsonResponse<Integer> cancelShowParticipatingVendor(@RequestBody DelShowParameter parameters) {

		ResultResponse<Integer> result = siteMgmtShowService.setCancelShowParticipatingVendor(parameters);

		JsonResponse<Integer> results = new JsonResponse<Integer>(result.getSuccess(), result.getMessage(),
				result.getData());
//		results.setSuccess(result.getSuccess());
//		results.setMessage(result.getMessage());

		return results;
	}

	/**
	 * 
	 * delete ShowPromotionPlan
	 * 
	 * @since 2018. 10. 11.
	 * @author Sanghyup Kim
	 * @param DelShowParameter
	 * @return JsonResponse<Integer>
	 */
	@RequestMapping(value = "show/promotion-plan/delete", method = RequestMethod.POST)
	@ApiOperation("site management > show info. - set show promotion-plan delete")
	public JsonResponse<Integer> deleteShowPromotionPlan(@RequestBody DelShowParameter parameters) {

		ResultResponse<Integer> result = siteMgmtShowService.setDeleteShowPromotionPlan(parameters);

		JsonResponse<Integer> results = new JsonResponse<Integer>(result.getSuccess(), result.getMessage(),
				result.getData());
//		results.setSuccess(result.getSuccess());
//		results.setMessage(result.getMessage());

		return results;
	}

	/**
	 * n/a get Shows -> getShowList()
	 * 
	 * @since 2018. 10. 11.
	 * @author Sanghyup Kim
	 * @param
	 * @return
	 */
	@Deprecated
	@RequestMapping(value = "show", method = RequestMethod.POST)
	@ApiOperation("site management > show info. - get shows")
	public JsonResponse<Object> getShows() {

		JsonResponse<Object> results = new JsonResponse<Object>();

		return results;
	}

	/**
	 * n/a get ShowScheduleList -> getShowSchedules()
	 * 
	 * @since 2018. 10. 11.
	 * @author Sanghyup Kim
	 * @param
	 * @return
	 */
	@Deprecated
	@RequestMapping(value = "getshowschedulelist", method = RequestMethod.POST)
	@ApiOperation("site management > show info. - get show schedule list")
	public JsonResponse<Object> getShowScheduleList() {

		JsonResponse<Object> results = new JsonResponse<Object>();

		return results;
	}

	/**
	 * @author Kelly Back
	 * @since 06-27-2019
	 */
	@GetMapping(value = "show/simpleActiveShows")
	public JsonResponse<List<ShowInfoDto>> getSimpleActiveShows() {
		JsonResponse<List<ShowInfoDto>> response = new JsonResponse<>(false, null, null);

		try {
			List<ShowInfoDto> result = siteMgmtShowService.getSimpleActiveShows();
			response.setSuccess(true);
			response.setData(result);
		} catch(Exception ex) {
			log.error("Exception Error: ", ex);
			response.setMessage(ex.getMessage());
		}

		return response;
	}

	/**
	 * @author Kelly Back
	 * @since 07-05-2019
	 */
	@GetMapping(value = "show/participatingVendors/{showScheduleId}")
	public JsonResponse<List<Integer>> getShowParticipatingVendorIds(@PathVariable("showScheduleId") Integer showScheduleId) {
		JsonResponse<List<Integer>> response = new JsonResponse<>(false, null, null);

		try {
			List<Integer> result = siteMgmtShowService.getShowParticipatingVendorIds(showScheduleId);
			response.setSuccess(true);
			response.setData(result);
		} catch(Exception ex) {
			log.error("Exception Error: ", ex);
			response.setMessage(ex.getMessage());
		}

		return response;
	}
}
