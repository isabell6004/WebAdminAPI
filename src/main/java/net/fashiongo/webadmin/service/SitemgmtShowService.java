package net.fashiongo.webadmin.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fashiongo.webadmin.common.SingleValueResult;
import net.fashiongo.webadmin.dao.primary.ListShowRepository;
import net.fashiongo.webadmin.dao.primary.ShowScheduleRepository;
import net.fashiongo.webadmin.model.pojo.ResultResponse;
import net.fashiongo.webadmin.model.pojo.parameter.GetShowListParameters;
import net.fashiongo.webadmin.model.pojo.parameter.GetShowScheduleListParameters;
import net.fashiongo.webadmin.model.pojo.parameter.SetShowInfoParameters;
import net.fashiongo.webadmin.model.pojo.parameter.SetShowParameters;
import net.fashiongo.webadmin.model.pojo.response.GetShowListResponse;
import net.fashiongo.webadmin.model.pojo.response.GetShowScheduleListResponse;
import net.fashiongo.webadmin.model.primary.ListShow;
import net.fashiongo.webadmin.model.primary.ShowSchedule;

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
		if (orderBy == "")
			orderBy = null;
		final String showName = parameters.getShowName();

		final Date fromDate = parameters.getFromDate();
		final Date toDate = parameters.getToDate();

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
	 * Get Show List
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
		final Boolean active = parameters.getActive();
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
		if (showName == "")
			showName = null;
		String location = parameters.getLocation();
		if (location == "")
			location = null;
		String orderBy = parameters.getOrderBy();
		if (orderBy == "")
			orderBy = null;

		Date dateFrom = parameters.getDateFrom();
		Date dateTo = parameters.getDateTo();

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
		resultSet.setShowScheduleList(showScheduleList);;

		return resultSet;
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
	public ResultResponse<Integer> setShow(SetShowParameters parameters) {

		ResultResponse<Integer> result = new ResultResponse<Integer>(false, -1, 0, null, null);
		
		Integer showID = parameters.getShowId();
		Boolean active = parameters.getActive();
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
		}
		else { // insert
			listShow = new ListShow();
			ListShow listShow2 = listShowRepository.findTopByOrderByShowIDDesc();
			if (listShow2 != null){
				showID = listShow2.getShowID() +1;
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
	
	
}
