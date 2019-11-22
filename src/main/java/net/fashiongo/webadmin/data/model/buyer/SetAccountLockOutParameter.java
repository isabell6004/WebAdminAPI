package net.fashiongo.webadmin.data.model.buyer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class SetAccountLockOutParameter {

	@JsonProperty(value = "retailerid")
	private Integer retailerId;

	@JsonProperty(value = "WholeSalerID")
	private Integer wholeSalerID;

	@JsonProperty(value = "Active")
	private Boolean active;
}
