package net.fashiongo.webadmin.model.pojo.ad.parameter;

import java.time.LocalDateTime;

import javax.persistence.Column;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
* @author Jiwon Kim
*/
public class GetFGCategoryAdListParameter {
    
	
    @ApiModelProperty(required = false, example="")
	@JsonProperty("categorydate")
	@Column(name = "categorydate")
	private String categorydate;
	
    @ApiModelProperty(required = false, example="1")
   	@JsonProperty("categoryId")
   	@Column(name = "categoryId")
   	private Integer categoryId;

    
	public String getCategoryDate() {
		return StringUtils.isEmpty(categorydate) ? null : categorydate;
	}
	public void setCategoryDate(String categorydate) {
		this.categorydate = categorydate;
	}
	
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

}