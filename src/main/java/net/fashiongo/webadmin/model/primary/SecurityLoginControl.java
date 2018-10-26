package net.fashiongo.webadmin.model.primary;

import javax.persistence.Column;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Incheol Jung
 */
@Entity
@Table(name = "[security.Login_Control]")
public class SecurityLoginControl {
	@Id
	@Column(name = "ControlID")
	@JsonProperty("ControlID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer controlID;
	
	@Column(name = "UserID")
	@JsonProperty("UserID")
	private Integer userID;
	
	@Column(name = "Weekday")
	@JsonProperty("Weekday")
	private Integer weekday;
	
	@Column(name = "TimeFrom")
	@JsonProperty("TimeFrom")
	private Date timeFrom;
	
	@Column(name = "TimeTo")
	@JsonProperty("TimeTo")
	private Date timeTo;
	
	@Column(name = "Allow")
	@JsonProperty("Allow")
	private Boolean allow;
	
	@Column(name = "Active")
	@JsonProperty("Active")
	private Boolean active;

	public Integer getControlID() {
		return controlID;
	}

	public void setControlID(Integer controlID) {
		this.controlID = controlID;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public Integer getWeekday() {
		return weekday;
	}

	public void setWeekday(Integer weekday) {
		this.weekday = weekday;
	}
	

	public Date getTimeFrom() {
		return timeFrom;
	}

	public void setTimeFrom(Date timeFrom) {
		this.timeFrom = timeFrom;
	}

	public Date getTimeTo() {
		return timeTo;
	}

	public void setTimeTo(Date timeTo) {
		this.timeTo = timeTo;
	}

	public Boolean getAllow() {
		return allow;
	}

	public void setAllow(Boolean allow) {
		this.allow = allow;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	
}
