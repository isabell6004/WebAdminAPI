package net.fashiongo.webadmin.model.pojo.parameter;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
* @author Jiwon Kim
*/
public class GetCategoryAdCalendarParameter {
    @ApiModelProperty(required = false, example="2018-01")
	@JsonProperty("categoryDate")
	private String categoryDate;

	public String getCategoryDate() {
		return categoryDate + "-01";
	}

	public void setCategoryDate(String categoryDate) {
		this.categoryDate = categoryDate;
	}
    
    
}