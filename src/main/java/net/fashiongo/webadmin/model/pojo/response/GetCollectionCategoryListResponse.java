/**
 * 
 */
package net.fashiongo.webadmin.model.pojo.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import net.fashiongo.webadmin.model.primary.AdPageSpot;
import net.fashiongo.webadmin.model.primary.Category;
import net.fashiongo.webadmin.model.primary.CollectionCategory;
import net.fashiongo.webadmin.model.primary.MapCollectionCategory;

/**
 * @author Sanghyup Kim
 */
@JsonSerialize
public class GetCollectionCategoryListResponse {
	@JsonProperty("Table")
	private List<CollectionCategory> collectionCategoryList;

	@JsonProperty("Table1")
	private List<MapCollectionCategory> mapCollectionCategoryList;

	@JsonProperty("Table2")
	private List<AdPageSpot> adPageSpotList;

	@JsonProperty("Table3")
	private List<Category> categoryList;

	public List<CollectionCategory> getCollectionCategoryList() {
		return collectionCategoryList;
	}

	public void setCollectionCategoryList(List<CollectionCategory> collectionCategoryList) {
		this.collectionCategoryList = collectionCategoryList;
	}

	public List<MapCollectionCategory> getMapCollectionCategoryList() {
		return mapCollectionCategoryList;
	}

	public void setMapCollectionCategoryList(List<MapCollectionCategory> mapCollectionCategoryList) {
		this.mapCollectionCategoryList = mapCollectionCategoryList;
	}

	public List<AdPageSpot> getAdPageSpotList() {
		return adPageSpotList;
	}

	public void setAdPageSpotList(List<AdPageSpot> adPageSpotList) {
		this.adPageSpotList = adPageSpotList;
	}

	public List<Category> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}

}
