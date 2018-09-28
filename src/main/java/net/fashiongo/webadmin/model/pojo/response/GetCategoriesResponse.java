package net.fashiongo.webadmin.model.pojo.response;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Categories")
public class GetCategoriesResponse {
	
	private Integer categoryId;
	private String categoryName;
	
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	
}
