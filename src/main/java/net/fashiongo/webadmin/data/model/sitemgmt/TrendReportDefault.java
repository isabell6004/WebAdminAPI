package net.fashiongo.webadmin.data.model.sitemgmt;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class TrendReportDefault {
    @JsonProperty("TrendReportID")
    private Integer trendReportID;

    @JsonProperty("Title")
    private String title;

    @JsonProperty("Image")
    private String image;

    @JsonProperty("DateFrom")
    private LocalDateTime dateFrom;

    @JsonProperty("DateTo")
    private LocalDateTime dateTo;

    @JsonProperty("ListOrder")
    private Integer listOrder;

    @JsonProperty("Active")
    private Boolean active;

    @JsonProperty("CreatedBy")
    private String createdBy;

    @JsonProperty("ItemCount")
    private Long itemCount;

}
