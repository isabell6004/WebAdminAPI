package net.fashiongo.webadmin.data.model.buyer.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.model.buyer.OrderHistoryStatistics;

import java.util.List;

@Getter
@Builder
public class GetOrderHistoryStatisticsResponse {
    @JsonProperty("Table")
    List<OrderHistoryStatistics> orderHistoryStatistics;
}
