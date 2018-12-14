package net.fashiongo.webadmin.model.primary;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Reo
 *
 */
@Entity
@Table(name = "tblWholeSaler")
public class VendorDetailDate implements Serializable {
	@Id
	@JsonProperty("WholeSalerID")
	@Column(name = "WholeSalerID")
	private Integer wholeSalerId;
	
	@JsonProperty("LastModifiedDateTime")
	@Column(name = "LastModifiedDateTime")
	private LocalDateTime lastModifiedDateTime;
	
	@JsonProperty("LastUser")
	@Column(name = "LastUser")
	private String lastUser;
	
	@JsonProperty("UserID")
	@Column(name = "UserID")
	private String userID;
	
	@JsonProperty("ActualOpenDate")
	@Column(name = "ActualOpenDate")
	private LocalDateTime actualOpenDate;

	public Integer getWholeSalerId() {
		return wholeSalerId;
	}

	public void setWholeSalerId(Integer wholeSalerId) {
		this.wholeSalerId = wholeSalerId;
	}

	public LocalDateTime getLastModifiedDateTime() {
		return lastModifiedDateTime;
	}

	public void setLastModifiedDateTime(LocalDateTime lastModifiedDateTime) {
		this.lastModifiedDateTime = lastModifiedDateTime;
	}

	public String getLastUser() {
		return lastUser;
	}

	public void setLastUser(String lastUser) {
		this.lastUser = lastUser;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public LocalDateTime getActualOpenDate() {
		return actualOpenDate;
	}

	public void setActualOpenDate(LocalDateTime actualOpenDate) {
		this.actualOpenDate = actualOpenDate;
	}
	
	
}
