package net.fashiongo.webadmin.model.pojo.buyer.parameter;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class SetAdminRetailerInfoParameter {
	@JsonProperty("obj")
	private List<RetailerInfo> retailerList;

	@Data
	public static class RetailerInfo {
		@JsonProperty("retailerId")
		private int retailerId;

		@JsonProperty("active")
		private String active;

		@JsonProperty("currentStatus")
		private int currentStatus;
	}
}
