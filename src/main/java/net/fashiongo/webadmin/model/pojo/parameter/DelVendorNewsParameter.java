package net.fashiongo.webadmin.model.pojo.parameter;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class DelVendorNewsParameter {
	@ApiModelProperty(required = false, example="12637")
	@JsonProperty("arraynewsid")
	private String arrayNewsID;

	public String getArrayNewsID() {
		return arrayNewsID;
	}

	public void setArrayNewsID(String arrayNewsID) {
		this.arrayNewsID = arrayNewsID;
	}
	
	
}
