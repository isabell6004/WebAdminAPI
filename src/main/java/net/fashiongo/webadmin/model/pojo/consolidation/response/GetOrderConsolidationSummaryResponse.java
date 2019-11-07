package net.fashiongo.webadmin.model.pojo.consolidation.response;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import net.fashiongo.webadmin.model.pojo.consolidation.OrderConsolidationSummary;

@Getter
@Setter
public class GetOrderConsolidationSummaryResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("Table")
	private List<OrderConsolidationSummary> orderConsolidationSummary;
	
}
