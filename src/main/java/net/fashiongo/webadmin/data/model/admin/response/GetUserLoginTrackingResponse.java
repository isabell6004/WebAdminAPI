package net.fashiongo.webadmin.data.model.admin.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.model.admin.ColumnCount;
import net.fashiongo.webadmin.data.model.admin.UserLogin;
import net.fashiongo.webadmin.data.model.admin.UserMappingVendor;
import net.fashiongo.webadmin.data.model.admin.UserMappingVendorAssigned;

import java.util.List;

@Getter
@Builder
public class GetUserLoginTrackingResponse {

	@JsonProperty("Table")
	private List<UserLogin> userLoginList;

	@JsonProperty("Table1")
	private List<ColumnCount> columnCountList;
}
