package net.fashiongo.webadmin.model.primary;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author sanghyup
 */
@Entity
@Table(name = "List_Show")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "ListShow.findAll", query = "SELECT l FROM ListShow l"),
		@NamedQuery(name = "ListShow.findByShowID", query = "SELECT l FROM ListShow l WHERE l.showID = :showID"),
		@NamedQuery(name = "ListShow.findByShowName", query = "SELECT l FROM ListShow l WHERE l.showName = :showName"),
		@NamedQuery(name = "ListShow.findByLocation", query = "SELECT l FROM ListShow l WHERE l.location = :location"),
		@NamedQuery(name = "ListShow.findByUrl", query = "SELECT l FROM ListShow l WHERE l.url = :url"),
		@NamedQuery(name = "ListShow.findByActive", query = "SELECT l FROM ListShow l WHERE l.active = :active"),
		@NamedQuery(name = "ListShow.findByLogoFileName", query = "SELECT l FROM ListShow l WHERE l.logoFileName = :logoFileName"),
		@NamedQuery(name = "ListShow.findByShowCode", query = "SELECT l FROM ListShow l WHERE l.showCode = :showCode") })
public class ListShow implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "ShowID")
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonProperty("ShowID")
	private Integer showID;

	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 50)
	@Column(name = "ShowName")
	@JsonProperty("ShowName")
	private String showName;

	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 100)
	@Column(name = "Location")
	@JsonProperty("Location")
	private String location;

	@Size(max = 250)
	@Column(name = "Url")
	@JsonProperty("Url")
	private String url;

	@Basic(optional = false)
	@NotNull
	@Column(name = "Active")
	@JsonProperty("Active")
	private boolean active;

	@Size(max = 150)
	@Column(name = "LogoFileName")
	@JsonProperty("LogoFileName")
	private String logoFileName;

	@Size(max = 50)
	@Column(name = "ShowCode")
	@JsonProperty("ShowCode")
	private String showCode;

	@Transient
	@Column(name = "row")
	@JsonProperty("")
	private long row;

	@Transient
	@Column(name = "DeleteFlag")
	@JsonProperty("DeleteFlag")
	private int deleteFlag;

	public ListShow() {
	}

	public ListShow(Integer showID) {
		this.showID = showID;
	}

	public ListShow(Integer showID, String showName, String location, boolean active) {
		this.showID = showID;
		this.showName = showName;
		this.location = location;
		this.active = active;
	}

	public Integer getShowID() {
		return showID;
	}

	public void setShowID(Integer showID) {
		this.showID = showID;
	}

	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getLogoFileName() {
		return logoFileName;
	}

	public void setLogoFileName(String logoFileName) {
		this.logoFileName = logoFileName;
	}

	public String getShowCode() {
		return showCode;
	}

	public void setShowCode(String showCode) {
		this.showCode = showCode;
	}

	public long getRow() {
		return row;
	}

	public void setRow(long row) {
		this.row = row;
	}

	public int getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (showID != null ? showID.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof ListShow)) {
			return false;
		}
		ListShow other = (ListShow) object;
		if ((this.showID == null && other.showID != null)
				|| (this.showID != null && !this.showID.equals(other.showID))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.example.mavenproject3.ListShow[ showID=" + showID + " ]";
	}

}
