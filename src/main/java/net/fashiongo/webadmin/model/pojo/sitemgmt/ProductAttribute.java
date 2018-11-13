package net.fashiongo.webadmin.model.pojo.sitemgmt;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Reo
 *
 */
public class ProductAttribute {
	@JsonProperty("CodeID")
	private Integer codeID;
	@JsonProperty("MapID")
	private Integer mapID;
	@JsonProperty("CategoryID")
	private Integer categoryID;
	
	public Integer getCodeID() {
		return codeID;
	}
	public void setCodeID(Integer codeID) {
		this.codeID = codeID;
	}
	public Integer getMapID() {
		return mapID;
	}
	public void setMapID(Integer mapID) {
		this.mapID = mapID;
	}
	public Integer getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(Integer categoryID) {
		this.categoryID = categoryID;
	}
	
	
}
