package net.fashiongo.webadmin.data.model.sitemgmt.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.model.sitemgmt.ActiveTodayDealDetail;
import net.fashiongo.webadmin.data.model.sitemgmt.InactiveTodayDealDetail;

import java.util.List;

@Getter
@Builder
public class GetTodayDealCalendarListResponse {
    @JsonProperty("Table")
    private List<ActiveTodayDealDetail> activeTodayDeals;
    @JsonProperty("Table1")
    private List<InactiveTodayDealDetail> inactiveTodayDeals;
}
