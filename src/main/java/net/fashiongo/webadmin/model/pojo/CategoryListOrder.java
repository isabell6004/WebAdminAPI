package net.fashiongo.webadmin.model.pojo;

import java.io.Serializable;

public class CategoryListOrder implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer categoryID;
	private Integer parentCategoryID;
	private String categoryName;
	private Integer lvl;
	private Integer listOrder;
	private Boolean active;
	
	public CategoryListOrder() {}
	public CategoryListOrder(Integer categoryID, Integer parentCategoryID, String categoryName, Integer lvl, Integer listOrder, Boolean active) {
		this.categoryID = categoryID;
		this.parentCategoryID = parentCategoryID;
		this.categoryName = categoryName;
		this.lvl = lvl;
		this.listOrder = listOrder;
		this.active = active;
	}
	
	public Integer getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(Integer categoryID) {
		this.categoryID = categoryID;
	}
	public Integer getParentCategoryID() {
		return parentCategoryID;
	}
	public void setParentCategoryID(Integer parentCategoryID) {
		this.parentCategoryID = parentCategoryID;
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
	public Integer getListOrder() {
		return listOrder;
	}
	public void setListOrder(Integer listOrder) {
		this.listOrder = listOrder;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
