/**
 * 
 */
package net.fashiongo.webadmin.model.pojo.sitemgmt.parameter;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author Sanghyup Kim
 */
public class GetCategoryListParameters {
	@ApiModelProperty(required = true, example="0")
	@JsonProperty("categoryid")
	private Integer categoryId;
	
	@ApiModelProperty(required = true, example="1")
	@JsonProperty("expandall")
	private Integer expandAll;

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getExpandAll() {
		return expandAll;
	}

	public void setExpandAll(Integer expandAll) {
		this.expandAll = expandAll;
	}
	
	
}
