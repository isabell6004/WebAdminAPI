package net.fashiongo.webadmin.data.model.buyer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class SetShippingStatusParameter {

	@JsonProperty(value = "type")
	private String type;

	@JsonProperty(value = "shipaddid")
	private Integer shipaddid;

	@JsonProperty(value = "value")
	private Boolean value;
}
