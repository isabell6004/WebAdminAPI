package net.fashiongo.webadmin.data.model.admin.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.model.admin.SecurityMenus2;

import java.util.List;

@Getter
@Builder
public class GetSecurityMenus2Response {

	@JsonProperty("Table")
	private List<SecurityMenus2> securityMenus2;
}
