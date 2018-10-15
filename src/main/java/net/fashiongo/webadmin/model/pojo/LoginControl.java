package net.fashiongo.webadmin.model.pojo;

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
	private Date timeFrom;
	
	@JsonProperty("TimeTo")
	private Date timeTo;
	
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
