package net.fashiongo.webadmin.model.pojo.parameter;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class GetVendorNewsDetailParameter {
	@ApiModelProperty(required = false, example = "1")
	@JsonProperty("newsid")
	private Integer newsID;

	public Integer getNewsID() {
		return newsID == null ? 0: newsID;
	}

	public void setNewsID(Integer newsID) {
		this.newsID = newsID;
	}
	
	
}
