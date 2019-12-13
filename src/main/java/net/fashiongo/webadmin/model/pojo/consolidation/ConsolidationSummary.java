package net.fashiongo.webadmin.model.pojo.consolidation;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ConsolidationSummary implements Serializable {

	private static final long serialVersionUID = 1775046882787109745L;

	@JsonProperty("Order_Qty")
	@Column(name = "Order_Qty")
	private Integer orderQty;

	@JsonProperty("Order_Amount")
	@Column(name = "Order_Amount")
	private BigDecimal orderAmount;

	@JsonProperty("Shipping_Charge")
	@Column(name = "Shipping_Charge")
	private BigDecimal shippingCharge;

	@JsonProperty("Actual_Shipping_Charge")
	@Column(name = "Actual_Shipping_Charge")
	private BigDecimal actualShippingCharge;

	@Column(name = "totalShippingProcessingHours")	
	private Integer totalShippingProcessingHours;

	@Column(name = "totalCount")
	private Integer totalCount;

}
