package net.fashiongo.webadmin.model.pojo.sitemgmt.parameter;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SetTrendDailyKeywordParameter implements Serializable {
    @JsonProperty("TrendDailyKeywordID")
    private Long trendDailyKeywordID;

    @JsonProperty("ExposeDate")
    private String exposeDate;

    @JsonProperty("SortNo")
    private Integer sortNo;

    @JsonProperty("KeywordText")
    private String keywordText;

    @JsonProperty("KeywordType")
    private Integer keywordType;

    @JsonProperty("CategoryID")
    private Integer categoryID;
}
