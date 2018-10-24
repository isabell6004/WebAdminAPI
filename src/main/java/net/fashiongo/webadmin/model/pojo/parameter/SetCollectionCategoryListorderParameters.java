/**
 * 
 */
package net.fashiongo.webadmin.model.pojo.parameter;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * Set CollectionCategoryListorder Parameters
 * @author Sanghyup Kim
 */
public class SetCollectionCategoryListorderParameters {
	@ApiModelProperty(required = true, example = "8")
	@JsonProperty("categoryid")
	private Integer categoryId;

	@ApiModelProperty(required = true, example = "1")
	@JsonProperty("parentcategoryid")
	private Integer parentCategoryId;

	@ApiModelProperty(required = true, example = "3")
	@JsonProperty("listorder")
	private Integer listOrder;

	@ApiModelProperty(required = true, example = "2")
	@JsonProperty("lvl")
	private Integer lvl;

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getParentCategoryId() {
		return parentCategoryId;
	}

	public void setParentCategoryId(Integer parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}

	public Integer getListOrder() {
		return listOrder;
	}

	public void setListOrder(Integer listOrder) {
		this.listOrder = listOrder;
	}

	public Integer getLvl() {
		return lvl;
	}

	public void setLvl(Integer lvl) {
		this.lvl = lvl;
	}

	
}
