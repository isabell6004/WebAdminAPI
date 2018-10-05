package net.fashiongo.webadmin.model.pojo.parameter;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author Nayeon Kim
 */
public class SetAddPageParameter {
	@ApiModelProperty(required = false, example="")
	@JsonProperty("pageid")
	private Integer pageID;
	
	@ApiModelProperty(required = false, example="Test")
	@JsonProperty("pagename")
	private String pageName;

	public Integer getPageID() {
		return pageID;
	}

	public void setPageID(Integer pageID) {
		this.pageID = pageID;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
}
