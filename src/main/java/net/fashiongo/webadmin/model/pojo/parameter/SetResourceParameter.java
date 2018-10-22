package net.fashiongo.webadmin.model.pojo.parameter;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
/**
 * 
 * @author DAHYE
 *
 */
@SuppressWarnings("serial")
public class SetResourceParameter implements Serializable {
	@ApiModelProperty(required = false, example="161")
	private Integer resourceId;
	@ApiModelProperty(required = false, example="false")
	private Boolean active;
	public Integer getResourceId() {
		return resourceId == null ? 0 : resourceId;
	}
	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}
	public Boolean getActive() {
		return active == null ? false : active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	
}
