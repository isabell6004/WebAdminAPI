package net.fashiongo.webadmin.model.photostudio;

import java.math.BigDecimal;

import javax.persistence.Column;

public class CalendarPhotoModel {

	@Column(name = "ModelID")
	private Integer modelID;
	public Integer getModelID() {
		return modelID;
	}
	public void setModelID(Integer modelID) {
		this.modelID = modelID;
	}

	@Column(name = "ModelName")
	private String modelName;
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	@Column(name = "ModelScheduleID")
	private Integer ModelScheduleID;
	public Integer getModelScheduleID() {
		return ModelScheduleID;
	}
	public void setModelScheduleID(Integer modelScheduleID) {
		ModelScheduleID = modelScheduleID;
	}

	@Column(name = "CalendarID")
	private Integer CalendarID;
	public Integer getCalendarID() {
		return CalendarID;
	}
	public void setCalendarID(Integer calendarID) {
		CalendarID = calendarID;
	}

	@Column(name = "AvailableUnit")
	private BigDecimal availableUnit;
	public BigDecimal getAvailableUnit() {
		return availableUnit;
	}
	public void setAvailableUnit(BigDecimal availableUnit) {
		this.availableUnit = availableUnit;
	}

	@Column(name = "BookingID")
	private Integer bookingID;
	public Integer getBookingID() {
		return bookingID;
	}
	public void setBookingID(Integer bookingID) {
		this.bookingID = bookingID;
	}

	@Column(name = "BookedUnit")
	private BigDecimal bookedUnit;
	public BigDecimal getBookedUnit() {
		return bookedUnit;
	}
	public void setBookedUnit(BigDecimal bookedUnit) {
		this.bookedUnit = bookedUnit;
	}
}
