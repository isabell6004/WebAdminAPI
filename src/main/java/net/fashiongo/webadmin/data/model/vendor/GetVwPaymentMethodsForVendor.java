package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public class GetVwPaymentMethodsForVendor {

	@JsonProperty("MapID")
	private Integer mapID;

	@JsonProperty("PaymentMethodID")
	private Integer paymentMethodID;

	@JsonProperty("PaymentMethodName")
	private String paymentMethodName;

	@JsonProperty("WholeSalerID")
	private Integer wholeSalerID;

	public Integer getMapID() {
		return mapID;
	}

	public Integer getPaymentMethodID() {
		return paymentMethodID;
	}

	public String getPaymentMethodName() {
		return paymentMethodName;
	}

	public Integer getWholeSalerID() {
		return wholeSalerID;
	}
}
