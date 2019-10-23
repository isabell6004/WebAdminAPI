package net.fashiongo.webadmin.data.model.buyer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class SetInaccessibleVendorsParameter {

	@JsonProperty(value = "wholesalerid")
	private Integer wholesalerid;

	@JsonProperty(value = "retailerid")
	private Integer retailerid;
}
