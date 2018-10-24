package net.fashiongo.webadmin.model.primary;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * 
 * @author DAHYE
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "tblNews")
public class VendorNewsDetail implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "NewsID")
	@JsonProperty("NewsID")
	private Integer newsID;
	
	@Column(name = "WholeSalerID")
	@JsonProperty("WholeSalerID")
	private Integer wholeSalerID;
	
	@Column(name = "NewsTitle")
	@JsonProperty("NewsTitle")
	private String newsTitle;
	
	@Column(name = "NewsContent")
	@JsonProperty("NewsContent")
	private String newsContent;
	
	@Column(name = "StartingDate")
	@JsonProperty("StartingDate")
	private String startingDate;
	
	@Column(name = "FromDate")
	@JsonProperty("FromDate")
	private String fromDate;
	
	@Column(name = "ToDate")
	@JsonProperty("ToDate")
	private String toDate;
	
	@Column(name = "LastUser")
	@JsonProperty("LastUser")
	private String lastUser;
	
	@Column(name = "LastModifiedDateTime")
	@JsonProperty("LastModifiedDateTime")
	private String lastModifiedDateTime;
	
	@Column(name = "SortNo")
	@JsonProperty("SortNo")
	private Integer sortNo;
	
	@Column(name = "Active")
	@JsonProperty("Active")
	private Boolean active;
	
	@Column(name = "ShowBanner")
	@JsonProperty("ShowBanner")
	private Boolean showBanner;

	public Integer getNewsID() {
		return newsID;
	}

	public Integer getWholeSalerID() {
		return wholeSalerID;
	}

	public String getNewsTitle() {
		return newsTitle;
	}

	public String getNewsContent() {
		return newsContent;
	}

	public String getStartingDate() {
		return startingDate;
	}

	public String getFromDate() {
		return fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public String getLastUser() {
		return lastUser;
	}

	public String getLastModifiedDateTime() {
		return lastModifiedDateTime;
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public Boolean getActive() {
		return active;
	}

	public Boolean getShowBanner() {
		return showBanner;
	}

	public void setNewsID(Integer newsID) {
		this.newsID = newsID;
	}

	public void setWholeSalerID(Integer wholeSalerID) {
		this.wholeSalerID = wholeSalerID;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}

	public void setStartingDate(String startingDate) {
		this.startingDate = startingDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public void setLastUser(String lastUser) {
		this.lastUser = lastUser;
	}

	public void setLastModifiedDateTime(String lastModifiedDateTime) {
		this.lastModifiedDateTime = lastModifiedDateTime;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public void setShowBanner(Boolean showBanner) {
		this.showBanner = showBanner;
	}
	
}
