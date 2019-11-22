package net.fashiongo.webadmin.data.model.buyer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ShippingInfo {

	@JsonProperty("ShipAddID")
	private Integer ShipAddID;

	@JsonProperty("StoreNo")
	private String StoreNo;

	@JsonProperty("ShipAttention")
	private String ShipAttention;

	@JsonProperty("ShipAddress2")
	private String ShipAddress2;

	@JsonProperty("ShipAddress2B")
	private String ShipAddress2B;

	@JsonProperty("ShipCity2")
	private String ShipCity2;

	@JsonProperty("ShipState2")
	private String ShipState2;

	@JsonProperty("ShipCountry2")
	private String ShipCountry2;

	@JsonProperty("ShipCountry2ID")
	private Integer ShipCountry2ID;

	@JsonProperty("IsCommercialAddress")
	private boolean IsCommercialAddress;

	@JsonProperty("ShipZip2")
	private String ShipZip2;

	@JsonProperty("ShipPhone")
	private String ShipPhone;

	@JsonProperty("ShipFax")
	private String ShipFax;
}
