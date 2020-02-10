package net.fashiongo.webadmin.model.primary;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import net.fashiongo.webadmin.converter.HtmlEscapeConverter;

/**
 * 
 * @author Reo
 *
 */
@Entity
@Table(name = "tblRetailerNews")
public class TblRetailerNews {
	@Id
	@Column(name = "NewsID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("NewsID")
	private Integer newsID;
	
	@Column(name = "RetailerID")
	@JsonProperty("RetailerID")
	private Integer retailerID;
	
	@Convert(converter = HtmlEscapeConverter.class)
	@Column(name = "NewsTitle")
	@JsonProperty("NewsTitle")
	private String newsTitle;
	
	@Convert(converter = HtmlEscapeConverter.class)
	@Column(name = "NewsContent")
	@JsonProperty("NewsContent")
	private String newsContent;
	
	@Column(name = "Active")
	@JsonProperty("Active")
	private String active;
	
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

	public Integer getNewsID() {
		return newsID;
	}

	public void setNewsID(Integer newsID) {
		this.newsID = newsID;
	}

	public Integer getRetailerID() {
		return retailerID;
	}

	public void setRetailerID(Integer retailerID) {
		this.retailerID = retailerID;
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

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public LocalDateTime getStartingDate() {
		return startingDate;
	}

	public void setStartingDate(LocalDateTime startingDate) {
		this.startingDate = startingDate;
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

	public String getLastUser() {
		return lastUser;
	}

	public void setLastUser(String lastUser) {
		this.lastUser = lastUser;
	}
	
	public LocalDateTime getLastModifiedDateTime() {
		return lastModifiedDateTime;
	}

	public void setLastModifiedDateTime(LocalDateTime lastModifiedDateTime) {
		this.lastModifiedDateTime = lastModifiedDateTime;
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}
	
	
}
