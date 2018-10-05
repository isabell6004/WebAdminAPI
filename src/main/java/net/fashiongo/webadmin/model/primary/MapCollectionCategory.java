package net.fashiongo.webadmin.model.primary;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author sanghyup
 */
@Entity
@Table(name = "Map_CollectionCategory")
public class MapCollectionCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@NotNull
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonProperty("MapID")
	@Column(name = "MapID")
	private Integer mapID;

	@JsonProperty("CollectionCategoryID")
	@Column(name = "CollectionCategoryID")
	private Integer collectionCategoryID;

	@JsonProperty("CategoryID")
	@Column(name = "CategoryID")
	private Integer categoryID;

	@Transient
	@JsonProperty("CategoryName")
	@Column(name = "CategoryName")
	private String categoryName;

	public Integer getMapID() {
		return mapID;
	}

	public void setMapID(Integer mapID) {
		this.mapID = mapID;
	}

	public Integer getCollectionCategoryID() {
		return collectionCategoryID;
	}

	public void setCollectionCategoryID(Integer collectionCategoryID) {
		this.collectionCategoryID = collectionCategoryID;
	}

	public Integer getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(Integer categoryID) {
		this.categoryID = categoryID;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
