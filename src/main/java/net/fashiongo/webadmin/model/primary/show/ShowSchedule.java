package net.fashiongo.webadmin.model.primary.show;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.common.conversion.LocalDateTimeConverter;

/**
 *
 * @author sanghyup
 */
@Entity
@Table(name = "ShowSchedule")
@XmlRootElement
/*@NamedQueries({ @NamedQuery(name = "ShowSchedule.findAll", query = "SELECT s FROM ShowSchedule s"),
		@NamedQuery(name = "ShowSchedule.findByShowScheduleID", query = "SELECT s FROM ShowSchedule s WHERE s.showScheduleID = :showScheduleID"),
		@NamedQuery(name = "ShowSchedule.findByShowID", query = "SELECT s FROM ShowSchedule s WHERE s.showID = :showID"),
		@NamedQuery(name = "ShowSchedule.findByBannerImage", query = "SELECT s FROM ShowSchedule s WHERE s.bannerImage = :bannerImage"),
		@NamedQuery(name = "ShowSchedule.findByTitleImage", query = "SELECT s FROM ShowSchedule s WHERE s.titleImage = :titleImage"),
		@NamedQuery(name = "ShowSchedule.findByDateFrom", query = "SELECT s FROM ShowSchedule s WHERE s.dateFrom = :dateFrom"),
		@NamedQuery(name = "ShowSchedule.findByDateTo", query = "SELECT s FROM ShowSchedule s WHERE s.dateTo = :dateTo"),
		@NamedQuery(name = "ShowSchedule.findByActive", query = "SELECT s FROM ShowSchedule s WHERE s.active = :active"),
		@NamedQuery(name = "ShowSchedule.findByListOrder", query = "SELECT s FROM ShowSchedule s WHERE s.listOrder = :listOrder"),
		@NamedQuery(name = "ShowSchedule.findByMobileImage", query = "SELECT s FROM ShowSchedule s WHERE s.mobileImage = :mobileImage") })
*/
public class ShowSchedule implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "ShowScheduleID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ShowScheduleID")
	private Integer showScheduleID;

	@Basic(optional = false)
	@NotNull
	@Column(name = "ShowID")
	@JsonProperty("ShowID")
	private int showID;

	@Size(max = 50)
	@Column(name = "BannerImage")
	@JsonProperty("BannerImage")
	private String bannerImage;

	@Size(max = 50)
	@Column(name = "TitleImage")
	@JsonProperty("TitleImage")
	private String titleImage;
	/*
	 * @Basic(optional = false)
	 * 
	 * @NotNull
	 * 
	 * @Column(name = "DateFrom")
	 * 
	 * @Temporal(TemporalType.TIMESTAMP)
	 * 
	 * @JsonProperty("DateFrom") private Date dateFrom;
	 */
	@JsonProperty("DateFrom")
	@Column(name = "DateFrom")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime dateFrom;

	/*
	 * @Basic(optional = false)
	 * 
	 * @NotNull
	 * 
	 * @Column(name = "DateTo")
	 * 
	 * @Temporal(TemporalType.TIMESTAMP)
	 * 
	 * @JsonProperty("DateTo") private Date dateTo;
	 */
	@JsonProperty("DateTo")
	@Column(name = "DateTo")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime dateTo;

	@Basic(optional = false)
	@NotNull
	@Column(name = "Active")
	@JsonProperty("Active")
	private boolean active;

	@Basic(optional = false)
	@NotNull
	@Column(name = "ListOrder")
	@JsonProperty("ListOrder")
	private int listOrder;

	@Size(max = 50)
	@Column(name = "MobileImage")
	@JsonProperty("MobileImage")
	private String mobileImage;

	@Transient
	@Column(name = "rowno")
	@JsonProperty("rowno")
	private long rowNo;

	@Transient
	@Column(name = "ShowName")
	@JsonProperty("ShowName")
	private String showName;

	@Transient
	@Column(name = "Location")
	@JsonProperty("Location")
	private String location;

	@Transient
	@Column(name = "Url")
	@JsonProperty("Url")
	private String url;

	@Transient
	@Column(name = "VendorCount")
	@JsonProperty("VendorCount")
	private long vendorCount;

	@Transient
	@Column(name = "DeleteFlag")
	@JsonProperty("DeleteFlag")
	private int deleteFlag;

	public ShowSchedule() {
	}

	public ShowSchedule(Integer showScheduleID) {
		this.showScheduleID = showScheduleID;
	}

	public ShowSchedule(Integer showScheduleID, int showID, LocalDateTime dateFrom, LocalDateTime dateTo,
			boolean active, int listOrder) {
		this.showScheduleID = showScheduleID;
		this.showID = showID;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.active = active;
		this.listOrder = listOrder;
	}

	public Integer getShowScheduleID() {
		return showScheduleID;
	}

	public void setShowScheduleID(Integer showScheduleID) {
		this.showScheduleID = showScheduleID;
	}

	public int getShowID() {
		return showID;
	}

	public void setShowID(int showID) {
		this.showID = showID;
	}

	public String getBannerImage() {
		return bannerImage;
	}

	public void setBannerImage(String bannerImage) {
		this.bannerImage = bannerImage;
	}

	public String getTitleImage() {
		return titleImage;
	}

	public void setTitleImage(String titleImage) {
		this.titleImage = titleImage;
	}

	public LocalDateTime getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(LocalDateTime dateFrom) {
		this.dateFrom = dateFrom;
	}

	public LocalDateTime getDateTo() {
		return dateTo;
	}

	public void setDateTo(LocalDateTime dateTo) {
		this.dateTo = dateTo;
	}

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getListOrder() {
		return listOrder;
	}

	public void setListOrder(int listOrder) {
		this.listOrder = listOrder;
	}

	public String getMobileImage() {
		return mobileImage;
	}

	public void setMobileImage(String mobileImage) {
		this.mobileImage = mobileImage;
	}

	public long getRowNo() {
		return rowNo;
	}

	public void setRowNo(long rowNo) {
		this.rowNo = rowNo;
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

	public long getVendorCount() {
		return vendorCount;
	}

	public void setVendorCount(long vendorCount) {
		this.vendorCount = vendorCount;
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
		hash += (showScheduleID != null ? showScheduleID.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof ShowSchedule)) {
			return false;
		}
		ShowSchedule other = (ShowSchedule) object;
		if ((this.showScheduleID == null && other.showScheduleID != null)
				|| (this.showScheduleID != null && !this.showScheduleID.equals(other.showScheduleID))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.example.mavenproject3.ShowSchedule[ showScheduleID=" + showScheduleID + " ]";
	}

}
