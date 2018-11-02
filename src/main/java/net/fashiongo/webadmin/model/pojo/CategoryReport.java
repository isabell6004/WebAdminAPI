package net.fashiongo.webadmin.model.pojo;

import java.io.Serializable;

public class CategoryReport implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer parentCategoryID;
	private Integer categoryID;
	private String categoryName;
	private Integer lvl;
	
	public CategoryReport() {}
	public CategoryReport(Integer parentCategoryID, Integer categoryID, String categoryName, Integer lvl) {
		this.parentCategoryID = parentCategoryID;
		this.categoryID = categoryID;
		this.categoryName = categoryName;
		this.lvl = lvl;
	}
	
	public Integer getParentCategoryID() {
		return parentCategoryID;
	}
	public void setParentCategoryID(Integer parentCategoryID) {
		this.parentCategoryID = parentCategoryID;
	}
	public Integer getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(Integer categoryID) {
		this.categoryID = categoryID;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Integer getLvl() {
		return lvl;
	}
	public void setLvl(Integer lvl) {
		this.lvl = lvl;
	}

}
