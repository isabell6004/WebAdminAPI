package net.fashiongo.webadmin.service.statistics;

import net.fashiongo.webadmin.data.model.statistics.response.GetAbandonedCartResponse;
import net.fashiongo.webadmin.service.externalutil.response.CollectionObject;

public interface CampaignStatisticsService {

    CollectionObject<GetAbandonedCartResponse> getCampaignStatistics(long campaignId, Integer pageNumber, Integer pageSize, String fromDate, String toDate, String orderBy);

    long getCampaignItemCount(long campaignId, boolean isUnique);

    long getCampaignUnsubscribeCount(long campaignId);
}
