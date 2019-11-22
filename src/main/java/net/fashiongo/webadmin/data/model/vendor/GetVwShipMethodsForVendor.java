package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public class GetVwShipMethodsForVendor {

	@JsonProperty("IsDefault")
	private boolean isDefault;

	@JsonProperty("ShipMethodID")
	private Integer shipMethodID;

	@JsonProperty("ShipMethodName")
	private String shipMethodName;

	@JsonProperty("WholeSalerID")
	private Integer wholeSalerID;

	@JsonProperty("WholeShipID")
	private Integer wholeShipID;

	public boolean getIsDefault() {
		return isDefault;
	}

	public Integer getShipMethodID() {
		return shipMethodID;
	}

	public String getShipMethodName() {
		return shipMethodName;
	}

	public Integer getWholeSalerID() {
		return wholeSalerID;
	}

	public Integer getWholeShipID() {
		return wholeShipID;
	}
}
