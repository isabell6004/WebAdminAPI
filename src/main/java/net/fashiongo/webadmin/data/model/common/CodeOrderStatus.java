package net.fashiongo.webadmin.data.model.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CodeOrderStatus {

	@JsonProperty("OrderStatusID")
	private Integer orderStatusID;

	@JsonProperty("OrderStatusName")
	private String orderStatusName;

	@JsonProperty("Name2Vendor")
	private String name2Vendor;

	@JsonProperty("Name2Retailer")
	private String name2Retailer;

	public CodeOrderStatus(Integer orderStatusID, String orderStatusName, String name2Vendor, String name2Retailer) {
		this.orderStatusID = orderStatusID;
		this.orderStatusName = orderStatusName;
		this.name2Vendor = name2Vendor;
		this.name2Retailer = name2Retailer;
	}
}
