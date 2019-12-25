package net.fashiongo.webadmin.data.model.statistics;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class GetStatReportParameter {
    @JsonProperty(value = "startdate")
    private String startdate;

    @JsonProperty(value = "enddate")
    private String enddate;

    @JsonProperty(value = "interval_type")
    private Integer interval_type;

    @JsonProperty(value = "samepoint")
    private Boolean samepoint;

    @JsonProperty(value = "reporttype")
    private Integer reporttype;
}
