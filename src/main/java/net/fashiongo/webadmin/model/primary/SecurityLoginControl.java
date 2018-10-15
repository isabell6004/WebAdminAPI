package net.fashiongo.webadmin.model.primary;

import javax.persistence.Column;
import java.util.Date;

import javax.persistence.Entity;
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
	private Integer controlID;
	
	@Column(name = "UserID")
	@JsonProperty("UserID")
	private Integer userID;
	
	@Column(name = "Weekday")
	@JsonProperty("Weekday")
	private Integer weekday;
	
	@Column(name = "TimeFrom")
	@JsonProperty("TimeFrom")
	private Integer timeFrom;
	
	@Column(name = "TimeTo")
	@JsonProperty("TimeTo")
	private Integer timeTo;
	
	@Column(name = "Allow")
	@JsonProperty("Allow")
	private Integer allow;
	
	@Column(name = "Active")
	@JsonProperty("Active")
	private Integer active;

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

	public Integer getTimeFrom() {
		return timeFrom;
	}

	public void setTimeFrom(Integer timeFrom) {
		this.timeFrom = timeFrom;
	}

	public Integer getTimeTo() {
		return timeTo;
	}

	public void setTimeTo(Integer timeTo) {
		this.timeTo = timeTo;
	}

	public Integer getAllow() {
		return allow;
	}

	public void setAllow(Integer allow) {
		this.allow = allow;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}
	
}
