package net.fashiongo.webadmin.model.pojo.admin;

import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginControl {
	@JsonProperty("ControlID")
	private Integer controlID;
	
	@JsonProperty("UserID")
	private Integer userID;
	
	@JsonProperty("Weekday")
	private Integer weekday;
	
	@JsonProperty("TimeFrom")
	private LocalDateTime timeFrom;
	
	@JsonProperty("TimeTo")
	private LocalDateTime timeTo;
	
	@JsonProperty("Allow")
	private Boolean allow;
	
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

	public LocalDateTime getTimeFrom() {
		return timeFrom;
	}

	public void setTimeFrom(LocalDateTime timeFrom) {
		this.timeFrom = timeFrom;
	}

	public LocalDateTime getTimeTo() {
		return timeTo;
	}

	public void setTimeTo(LocalDateTime timeTo) {
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
