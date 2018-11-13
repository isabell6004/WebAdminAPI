package net.fashiongo.webadmin.model.pojo.admin.parameter;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author Jiwon Kim
 */

public class GetSecurityMenus2Parameter {
	@ApiModelProperty(required = false, example="Web Admin")
	@JsonProperty("menuname")
	private String menuname;
	@ApiModelProperty(required = false, example="")
	@JsonProperty("parentmenuid")
	private String parentmenuid;
	@ApiModelProperty(required = false, example="All")
	@JsonProperty("applicationid")
	private String applicationid;
	@ApiModelProperty(required = true, example = "1")
	@JsonProperty("active")
	private Integer active;
	
	public String getMenuname() {
		return menuname;
	}
	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}
	public String getParentmenuid() {
		return parentmenuid;
	}
	public void setParentmenuid(String parentmenuid) {
		this.parentmenuid = parentmenuid;
	}
	public String getApplicationid() {
		return applicationid;
	}
	public void setApplicationid(String applicationid) {
		this.applicationid = applicationid;
	}
	public Integer getActive() {
		return active;
	}
	public void setActive(Integer active) {
		this.active = active;
	}
	
	
	
}
