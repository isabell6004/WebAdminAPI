package net.fashiongo.webadmin.model.photostudio;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.fashiongo.common.conversion.LocalDateTimeConverter;
import net.fashiongo.common.dal.IPersistent;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "Photo_Calendar")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PhotoCalendar implements IPersistent, Serializable {
	
	private static final String DATE_PATTERN_YYYYMMDD = "yyyy-MM-dd";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CalendarID")
	private Integer calendarID;
	public Integer getCalendarID() {
		return calendarID;
	}

	public void setCalendarID(Integer calendarID) {
		this.calendarID = calendarID;
	}
	
	@JsonIgnore
	@Column(name = "TheDate")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime _theDate;
	public LocalDateTime get_theDate() {
		return _theDate;
	}

	public void set_theDate(LocalDateTime _theDate) {
		this._theDate = _theDate;
	}

	@Transient
	private String theDate;
	public String getTheDate() {
		return _theDate != null ? _theDate.format(DateTimeFormatter.ofPattern(DATE_PATTERN_YYYYMMDD)) : null;
	}

	@Column(name = "Available")
	private Boolean available;
	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	@Column(name = "IsHoliday", insertable=false, updatable=false)
	private Boolean isHoliday;
	public Boolean getIsHoliday() {
		return isHoliday;
	}

	public void setIsHoliday(Boolean isHoliday) {
		this.isHoliday = isHoliday;
	}

	@Column(name = "IsModelShot", insertable=false, updatable=false)
	private Boolean isModelShot;
	public Boolean getIsModelShot() {
		return isModelShot;
	}

	public void setIsModelShot(Boolean isModelShot) {
		this.isModelShot = isModelShot;
	}

	@Column(name = "DateName", insertable=false, updatable=false)
	private String dateName;
	public String getDateName() {
		return dateName;
	}

	public void setDateName(String dateName) {
		this.dateName = dateName;
	}

	@Column(name = "MaxUnitPerDay")
	private BigDecimal maxUnitPerDay;
	public BigDecimal getMaxUnitPerDay() {
		return maxUnitPerDay;
	}

	public void setMaxUnitPerDay(BigDecimal maxUnitPerDay) {
		this.maxUnitPerDay = maxUnitPerDay;
	}

	@JsonIgnore
	@Column(name = "CreatedOn", updatable = false)
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime createdOnDate;

	public LocalDateTime getCreatedOnDate() {
		return createdOnDate;
	}

	public void setCreatedOnDate(LocalDateTime createdOnDate) {
		this.createdOnDate = createdOnDate;
	}

	@JsonIgnore
	@Transient
	private String createdOn;
	public String getCreatedOn() {
		return createdOnDate != null ? createdOnDate.toString() : null;
	}

	@JsonIgnore
	@Column(name = "CreatedBy")
	private String createdBy;

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@JsonIgnore
	@Column(name = "ModifiedOn")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime modifiedOnDate;

	public LocalDateTime getModifiedOnDate() {
		return modifiedOnDate;
	}

	public void setModifiedOnDate(LocalDateTime modifiedOnDate) {
		this.modifiedOnDate = modifiedOnDate;
	}

	@JsonIgnore
	@Transient
	private String modifiedOn;

	public String getModifiedOn() {
		return modifiedOnDate != null ? modifiedOnDate.toString() : null;
	}

	@JsonIgnore
	@Column(name = "ModifiedBY")
	private String modifiedBY;

	public String getModifiedBY() {
		return modifiedBY;
	}

	public void setModifiedBY(String modifiedBY) {
		this.modifiedBY = modifiedBY;
	}

	@Transient
	List<MapPhotoCalendarModel> mapPhotoCalendarModels;
	public List<MapPhotoCalendarModel> getMapPhotoCalendarModels() {
		return mapPhotoCalendarModels;
	}

	public void setMapPhotoCalendarModels(List<MapPhotoCalendarModel> mapPhotoCalendarModels) {
		this.mapPhotoCalendarModels = mapPhotoCalendarModels;
	}
	
	@Transient
	@Column(name = "AvailableUnits")
	private BigDecimal availableUnits;
	public BigDecimal getAvailableUnits() {
		return availableUnits == null? BigDecimal.ZERO: availableUnits;
	}

	public void setAvailableUnits(BigDecimal availableUnits) {
		this.availableUnits = availableUnits;
	}

	@Transient
	@Column(name = "BookedCnt")
	private Integer bookedCnt;
	public Integer getBookedCnt() {
		return bookedCnt;
	}

	public void setBookedCnt(Integer bookedCnt) {
		this.bookedCnt = bookedCnt;
	}
	
	@Transient
	@Column(name = "IsDelayed")
	private Boolean isDelayed;
	public Boolean getIsDelayed() {
		return isDelayed;
	}

	public void setIsDelayed(Boolean isDelayed) {
		this.isDelayed = isDelayed;
	}
	
	@Column(name = "minUnit")
	private BigDecimal minUnit;
	public BigDecimal getMinUnit() {
		return minUnit;
	}

	public void setMinUnit(BigDecimal minUnit) {
		this.minUnit = minUnit;
	}
	
	@Transient
	private Boolean isFromModelDetail;
	public Boolean getIsFromModelDetail() {
		return isFromModelDetail;
	}

	public void setIsFromModelDetail(Boolean isFromModelDetail) {
		this.isFromModelDetail = isFromModelDetail;
	}
}
