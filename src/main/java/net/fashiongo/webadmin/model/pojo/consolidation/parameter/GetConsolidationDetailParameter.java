package net.fashiongo.webadmin.model.pojo.consolidation.parameter;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetConsolidationDetailParameter {
	@JsonProperty("ConsolidationId")
	private Integer consolidationId;

}
