/**
 * 
 */
package net.fashiongo.webadmin.controller;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.model.statistics.*;
import net.fashiongo.webadmin.data.model.statistics.response.GetBestItemsResponse;
import net.fashiongo.webadmin.data.model.statistics.response.GetHotSearchKeywordResponse;
import net.fashiongo.webadmin.data.model.statistics.response.GetHotSearchResponse;
import net.fashiongo.webadmin.data.model.statistics.response.GetStatWholeSalerItemResponse;
import net.fashiongo.webadmin.model.pojo.statics.response.GetDashboardResponse;
import net.fashiongo.webadmin.service.StaticService;
import net.fashiongo.webadmin.service.renewal.RenewalStaticService;
import net.fashiongo.webadmin.utility.DateUtils;
import net.fashiongo.webadmin.utility.HttpClient;
import net.fashiongo.webadmin.utility.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * @author Incheol Jung
 */
@Slf4j
@RestController
@RequestMapping(value="/stat", produces = "application/json")
public class StaticController {
	
	@Autowired
	StaticService staticService;

	@Autowired
	private RenewalStaticService renewalStaticService;

	@Autowired
	@Qualifier("vendorApiJsonClient")
	HttpClient jsonClient;
	
	@Autowired
	@Qualifier("statsApiJsonClient")
	HttpClient statsJsonClient;
	
	/**
	 * 
	 * Get Dashboard
	 * 
	 * @since 2018. 10. 8.
	 * @author Incheol Jung
	 * @return
	 */
	@RequestMapping(value="getdashboard", method=RequestMethod.POST)
	public JsonResponse<GetDashboardResponse> getDashboard() {
		JsonResponse<GetDashboardResponse> results = new JsonResponse<GetDashboardResponse>(true, null, 0, null);
		
		GetDashboardResponse result = staticService.getDashboard();
		results.setData(result);
		
		return results;
	}
	
	@RequestMapping(value="vpi", method=RequestMethod.GET)
	public JsonResponse<String> getVpi(
			HttpServletRequest request) {
		return jsonClient.get("/vpi/?" + request.getQueryString());
	}
	
	/**
	 * @author Kenny/Kyungwoo
	 * @since 2019-04-17
	 */
	@GetMapping(value="vendorkpi")
	public JsonResponse<String> getVendorKpi(
			@RequestParam(value="pn") Integer pn,
			@RequestParam(value="ps") Integer ps,
			@RequestParam(value="unit") Integer unit,
			@RequestParam(value="df") String df,
			@RequestParam(value="dt") String dt,
			@RequestParam(value="so") String so,
			@RequestParam(value="sq") String sq,
			@RequestParam(value="vendorStatus") Integer vendorStatus,
			@RequestParam(value="vendorCategory") Integer vendorCategory,
			@RequestParam(value="vendorType") String vendorType,
			@RequestParam(value="location") String location,
			@RequestParam(value="state") String state,
			@RequestParam(value="assignedUser") String assignedUser,
			@RequestParam(value="contractTypeId") Integer contractTypeId,
			@RequestParam(value="orderBy", required=false) String orderBy) {
		//log.debug("===========================getVendorKpi");
		
		//1. Process params
		ArrayList<String> params = new ArrayList<>();		
		if(pn!=null && pn!=0) params.add("pn="+pn);
		if(ps!=null && ps!=0) params.add("ps="+ps);
		if(unit!=null && unit!=0) params.add("unit="+unit);
		if(df!=null && !df.isEmpty()) params.add("df="+df);
		if(dt!=null && !dt.isEmpty()) params.add("dt="+dt);
		if(so!=null && !so.isEmpty()) params.add("so="+so);
		if(sq!=null && !sq.isEmpty()) params.add("sq="+sq);
		if(vendorStatus!=null && vendorStatus!=0) params.add("vendorStatus="+vendorStatus);
		if(vendorCategory!=null && vendorCategory!=0) params.add("vendorCategory="+vendorCategory);
		if(vendorType!=null && !vendorType.isEmpty()) params.add("vendorType="+vendorType);
		if(location!=null && !location.isEmpty()) params.add("location="+location);
		if(state!=null && !state.isEmpty()) params.add("state="+state);
		if(assignedUser!=null && !assignedUser.isEmpty()) params.add("assignedUser="+assignedUser);
		if(contractTypeId!=null && contractTypeId!=0) params.add("contractTypeId="+contractTypeId);
		if(orderBy!=null && !orderBy.isEmpty()) params.add("orderBy="+orderBy);
		
		//2. Call StatsAPI
		return statsJsonClient.get("/kpi/vendor?" + String.join("&", params));
	}

	@PostMapping(value = "gethotsearch")
	public JsonResponse<GetHotSearchResponse> getHotSearch(@RequestBody GetHotSearchParameter parameter) {
		Integer top = Optional.ofNullable(parameter.getTop()).orElse(0);
		Integer periodType = Optional.ofNullable(parameter.getPeriodtype()).orElse(0);

		LocalDateTime fromDate = Optional.ofNullable(parameter.getFromdate())
				.filter(s -> StringUtils.hasLength(s))
				.map(s -> new Date(s).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
				.orElse(LocalDateTime.of(1970,1,1,0,0,0,0));

		LocalDateTime toDate = Optional.ofNullable(parameter.getTodate())
				.filter(s -> StringUtils.hasLength(s))
				.map(s -> new Date(s).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
				.map(dateTime -> {
					if(dateTime.getHour() == 0) {
						return dateTime.plusDays(1).minusSeconds(1);
					}
					return dateTime;
				})
				.orElse(LocalDateTime.of(2099,12,31,0,0,0,0));


		String orderBy = parameter.getOrderby();
		String searchfield = parameter.getSearchfield();
		String searchkeyword = parameter.getSearchkeyword();

		LocalDateTime today = LocalDateTime.now();
		LocalDateTime lastDayOfMonth = LocalDateTime.of(today.getYear(), today.getMonth(), 1,0,0).minusDays(1);

		switch (periodType)
		{
			case 0:

				break;
			case 1:
				fromDate = LocalDateTime.of(lastDayOfMonth.getYear(), lastDayOfMonth.getMonth(), 1,0,0);;
				break;
			case 12:
				fromDate = LocalDateTime.of(lastDayOfMonth.getYear(), lastDayOfMonth.getMonth(), 1,0,0).minusMonths(11);
				break;
			default:
				break;
		}

		GetHotSearchResponse getHotSearchResponse = renewalStaticService.getHotSearch(top, fromDate, toDate, orderBy, searchfield, searchkeyword);
		return new JsonResponse<>(true,"",getHotSearchResponse);
	}

	@PostMapping(value = "gethotsearchkeyword")
	public JsonResponse<GetHotSearchKeywordResponse> getHotSearchKeyword(@RequestBody GetHotSearchKeywordParameter parameter) {
		Integer periodType = Optional.ofNullable(parameter.getPeriodtype()).orElse(0);

		LocalDateTime fromDate = Optional.ofNullable(parameter.getFromdate())
				.filter(s -> StringUtils.hasLength(s))
				.map(s -> new Date(s).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
				.orElse(LocalDateTime.of(1970,1,1,0,0,0,0));

		LocalDateTime toDate = Optional.ofNullable(parameter.getTodate())
				.filter(s -> StringUtils.hasLength(s))
				.map(s -> new Date(s).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
				.map(dateTime -> {
					if(dateTime.getHour() == 0) {
						return dateTime.plusDays(1).minusSeconds(1);
					}
					return dateTime;
				})
				.orElse(LocalDateTime.of(2099,12,31,0,0,0,0));

		String keyword = parameter.getKeyword();

		GetHotSearchKeywordResponse getHotSearchResponse = renewalStaticService.getHotSearchKeyword(periodType, fromDate, toDate, keyword);
		return new JsonResponse<>(true,"",getHotSearchResponse);
	}

	@PostMapping(value = "getvendorstat")
	public JsonResponse<List<Map<String, Object>>> getVendorStat(@RequestBody GetVendorStatParameter parameter) {
		LocalDateTime fromDate = Optional.ofNullable(parameter.getFromDate())
				.filter(s -> StringUtils.hasLength(s))
				.map(s -> new Date(s).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
				.orElse(LocalDateTime.of(1970,1,1,0,0,0,0));

		LocalDateTime toDate = Optional.ofNullable(parameter.getToDate())
				.filter(s -> StringUtils.hasLength(s))
				.map(s -> new Date(s).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
				.map(dateTime -> {
					if(dateTime.getHour() == 0) {
						return dateTime.plusDays(1).minusSeconds(1);
					}
					return dateTime;
				})
				.orElse(LocalDateTime.of(2099,12,31,0,0,0,0));

		Integer interval = Optional.ofNullable(parameter.getInterval()).orElse(0);
		Integer wholeSalerId = Optional.ofNullable(parameter.getWholeSalerID()).orElse(0);

		List<Map<String,Object>> result = renewalStaticService.getVendorStat(fromDate, toDate, interval, wholeSalerId);

		return new JsonResponse<>(true, "", result);
	}

	@PostMapping(value = "getstatwholesaleritem")
	public JsonResponse<GetStatWholeSalerItemResponse> getstatwholesaleritem(@RequestBody GetStatWholeSalerItemParamter parameter) {

		Integer adminWebServerID = Optional.ofNullable(parameter.getAdminWebServerID()).orElse(0);
		Integer imageServerID = Optional.ofNullable(parameter.getImageServerID()).orElse(0);
		String vendorName = parameter.getVendorname();

//		LocalDateTime df = DateUtils.convertToLocalDateTime(parameter.getFromDate(),"F").toLocalDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy");
//		LocalDateTime dt = DateUtils.convertToLocalDateTime(parameter.getToDate(),"T").toLocalDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy");

		GetStatWholeSalerItemResponse data = renewalStaticService.getStatWholeSalerItem(adminWebServerID, imageServerID, vendorName, null, null);

		return new JsonResponse<>(true, "", data);
	}

	@PostMapping(value = "getstatreport")
	public JsonResponse<Map<String, Object>> getStatReport(@RequestBody GetStatReportParameter parameter) {

		Integer intervalType = Optional.ofNullable(parameter.getInterval_type()).orElse(0);
		Boolean samepoint = Optional.ofNullable(parameter.getSamepoint()).orElse(false);
		Integer reporttype = Optional.ofNullable(parameter.getReporttype()).orElse(0);
		LocalDateTime dtStart = DateUtils.convertToLocalDateTime(parameter.getStartdate(), "F");
		LocalDateTime dtEnd = DateUtils.convertToLocalDateTime(parameter.getEnddate(), "T");

		Map<String, Object> statReport = renewalStaticService.getStatReport(intervalType, samepoint, reporttype, dtStart, dtEnd);

		return new JsonResponse<>(true, "", statReport);
	}

	@PostMapping(value = "getbestitems2")
	public JsonResponse<GetBestItemsResponse> getBestItems2(@RequestBody GetBestItemsParamter parameter) {

		Integer pageNo = Optional.ofNullable(parameter.getPageno()).orElse(0);
		Integer pageSize = Optional.ofNullable(parameter.getPagesize()).orElse(0);

		LocalDateTime fromDate = DateUtils.convertToLocalDateTime(parameter.getFromDate(), "F");
		LocalDateTime toDate = DateUtils.convertToLocalDateTime(parameter.getToDate(), "T");

		Integer statisticsType = Optional.ofNullable(parameter.getStatisticsType()).orElse(0);
		Integer cateId = Optional.ofNullable(parameter.getCateId()).orElse(0);
		Integer subCateId = Optional.ofNullable(parameter.getSubCateId()).orElse(0);
		Integer subSubCateId = Optional.ofNullable(parameter.getSubSubCateId()).orElse(0);

		Integer wholeSalerId = Optional.ofNullable(parameter.getWholesalerId()).orElse(0);
		String orderBy = parameter.getOrderBy();

		Integer lastCategoryID = 0;

		if(subSubCateId > 0) {
			lastCategoryID = subSubCateId;
		} else if (subCateId > 0) {
			lastCategoryID = subCateId;
		} else {
			lastCategoryID = cateId;
		}

		GetBestItemsResponse data = renewalStaticService.getBestItems(pageNo, pageSize, fromDate, toDate, statisticsType, lastCategoryID, wholeSalerId, orderBy);

		return new JsonResponse<GetBestItemsResponse>(true, "success", data);
	}
}
