package net.fashiongo.webadmin.model.pojo.consolidation.parameter;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetConsolidationSummaryParameter {
	@JsonProperty("periodtype")	
	private Integer periodType;
	private LocalDateTime fromDate;
	private LocalDateTime toDate;

}
