package net.fashiongo.webadmin.model.pojo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonProperty;


public class VendorNews {
	@JsonProperty("row")
	@Column(name = "row")
	private Long row;
	
	@JsonProperty("NewsID")
	@Column(name = "NewsID")
	private Integer newsID;
	
	@JsonProperty("Recipient")
	@Column(name = "Recipient")
	private String recipient;
	
	@JsonProperty("NewsTitle")
	@Column(name = "NewsTitle")
	private String newsTitle;
	
	@JsonProperty("FromDate")
	@Column(name = "FromDate")
	private LocalDateTime fromDate;
	
	@JsonProperty("ToDate")
	@Column(name = "ToDate")
	private LocalDateTime toDate;
	
	@JsonProperty("Active")
	@Column(name = "Active")
	private Boolean active;
	
	@JsonProperty("WholeSalerID")
	@Column(name = "WholeSalerID")
	private Integer wholeSalerID;
	
	public Long getRow() {
		return row;
	}
	public Integer getNewsID() {
		return newsID;
	}
	public String getRecipient() {
		return recipient;
	}
	public String getNewsTitle() {
		return newsTitle;
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
	public Boolean getActive() {
		return active;
	}
	public Integer getWholeSalerID() {
		return wholeSalerID;
	}
	public void setRow(Long row) {
		this.row = row;
	}
	public void setNewsID(Integer newsID) {
		this.newsID = newsID;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}
	public void setFromDate(LocalDateTime fromDate) {
		this.fromDate = fromDate;
	}
	
	public void setFromDate(String fromDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");	
		this.fromDate = LocalDateTime.parse(fromDate, formatter);
	}

	public void setToDate(LocalDateTime toDate) {
		this.toDate = toDate;
	}
	
	public void setToDate(String toDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");	
		this.toDate = LocalDateTime.parse(toDate, formatter);
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public void setWholeSalerID(Integer wholeSalerID) {
		this.wholeSalerID = wholeSalerID;
	}
	
	
}
