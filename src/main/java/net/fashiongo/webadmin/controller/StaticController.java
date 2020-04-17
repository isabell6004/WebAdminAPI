package net.fashiongo.webadmin.controller;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.controller.request.VendorKpiRequest;
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
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(value = "/stat", produces = "application/json")
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

    @PostMapping("getdashboard")
    public JsonResponse<GetDashboardResponse> getDashboard() {
        GetDashboardResponse result = staticService.getDashboard();
        return JsonResponse.success(result);
    }

    @GetMapping("vpi")
    public JsonResponse<String> getVpi(
            HttpServletRequest request) {
        return jsonClient.get("/vpi/?" + request.getQueryString());
    }

    /**
     * @author Kenny/Kyungwoo
     * @since 2019-04-17
     */
    @GetMapping(value = "vendorkpi")
    public JsonResponse<String> getVendorKpi(
            @Valid VendorKpiRequest request) {
        log.debug("getVendorKpigetVendorKpigetVendorKpigetVendorKpi");

        //1. Process params
        List<String> params = request.getParams();
        log.debug("String.join(\"&\", params): {}", String.join("&", params));

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
                .orElse(LocalDateTime.of(1970, 1, 1, 0, 0, 0, 0));

        LocalDateTime toDate = Optional.ofNullable(parameter.getTodate())
                .filter(s -> StringUtils.hasLength(s))
                .map(s -> new Date(s).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
                .map(dateTime -> {
                    if (dateTime.getHour() == 0) {
                        return dateTime.plusDays(1).minusSeconds(1);
                    }
                    return dateTime;
                })
                .orElse(LocalDateTime.of(2099, 12, 31, 0, 0, 0, 0));


        String orderBy = parameter.getOrderby();
        String searchfield = parameter.getSearchfield();
        String searchkeyword = parameter.getSearchkeyword();

        LocalDateTime today = LocalDateTime.now();
        LocalDateTime lastDayOfMonth = LocalDateTime.of(today.getYear(), today.getMonth(), 1, 0, 0).minusDays(1);

        switch (periodType) {
            case 0:

                break;
            case 1:
                fromDate = LocalDateTime.of(lastDayOfMonth.getYear(), lastDayOfMonth.getMonth(), 1, 0, 0);
                ;
                break;
            case 12:
                fromDate = LocalDateTime.of(lastDayOfMonth.getYear(), lastDayOfMonth.getMonth(), 1, 0, 0).minusMonths(11);
                break;
            default:
                break;
        }

        GetHotSearchResponse getHotSearchResponse = renewalStaticService.getHotSearch(top, fromDate, toDate, orderBy, searchfield, searchkeyword);
        return new JsonResponse<>(true, "", getHotSearchResponse);
    }

    @PostMapping(value = "gethotsearchkeyword")
    public JsonResponse<GetHotSearchKeywordResponse> getHotSearchKeyword(@RequestBody GetHotSearchKeywordParameter parameter) {
        Integer periodType = Optional.ofNullable(parameter.getPeriodtype()).orElse(0);

        LocalDateTime fromDate = Optional.ofNullable(parameter.getFromdate())
                .filter(s -> StringUtils.hasLength(s))
                .map(s -> new Date(s).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
                .orElse(LocalDateTime.of(1970, 1, 1, 0, 0, 0, 0));

        LocalDateTime toDate = Optional.ofNullable(parameter.getTodate())
                .filter(s -> StringUtils.hasLength(s))
                .map(s -> new Date(s).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
                .map(dateTime -> {
                    if (dateTime.getHour() == 0) {
                        return dateTime.plusDays(1).minusSeconds(1);
                    }
                    return dateTime;
                })
                .orElse(LocalDateTime.of(2099, 12, 31, 0, 0, 0, 0));

        String keyword = parameter.getKeyword();

        GetHotSearchKeywordResponse getHotSearchResponse = renewalStaticService.getHotSearchKeyword(periodType, fromDate, toDate, keyword);
        return new JsonResponse<>(true, "", getHotSearchResponse);
    }

    @PostMapping(value = "getvendorstat")
    public JsonResponse<List<Map<String, Object>>> getVendorStat(@RequestBody GetVendorStatParameter parameter) {
        LocalDateTime fromDate = Optional.ofNullable(parameter.getFromDate())
                .filter(s -> StringUtils.hasLength(s))
                .map(s -> new Date(s).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
                .orElse(LocalDateTime.of(1970, 1, 1, 0, 0, 0, 0));

        LocalDateTime toDate = Optional.ofNullable(parameter.getToDate())
                .filter(s -> StringUtils.hasLength(s))
                .map(s -> new Date(s).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
                .map(dateTime -> {
                    if (dateTime.getHour() == 0) {
                        return dateTime.plusDays(1).minusSeconds(1);
                    }
                    return dateTime;
                })
                .orElse(LocalDateTime.of(2099, 12, 31, 0, 0, 0, 0));

        Integer interval = Optional.ofNullable(parameter.getInterval()).orElse(0);
        Integer wholeSalerId = Optional.ofNullable(parameter.getWholeSalerID()).orElse(0);

        List<Map<String, Object>> result = renewalStaticService.getVendorStat(fromDate, toDate, interval, wholeSalerId);

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

        if (subSubCateId > 0) {
            lastCategoryID = subSubCateId;
        } else if (subCateId > 0) {
            lastCategoryID = subCateId;
        } else {
            lastCategoryID = cateId;
        }

        GetBestItemsResponse data = renewalStaticService.getBestItems(pageNo, pageSize, fromDate, toDate, statisticsType, lastCategoryID, wholeSalerId, orderBy);

        return new JsonResponse<>(true, "success", data);
    }
}
