package net.fashiongo.webadmin.data.model.kmm;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Getter
public class KmmListDetail {
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
    private String createdBy;

    @JsonProperty(value = "ItemCount")
    private Integer itemCount;

    @JsonProperty(value = "KMMImage1")
    private String kmmImage1;

    @JsonProperty(value = "KMMImage2")
    private String kmmImage2;

    @JsonProperty(value = "Sticky")
    private Boolean sticky;

    @JsonProperty(value = "ShowID")
    private Integer showID;

    @JsonProperty(value = "ShowScheduleID")
    private Integer showScheduleID;

    @JsonProperty("row")
    private Integer row;

    public KmmListDetail(Integer trendReportId, String title, String image, String squareImage, String miniImage, int curatedType, String trDescription,
                         Timestamp dateFrom, Timestamp dateTo, Integer listOrder, Boolean active, String createdBy, Long itemCount, String kmmImage1, String kmmImage2,
                         Boolean sticky, Integer showID, Integer showScheduleID, Long row) {
        this.trendReportId = trendReportId;
        this.title = title;
        this.image = image;
        this.squareImage = squareImage;
        this.miniImage = miniImage;
        this.curatedType = curatedType;
        this.trDescription = trDescription;
        this.dateFrom = Optional.ofNullable(dateFrom).map(Timestamp::toLocalDateTime).orElse(null);
        this.dateTo = Optional.ofNullable(dateTo).map(Timestamp::toLocalDateTime).orElse(null);
        this.listOrder = listOrder;
        this.active = active;
        this.createdBy = createdBy;
        this.itemCount = Optional.ofNullable(itemCount).map(Long::intValue).orElse(null);
        this.kmmImage1 = kmmImage1;
        this.kmmImage2 = kmmImage2;
        this.sticky = sticky;
        this.showID = showID;
        this.showScheduleID = showScheduleID;
        this.row = Optional.ofNullable(row).map(Long::intValue).orElse(null);
    }
}
