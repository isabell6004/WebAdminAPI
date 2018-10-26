package net.fashiongo.webadmin.model.primary;

import java.awt.Image;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "[aspnet_Profile]")
public class AspnetProfile {
	@Id
	@JsonProperty("UserID")
	@Column(name = "UserID")
	private String userId;
	
	@JsonProperty("PropertyNames")
	@Column(name = "PropertyNames")
	private String propertyNames;
	
	@JsonProperty("PropertyValuesString")
	@Column(name = "PropertyValuesString")
	private String propertyValuesString;
	
	@JsonProperty("PropertyValuesBinary")
	@Column(name = "PropertyValuesBinary")
	private byte[] propertyValuesBinary;
	
	@JsonProperty("LastUpdatedDate")
	@Column(name = "LastUpdatedDate")
	private LocalDateTime lastUpdatedDate;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPropertyNames() {
		return propertyNames;
	}

	public void setPropertyNames(String propertyNames) {
		this.propertyNames = propertyNames;
	}

	public String getPropertyValuesString() {
		return propertyValuesString;
	}

	public void setPropertyValuesString(String propertyValuesString) {
		this.propertyValuesString = propertyValuesString;
	}

	public byte[] getPropertyValuesBinary() {
		return propertyValuesBinary;
	}

	public void setPropertyValuesBinary(byte[] propertyValuesBinary) {
		this.propertyValuesBinary = propertyValuesBinary;
	}

	public LocalDateTime getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(LocalDateTime lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

}
