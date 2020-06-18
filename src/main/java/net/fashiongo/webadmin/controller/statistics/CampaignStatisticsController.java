package net.fashiongo.webadmin.controller.statistics;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.model.statistics.response.GetAbandonedCartResponse;
import net.fashiongo.webadmin.service.externalutil.response.CollectionObject;
import net.fashiongo.webadmin.service.statistics.CampaignStatisticsService;
import net.fashiongo.webadmin.utility.JsonResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/stat/campaign", produces = "application/json")
public class CampaignStatisticsController {

    private final CampaignStatisticsService campaignStatisticsService;

    public CampaignStatisticsController(CampaignStatisticsService campaignStatisticsService) {
        this.campaignStatisticsService = campaignStatisticsService;
    }

    @GetMapping(value = "abandoned-cart")
    public JsonResponse<CollectionObject<GetAbandonedCartResponse>> getAbandonedCartStatistics(@RequestParam(name = "pn", defaultValue = "1") Integer pageNumber,
                                                                                              @RequestParam(name = "ps", defaultValue = "30") Integer pageSize,
                                                                                              @RequestParam(name = "df", defaultValue = "") String fromDate,
                                                                                              @RequestParam(name = "dt", defaultValue = "") String toDate,
                                                                                              @RequestParam(name = "orderBy", defaultValue = "") String orderBy) {
        JsonResponse<CollectionObject<GetAbandonedCartResponse>> response = new JsonResponse<>(false, null, null);

        try {
            response.setSuccess(true);
            response.setData(campaignStatisticsService.getCampaignStatistics(1L, pageNumber, pageSize, fromDate, toDate, orderBy));
        } catch (Exception e) {
            log.error("fail to get statistics of abandoned cart campaign", e);
            response.setMessage("Fail to get statistics of abandoned cart campaign");
        }

        return response;
    }
}
