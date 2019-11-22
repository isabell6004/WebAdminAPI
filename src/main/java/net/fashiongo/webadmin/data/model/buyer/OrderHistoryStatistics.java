package net.fashiongo.webadmin.data.model.buyer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.math.BigDecimal;

@Getter
@Builder
public class OrderHistoryStatistics {

	@JsonProperty("Average_Order_Amount")
	@Column(name = "Average_Order_Amount")
	private BigDecimal averageOrderAmount;

	@JsonProperty("Cancelled_By_Buyer")
	@Column(name = "Cancelled_By_Buyer")
	private BigDecimal cancelledByBuyer;

	@JsonProperty("Cancelled_By_Vendor")
	@Column(name = "Cancelled_By_Vendor")
	private BigDecimal cancelledByVendor;

	@JsonProperty("Order_Amount_VS_Cancel_Ratio")
	@Column(name = "Order_Amount_VS_Cancel_Ratio")
	private BigDecimal orderAmountVSCancelRatio;

	@JsonProperty("Total_Order_Amount")
	@Column(name = "Total_Order_Amount")
	private BigDecimal totalOrderAmount;

	@JsonProperty("Total_Order_Qty")
	@Column(name = "Total_Order_Qty")
	private Integer totalOrderQty;

	@JsonProperty("Total_Vendor_Qty")
	@Column(name = "Total_Vendor_Qty")
	private Integer totalVendorQty;

	public OrderHistoryStatistics() {
	}

	public OrderHistoryStatistics(BigDecimal averageOrderAmount, BigDecimal cancelledByBuyer, BigDecimal cancelledByVendor, BigDecimal orderAmountVSCancelRatio, BigDecimal totalOrderAmount, Integer totalOrderQty, Integer totalVendorQty) {
		this.averageOrderAmount = averageOrderAmount;
		this.cancelledByBuyer = cancelledByBuyer;
		this.cancelledByVendor = cancelledByVendor;
		this.orderAmountVSCancelRatio = orderAmountVSCancelRatio;
		this.totalOrderAmount = totalOrderAmount;
		this.totalOrderQty = totalOrderQty;
		this.totalVendorQty = totalVendorQty;
	}
}
