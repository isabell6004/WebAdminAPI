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
}
