package net.fashiongo.webadmin.model.primary;

import java.awt.Image;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "[aspnet_PersonalizationPerUser]")
public class AspnetPersonalizationPerUser {
	@Id
	@JsonProperty("Id")
	@Column(name = "Id", columnDefinition="uniqueidentifier")
	private String id;
	
	@JsonProperty("PathId")
	@Column(name = "PathId", columnDefinition="uniqueidentifier")
	private String pathId;
	
	@JsonProperty("UserId")
	@Column(name = "UserId", columnDefinition="uniqueidentifier")
	private String userId;
	
	@JsonProperty("PageSettings")
	@Column(name = "PageSettings")
	private byte[] pageSettings;
	
	@JsonProperty("LastUpdatedDate")
	@Column(name = "LastUpdatedDate")
	private LocalDateTime lastUpdatedDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPathId() {
		return pathId;
	}

	public void setPathId(String pathId) {
		this.pathId = pathId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public byte[] getPageSettings() {
		return pageSettings;
	}

	public void setPageSettings(byte[] pageSettings) {
		this.pageSettings = pageSettings;
	}

	public LocalDateTime getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(LocalDateTime lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}
	
	
}
