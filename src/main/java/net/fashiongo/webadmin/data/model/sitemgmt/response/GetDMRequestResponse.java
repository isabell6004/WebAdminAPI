package net.fashiongo.webadmin.data.model.sitemgmt.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.model.sitemgmt.DMRequest;

import java.util.List;

@Getter
@Builder
public class GetDMRequestResponse {

	@JsonProperty("Table")
	private List<DMRequest> dmList;
}
