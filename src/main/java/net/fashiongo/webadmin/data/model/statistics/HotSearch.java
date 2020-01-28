package net.fashiongo.webadmin.data.model.statistics;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class HotSearch {

	@JsonProperty("SearchQuery")
	private String SearchQuery;

	@JsonProperty("Count")
	private Integer Count;

	@JsonProperty("rankno")
	private Long rankno;
}
