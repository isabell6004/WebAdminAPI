package net.fashiongo.webadmin.model.pojo.sitemgmt;

import java.io.Serializable;

public class CategoryListOrder implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer categoryId;
	private Integer parentCategoryId;
	private String categoryName;
	private Integer lvl;
	private Integer listOrder;
	private Boolean active;
	
	public CategoryListOrder() {}
	public CategoryListOrder(Integer categoryId, Integer parentCategoryId, String categoryName, Integer lvl, Integer listOrder, Boolean active) {
		this.categoryId = categoryId;
		this.parentCategoryId = parentCategoryId;
		this.categoryName = categoryName;
		this.lvl = lvl;
		this.listOrder = listOrder;
		this.active = active;
	}
	
	public Integer getCategoryID() {
		return categoryId;
	}
	public void setCategoryID(Integer categoryID) {
		this.categoryId = categoryID;
	}
	public Integer getParentCategoryID() {
		return parentCategoryId;
	}
	public void setParentCategoryID(Integer parentCategoryID) {
		this.parentCategoryId = parentCategoryID;
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
