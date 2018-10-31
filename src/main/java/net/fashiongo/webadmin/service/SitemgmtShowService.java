package net.fashiongo.webadmin.service;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import net.fashiongo.webadmin.common.SingleValueResult;
import net.fashiongo.webadmin.dao.primary.ListShowRepository;
import net.fashiongo.webadmin.dao.primary.show.MapShowSchedulePromotionPlanVendorRepository;
import net.fashiongo.webadmin.dao.primary.show.MapShowScheduleWholeSalerRepository;
import net.fashiongo.webadmin.dao.primary.show.ShowSchedulePromotionPlanRepository;
import net.fashiongo.webadmin.dao.primary.show.ShowScheduleRepository;
import net.fashiongo.webadmin.model.pojo.ResultResponse;
import net.fashiongo.webadmin.model.pojo.parameter.show.DelShowParameter;
import net.fashiongo.webadmin.model.pojo.parameter.show.GetShowListParameters;
import net.fashiongo.webadmin.model.pojo.parameter.show.GetShowParameter;
import net.fashiongo.webadmin.model.pojo.parameter.show.GetShowScheduleListParameters;
import net.fashiongo.webadmin.model.pojo.parameter.show.SetShowInfoParameters;
import net.fashiongo.webadmin.model.pojo.parameter.show.SetShowParameters;
import net.fashiongo.webadmin.model.pojo.parameter.show.SetShowParticipatingVendorParameters;
import net.fashiongo.webadmin.model.pojo.parameter.show.SetShowPromotionPlanParameters;
import net.fashiongo.webadmin.model.pojo.parameter.show.SetShowScheduleParameters;
import net.fashiongo.webadmin.model.pojo.response.show.GetShowCategoriesResponse;
import net.fashiongo.webadmin.model.pojo.response.show.GetShowListResponse;
import net.fashiongo.webadmin.model.pojo.response.show.GetShowParticipatingVendorsResponse;
import net.fashiongo.webadmin.model.pojo.response.show.GetShowPromotionPlanResponse;
import net.fashiongo.webadmin.model.pojo.response.show.GetShowScheduleListResponse;
import net.fashiongo.webadmin.model.primary.ShowSchedule;
import net.fashiongo.webadmin.model.primary.show.ListShow;
import net.fashiongo.webadmin.model.primary.show.MapShowSchedulePromotionPlanVendor;
import net.fashiongo.webadmin.model.primary.show.MapShowScheduleWholeSaler;
import net.fashiongo.webadmin.model.primary.show.ShowSchedulePromotionPlan;

/**
 * 
 * @author Sanghyup Kim
 */
@Service
public class SitemgmtShowService extends ApiService {

	@Autowired
	ListShowRepository listShowRepository;

	@Autowired
	ShowScheduleRepository showScheduleRepository;

	@Autowired
	ShowSchedulePromotionPlanRepository showSchedulePromotionPlanRepository;

	@Autowired
	MapShowSchedulePromotionPlanVendorRepository mapShowSchedulePromotionPlanVendorRepository;

	@Autowired
	MapShowScheduleWholeSalerRepository mapShowScheduleWholeSalerRepository;

	/**
	 * 
	 * Get Show List
	 * 
	 * @since 2018. 10. 15.
	 * @author Sanghyup Kim
	 * @param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public GetShowListResponse getShowList(GetShowListParameters parameters) {

		List<Object> params = new ArrayList<Object>();

		// add parameters
		final Integer pageNum = parameters.getPageNum();
		final Integer pageSize = parameters.getPageSize();
		final Integer active = parameters.getActive();
		final String location = parameters.getLocation();
		String orderBy = parameters.getOrderBy();
//		if (orderBy == "")
//			orderBy = null;
		final String showName = parameters.getShowName();

		final LocalDateTime fromDate = parameters.getFromDate();
		final LocalDateTime toDate = parameters.getToDate();

		params.add(pageNum);
		params.add(pageSize);
		params.add(showName);
		params.add(location);
		params.add(active);
		params.add(orderBy);

		params.add(fromDate);
		params.add(toDate);

		final String spName = "up_wa_GetAdminShowList";

		GetShowListResponse resultSet = new GetShowListResponse();
		final List<Object> _result = jdbcHelper.executeSP(spName, params, SingleValueResult.class, ListShow.class);

		final List<SingleValueResult> singleValueResultList = (List<SingleValueResult>) _result.get(0);
		final List<ListShow> showList = (List<ListShow>) _result.get(1);

		resultSet.setSingleValueResultList(singleValueResultList);
		resultSet.setShowList(showList);

		return resultSet;
	}

	/**
	 * 
	 * set ShowInfo
	 * 
	 * @since 2018. 10. 15.
	 * @author Sanghyup Kim
	 * @param
	 * @return
	 */
	public ResultResponse<Integer> setShowInfo(SetShowInfoParameters parameters) {

		ResultResponse<Integer> result = new ResultResponse<Integer>(false, -1, 0, null, null);

		final String crud = parameters.getCrud();
		final String sType = parameters.getSType();

		// list_show
		Integer showID = parameters.getShowId();
		final String location = parameters.getLocation();
		final String showName = parameters.getShowName();
		final String url = parameters.getShowUrl();
		Boolean active = parameters.getActive();
//		if (active == null) {
//			active = false;
//		}

		final String showCode = null;

		// showschedule
		final Integer showScheduleID = parameters.getShowScheduleId();
		final Integer listOrder = parameters.getListOrder();
		final LocalDateTime dateFrom = parameters.getDateFrom();
		final LocalDateTime dateTo = parameters.getDateTo();
		final String bannerImage = null;
		final String mobileImage = null;
		final String titleImage = null;

		ListShow listShow;
		switch (sType) {
		case "show":
			switch (crud) {
			case "I": // insert
				listShow = new ListShow();
				ListShow listShow2 = listShowRepository.findTopByOrderByShowIDDesc();
				if (listShow2 != null) {
					showID = listShow2.getShowID() + 1;
				}

				listShow.setShowID(showID);
				listShow.setActive(active);
				listShow.setLocation(location);
				listShow.setShowCode(showCode);
				listShow.setShowName(showName);
				listShow.setUrl(url);

				listShowRepository.save(listShow);
				result.setResultWrapper(true, 1, showID, MSG_INSERT_SUCCESS, 1);

				break;
			case "U": // update
				listShow = listShowRepository.findOneByShowID(showID);
				if (listShow != null) {
					listShow.setActive(active);
					listShow.setLocation(location);
					listShow.setShowCode(showCode);
					listShow.setShowName(showName);
					listShow.setUrl(url);

					listShowRepository.save(listShow);
					result.setResultWrapper(true, 1, showID, MSG_UPDATE_SUCCESS, 1);
				}

				break;
			case "A": // active/inactive
				listShow = listShowRepository.findOneByShowID(showID);
				if (listShow != null) {
					listShow.setActive(active);

					listShowRepository.save(listShow);
					result.setResultWrapper(true, 1, showID, MSG_CHANGE_SUCCESS, 1);
				}

				break;
			case "D": // delete
				listShowRepository.deleteById(showID);
				result.setResultWrapper(true, 1, showID, MSG_DELETE_SUCCESS, 1);

				break;

			default:
				break;
			}
			break;
		case "schedule":
			ShowSchedule showSchedule;
			switch (crud) {
			case "I": // insert
				showSchedule = new ShowSchedule();
//				showSchedule.setShowScheduleID(showScheduleID);
				showSchedule.setShowID(showID);
				showSchedule.setActive(active);
				showSchedule.setDateFrom(dateFrom);
				showSchedule.setDateTo(dateTo);
				showSchedule.setListOrder(listOrder);
				showSchedule.setBannerImage(bannerImage);
				showSchedule.setMobileImage(mobileImage);
				showSchedule.setTitleImage(titleImage);

				showScheduleRepository.save(showSchedule);
				result.setResultWrapper(true, 1, showSchedule.getShowScheduleID(), MSG_INSERT_SUCCESS, 1);

				break;
			case "U": // update
				showSchedule = showScheduleRepository.findOneByShowScheduleID(showScheduleID);
				if (showSchedule != null) {
					showSchedule.setShowID(showID);
					showSchedule.setActive(active);
					showSchedule.setDateFrom(dateFrom);
					showSchedule.setDateTo(dateTo);
					showSchedule.setListOrder(listOrder);
					showSchedule.setBannerImage(bannerImage);
					showSchedule.setMobileImage(mobileImage);
					showSchedule.setTitleImage(titleImage);

					showScheduleRepository.save(showSchedule);
					result.setResultWrapper(true, 1, showScheduleID, MSG_UPDATE_SUCCESS, 1);
				}

				break;
			case "A": // active/inactive
				showSchedule = showScheduleRepository.findOneByShowScheduleID(showScheduleID);
				if (showSchedule != null) {
					showSchedule.setActive(active);

					showScheduleRepository.save(showSchedule);
					result.setResultWrapper(true, 1, showScheduleID, MSG_CHANGE_SUCCESS, 1);
				}

				break;
			case "D": // delete
				showScheduleRepository.deleteById(showScheduleID);
				result.setResultWrapper(true, 1, showScheduleID, MSG_DELETE_SUCCESS, 1);

				break;

			default:
				break;
			}

			break;

		default:
			break;
		}

		return result;
	}

	/**
	 * 
	 * Get Show detail
	 * 
	 * @since 2018. 10. 15.
	 * @author Sanghyup Kim
	 * @param
	 * @return
	 */
	public ResultResponse<ListShow> getShowDetail(Integer showID) {

		ResultResponse<ListShow> result = new ResultResponse<ListShow>();

		ListShow listShow = listShowRepository.findOneByShowID(showID);
		result.setData(listShow);
		return result;
	}

	/**
	 * 
	 * Get Show List
	 * 
	 * @since 2018. 10. 16.
	 * @author Sanghyup Kim
	 * @param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public GetShowScheduleListResponse getShowScheduleList(GetShowScheduleListParameters parameters) {

		List<Object> params = new ArrayList<Object>();

		// add parameters
		final Integer showId = parameters.getShowId();
		final Integer pageNum = parameters.getPageNum();
		final Integer pageSize = parameters.getPageSize();
		final Integer active = parameters.getActive();

		String showName = parameters.getShowName();
//		if (showName == "")
//			showName = null;
		String location = parameters.getLocation();
//		if (location == "")
//			location = null;
		String orderBy = parameters.getOrderBy();
//		if (orderBy == "")
//			orderBy = null;

		LocalDateTime dateFrom = null;
		LocalDateTime dateTo = null;

		String df = parameters.getDateFrom();
		String dt = parameters.getDateTo();

		DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("M/d/yyyy HH:mm:ss");
		if (StringUtils.isNotEmpty(df)) {
			dateFrom = LocalDateTime.parse(parameters.getDateFrom(), dtFormatter);
		}
		if (StringUtils.isNotEmpty(dt)) {
			dateTo = LocalDateTime.parse(parameters.getDateTo(), dtFormatter);
		}

		params.add(pageNum);
		params.add(pageSize);
		params.add(showId);
		params.add(showName);
		params.add(location);
		params.add(active);
		params.add(orderBy);
		params.add(dateFrom);
		params.add(dateTo);

		final String spName = "up_wa_GetAdminShowSchedule2";

		GetShowScheduleListResponse resultSet = new GetShowScheduleListResponse();
		final List<Object> _result = jdbcHelper.executeSP(spName, params, SingleValueResult.class, ShowSchedule.class);

		final List<SingleValueResult> singleValueResultList = (List<SingleValueResult>) _result.get(0);
//		final List<SingleValueResult> rs1 = (List<SingleValueResult>) _results.get(0);
		final List<ShowSchedule> showScheduleList = (List<ShowSchedule>) _result.get(1);

		resultSet.setSingleValueResultList(singleValueResultList);
		resultSet.setShowScheduleList(showScheduleList);
		;

		return resultSet;
	}

	/**
	 * 
	 * set Show
	 * 
	 * @since 2018. 10. 16.
	 * @author Sanghyup Kim
	 * @param
	 * @return
	 */
	public ResultResponse<Integer> setShow(SetShowParameters parameters) {

		ResultResponse<Integer> result = new ResultResponse<Integer>(false, -1, 0, null, null);

		Integer showID = parameters.getShowId();
		Boolean active = parameters.getActive();
//		if (active == null) {
//			active = false;
//		}
		String location = parameters.getLocation();
		String showName = parameters.getShowName();
		String url = parameters.getUrl();

		ListShow listShow;
		if (showID > 0) { // update
			listShow = listShowRepository.findOneByShowID(showID);
			if (listShow != null) {
				listShow.setActive(active);
				listShow.setLocation(location);
				listShow.setShowName(showName);
				listShow.setUrl(url);

				listShowRepository.save(listShow);
			}
		} else { // insert
			listShow = new ListShow();
			ListShow listShow2 = listShowRepository.findTopByOrderByShowIDDesc();
			if (listShow2 != null) {
				showID = listShow2.getShowID() + 1;
			}

			listShow.setShowID(showID);
			listShow.setActive(active);
			listShow.setLocation(location);
			listShow.setShowName(showName);
			listShow.setUrl(url);

			listShowRepository.save(listShow);
		}

		result.setResultWrapper(true, 1, showID, MSG_SAVE_SUCCESS, showID);

		return result;
	}

	/**
	 * 
	 * set DeleteShow
	 * 
	 * @since 2018. 10. 16.
	 * @author Sanghyup Kim
	 * @param
	 * @return
	 */
	public ResultResponse<Integer> setDeleteShow(DelShowParameter parameters) {
		Integer showID = parameters.getShowID();

		ResultResponse<Integer> result = new ResultResponse<Integer>(false, -1, 0, null, null);

//		int size = showScheduleRepository.findByShowID(showID).size();
//		if (size > 0) {
		List<ShowSchedule> showScheduleList = showScheduleRepository.findByShowID(showID);

		if (!CollectionUtils.isEmpty(showScheduleList)) {
			result.setMessage("Unable to delete show. There are still schedules linked to this show.");
			return result;
		}

		listShowRepository.deleteById(showID);

		result.setResultWrapper(true, 1, showID, MSG_DELETE_SUCCESS, showID);

		return result;
	}

	/**
	 * 
	 * Delete ShowSchedule List
	 * 
	 * @since 2018. 10. 16.
	 * @author Sanghyup Kim
	 * @param
	 * @return
	 */
	public ResultResponse<Integer> setDeleteShowSchedule(DelShowParameter parameters) {
//		Integer showID = parameters.getShowID();
		Integer showScheduleID = parameters.getShowScheduleID();

		ResultResponse<Integer> result = new ResultResponse<Integer>(false, -1, 0, null, null);

//		int size = showSchedulePromotionPlanRepository.findByShowScheduleID(showScheduleID).size();
//		if (size > 0) {
		List<ShowSchedulePromotionPlan> showSchedulePromotionPlanList = showSchedulePromotionPlanRepository.findByShowScheduleID(showScheduleID);

		if (!CollectionUtils.isEmpty(showSchedulePromotionPlanList) ) {
			result.setMessage(
					"Unable to delete schedule. Either the Show Schedule date has passed or there are still plans/vendors linked to this schedule.");
			return result;
		}

		showScheduleRepository.deleteById(showScheduleID);

		result.setResultWrapper(true, 1, showScheduleID, MSG_DELETE_SUCCESS, showScheduleID);

		return result;
	}

	/**
	 * 
	 * Set Showschedule
	 * 
	 * @since 2018. 10. 17.
	 * @author Sanghyup Kim
	 * @param
	 * @return
	 */
	public ResultResponse<Integer> setShowSchedule(SetShowScheduleParameters parameters) throws Exception {

		ResultResponse<Integer> result = new ResultResponse<Integer>(false, -1, 0, null, null);

		Integer showScheduleID = parameters.getShowScheduleId();
		Integer showID = parameters.getShowId();

		String bannerImage = parameters.getBannerImage();
		String mobileImage = parameters.getMobileImage();
		String titleImage = parameters.getTitleImage();

		SimpleDateFormat dt = new SimpleDateFormat("MM/dd/yyyy");

		Date dateFrom = dt.parse(parameters.getDateFrom());
		Date dateTo = dt.parse(parameters.getDateTo());
		if (dateFrom == null || dateTo == null) {
			result.setMessage("FromDate or ToDate is null!");
			return result;
		}

		Instant instant = dateFrom.toInstant();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		LocalDateTime dateFrom2 = instant.atZone(defaultZoneId).toLocalDateTime();

		instant = dateTo.toInstant();
		LocalDateTime dateTo2 = instant.atZone(defaultZoneId).toLocalDateTime();

		Integer listOrder = parameters.getListOrder();
//		if (listOrder == null) {
//			listOrder = 0;
//		}
		Boolean active = parameters.getActive();
		if (active == null) {
			active = false;
		}

		ShowSchedule showSchedule;
		if (showScheduleID > 0) { // update
			showSchedule = showScheduleRepository.findOneByShowScheduleID(showScheduleID);
			if (showSchedule != null) {
				showSchedule.setActive(active);
				showSchedule.setBannerImage(bannerImage);
				showSchedule.setDateFrom(dateFrom2);
				showSchedule.setDateTo(dateTo2);
				showSchedule.setListOrder(listOrder);
				showSchedule.setMobileImage(mobileImage);
				showSchedule.setShowID(showID);
				showSchedule.setTitleImage(titleImage);

				showScheduleRepository.save(showSchedule);
			}
		} else { // insert
			showSchedule = new ShowSchedule();

			showSchedule.setShowScheduleID(showScheduleID);

			showSchedule.setActive(active);
			showSchedule.setBannerImage(bannerImage);
			showSchedule.setDateFrom(dateFrom2);
			showSchedule.setDateTo(dateTo2);
			showSchedule.setListOrder(listOrder);
			showSchedule.setMobileImage(mobileImage);
			showSchedule.setShowID(showID);
			showSchedule.setTitleImage(titleImage);

			ShowSchedule showSchedule2 = showScheduleRepository.save(showSchedule);
			showScheduleID = showSchedule2.getShowScheduleID();
		}

		result.setResultWrapper(true, 1, showScheduleID, MSG_SAVE_SUCCESS, showScheduleID);

		return result;
	}

	/**
	 * 
	 * Get Showschedule detail
	 * 
	 * @since 2018. 10. 17.
	 * @author Sanghyup Kim
	 * @param
	 * @return
	 */
	public ResultResponse<ShowSchedule> getShowScheduleDetail(Integer showScheduleID) {

		ResultResponse<ShowSchedule> result = new ResultResponse<ShowSchedule>();

		ShowSchedule showSchedule = showScheduleRepository.findOneByShowScheduleID(showScheduleID);
		result.setData(showSchedule);
		return result;
	}

	/**
	 * 
	 * get ShowCategories -combobox
	 * 
	 * @since 2018. 10. 18.
	 * @author Sanghyup Kim
	 * @param
	 * @return
	 */
	public GetShowCategoriesResponse getShowCategories(GetShowParameter parameters) {

		Integer showID = parameters.getShowID();
		List<ListShow> showCategoryList;
		if (showID == null) {
			showCategoryList = listShowRepository.findAllByOrderByShowNameAsc();
		} else {
			if (showID == 0) {
				showCategoryList = listShowRepository.findAllByOrderByShowNameAsc();
			} else {
				showCategoryList = listShowRepository.findByShowID(showID);
			}
		}
		GetShowCategoriesResponse getShowCategoriesResponse = new GetShowCategoriesResponse();

		getShowCategoriesResponse.setShowCategoryList(showCategoryList);
		return getShowCategoriesResponse;
	}

	/**
	 * 
	 * get ShowPromotionPlans
	 * 
	 * @since 2018. 10. 18.
	 * @author Sanghyup Kim
	 * @param
	 * @return
	 */
	public List<ShowSchedulePromotionPlan> getShowPromotionPlans(GetShowParameter parameters) {
		/*
		 * Integer pageNum = parameters.getPageNum(); Integer pageSize =
		 * parameters.getPageSize(); Integer showID = parameters.getShowID(); Integer
		 * planID = parameters.getPlanID();
		 */
		Integer showScheduleID = parameters.getShowScheduleID();

		List<ShowSchedulePromotionPlan> showSchedulePromotionPlanList;
		if (showScheduleID == null) {
			showSchedulePromotionPlanList = showSchedulePromotionPlanRepository.findAll();
		} else {
//			if (showScheduleID == 0) {
//				showSchedulePromotionPlanList = showSchedulePromotionPlanRepository.findAll();
//			}
//			else {
			showSchedulePromotionPlanList = showSchedulePromotionPlanRepository.findByShowScheduleID(showScheduleID);
//			}
		}
		return showSchedulePromotionPlanList;
	}

	/**
	 * 
	 * get ShowParticipating Vendors
	 * 
	 * @since 2018. 10. 18.
	 * @author Sanghyup Kim
	 * @param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public GetShowParticipatingVendorsResponse getShowParticipatingVendors(GetShowParameter parameters) {

		List<Object> params = new ArrayList<Object>();

		// add parameters
		final Integer pageNum = parameters.getPageNum();
		final Integer pageSize = parameters.getPageSize();
		final Integer showScheduleID = parameters.getShowScheduleID();
		final Integer planID = parameters.getPlanID();

		params.add(pageNum);
		params.add(pageSize);
		params.add(showScheduleID);
		params.add(planID);

		final String spName = "up_wa_GetShowVendor";

		GetShowParticipatingVendorsResponse resultSet = new GetShowParticipatingVendorsResponse();
		final List<Object> _result = jdbcHelper.executeSP(spName, params, SingleValueResult.class,
				MapShowSchedulePromotionPlanVendor.class);

		final List<SingleValueResult> singleValueResultList = (List<SingleValueResult>) _result.get(0);
		final List<MapShowSchedulePromotionPlanVendor> showSchedulePromotionPlanVendorList = (List<MapShowSchedulePromotionPlanVendor>) _result
				.get(1);

		resultSet.setSingleValueResultList(singleValueResultList);
		resultSet.setShowSchedulePromotionPlanVendorList(showSchedulePromotionPlanVendorList);

		return resultSet;
	}

	/**
	 * 
	 * get ShowPromotionPlan
	 * 
	 * @since 2018. 10. 25.
	 * @author Sanghyup Kim
	 * @param
	 * @return
	 */
	public GetShowPromotionPlanResponse getShowPromotionPlan(Integer planID) {

		GetShowPromotionPlanResponse result = new GetShowPromotionPlanResponse();

		ShowSchedulePromotionPlan showSchedulePromotionPlan = showSchedulePromotionPlanRepository
				.findOneByPlanID(planID);
		result.setShowSchedulePromotionPlan(showSchedulePromotionPlan);
		return result;
	}

	/**
	 * 
	 * get ShowParticipatingVendor
	 * 
	 * @since 2018. 10. 25.
	 * @author Sanghyup Kim
	 * @param
	 * @return
	 */
	public ResultResponse<MapShowSchedulePromotionPlanVendor> getShowParticipatingVendor(Integer mapID) {

		ResultResponse<MapShowSchedulePromotionPlanVendor> result = new ResultResponse<MapShowSchedulePromotionPlanVendor>();

		MapShowSchedulePromotionPlanVendor mapShowSchedulePromotionPlanVendor = mapShowSchedulePromotionPlanVendorRepository
				.findOneByMapID(mapID);
		result.setData(mapShowSchedulePromotionPlanVendor);
		return result;
	}

	/**
	 * 
	 * set ShowParticipatingVendor
	 * 
	 * @since 2018. 10. 16.
	 * @author Sanghyup Kim
	 * @param
	 * @return
	 */
	public ResultResponse<Integer> setShowParticipatingVendor(SetShowParticipatingVendorParameters parameters) {
		Integer mapID = parameters.getMapId();
		Integer planID = parameters.getPlanId();
		Integer wholeSalerID = parameters.getWholesalerId();

		ResultResponse<Integer> result = new ResultResponse<Integer>(false, -1, 0, null, null);

		// mapShowSchedulePromotionPlanVendor save
		MapShowSchedulePromotionPlanVendor mapShowSchedulePromotionPlanVendor;
		List<MapShowSchedulePromotionPlanVendor> mapShowSchedulePromotionPlanVendorList = mapShowSchedulePromotionPlanVendorRepository
				.findByPlanIDAndWholeSalerID(planID, wholeSalerID);
//		if (mapShowSchedulePromotionPlanVendorList.size() > 0) {			
		if (!CollectionUtils.isEmpty(mapShowSchedulePromotionPlanVendorList)) {
			mapShowSchedulePromotionPlanVendor = mapShowSchedulePromotionPlanVendorList.get(0);
		} else {
			mapShowSchedulePromotionPlanVendor = new MapShowSchedulePromotionPlanVendor();
			mapShowSchedulePromotionPlanVendor.setMapID(0);
		}

		mapShowSchedulePromotionPlanVendor.setPlanID(planID);
		mapShowSchedulePromotionPlanVendor.setWholeSalerID(wholeSalerID);
		mapShowSchedulePromotionPlanVendor.setCommissionRate(parameters.getCommissionRate());
		mapShowSchedulePromotionPlanVendor.setRackCount(parameters.getRackCount());
		mapShowSchedulePromotionPlanVendor.setFee(parameters.getFee());

		mapShowSchedulePromotionPlanVendor = mapShowSchedulePromotionPlanVendorRepository
				.save(mapShowSchedulePromotionPlanVendor);
		mapID = mapShowSchedulePromotionPlanVendor.getMapID();

		// MapShowScheduleWholeSaler save
		ShowSchedulePromotionPlan showSchedulePromotionPlan = showSchedulePromotionPlanRepository
				.findOneByPlanID(planID);
		Integer showScheduleID = showSchedulePromotionPlan.getShowScheduleID();
		List<MapShowScheduleWholeSaler> mapShowScheduleWholeSalerList = mapShowScheduleWholeSalerRepository
				.findByShowScheduleIDAndWholeSalerID(showScheduleID, wholeSalerID);

		if (CollectionUtils.isEmpty(mapShowScheduleWholeSalerList)) {
			MapShowScheduleWholeSaler mapShowScheduleWholeSaler = new MapShowScheduleWholeSaler();

			mapShowScheduleWholeSaler.setMapID(0);
			mapShowScheduleWholeSaler.setShowScheduleID(showScheduleID);
			mapShowScheduleWholeSaler.setWholeSalerID(wholeSalerID);
			mapShowScheduleWholeSalerRepository.save(mapShowScheduleWholeSaler);
		}

		result.setResultWrapper(true, 1, mapID, MSG_SAVE_SUCCESS, mapID);

		return result;
	}

	/**
	 * 
	 * set ShowPromotionPlan
	 * 
	 * @since 2018. 10. 16.
	 * @author Sanghyup Kim
	 * @param
	 * @return
	 */
	public ResultResponse<Integer> setShowPromotionPlan(SetShowPromotionPlanParameters parameters) {
		Integer planID = parameters.getPlanId();
		Integer showScheduleID = parameters.getShowScheduleId();

		LocalDateTime modifiedOn = LocalDateTime.now();
		String modifiedBy = parameters.getModifiedBy();
		LocalDateTime createdOn = modifiedOn;
		String createdBy = modifiedBy;
		Boolean isOnline = parameters.getIsOnline();
		Boolean isOffline = parameters.getIsOffline();
		LocalDateTime commissionEffectiveFrom = parameters.getCommissionEffectiveFrom();
		LocalDateTime commissionEffectiveTo = parameters.getCommissionEffectiveTo();
		String planName = parameters.getPlanName();

		ShowSchedulePromotionPlan showSchedulePromotionPlan;
		if (planID != 0) {
			showSchedulePromotionPlan = showSchedulePromotionPlanRepository.findOneByPlanID(planID);
			if (showSchedulePromotionPlan != null) {
				showSchedulePromotionPlan.setModifiedOn(modifiedOn);
				showSchedulePromotionPlan.setModifiedBy(modifiedBy);
			} else {
				showSchedulePromotionPlan = new ShowSchedulePromotionPlan();

				showSchedulePromotionPlan.setPlanID(planID);
				showSchedulePromotionPlan.setCreatedOn(createdOn);
				showSchedulePromotionPlan.setCreatedBy(createdBy);
			}
		} else {
			showSchedulePromotionPlan = new ShowSchedulePromotionPlan();
		}

		showSchedulePromotionPlan.setPlanID(planID);
		showSchedulePromotionPlan.setShowScheduleID(showScheduleID);
		showSchedulePromotionPlan.setIsOnline(isOnline);
		showSchedulePromotionPlan.setIsOffline(isOffline);
		showSchedulePromotionPlan.setCommissionEffectiveFrom(commissionEffectiveFrom);
		showSchedulePromotionPlan.setCommissionEffectiveTo(commissionEffectiveTo);
		showSchedulePromotionPlan.setPlanName(planName);

		showSchedulePromotionPlan = showSchedulePromotionPlanRepository.save(showSchedulePromotionPlan);
		planID = showSchedulePromotionPlan.getPlanID();

		ResultResponse<Integer> result = new ResultResponse<Integer>(false, -1, 0, null, null);

		result.setResultWrapper(true, 1, planID, MSG_SAVE_SUCCESS, planID);

		return result;
	}

	/**
	 * 
	 * set Delete ShowPromotionPlan
	 * 
	 * @since 2018. 10. 16.
	 * @author Sanghyup Kim
	 * @param
	 * @return
	 */
	public ResultResponse<Integer> setDeleteShowPromotionPlan(DelShowParameter parameters) {
		Integer planID = parameters.getPlanId();

		ResultResponse<Integer> result = new ResultResponse<Integer>(false, -1, 0, null, null);

		Boolean hasPlanDatePassed = false;
		ShowSchedulePromotionPlan showSchedulePromotionPlan = showSchedulePromotionPlanRepository
				.findOneByPlanID(planID);
		if (showSchedulePromotionPlan != null) {
			LocalDateTime commissionEffectiveFrom = showSchedulePromotionPlan.getCommissionEffectiveFrom();
//			LocalDateTime commissionEffectiveTo = showSchedulePromotionPlan.getCommissionEffectiveTo();
			LocalDateTime currentDateTime = LocalDateTime.now();
			int planDateCompare = currentDateTime.compareTo(commissionEffectiveFrom);
			hasPlanDatePassed = planDateCompare >= 0 ? true : false;
		}

		List<MapShowSchedulePromotionPlanVendor> mapShowSchedulePromotionPlanVendorList = mapShowSchedulePromotionPlanVendorRepository
				.findByPlanID(planID);
		Boolean planHasVendors = !CollectionUtils.isEmpty(mapShowSchedulePromotionPlanVendorList);
		if (planHasVendors || hasPlanDatePassed) {
			result.setMessage(
					"Unable to delete plan. Either the Commission Effective From has passed or there are still vendors linked to this plan.");
			return result;
		}

		showSchedulePromotionPlanRepository.deleteById(planID);

		result.setResultWrapper(true, 1, planID, MSG_DELETE_SUCCESS, planID);

		return result;
	}

	/**
	 * 
	 * set Delete ShowParticipatingVendor
	 * 
	 * @since 2018. 10. 16.
	 * @author Sanghyup Kim
	 * @param
	 * @return
	 */
	public ResultResponse<Integer> setDeleteShowParticipatingVendor(DelShowParameter parameters) {
		Integer mapID = parameters.getMapId();

		ResultResponse<Integer> result = new ResultResponse<Integer>(false, -1, 0, null, null);

		Boolean hasPlanDatePassed = false;

		MapShowSchedulePromotionPlanVendor mapShowSchedulePromotionPlanVendor = mapShowSchedulePromotionPlanVendorRepository
				.findOneByMapID(mapID);
		if (mapShowSchedulePromotionPlanVendor != null) {
			int planID = mapShowSchedulePromotionPlanVendor.getPlanID();

			ShowSchedulePromotionPlan showSchedulePromotionPlan = showSchedulePromotionPlanRepository
					.findOneByPlanID(planID);
			if (showSchedulePromotionPlan != null) {
				LocalDateTime commissionEffectiveFrom = showSchedulePromotionPlan.getCommissionEffectiveFrom();
				LocalDateTime currentDateTime = LocalDateTime.now();
				int planDateCompare = currentDateTime.compareTo(commissionEffectiveFrom);
				hasPlanDatePassed = planDateCompare >= 0 ? true : false;
			}
		}

		if (hasPlanDatePassed) {
			result.setMessage("Unable to delete vendor since it is already passed the Commission Effective From date");
			return result;
		}

		mapShowSchedulePromotionPlanVendorRepository.deleteById(mapID);

		result.setResultWrapper(true, 1, mapID, MSG_DELETE_SUCCESS, mapID);

		return result;
	}
}
