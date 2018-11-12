package net.fashiongo.webadmin.model.pojo.ad.parameter;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
* @author Jiwon Kim
*/
public class DelCategoryAdItemParameter {
    @ApiModelProperty(required = false, example="0")
	@JsonProperty("ccItemID")
	private Integer ccItemID;

	public Integer getCcItemID() {
		return ccItemID;
	}

	public void setCcItemID(Integer ccItemID) {
		this.ccItemID = ccItemID;
	}
    
    
}