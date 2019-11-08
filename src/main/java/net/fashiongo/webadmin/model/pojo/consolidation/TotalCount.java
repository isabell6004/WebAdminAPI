package net.fashiongo.webadmin.model.pojo.consolidation;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TotalCount {
	@JsonProperty("RecCount")
	private Integer recCount;
}
