package net.fashiongo.webadmin.model.pojo.sitemgmt.parameter;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author Nayeon Kim
 */
public class SetCategoryListOrderParameter {
	@ApiModelProperty(required = false, example = "8")
	private Integer categoryid;
	
	@ApiModelProperty(required = false, example = "1")
	private Integer parentcategoryid;
	
	@ApiModelProperty(required = false, example = "3")
	private Integer listorder;
	
	@ApiModelProperty(required = false, example = "2")
	private Integer lvl;

	public Integer getCategoryid() {
		return categoryid == null ? 0 : categoryid;
	}

	public void setCategoryid(Integer categoryid) {
		this.categoryid = categoryid;
	}

	public Integer getParentcategoryid() {
		return parentcategoryid == null ? 0 : parentcategoryid;
	}

	public void setParentcategoryid(Integer parentcategoryid) {
		this.parentcategoryid = parentcategoryid;
	}

	public Integer getListorder() {
		return listorder == null ? 0 : listorder;
	}

	public void setListorder(Integer listorder) {
		this.listorder = listorder;
	}

	public Integer getLvl() {
		return lvl == null ? 0 : lvl;
	}

	public void setLvl(Integer lvl) {
		this.lvl = lvl;
	}
}
