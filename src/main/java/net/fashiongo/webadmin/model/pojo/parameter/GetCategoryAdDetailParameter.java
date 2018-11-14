package net.fashiongo.webadmin.model.pojo.parameter;

import java.time.LocalDateTime;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
* @author Jiwon Kim
*/
public class GetCategoryAdDetailParameter {
    @ApiModelProperty(required = false, example="76")
	@JsonProperty("spotID")
	private Integer spotID;
    
    @ApiModelProperty(required = false, example="2018-10-24")
    @JsonProperty("categorydate")
	private String categorydate;

	public Integer getSpotID() {
		return spotID;
	}

	public void setSpotID(Integer spotID) {
		this.spotID = spotID;
	}

	public String getCategorydate() {
		return categorydate;
	}

	public void setCategorydate(String categorydate) {
		this.categorydate = categorydate;
	}

	
    
    
}