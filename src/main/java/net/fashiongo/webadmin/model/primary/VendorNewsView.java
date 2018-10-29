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
@Table(name = "vwtblNews")
public class VendorNewsView implements Serializable {
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
	
	@Column(name = "NewsType")
	@JsonProperty("NewsType")
	private String newsType;
	
	@Column(name = "StartingDate")
	@JsonProperty("StartingDate")
	private LocalDateTime startingDate;
	
	@Column(name = "FromDate")
	@JsonProperty("FromDate")
	private LocalDateTime fromDate;
	
	@Column(name = "ToDate")
	@JsonProperty("ToDate")
	private LocalDateTime toDate;
	
	@Column(name = "LastUser")
	@JsonProperty("LastUser")
	private String lastUser;
	
	@Column(name = "LastModifiedDateTime")
	@JsonProperty("LastModifiedDateTime")
	private LocalDateTime lastModifiedDateTime;
	
	@Column(name = "SortNo")
	@JsonProperty("SortNo")
	private Integer sortNo;
	
	@Column(name = "Active")
	@JsonProperty("Active")
	private Boolean active;
	
	@Column(name = "ShowBanner")
	@JsonProperty("ShowBanner")
	private Boolean showBanner;
	
	@Column(name = "Recipient")
	@JsonProperty("Recipient")
	private String recipient;
	

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
		if (startingDate == null) return null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedStartingDate = startingDate.format(formatter);		
		return formattedStartingDate;
	}

	public String getFromDate() {
		if (fromDate == null) return null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedFromDate = fromDate.format(formatter);				
		return formattedFromDate;
	}

	public String getToDate() {
		if (toDate == null) return null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedToDate = toDate.format(formatter);				
		return formattedToDate;
	}

	public String getLastUser() {
		return lastUser;
	}

	public String getLastModifiedDateTime() {
		if (lastModifiedDateTime == null) return null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
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

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	
	
}
