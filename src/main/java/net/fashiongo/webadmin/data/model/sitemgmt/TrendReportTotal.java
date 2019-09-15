package net.fashiongo.webadmin.data.model.sitemgmt;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TrendReportTotal {
    @JsonProperty("RecCnt")
    private Long recCnt;
}
