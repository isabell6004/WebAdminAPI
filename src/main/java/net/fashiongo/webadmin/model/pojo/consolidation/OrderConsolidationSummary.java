package net.fashiongo.webadmin.model.pojo.consolidation;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class OrderConsolidationSummary implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("OrderQty")
	private Integer orderQty;

	@JsonProperty("OrderAmount")
	private Double orderAmount;

	@JsonProperty("ShippingCharge")
	private Double shippingCharge;

	@JsonProperty("ActualShippingCharge")
	private Double actualShippingCharge;

	@JsonProperty("AvgShippinProcessingTime")
	private Integer avgShippinProcessingTime;
	
}
