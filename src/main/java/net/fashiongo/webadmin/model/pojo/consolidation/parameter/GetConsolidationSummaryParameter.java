package net.fashiongo.webadmin.model.pojo.consolidation.parameter;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetConsolidationSummaryParameter {
	private Integer periodType;
	private LocalDateTime fromDate;
	private LocalDateTime toDate;

}
