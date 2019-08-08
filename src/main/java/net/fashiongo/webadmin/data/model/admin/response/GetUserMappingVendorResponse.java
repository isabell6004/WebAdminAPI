package net.fashiongo.webadmin.data.model.admin.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.model.admin.UserMappingVendor;
import net.fashiongo.webadmin.data.model.admin.UserMappingVendorAssigned;

import java.util.List;

@Getter
@Builder
public class GetUserMappingVendorResponse {

	@JsonProperty("Table")
	private List<UserMappingVendor> userMappingVendorList;

	@JsonProperty("Table1")
	private List<UserMappingVendorAssigned> userMappingVendorAssigned;
}
