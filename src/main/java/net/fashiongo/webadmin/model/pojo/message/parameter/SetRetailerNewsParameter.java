package net.fashiongo.webadmin.model.pojo.message.parameter;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Reo
 *
 */
public class SetRetailerNewsParameter {
	@JsonProperty("newsid")
	private Integer newsID;
	
	@JsonProperty("newstitle")
	private String newsTitle;
	
	@JsonProperty("newscontent")
	private String newsContent;
	
	@JsonProperty("active")
	private Boolean active;
	
	@JsonProperty("fromdate")
	private LocalDateTime fromDate;
	
	@JsonProperty("todate")
	private LocalDateTime toDate;
	
	@JsonProperty("sortno")
	private Integer sortNo;

	@JsonProperty("externalLink")
	private Boolean externalLink;

	@JsonProperty("externalUrl")
	private String externalUrl;

	public Integer getNewsID() {
		return newsID;
	}

	public void setNewsID(Integer newsID) {
		this.newsID = newsID;
	}

	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public String getNewsContent() {
		return newsContent;
	}

	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
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

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	public Boolean getExternalLink() {
		return externalLink;
	}

	public void setExternalLink(Boolean externalLink) {
		this.externalLink = externalLink;
	}

	public String getExternalUrl() {
		return externalUrl;
	}

	public void setExternalUrl(String externalUrl) {
		this.externalUrl = externalUrl;
	}

}
