package net.fashiongo.webadmin.data.model.sitemgmt.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.model.Total;
import net.fashiongo.webadmin.data.model.sitemgmt.TrendReport;

import java.util.List;

@Getter
@Builder
public class GetTrendReportResponse {
    @JsonProperty("Table")
    private List<Total> recCnt;

    @JsonProperty("Table1")
    private List<TrendReport> trendReports;
}
