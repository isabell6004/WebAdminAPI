package net.fashiongo.webadmin.data.model.admin;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserMappingVendorAssigned {

	@JsonProperty("Assigned")
	private Integer assigned;
}
