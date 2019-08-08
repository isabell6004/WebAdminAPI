package net.fashiongo.webadmin.data.model.admin.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.model.admin.SecurityGroupPermissions;

import java.util.List;

@Getter
@Builder
public class GetSecurityGroupPermissionsResponse {

	@JsonProperty("Table")
	private List<SecurityGroupPermissions> securityGroupsPermissions;
}
