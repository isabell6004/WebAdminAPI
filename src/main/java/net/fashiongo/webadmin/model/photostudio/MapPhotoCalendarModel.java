package net.fashiongo.webadmin.model.photostudio;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import net.fashiongo.common.dal.IPersistent;

@Entity
@Table(name = "Map_Photo_Calendar_Model")
public class MapPhotoCalendarModel implements IPersistent, Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ModelScheduleID")
	private Integer modelScheduleID;
	public Integer getModelScheduleID() {
		return modelScheduleID;
	}

	public void setModelScheduleID(Integer modelScheduleID) {
		this.modelScheduleID = modelScheduleID;
	}

	@Column(name = "CalendarID")
	private Integer calendarID;
	public Integer getCalendarID() {
		return calendarID;
	}

	public void setCalendarID(Integer calendarID) {
		this.calendarID = calendarID;
	}

	@Column(name = "ModelID")
	private Integer modelID;
	public Integer getModelID() {
		return modelID;
	}

	public void setModelID(Integer modelID) {
		this.modelID = modelID;
	}
	
	@Column(name = "AvailableUnit")
	private BigDecimal availableUnit;
	public BigDecimal getAvailableUnit() {
		return availableUnit;
	}

	public void setAvailableUnit(BigDecimal availableUnit) {
		this.availableUnit = availableUnit;
	}
	
}
