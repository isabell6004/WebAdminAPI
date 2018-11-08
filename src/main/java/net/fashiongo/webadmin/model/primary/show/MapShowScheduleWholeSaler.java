package net.fashiongo.webadmin.model.primary.show;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author sanghyup
 */
@Entity
@Table(name = "Map_ShowSchedule_WholeSaler")
@XmlRootElement
/*@NamedQueries({
		@NamedQuery(name = "MapShowScheduleWholeSaler.findAll", query = "SELECT m FROM MapShowScheduleWholeSaler m"),
		@NamedQuery(name = "MapShowScheduleWholeSaler.findByMapID", query = "SELECT m FROM MapShowScheduleWholeSaler m WHERE m.mapID = :mapID"),
		@NamedQuery(name = "MapShowScheduleWholeSaler.findByShowScheduleID", query = "SELECT m FROM MapShowScheduleWholeSaler m WHERE m.showScheduleID = :showScheduleID"),
		@NamedQuery(name = "MapShowScheduleWholeSaler.findByWholeSalerID", query = "SELECT m FROM MapShowScheduleWholeSaler m WHERE m.wholeSalerID = :wholeSalerID"),
		@NamedQuery(name = "MapShowScheduleWholeSaler.findByBooth", query = "SELECT m FROM MapShowScheduleWholeSaler m WHERE m.booth = :booth") })
*/
public class MapShowScheduleWholeSaler implements Serializable {

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
	@Column(name = "WholeSalerID")
	@JsonProperty("")
	private int wholeSalerID;
	
	@Size(max = 50)
	@Column(name = "Booth")
	@JsonProperty("")
	private String booth;

	public MapShowScheduleWholeSaler() {
	}

	public MapShowScheduleWholeSaler(Integer mapID) {
		this.mapID = mapID;
	}

	public MapShowScheduleWholeSaler(Integer mapID, int showScheduleID, int wholeSalerID) {
		this.mapID = mapID;
		this.showScheduleID = showScheduleID;
		this.wholeSalerID = wholeSalerID;
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

	public int getWholeSalerID() {
		return wholeSalerID;
	}

	public void setWholeSalerID(int wholeSalerID) {
		this.wholeSalerID = wholeSalerID;
	}

	public String getBooth() {
		return booth;
	}

	public void setBooth(String booth) {
		this.booth = booth;
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
		if (!(object instanceof MapShowScheduleWholeSaler)) {
			return false;
		}
		MapShowScheduleWholeSaler other = (MapShowScheduleWholeSaler) object;
		if ((this.mapID == null && other.mapID != null) || (this.mapID != null && !this.mapID.equals(other.mapID))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.example.mavenproject3.MapShowScheduleWholeSaler[ mapID=" + mapID + " ]";
	}

}
