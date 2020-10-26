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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
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

    /**
     * Get Best Items
     * @deprecated using {@link StaticController#getBestItems(GetBestItemsParamter)}
     * @param param GetBestItemsParamter
     * @return JsonResponse
     */
    @Deprecated
    @PostMapping(value = "getbestitems2")
    public ResponseEntity getBestItems2(@RequestBody GetBestItemsParamter param) {

//        LocalDateTime fromDate = DateUtils.convertToLocalDateTime(param.getFromDate(), "F");
//        LocalDateTime toDate = DateUtils.convertToLocalDateTime(param.getToDate(), "T");
//
//        int lastCategoryID = 0;
//
//        if (param.getSubSubCateId() > 0) {
//            lastCategoryID = param.getSubSubCateId();
//        } else if (param.getSubCateId() > 0) {
//            lastCategoryID = param.getSubCateId();
//        } else {
//            lastCategoryID = param.getCateId();
//        }
//
//        GetBestItemsResponse data = renewalStaticService.getBestItems(param.getPageno(), param.getPagesize(), fromDate, toDate,
//                param.getStatisticsType(), lastCategoryID, param.getWholesalerId(), param.getSortBy());
//
//        return new JsonResponse<>(true, "success", data);

        return ResponseEntity.status(HttpStatus.PERMANENT_REDIRECT).location(URI.create("/api/stat/getbestitems")).build();
    }

    /**
     * Get Best Items
     * @param param GetBestItemsParamter
     * @return JsonResponse
     */
    @PostMapping(value = "getbestitems")
    public JsonResponse<GetBestItemsResponse> getBestItems(@RequestBody GetBestItemsParamter param) {

        LocalDateTime fromDate = DateUtils.convertToLocalDateTime(param.getFromDate(), "F");
        LocalDateTime toDate = DateUtils.convertToLocalDateTime(param.getToDate(), "T");

        int lastCategoryID = 0;

        if (param.getSubSubCateId() > 0) {
            lastCategoryID = param.getSubSubCateId();
        } else if (param.getSubCateId() > 0) {
            lastCategoryID = param.getSubCateId();
        } else {
            lastCategoryID = param.getCateId();
        }

        GetBestItemsResponse data = renewalStaticService.selectBestItems(param.getPageno(), param.getPagesize(), fromDate, toDate,
                param.getStatisticsType(), lastCategoryID, param.getWholesalerId(), param.getSortBy());

        return new JsonResponse<>(true, "success", data);
    }
}
