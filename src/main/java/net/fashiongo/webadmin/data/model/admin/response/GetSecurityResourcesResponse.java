package net.fashiongo.webadmin.data.model.admin.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.model.admin.Resource;

import java.util.List;

@Getter
@Builder
public class GetSecurityResourcesResponse {

	@JsonProperty("Table")
	private List<Resource> resource;
}
