package net.fashiongo.webadmin.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fashiongo.webadmin.model.pojo.ResultResponse;
import net.fashiongo.webadmin.model.pojo.parameter.DelShowParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetShowListParameters;
import net.fashiongo.webadmin.model.pojo.parameter.GetShowParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetShowScheduleListParameters;
import net.fashiongo.webadmin.model.pojo.parameter.SetShowInfoParameters;
import net.fashiongo.webadmin.model.pojo.parameter.SetShowParameters;
import net.fashiongo.webadmin.model.pojo.parameter.SetShowParticipatingVendorParameters;
import net.fashiongo.webadmin.model.pojo.parameter.SetShowScheduleParameters;
import net.fashiongo.webadmin.model.pojo.response.GetShowCategoriesResponse;
import net.fashiongo.webadmin.model.pojo.response.GetShowListResponse;
import net.fashiongo.webadmin.model.pojo.response.GetShowParticipatingVendorsResponse;
import net.fashiongo.webadmin.model.pojo.response.GetShowPromotionPlanResponse;
import net.fashiongo.webadmin.model.pojo.response.GetShowScheduleListResponse;
import net.fashiongo.webadmin.model.primary.ListShow;
import net.fashiongo.webadmin.model.primary.MapShowSchedulePromotionPlanVendor;
import net.fashiongo.webadmin.model.primary.ShowSchedule;
import net.fashiongo.webadmin.model.primary.ShowSchedulePromotionPlan;
import net.fashiongo.webadmin.service.SitemgmtShowService;
import net.fashiongo.webadmin.utility.JsonResponse;

/*
 * @author Sanghyup Kim
 */
@Api(description = "Site Management Show", tags = { "sitemgmt-show" })
@RestController
@Consumes(MediaType.APPLICATION_JSON)
@RequestMapping(value = "/sitemgmt", produces = "application/json")
public class SitemgmtShowController {

	@Autowired
	SitemgmtShowService siteMgmtShowService;

	/**
	 * 
	 * getShowList
	 * 
	 * @since 2018. 10. 15.
	 * @author Sanghyup Kim
	 * @param
	 * @return
	 */
	@RequestMapping(value = "getshowlist", method = RequestMethod.POST)
	@ApiOperation("site management > show info. - get show list")
	public JsonResponse<GetShowListResponse> getShowList(@RequestBody GetShowListParameters parameters) {

		JsonResponse<GetShowListResponse> results = new JsonResponse<GetShowListResponse>();

		GetShowListResponse data = siteMgmtShowService.getShowList(parameters);
		results.setData(data);

		return results;
	}

	/**
	 * 
	 * 
	 * 
	 * @since 2018. 10. 15.
	 * @author Sanghyup Kim
	 * @param
	 * @return
	 */
	@RequestMapping(value = "setshowinfo", method = RequestMethod.POST)
	@ApiOperation("site management > show info. - set show info")
	public JsonResponse<Integer> setShowInfo(@RequestBody SetShowInfoParameters parameters) {

		JsonResponse<Integer> results = new JsonResponse<Integer>();

		ResultResponse<Integer> result = siteMgmtShowService.setShowInfo(parameters);
		results.setCode(result.getCode());
		results.setData(result.getData());
		results.setMessage(result.getMessage());
		results.setPk(result.getPk());
		results.setSuccess(result.getSuccess());

		return results;
	}

	/**
	 * 
	 * 
	 * 
	 * @since 2018. 10. 15.
	 * @author Sanghyup Kim
	 * @param
	 * @return
	 */
	@RequestMapping(value = "show/{showId}", method = RequestMethod.POST)
	@ApiOperation("site management > show info. - get show")
	public JsonResponse<ListShow> getShow(@PathVariable("showId") Integer showID) {

		JsonResponse<ListShow> results = new JsonResponse<ListShow>();

		ResultResponse<ListShow> result = siteMgmtShowService.getShowDetail(showID);
		results.setData(result.getData());
		return results;
	}

	/**
	 * 
	 * 
	 * 
	 * @since 2018. 10. 15.
	 * @author Sanghyup Kim
	 * @param
	 * @return
	 */
	@RequestMapping(value = "show/schedule", method = RequestMethod.POST)
	@ApiOperation("site management > show info. - get show schedule list")
	public JsonResponse<GetShowScheduleListResponse> getShowSchedules(
			@RequestBody GetShowScheduleListParameters parameters) {

		JsonResponse<GetShowScheduleListResponse> results = new JsonResponse<GetShowScheduleListResponse>();
		GetShowScheduleListResponse data = siteMgmtShowService.getShowScheduleList(parameters);

		results.setData(data);
		return results;
	}

	/**
	 * 
	 * 
	 * 
	 * @since 2018. 10. 16.
	 * @author Sanghyup Kim
	 * @param
	 * @return
	 */
	@RequestMapping(value = "show/save", method = RequestMethod.POST)
	@ApiOperation("site management > show info. - set show save")
	public JsonResponse<SetShowParameters> setShow(@RequestBody SetShowParameters parameters) {

		JsonResponse<SetShowParameters> results = new JsonResponse<SetShowParameters>();
		ResultResponse<Integer> result = siteMgmtShowService.setShow(parameters);

		parameters.setShowId(result.getData());

		results.setData(parameters);
		return results;
	}

	/**
	 * 
	 * delete showschedule
	 * 
	 * @since 2018. 10. 17.
	 * @author Sanghyup Kim
	 * @param
	 * @return
	 */
	@RequestMapping(value = "show/schedule/delete", method = RequestMethod.POST)
	@ApiOperation("site management > show info. - set show schedule delete")
	public JsonResponse<Integer> deleteShowSchedule(@RequestBody DelShowParameter parameters) {

		JsonResponse<Integer> results = new JsonResponse<Integer>();
		ResultResponse<Integer> result = siteMgmtShowService.setDeleteShowSchedule(parameters);

		results.setSuccess(result.getSuccess());
		results.setMessage(result.getMessage());

		return results;
	}

	/**
	 * 
	 * delete show
	 * 
	 * @since 2018. 10. 17.
	 * @author Sanghyup Kim
	 * @param
	 * @return
	 */
	@RequestMapping(value = "show/delete", method = RequestMethod.POST)
	@ApiOperation("site management > show info. - set show delete")
	public JsonResponse<Integer> deleteShow(@RequestBody DelShowParameter parameters) {

		JsonResponse<Integer> results = new JsonResponse<Integer>();
		ResultResponse<Integer> result = siteMgmtShowService.setDeleteShow(parameters);

		results.setSuccess(result.getSuccess());
		results.setMessage(result.getMessage());

		return results;
	}

	/**
	 * 
	 * 
	 * 
	 * @since 2018. 10. 11.
	 * @author Sanghyup Kim
	 * @param
	 * @return
	 */
	@RequestMapping(value = "show/schedule/save", method = RequestMethod.POST)
	@ApiOperation("site management > show info. - set show schedule save")
	public JsonResponse<SetShowScheduleParameters> setShowSchedule(@RequestBody SetShowScheduleParameters parameters)
			throws Exception {

		JsonResponse<SetShowScheduleParameters> results = new JsonResponse<SetShowScheduleParameters>();
		ResultResponse<Integer> result = siteMgmtShowService.setShowSchedule(parameters);

		parameters.setShowScheduleId(result.getData());

		results.setSuccess(result.getSuccess());
		results.setMessage(result.getMessage());
		results.setData(parameters);

		return results;
	}

	/**
	 * 
	 * getshowcategorylist (for comobobox)
	 * 
	 * @since 2018. 10. 11.
	 * @author Sanghyup Kim
	 * @param
	 * @return
	 */
	@RequestMapping(value = "getshowcategorylist", method = RequestMethod.POST)
	@ApiOperation("site management > show info. - get show category list")
	public JsonResponse<GetShowCategoriesResponse> getShowCategories(@RequestBody GetShowParameter parameters) {

		JsonResponse<GetShowCategoriesResponse> results = new JsonResponse<GetShowCategoriesResponse>();
		GetShowCategoriesResponse getShowCategoriesResponse = siteMgmtShowService.getShowCategories(parameters);

		results.setData(getShowCategoriesResponse);

		return results;
	}

	/**
	 * 
	 * 
	 * 
	 * @since 2018. 10. 18.
	 * @author Sanghyup Kim
	 * @param
	 * @return
	 */
	@RequestMapping(value = "show/promotion-plan", method = RequestMethod.POST)
	@ApiOperation("site management > show info. - get show promotion-plans")
	public JsonResponse<List<ShowSchedulePromotionPlan>> getShowPromotionPlans(
			@RequestBody GetShowParameter parameters) {

		JsonResponse<List<ShowSchedulePromotionPlan>> results = new JsonResponse<List<ShowSchedulePromotionPlan>>();
		List<ShowSchedulePromotionPlan> showSchedulePromotionPlanList = siteMgmtShowService
				.getShowPromotionPlans(parameters);

		results.setData(showSchedulePromotionPlanList);
		return results;
	}

	/**
	 * 
	 * 
	 * 
	 * @since 2018. 10. 11.
	 * @author Sanghyup Kim
	 * @param
	 * @return
	 */
	@RequestMapping(value = "show/participating-vendor", method = RequestMethod.POST)
	@ApiOperation("site management > show info. - get show participating vendors")
	public JsonResponse<GetShowParticipatingVendorsResponse> getShowParticipatingVendors(
			@RequestBody GetShowParameter parameters) {

		JsonResponse<GetShowParticipatingVendorsResponse> results = new JsonResponse<GetShowParticipatingVendorsResponse>();
		GetShowParticipatingVendorsResponse getShowCategoriesResponse = siteMgmtShowService
				.getShowParticipatingVendors(parameters);

		results.setData(getShowCategoriesResponse);
		return results;
	}

	/**
	 * 
	 * 
	 * 
	 * @since 2018. 10. 11.
	 * @author Sanghyup Kim
	 * @param
	 * @return
	 */
	@RequestMapping(value = "getshowschedulelist", method = RequestMethod.POST)
	@ApiOperation("site management > show info. - get show schedule list")
	public JsonResponse<Object> getShowScheduleList() {

		JsonResponse<Object> results = new JsonResponse<Object>();

		return results;
	}

	/**
	 * 
	 * 
	 * 
	 * @since 2018. 10. 11.
	 * @author Sanghyup Kim
	 * @param
	 * @return
	 */
	@RequestMapping(value = "show/participating-vendor/save", method = RequestMethod.POST)
	@ApiOperation("site management > show info. - set show participating-vendor save")
	public JsonResponse<Integer> setShowParticipatingVendor(
			@RequestBody SetShowParticipatingVendorParameters parameters) {

		JsonResponse<Integer> results = new JsonResponse<Integer>();
		ResultResponse<Integer> result = siteMgmtShowService.setShowParticipatingVendor(parameters);

		results.setData(result.getData());
		return results;
	}

	/**
	 * 
	 * 
	 * 
	 * @since 2018. 10. 11.
	 * @author Sanghyup Kim
	 * @param
	 * @return
	 */
	@RequestMapping(value = "show", method = RequestMethod.POST)
	@ApiOperation("site management > show info. - get shows")
	public JsonResponse<Object> getShows() {

		JsonResponse<Object> results = new JsonResponse<Object>();

		return results;
	}

	/**
	 * 
	 * 
	 * 
	 * @since 2018. 10. 11.
	 * @author Sanghyup Kim
	 * @param
	 * @return
	 */
	@RequestMapping(value = "show/promotion-plan/{planId}", method = RequestMethod.POST)
	@ApiOperation("site management > show info. - get show promotion-plan")
	public JsonResponse<GetShowPromotionPlanResponse> getShowPromotionPlan(@PathVariable("planId") Integer planId) {

		JsonResponse<GetShowPromotionPlanResponse> results = new JsonResponse<GetShowPromotionPlanResponse>();

		GetShowPromotionPlanResponse result = siteMgmtShowService.getShowPromotionPlan(planId);
		results.setData(result);
		return results;
	}

	/**
	 * 
	 * 
	 * 
	 * @since 2018. 10. 11.
	 * @author Sanghyup Kim
	 * @param
	 * @return
	 */
	@RequestMapping(value = "show/promotion-plan/save", method = RequestMethod.POST)
	@ApiOperation("site management > show info. - set show promotion-plan save")
	public JsonResponse<Object> setShowPromotionPlan() {

		JsonResponse<Object> results = new JsonResponse<Object>();

		return results;
	}

	/**
	 * 
	 * 
	 * 
	 * @since 2018. 10. 11.
	 * @author Sanghyup Kim
	 * @param
	 * @return
	 */
	@RequestMapping(value = "show/schedule/{showScheduleId}", method = RequestMethod.POST)
	@ApiOperation("site management > show info. - get show schedule")
	public JsonResponse<ShowSchedule> getShowSchedule(@PathVariable("showScheduleId") Integer showScheduleId) {

		JsonResponse<ShowSchedule> results = new JsonResponse<ShowSchedule>();

		ResultResponse<ShowSchedule> result = siteMgmtShowService.getShowScheduleDetail(showScheduleId);
		results.setData(result.getData());
		return results;
	}

	/**
	 * 
	 * 
	 * 
	 * @since 2018. 10. 11.
	 * @author Sanghyup Kim
	 * @param
	 * @return
	 */
	@RequestMapping(value = "show/participating-vendor/{mapId}", method = RequestMethod.POST)
	@ApiOperation("site management > show info. - get show participating vendor")
	public JsonResponse<MapShowSchedulePromotionPlanVendor> getShowParticipatingVendor(
			@PathVariable("mapId") Integer mapId) {

		JsonResponse<MapShowSchedulePromotionPlanVendor> results = new JsonResponse<MapShowSchedulePromotionPlanVendor>();

		ResultResponse<MapShowSchedulePromotionPlanVendor> result = siteMgmtShowService
				.getShowParticipatingVendor(mapId);
		results.setData(result.getData());
		return results;
	}

	/**
	 * 
	 * 
	 * 
	 * @since 2018. 10. 11.
	 * @author Sanghyup Kim
	 * @param
	 * @return
	 */
	@RequestMapping(value = "show/participating-vendor/delete", method = RequestMethod.POST)
	@ApiOperation("site management > show info. - set show participating-vendor delete")
	public JsonResponse<Object> deleteShowParticipatingVendor() {

		JsonResponse<Object> results = new JsonResponse<Object>();

		return results;
	}

	/**
	 * 
	 * 
	 * 
	 * @since 2018. 10. 11.
	 * @author Sanghyup Kim
	 * @param
	 * @return
	 */
	@RequestMapping(value = "show/promotion-plan/delete", method = RequestMethod.POST)
	@ApiOperation("site management > show info. - set show promotion-plan delete")
	public JsonResponse<Object> deleteShowPromotionPlan() {

		JsonResponse<Object> results = new JsonResponse<Object>();

		return results;
	}

}
