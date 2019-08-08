package net.fashiongo.webadmin.data.model.admin;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Resource {

	@JsonProperty("ResourceID")
	private Integer resourceID;
	@JsonProperty("ApplicationID")
	private Integer applicationID;
	@JsonProperty("ApplicationName")
	private String applicationName;
	@JsonProperty("ResourceName")
	private String resourceName;
	@JsonProperty("DispName")
	private String dispName;
	@JsonProperty("ResourceParent")
	private String resourceParent;
	@JsonProperty("ResourceType")
	private String resourceType;
	@JsonProperty("ResourceUrl")
	private String resourceUrl;
	@JsonProperty("Active")
	private Boolean active;

	public String getDispName() {
		String ReturnData = "";
		if (active) ReturnData = "";
		else ReturnData = "[x] ";
		ReturnData += resourceName;
		return ReturnData;
	}
}
