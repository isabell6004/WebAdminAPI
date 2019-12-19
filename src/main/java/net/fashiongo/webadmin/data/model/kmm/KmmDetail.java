package net.fashiongo.webadmin.data.model.kmm;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class KmmDetail {
    @JsonProperty(value = "TrendReportID")
    private Integer trendReportId;

    @JsonProperty(value = "Title")
    private String title;

    @JsonProperty(value = "Image")
    private String image;

    @JsonProperty(value = "SquareImage")
    private String squareImage;

    @JsonProperty(value = "MiniImage")
    private String miniImage;

    @JsonProperty(value = "CuratedType")
    private int curatedType;

    @JsonProperty(value = "TRDescription")
    private String trDescription;

    @JsonProperty(value = "DateFrom")
    private LocalDateTime dateFrom;

    @JsonProperty(value = "DateTo")
    private LocalDateTime dateTo;

    @JsonProperty(value = "ListOrder")
    private Integer listOrder;

    @JsonProperty(value = "Active")
    private Boolean active;

    @JsonProperty(value = "CreatedBy")
    private String CreatedBy;

    @JsonProperty(value = "KMMImage1")
    private String kmmImage1;

    @JsonProperty(value = "KMMImage2")
    private String kmmImage2;

    @JsonProperty(value = "Sticky")
    private Boolean sticky;

    @JsonProperty(value = "TrendReportContentId")
    private Integer trendReportContentId;

    @JsonProperty(value = "Content")
    private String content;
}
