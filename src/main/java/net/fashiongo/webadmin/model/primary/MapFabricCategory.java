package net.fashiongo.webadmin.model.primary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Reo
 *
 */
@Entity
@Table(name = "Map_Fabric_Category")
public class MapFabricCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("MapID")
	@Column(name = "MapID")
	private Integer mapID;
	
	@JsonProperty("FabricID")
	@Column(name = "FabricID")
	private Integer fabricID;
	
	@JsonProperty("CategoryID")
	@Column(name = "CategoryID")
	private Integer categoryID;

	public Integer getMapID() {
		return mapID;
	}

	public void setMapID(Integer mapID) {
		this.mapID = mapID;
	}

	public Integer getFabricID() {
		return fabricID;
	}

	public void setFabricID(Integer fabricID) {
		this.fabricID = fabricID;
	}

	public Integer getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(Integer categoryID) {
		this.categoryID = categoryID;
	}
	
	
}
