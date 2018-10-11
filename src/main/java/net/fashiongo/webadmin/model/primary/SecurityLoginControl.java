package net.fashiongo.webadmin.model.primary;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Incheol Jung
 */
@Entity
@Table(name = "[security.Login_Control]")
public class SecurityLoginControl {
	@Id
	private Integer controlID;
	private Integer userID;
	private Integer weekDay;
	private Date timeFrom;
	private Date timeTo;
	private Boolean allow;
	private Boolean active;
	
	public Integer getControlID() {
		return controlID;
	}
	public void setControlID(Integer controlID) {
		controlID = controlID;
	}
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public Integer getWeekDay() {
		return weekDay;
	}
	public void setWeekDay(Integer weekDay) {
		this.weekDay = weekDay;
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
