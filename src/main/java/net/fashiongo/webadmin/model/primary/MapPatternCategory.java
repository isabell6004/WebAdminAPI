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
@Table(name = "Map_Pattern_Category")
public class MapPatternCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("MapID")
	@Column(name = "MapID")
	private Integer mapID;
	
	@JsonProperty("PatternID")
	@Column(name = "PatternID")
	private Integer patternID;
	
	@JsonProperty("CategoryID")
	@Column(name = "CategoryID")
	private Integer categoryID;

	public Integer getMapID() {
		return mapID;
	}

	public void setMapID(Integer mapID) {
		this.mapID = mapID;
	}

	public Integer getPatternID() {
		return patternID;
	}

	public void setPatternID(Integer patternID) {
		this.patternID = patternID;
	}

	public Integer getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(Integer categoryID) {
		this.categoryID = categoryID;
	}
	
	
}
