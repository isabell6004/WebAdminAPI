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
@Table(name = "Map_Length_Category")
public class MapLengthCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("MapID")
	@Column(name = "MapID")
	private Integer mapID;
	
	@JsonProperty("LengthID")
	@Column(name = "LengthID")
	private Integer lengthID;
	
	@JsonProperty("CategoryID")
	@Column(name = "CategoryID")
	private Integer categoryID;

	public Integer getMapID() {
		return mapID;
	}

	public void setMapID(Integer mapID) {
		this.mapID = mapID;
	}

	public Integer getLengthID() {
		return lengthID;
	}

	public void setLengthID(Integer lengthID) {
		this.lengthID = lengthID;
	}

	public Integer getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(Integer categoryID) {
		this.categoryID = categoryID;
	}
	
	
}
