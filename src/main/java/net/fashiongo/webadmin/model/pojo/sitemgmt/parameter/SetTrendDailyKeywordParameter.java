package net.fashiongo.webadmin.model.pojo.sitemgmt.parameter;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class SetTrendDailyKeywordParameter implements Serializable {
    List<TrendDailyKeywordParameter> keywordList;

    @JsonProperty("applyToAllGivenDays")
    boolean applyToAllGivenDays;

    String srcExposeDate;

    List<String> daysToBeApplied;
}
