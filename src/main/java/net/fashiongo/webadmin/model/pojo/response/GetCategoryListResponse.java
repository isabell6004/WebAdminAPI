/**
 * 
 */
package net.fashiongo.webadmin.model.pojo.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import net.fashiongo.webadmin.model.primary.CollectionCategory;

/**
 * @author Sanghyup Kim
 */
@JsonSerialize
public class GetCategoryListResponse {
	@JsonProperty("Table")
	private List<CollectionCategory> categoryLst;

	public List<CollectionCategory> getCategoryLst() {
		return categoryLst;
	}

	public void setCategoryLst(List<CollectionCategory> categoryLst) {
		this.categoryLst = categoryLst;
	}


}
