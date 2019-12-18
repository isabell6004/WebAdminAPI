package net.fashiongo.webadmin.data.model.statistics;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class GetHotSearchParameter {

	@JsonProperty("top")
	private Integer top;

	@JsonProperty("periodtype")
	private Integer periodtype;

	@JsonProperty("fromdate")
	private String fromdate;

	@JsonProperty("todate")
	private String todate;

	@JsonProperty("orderby")
	private String orderby;

	@JsonProperty("searchfield")
	private String searchfield;

	@JsonProperty("searchkeyword")
	private String searchkeyword;
}
