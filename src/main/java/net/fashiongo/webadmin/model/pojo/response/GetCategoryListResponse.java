/**
 * 
 */
package net.fashiongo.webadmin.model.pojo.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.webadmin.model.primary.CollectionCategory;

/**
 * @author Sanghyup Kim
 */
public class GetCategoryListResponse {
	@JsonProperty("Table")
	private List<CollectionCategory> categorylist;

	public List<CollectionCategory> getCategorylist() {
		return categorylist;
	}

	public void setCategorylist(List<CollectionCategory> categorylist) {
		this.categorylist = categorylist;
	}

}
