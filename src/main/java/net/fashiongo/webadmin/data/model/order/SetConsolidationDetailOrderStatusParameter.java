package net.fashiongo.webadmin.data.model.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class SetConsolidationDetailOrderStatusParameter {

	@JsonProperty("consolidationid")
	private Integer consolidationId;

	@JsonProperty("orderid")
	private Integer orderId;

	@JsonProperty("orderstatusid")
	private Integer orderStatusId;
}
