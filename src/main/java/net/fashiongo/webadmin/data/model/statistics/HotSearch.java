package net.fashiongo.webadmin.data.model.statistics;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HotSearch {

	@JsonProperty("SearchQuery")
	private String SearchQuery;

	@JsonProperty("Count")
	private Long Count;

	@JsonProperty("rankno")
	private Integer rankno;
}
