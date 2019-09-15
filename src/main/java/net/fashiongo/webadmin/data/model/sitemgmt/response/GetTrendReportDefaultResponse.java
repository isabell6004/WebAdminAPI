package net.fashiongo.webadmin.data.model.sitemgmt.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.model.sitemgmt.TrendReportDefault;
import net.fashiongo.webadmin.data.model.sitemgmt.TrendReportTotal;

import java.util.List;

@Getter
@Builder
public class GetTrendReportDefaultResponse {
    @JsonProperty("Table")
    private TrendReportTotal total;

    @JsonProperty("Table1")
    private List<TrendReportDefault> trendReportDefault;

}
