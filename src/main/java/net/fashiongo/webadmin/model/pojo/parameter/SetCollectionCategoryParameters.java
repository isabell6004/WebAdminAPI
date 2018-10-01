/**
 * 
 */
package net.fashiongo.webadmin.model.pojo.parameter;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import net.fashiongo.webadmin.model.primary.CollectionCategory;
import net.fashiongo.webadmin.model.primary.MapCollectionCategory;

/**
 * Set CollectionCategory Parameters
 * @author Sanghyup Kim
 */
public class SetCollectionCategoryParameters {
	@ApiModelProperty(required = true, example = "{CollectionCategoryID, Active}")
	@JsonProperty("obj")
	private CollectionCategory collectionCategory;

	// Act/Del/Upd
	@ApiModelProperty(required = true, example = "Act")
	@JsonProperty("settype")
	private Integer setType;
	
	@ApiModelProperty(required = false, example = "{MapID, CollectionCategoryID, CategoryID, CategoryName}")
	@JsonProperty("collectioncategorymap")
	private MapCollectionCategory mapCollectionCategory;

	public CollectionCategory getCollectionCategory() {
		return collectionCategory;
	}

	public void setCollectionCategory(CollectionCategory collectionCategory) {
		this.collectionCategory = collectionCategory;
	}

	public Integer getSetType() {
		return setType;
	}

	public void setSetType(Integer setType) {
		this.setType = setType;
	}

	public MapCollectionCategory getMapCollectionCategory() {
		return mapCollectionCategory;
	}

	public void setMapCollectionCategory(MapCollectionCategory mapCollectionCategory) {
		this.mapCollectionCategory = mapCollectionCategory;
	}
	
	
}
