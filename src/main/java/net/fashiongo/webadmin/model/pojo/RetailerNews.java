package net.fashiongo.webadmin.model.pojo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Reo
 *
 */
public class RetailerNews {
	@JsonProperty("row")
	private Integer row;
	
	@JsonProperty("NewsID")
	private Integer newsID;
	
	@JsonProperty("Recipient")
	private String recipient;
	
	@JsonProperty("NewsTitle")
	private String newsTitle;
	
	@JsonProperty("FromDate")
	private LocalDateTime fromDate;
	
	@JsonProperty("ToDate")
	private LocalDateTime toDate;
	
	@JsonProperty("Active")
	private String active;

	public Integer getRow() {
		return row;
	}

	public void setRow(Integer row) {
		this.row = row;
	}

	public Integer getNewsID() {
		return newsID;
	}

	public void setNewsID(Integer newsID) {
		this.newsID = newsID;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public LocalDateTime getFromDate() {
		return fromDate;
	}

	public void setFromDate(LocalDateTime fromDate) {
		this.fromDate = fromDate;
	}

	public LocalDateTime getToDate() {
		return toDate;
	}

	public void setToDate(LocalDateTime toDate) {
		this.toDate = toDate;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}
	
	
}
