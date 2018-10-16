package net.fashiongo.webadmin.model.pojo.parameter;

import org.apache.commons.lang3.StringUtils;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author Nayeon Kim
 */
public class SetAddPageParameter {
	@ApiModelProperty(required = false, example="1")
	private String pageID;
	
	@ApiModelProperty(required = false, example="Test")
	private String pageName;

	public Integer getPageID() {
		return StringUtils.isEmpty(pageID) ? null : Integer.parseInt(pageID);
	}

	public String getPageName() {
		return StringUtils.isEmpty(pageName) ? "" : pageName;
	}
}
