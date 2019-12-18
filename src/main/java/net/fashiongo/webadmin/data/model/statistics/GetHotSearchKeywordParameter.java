package net.fashiongo.webadmin.data.model.statistics;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class GetHotSearchKeywordParameter {

	@JsonProperty("periodtype")
	private Integer periodtype;

	@JsonProperty("fromdate")
	private String fromdate;

	@JsonProperty("todate")
	private String todate;

	@JsonProperty("keyword")
	private String keyword;
}
