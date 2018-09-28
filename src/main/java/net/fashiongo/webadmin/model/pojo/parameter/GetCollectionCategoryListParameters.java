/**
 * 
 */
package net.fashiongo.webadmin.model.pojo.parameter;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author Sanghyup Kim
 */
public class GetCollectionCategoryListParameters {
	@ApiModelProperty(required = true, example="0")
	private Integer categoryId;
	
	@ApiModelProperty(required = true, example="1")
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
