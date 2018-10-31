package net.fashiongo.webadmin.model.pojo.parameter;

import java.time.LocalDateTime;

import javax.persistence.Column;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
* @author Jiwon Kim
*/
public class GetCategoryAdListParameter {
    
	
    @ApiModelProperty(required = false, example="")
	@JsonProperty("categorydate")
	@Column(name = "categorydate")
	private String categorydate;
	

    
	public String getCategoryDate() {
		return StringUtils.isEmpty(categorydate) ? null : categorydate;
	}
	public void setCategoryDate(String categorydate) {
		this.categorydate = categorydate;
	}

}