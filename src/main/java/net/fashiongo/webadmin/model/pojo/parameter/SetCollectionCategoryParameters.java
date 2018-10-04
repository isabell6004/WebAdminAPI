/**
 * 
 */
package net.fashiongo.webadmin.model.pojo.parameter;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import net.fashiongo.webadmin.model.primary.CollectionCategory;
import net.fashiongo.webadmin.model.primary.MapCollectionCategory;

/**
 * Set CollectionCategory Parameters
 * 
 * @author Sanghyup Kim
 */
public class SetCollectionCategoryParameters {
	@ApiModelProperty(required = true, example = "{CollectionCategoryID, Active}")
	@JsonProperty("obj")
	private CollectionCategory collectionCategory;

	// Act/Del/Upd
	@ApiModelProperty(required = true, example = "Act")
	@JsonProperty("settype")
	private String setType;

	@ApiModelProperty(required = false, example = "{MapID, CollectionCategoryID, CategoryID, CategoryName}")
	@JsonProperty("collectioncategorymap")
	private List<MapCollectionCategory> mapCollectionCategoryList;

	public CollectionCategory getCollectionCategory() {
		return collectionCategory;
	}

	public void setCollectionCategory(CollectionCategory collectionCategory) {
		this.collectionCategory = collectionCategory;
	}

	public String getSetType() {
		return setType;
	}

	public void setSetType(String setType) {
		this.setType = setType;
	}

	public List<MapCollectionCategory> getMapCollectionCategoryList() {
		return mapCollectionCategoryList;
	}

	public void setMapCollectionCategoryList(List<MapCollectionCategory> mapCollectionCategoryList) {
		this.mapCollectionCategoryList = mapCollectionCategoryList;
	}

}
