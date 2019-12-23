package net.fashiongo.webadmin.data.model.statistics;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class GetFGKPIParameter {

	@JsonProperty("pn")
	private Integer pn;

	@JsonProperty("ps")
	private Integer ps;

	@JsonProperty("unit")
	private Integer unit;

	@JsonProperty("df")
	private String df;

	@JsonProperty("dt")
	private String dt;

	@JsonProperty("orderBy")
	private String orderBy;

}
