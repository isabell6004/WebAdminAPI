package net.fashiongo.webadmin.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
	 * 
	 * 
	 * @since 2018. 10. 11.
	 * @author Sanghyup Kim
	 * @param
	 * @return
	 */
	@RequestMapping(value = "getshowcategorylist", method = RequestMethod.POST)
	@ApiOperation("site management > show info. - get show category list")
	public JsonResponse<Object> getShowCategories() {

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
	@RequestMapping(value = "getshowlist", method = RequestMethod.POST)
	@ApiOperation("site management > show info. - get show list")
	public JsonResponse<Object> getShowList() {

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
	@RequestMapping(value = "setshowinfo", method = RequestMethod.POST)
	@ApiOperation("site management > show info. - set show info")
	public JsonResponse<Object> setShowInfo() {

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
	public JsonResponse<Object> setShowParticipatingVendor() {

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
	@RequestMapping(value = "show/{showId}", method = RequestMethod.POST)
	@ApiOperation("site management > show info. - get show")
	public JsonResponse<Object> getShow(@PathVariable("showId") Integer showId) {

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
	@RequestMapping(value = "show/promotion-plan", method = RequestMethod.POST)
	@ApiOperation("site management > show info. - get show promotion-plans")
	public JsonResponse<Object> getShowPromotionPlans() {

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
	public JsonResponse<Object> getShowPromotionPlan(@PathVariable("planId") Integer planId) {

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
	public JsonResponse<Object> getShowSchedule(@PathVariable("showScheduleId") Integer showScheduleId) {

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
	@RequestMapping(value = "show/participating-vendor", method = RequestMethod.POST)
	@ApiOperation("site management > show info. - get show participating vendors")
	public JsonResponse<Object> getShowParticipatingVendors() {

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
	@RequestMapping(value = "show/participating-vendor/{mapId}", method = RequestMethod.POST)
	@ApiOperation("site management > show info. - get show participating vendor")
	public JsonResponse<Object> getShowParticipatingVendor(@PathVariable("mapId") Integer mapId) {

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
	@RequestMapping(value = "show/save", method = RequestMethod.POST)
	@ApiOperation("site management > show info. - set show save")
	public JsonResponse<Object> setShow() {

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
	@RequestMapping(value = "show/schedule/save", method = RequestMethod.POST)
	@ApiOperation("site management > show info. - set show schedule save")
	public JsonResponse<Object> setShowSchedule() {

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
	@RequestMapping(value = "show/delete", method = RequestMethod.POST)
	@ApiOperation("site management > show info. - set show delete")
	public JsonResponse<Object> deleteShow() {

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
	@RequestMapping(value = "show/schedule/delete", method = RequestMethod.POST)
	@ApiOperation("site management > show info. - set show schedule delete")
	public JsonResponse<Object> deleteShowSchedule() {

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

	/**
	 * 
	 * 
	 * 
	 * @since 2018. 10. 11.
	 * @author Sanghyup Kim
	 * @param
	 * @return
	 */
	@RequestMapping(value = "show/schedule", method = RequestMethod.POST)
	@ApiOperation("site management > show info. - get show schedule list")
	public JsonResponse<Object> getShowSchedules() {

		JsonResponse<Object> results = new JsonResponse<Object>();

		return results;
	}

}
