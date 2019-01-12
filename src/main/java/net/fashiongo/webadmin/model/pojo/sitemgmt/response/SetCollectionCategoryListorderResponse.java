package net.fashiongo.webadmin.model.pojo.sitemgmt.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.webadmin.model.primary.CollectionCategory2;

/*
 * Set CollectionCategoryListorder Response
 * @author sanghyup
 * 
 */
public class SetCollectionCategoryListorderResponse {

	@JsonProperty("Table")
	private List<CollectionCategory2> categoryCollectionlist;

	public List<CollectionCategory2> getCategoryCollectionlist() {
		return categoryCollectionlist;
	}

	public void setCategoryCollectionlist(List<CollectionCategory2> categoryCollectionlist) {
		this.categoryCollectionlist = categoryCollectionlist;
	}

}
