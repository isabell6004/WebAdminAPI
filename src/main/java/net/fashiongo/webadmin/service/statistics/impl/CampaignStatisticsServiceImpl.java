package net.fashiongo.webadmin.service.statistics.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import net.fashiongo.webadmin.data.model.statistics.response.GetAbandonedCartResponse;
import net.fashiongo.webadmin.model.pojo.login.WebAdminLoginUser;
import net.fashiongo.webadmin.service.externalutil.FashionGoApiConfig;
import net.fashiongo.webadmin.service.externalutil.FashionGoApiHeader;
import net.fashiongo.webadmin.service.externalutil.HttpClientWrapper;
import net.fashiongo.webadmin.service.externalutil.response.CollectionObject;
import net.fashiongo.webadmin.service.externalutil.response.FashionGoApiResponse;
import net.fashiongo.webadmin.service.statistics.CampaignStatisticsService;
import net.fashiongo.webadmin.utility.Utility;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@Service
public class CampaignStatisticsServiceImpl implements CampaignStatisticsService {

    private final static ObjectMapper mapper = new ObjectMapper();

    private HttpClientWrapper httpCaller;

    public CampaignStatisticsServiceImpl(HttpClientWrapper httpCaller) {
        this.httpCaller = httpCaller;
    }

    @Override
    public CollectionObject<GetAbandonedCartResponse> getCampaignStatistics(Long campaignId, Integer pageNumber, Integer pageSize, String fromDate, String toDate, String orderBy) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/campaigns/" + campaignId + "/statistics";

        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(endpoint)
                .queryParam("pn", pageNumber)
                .queryParam("ps", pageSize)
                .queryParam("fromDate", fromDate)
                .queryParam("toDate", toDate)
                .queryParam("orderBy", orderBy)
                .build(false);

        WebAdminLoginUser userInfo = Utility.getUserInfo();
        String responseBody = httpCaller.get(uriComponents.toUriString(), FashionGoApiHeader.getHeader(userInfo.getUserId(), userInfo.getUsername()));

        try {
            mapper.registerModule(new JavaTimeModule())
                    .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

            FashionGoApiResponse<CollectionObject<GetAbandonedCartResponse>> fashionGoApiResponse = mapper.readValue(responseBody, new TypeReference<FashionGoApiResponse<CollectionObject<GetAbandonedCartResponse>>>() {});

            if (fashionGoApiResponse.getHeader().isSuccessful())
                return fashionGoApiResponse.getData();
            else
                throw new RuntimeException("Fail to get campaign statistics from FashionGo API");
        } catch (IOException e) {
            throw new RuntimeException("Fail to get campaign statistics from FashionGo API");
        }
    }
}
