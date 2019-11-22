package net.fashiongo.webadmin.data.model.buyer.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ShippingAddressResponse {

	@JsonProperty("Active")
	private Boolean active;

	@JsonProperty("CustID2")
	private Integer custID2;

	@JsonProperty("DefaultYN")
	private Boolean defaultYN;

	@JsonProperty("IsCommercialAddress")
	private Boolean isCommercialAddress;

	@JsonProperty("ShipAddID")
	private Integer shipAddID;

	@JsonProperty("ShipAddress2")
	private String shipAddress2;

	@JsonProperty("ShipAddress2B")
	private String shipAddress2B;

	@JsonProperty("ShipAttention")
	private String shipAttention;

	@JsonProperty("ShipCity2")
	private String shipCity2;

	@JsonProperty("ShipCountry2")
	private String shipCountry2;

	@JsonProperty("ShipCountry2ID")
	private Integer shipCountry2ID;

	@JsonProperty("ShipFax")
	private String shipFax;

	@JsonProperty("ShipPhone")
	private String shipPhone;

	@JsonProperty("ShipState2")
	private String shipState2;

	@JsonProperty("ShipZip2")
	private String shipZip2;

	@JsonProperty("StoreNo")
	private String storeNo;
}
