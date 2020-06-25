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
import net.fashiongo.webadmin.service.externalutil.response.SingleObject;
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
    public CollectionObject<GetAbandonedCartResponse> getCampaignStatistics(long campaignId, Integer pageNumber, Integer pageSize, String fromDate, String toDate, String orderBy) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/campaigns/" + campaignId + "/statistics";

        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(endpoint)
                .queryParam("pn", pageNumber)
                .queryParam("ps", pageSize)
                .queryParam("fromDate", fromDate)
                .queryParam("toDate", toDate)
                .queryParam("orderBy", orderBy)
                .build(false);

        String responseBody = httpCaller.get(uriComponents.toUriString(), FashionGoApiHeader.getDefaultHeader());

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

    @Override
    public long getCampaignItemCount(long campaignId, boolean isUnique) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/campaigns/" + campaignId + "/items/count";

        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(endpoint)
                .queryParam("isUnique", isUnique)
                .build(false);

        String responseBody = httpCaller.get(uriComponents.toUriString(), FashionGoApiHeader.getDefaultHeader());

        try {
            FashionGoApiResponse<SingleObject<Long>> fashionGoApiResponse = mapper.readValue(responseBody, new TypeReference<FashionGoApiResponse<SingleObject<Long>>>() {});

            if (fashionGoApiResponse.getHeader().isSuccessful())
                return fashionGoApiResponse.getData().getContent();
            else
                throw new RuntimeException("Fail to get campaign item count from FashionGo API");
        } catch (IOException e) {
            throw new RuntimeException("Fail to get campaign item count from FashionGo API");
        }
    }

    @Override
    public long getCampaignUnsubscribeCount(long campaignId) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/campaigns/" + campaignId + "/unsubscribes/count";

        String responseBody = httpCaller.get(endpoint, FashionGoApiHeader.getDefaultHeader());

        try {
            FashionGoApiResponse<SingleObject<Long>> fashionGoApiResponse = mapper.readValue(responseBody, new TypeReference<FashionGoApiResponse<SingleObject<Long>>>() {});

            if (fashionGoApiResponse.getHeader().isSuccessful())
                return fashionGoApiResponse.getData().getContent();
            else
                throw new RuntimeException("Fail to get campaign unsubscribe count from FashionGo API");
        } catch (IOException e) {
            throw new RuntimeException("Fail to get campaign unsubscribe count from FashionGo API");
        }
    }
}
