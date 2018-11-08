package net.fashiongo.webadmin.model.primary;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author sanghyup
 */
@Entity
@Table(name = "Map_CollectionCategory")
@XmlRootElement
/*@NamedQueries({ @NamedQuery(name = "MapCollectionCategory.findAll", query = "SELECT m FROM MapCollectionCategory m"),
		@NamedQuery(name = "MapCollectionCategory.findByMapID", query = "SELECT m FROM MapCollectionCategory m WHERE m.mapID = :mapID"),
		@NamedQuery(name = "MapCollectionCategory.findByCollectionCategoryID", query = "SELECT m FROM MapCollectionCategory m WHERE m.collectionCategoryID = :collectionCategoryID"),
		@NamedQuery(name = "MapCollectionCategory.findByCategoryID", query = "SELECT m FROM MapCollectionCategory m WHERE m.categoryID = :categoryID") })
*/
public class MapCollectionCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	public MapCollectionCategory() {
	}

	public MapCollectionCategory(Integer mapID) {
		this.mapID = mapID;
	}

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

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (mapID != null ? mapID.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof MapCollectionCategory)) {
			return false;
		}
		MapCollectionCategory other = (MapCollectionCategory) object;
		if ((this.mapID == null && other.mapID != null) || (this.mapID != null && !this.mapID.equals(other.mapID))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.example.mavenproject3.MapCollectionCategory[ mapID=" + mapID + " ]";
	}
}
