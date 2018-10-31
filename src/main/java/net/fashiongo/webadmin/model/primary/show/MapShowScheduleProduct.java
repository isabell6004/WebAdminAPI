package net.fashiongo.webadmin.model.primary.show;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author sanghyup
 */
@Entity
@Table(name = "Map_ShowSchedule_Product")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "MapShowScheduleProduct.findAll", query = "SELECT m FROM MapShowScheduleProduct m"),
		@NamedQuery(name = "MapShowScheduleProduct.findByMapID", query = "SELECT m FROM MapShowScheduleProduct m WHERE m.mapID = :mapID"),
		@NamedQuery(name = "MapShowScheduleProduct.findByShowScheduleID", query = "SELECT m FROM MapShowScheduleProduct m WHERE m.showScheduleID = :showScheduleID"),
		@NamedQuery(name = "MapShowScheduleProduct.findByProductID", query = "SELECT m FROM MapShowScheduleProduct m WHERE m.productID = :productID"),
		@NamedQuery(name = "MapShowScheduleProduct.findByCreatedOn", query = "SELECT m FROM MapShowScheduleProduct m WHERE m.createdOn = :createdOn"),
		@NamedQuery(name = "MapShowScheduleProduct.findByActive", query = "SELECT m FROM MapShowScheduleProduct m WHERE m.active = :active") })
public class MapShowScheduleProduct implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "MapID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonProperty("")
	private Integer mapID;

	@Basic(optional = false)
	@NotNull
	@Column(name = "ShowScheduleID")
	@JsonProperty("")
	private int showScheduleID;

	@Basic(optional = false)
	@NotNull
	@Column(name = "ProductID")
	@JsonProperty("")
	private int productID;

	@Column(name = "CreatedOn")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonProperty("")
	private Date createdOn;
	
	@Column(name = "Active")
	@JsonProperty("")
	private Boolean active;

	public MapShowScheduleProduct() {
	}

	public MapShowScheduleProduct(Integer mapID) {
		this.mapID = mapID;
	}

	public MapShowScheduleProduct(Integer mapID, int showScheduleID, int productID) {
		this.mapID = mapID;
		this.showScheduleID = showScheduleID;
		this.productID = productID;
	}

	public Integer getMapID() {
		return mapID;
	}

	public void setMapID(Integer mapID) {
		this.mapID = mapID;
	}

	public int getShowScheduleID() {
		return showScheduleID;
	}

	public void setShowScheduleID(int showScheduleID) {
		this.showScheduleID = showScheduleID;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
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
		if (!(object instanceof MapShowScheduleProduct)) {
			return false;
		}
		MapShowScheduleProduct other = (MapShowScheduleProduct) object;
		if ((this.mapID == null && other.mapID != null) || (this.mapID != null && !this.mapID.equals(other.mapID))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.example.mavenproject3.MapShowScheduleProduct[ mapID=" + mapID + " ]";
	}

}
