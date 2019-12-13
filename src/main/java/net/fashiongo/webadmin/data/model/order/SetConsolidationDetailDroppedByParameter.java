package net.fashiongo.webadmin.data.model.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class SetConsolidationDetailDroppedByParameter {

	@JsonProperty("orderid")
	private Integer orderId;

	@JsonProperty("consolidationid")
	private Integer consolidationId;

	@JsonProperty("droppedby")
	private String droppedBy;

	@JsonProperty("receivedby")
	private String receivedBy;

	@JsonProperty("receivedon")
	private String receivedOn;
}
