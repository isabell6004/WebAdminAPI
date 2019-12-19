package net.fashiongo.webadmin.data.model.kmm;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class KmmTrendReport {
    @JsonProperty(value = "trendReportId")
    private Integer trendReportId;

    @JsonProperty(value = "title")
    private String title;

    @JsonProperty(value = "trDescription")
    private String trDescription;

    @JsonProperty(value = "dateFrom")
    private String dateFrom;

    @JsonProperty(value = "dateTo")
    private String dateTo;

    @JsonProperty(value = "attFile")
    private String attFile;

    @JsonProperty(value = "miniImage")
    private String miniImage;

    @JsonProperty(value = "squareImage")
    private String squareImage;

    @JsonProperty(value = "kmmImage1")
    private String kmmImage1;

    @JsonProperty(value = "kmmImage2")
    private String kmmImage2;

    @JsonProperty(value = "rActive")
    private Boolean rActive;

    @JsonProperty(value = "listOrder")
    private Integer listOrder;
}
