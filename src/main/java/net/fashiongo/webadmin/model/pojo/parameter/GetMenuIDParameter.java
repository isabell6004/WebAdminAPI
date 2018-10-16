package net.fashiongo.webadmin.model.pojo.parameter;

import org.apache.commons.lang3.StringUtils;

import io.swagger.annotations.ApiModelProperty;

public class GetMenuIDParameter {
	@ApiModelProperty(required = false, example="vendor-list")
	private String pageName;

	public String getPageName() {
		return StringUtils.isEmpty(pageName) ? "" : pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	
}
