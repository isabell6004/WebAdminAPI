package net.fashiongo.webadmin.data.model.admin;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SecurityGroupPermissions {

	@JsonProperty("MenuID")
	private Integer menuID;
	@JsonProperty("ResourceID")
	private Integer resourceID;
	@JsonProperty("Resource")
	private String resource;
	@JsonProperty("ParentMenuID")
	private Integer parentMenuID;
	@JsonProperty("Parent")
	private String parent;
	@JsonProperty("Type")
	private String type;
	@JsonProperty("Allow")
	private Boolean allow;
	@JsonProperty("AllowEdit")
	private Boolean allowEdit;
	@JsonProperty("AllowDelete")
	private Boolean allowDelete;
	@JsonProperty("AllowAdd")
	private Boolean allowAdd;
	@JsonProperty("Visible")
	private Boolean visible;
}
