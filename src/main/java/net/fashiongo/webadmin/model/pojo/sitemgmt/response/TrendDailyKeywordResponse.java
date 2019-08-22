package net.fashiongo.webadmin.model.pojo.sitemgmt.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrendDailyKeywordResponse {
    @JsonProperty("TrendDailyKeywordID")
    private long trendDailyKeywordID;

    @JsonProperty("ExposeDate")
    private LocalDateTime exposeDate;

    @JsonProperty("KeywordText")
    private String keywordText;

    @JsonProperty("KeywordType")
    private int keywordType;

    @JsonProperty("SortNo")
    private int sortNo;

    @JsonProperty("CategoryID")
    private Integer categoryID;

    @JsonProperty("ParentCategoryID")
    private Integer parentCategoryID;

    @JsonProperty("ParentParentCategoryID")
    private Integer parentParentCategoryID;

    @JsonProperty("CreatedOn")
    private LocalDateTime createdOn;

    @JsonProperty("CreatedBy")
    private String createdBy;

    @JsonProperty("ModifiedOn")
    private LocalDateTime modifiedOn;

    @JsonProperty("ModifiedBy")
    private String modifiedBy;
}
