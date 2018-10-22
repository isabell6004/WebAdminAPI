package net.fashiongo.webadmin.model.pojo.parameter;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author Nayeon Kim
 */
public class SetAddPageParameter {
	@JsonProperty("PageID")
	@ApiModelProperty(required = false, example="1")
	private String pageID;
	
	@JsonProperty("PageName")
	@ApiModelProperty(required = false, example="Test")
	private String pageName;

	public Integer getPageID() {
		return StringUtils.isEmpty(pageID) ? null : Integer.parseInt(pageID);
	}

	public String getPageName() {
		return StringUtils.isEmpty(pageName) ? "" : pageName;
	}

	public void setPageID(String pageID) {
		this.pageID = pageID;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
}
