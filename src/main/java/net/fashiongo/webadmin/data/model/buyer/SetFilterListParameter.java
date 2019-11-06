package net.fashiongo.webadmin.data.model.buyer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class SetFilterListParameter {

	@JsonProperty("savedid")
	private Integer savedid;

	@JsonProperty("display")
	private String display;

	@JsonProperty("filter")
	private String filter;
}
