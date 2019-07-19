package net.fashiongo.webadmin.model.pojo.sitemgmt.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class GetTrendDailyKeywordResponse {
    @JsonProperty("Table")
    List<TrendDailyKeywordResponse> trendDailyKeywords;
}
