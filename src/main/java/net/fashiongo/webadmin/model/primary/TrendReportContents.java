package net.fashiongo.webadmin.model.primary;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Junghwan Lee
 */
@Entity
@Table(name = "[TrendReport_Contents]")
public class TrendReportContents {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ContentID")
	@Column(name = "ContentID")
	private Integer contentID;
	
	@JsonProperty("TrendReportID")
	@Column(name = "TrendReportID")
	private Integer trendReportID;
	
	@JsonProperty("Contents")
	@Column(name = "Contents")
	private String contents;
	
	@JsonProperty("CreatedOn")
	@Column(name = "CreatedOn")
	private LocalDateTime createdOn;
	
	@JsonProperty("CreatedBy")
	@Column(name = "CreatedBy")
	private String createdBy;
	
	@JsonProperty("ModifiedOn")
	@Column(name = "ModifiedOn")
	private LocalDateTime modifiedOn;
	
	@JsonProperty("ModifiedBy")
	@Column(name = "ModifiedBy")
	private String modifiedBy;

	public Integer getContentID() {
		return contentID;
	}

	public void setContentID(Integer contentID) {
		this.contentID = contentID;
	}

	public Integer getTrendReportID() {
		return trendReportID;
	}

	public void setTrendReportID(Integer trendReportID) {
		this.trendReportID = trendReportID;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(LocalDateTime modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifieidBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
	
	
}
