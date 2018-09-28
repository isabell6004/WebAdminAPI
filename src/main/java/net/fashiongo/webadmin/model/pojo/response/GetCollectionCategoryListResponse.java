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
public class GetCollectionCategoryListResponse {
	@JsonProperty("Table")
	private List<CollectionCategory> collectionCategorylist;

	public List<CollectionCategory> getCollectionCategorylist() {
		return collectionCategorylist;
	}

	public void setCollectionCategorylist(List<CollectionCategory> collectionCategorylist) {
		this.collectionCategorylist = collectionCategorylist;
	}
	
}
