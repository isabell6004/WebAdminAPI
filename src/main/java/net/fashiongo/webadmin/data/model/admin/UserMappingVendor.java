package net.fashiongo.webadmin.data.model.admin;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserMappingVendor {

	@JsonProperty("WholeSalerID")
	private Integer wholeSalerID;
	@JsonProperty("CompanyName")
	private String companyName;
	@JsonProperty("MapID")
	private Integer mapID;
}
