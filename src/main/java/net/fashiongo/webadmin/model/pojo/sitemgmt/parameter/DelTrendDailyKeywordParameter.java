package net.fashiongo.webadmin.model.pojo.sitemgmt.parameter;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DelTrendDailyKeywordParameter {
    @JsonProperty("TrendKeywordID")
    private long trendKeywordID;
}
