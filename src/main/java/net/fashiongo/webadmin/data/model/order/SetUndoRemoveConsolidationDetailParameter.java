package net.fashiongo.webadmin.data.model.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class SetUndoRemoveConsolidationDetailParameter {

	@JsonProperty("orderId")
	private Integer orderId;
}
