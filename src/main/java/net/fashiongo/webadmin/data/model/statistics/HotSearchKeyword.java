package net.fashiongo.webadmin.data.model.statistics;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HotSearchKeyword {

	@JsonProperty("dt")
	private String dt;

	@JsonProperty("cnt")
	private Integer cnt;
}
