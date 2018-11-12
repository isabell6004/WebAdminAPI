package net.fashiongo.webadmin.model.pojo.admin.parameter;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class SetActiveSecurityMenusParameter {
	
	@ApiModelProperty(required = false, example="1")
	@JsonProperty("MenuID")
	private Integer menuID;
	
	@ApiModelProperty(required = false, example="1")
	@JsonProperty("Active")
	private Boolean Active;

	public Integer getMenuID() {
		return menuID;
	}

	public void setMenuID(Integer menuID) {
		this.menuID = menuID;
	}

	public Boolean getActive() {
		return Active;
	}

	public void setActive(Boolean active) {
		Active = active;
	}

	
}
