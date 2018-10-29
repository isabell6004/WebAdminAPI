package net.fashiongo.webadmin.model.pojo.parameter;

import java.time.LocalDateTime;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
* @author Jiwon Kim
*/
public class GetCategoryAdItemForBidVendorParameter {
    @ApiModelProperty(required = false, example="76")
	@JsonProperty("adID")
	private Integer adID;

	public Integer getAdID() {
		return adID;
	}

	public void setAdID(Integer adID) {
		this.adID = adID;
	}

}