package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class GetVwPaymentMethodsForVendorParameter {

	@JsonProperty(value = "wholesalerid")
	private Integer wholesalerId;
}
