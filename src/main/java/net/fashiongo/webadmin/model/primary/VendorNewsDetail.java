package net.fashiongo.webadmin.model.primary;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
	@JsonProperty("newsid")
	private Integer newsID;
	
	@Column(name = "WholeSalerID")
	@JsonProperty("wholesalerid")
	private Integer wholeSalerID;
	
	@Column(name = "NewsTitle")
	@JsonProperty("newstitle")
	private String newsTitle;
	
	@Column(name = "NewsContent")
	@JsonProperty("newscontent")
	private String newsContent;
	
	@Column(name = "NewsType")
	@JsonProperty("newstype")
	private String newsType;
	
	@Column(name = "StartingDate")
	@JsonProperty("startingdate")
	private LocalDateTime startingDate;
	
	@Column(name = "FromDate")
	@JsonProperty("fromdate")
	private LocalDateTime fromDate;
	
	@Column(name = "ToDate")
	@JsonProperty("todate")
	private LocalDateTime toDate;
	
	@Column(name = "LastUser")
	@JsonProperty("lastUser")
	private String lastUser;
	
	@Column(name = "LastModifiedDateTime")
	@JsonProperty("lastmodifieddatetime")
	private LocalDateTime lastModifiedDateTime;
	
	@Column(name = "SortNo")
	@JsonProperty("sortno")
	private Integer sortNo;
	
	@Column(name = "Active")
	@JsonProperty("active")
	private Boolean active;
	
	@Column(name = "ShowBanner")
	@JsonProperty("showbanner")
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
		if (startingDate == null) return "";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		String formattedStartingDate = startingDate.format(formatter);		
		return formattedStartingDate;
	}

	public String getFromDate() {
		if (fromDate == null) return "";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		String formattedFromDate = fromDate.format(formatter);				
		return formattedFromDate;
	}

	public String getToDate() {
		if (toDate == null) return "";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		String formattedToDate = toDate.format(formatter);				
		return formattedToDate;
	}

	public String getLastUser() {
		return lastUser;
	}

	public String getLastModifiedDateTime() {
		if (lastModifiedDateTime == null) return "";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		String formattedLastModifiedDateTime = lastModifiedDateTime.format(formatter);				
		return formattedLastModifiedDateTime;
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

	public void setStartingDate(LocalDateTime startingDate) {
		this.startingDate = startingDate;
	}
	
	public void setStartingDate(String startingDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");	
		this.startingDate = LocalDateTime.parse(startingDate+" 00:00:00", formatter);
	}

	public void setFromDate(LocalDateTime fromDate) {
		this.fromDate = fromDate;
	}
	
	public void setFromDate(String fromDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");	
		this.fromDate = LocalDateTime.parse(fromDate+" 00:00:00", formatter);
	}

	public void setToDate(LocalDateTime toDate) {
		this.toDate = toDate;
	}
	
	public void setToDate(String toDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");	
		this.toDate = LocalDateTime.parse(toDate+" 00:00:00", formatter);
	}

	public void setLastUser(String lastUser) {
		this.lastUser = lastUser;
	}

	public void setLastModifiedDateTime(LocalDateTime lastModifiedDateTime) {
		this.lastModifiedDateTime = lastModifiedDateTime;
	}
	
	public void setLastModifiedDateTime(String lastModifiedDateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");	
		this.lastModifiedDateTime = LocalDateTime.parse(lastModifiedDateTime+" 00:00:00", formatter);
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

	public String getNewsType() {
		return newsType;
	}

	public void setNewsType(String newsType) {
		this.newsType = newsType;
	}
	
	
	
}