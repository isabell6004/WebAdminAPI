package net.fashiongo.webadmin.data.model.statistics;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HotSearch {

	@JsonProperty("SearchQuery")
	private String SearchQuery;

	@JsonProperty("Count")
	private Integer Count;

	@JsonProperty("rankno")
	private Long rankno;
}
