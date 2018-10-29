package net.fashiongo.webadmin.model.pojo.parameter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class SetTodayDealCalendarParameter implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("TodayDealID")
	@ApiModelProperty(required = true, example="17572")
	private Integer todayDealID;
	
	@JsonProperty("FromDate")
	@ApiModelProperty(required = true, example="11/1/2018")
	private String fromDate;
	
	@JsonProperty("Active")
	@ApiModelProperty(required = true, example="0")
	private Boolean active;
	
	@JsonProperty("Notes")
	@ApiModelProperty(required = true, example="Test Note Contents")
	private String notes;
	
	public Integer getTodayDealID() {
		return todayDealID;
	}
	public void setTodayDealID(Integer todayDealID) {
		this.todayDealID = todayDealID;
	}
	public LocalDateTime getFromDate() {
		DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");
		LocalDate temp = LocalDate.parse(this.fromDate,dtFormatter);
		return temp.atStartOfDay();
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
}