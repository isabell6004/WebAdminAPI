package net.fashiongo.webadmin.model.photostudio;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "Photo_Model")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PhotoModel implements IPersistent, Serializable {

	private static final String DATE_PATTERN_YYYYMMDD = "yyyy-MM-dd";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	@Column(name = "AgencyName")
	private String agencyName;
	public String getAgencyName() {
		return agencyName;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}

	@Column(name = "Type")
	private String type;
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "Size")
	private String size;
	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	@Column(name = "HeightFt")
	private Integer heightFt;
	public Integer getHeightFt() {
		return heightFt;
	}

	public void setHeightFt(Integer heightFt) {
		this.heightFt = heightFt;
	}

	@Column(name = "HeightIn")
	private Integer heightIn;
	public Integer getHeightIn() {
		return heightIn;
	}

	public void setHeightIn(Integer heightIn) {
		this.heightIn = heightIn;
	}

	@Column(name = "Weight")
	private Integer weight;
	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	@Column(name = "ShoeSize")
	private BigDecimal shoeSize;
	public BigDecimal getShoeSize() {
		return shoeSize;
	}

	public void setShoeSize(BigDecimal shoeSize) {
		this.shoeSize = shoeSize;
	}

	@Column(name = "Cup")
	private String cup;
	public String getCup() {
		return cup;
	}

	public void setCup(String cup) {
		this.cup = cup;
	}

	@Column(name = "Bust")
	private String bust;
	public String getBust() {
		return bust;
	}

	public void setBust(String bust) {
		this.bust = bust;
	}

	@Column(name = "Waist")
	private String waist;
	public String getWaist() {
		return waist;
	}

	public void setWaist(String waist) {
		this.waist = waist;
	}

	@Column(name = "Hip")
	private String hip;
	public String getHip() {
		return hip;
	}

	public void setHip(String hip) {
		this.hip = hip;
	}

	@Column(name = "HairColor")
	private String hairColor;
	public String getHairColor() {
		return hairColor;
	}

	public void setHairColor(String hairColor) {
		this.hairColor = hairColor;
	}

	@Column(name = "EyesColor")
	private String eyesColor;
	public String getEyesColor() {
		return eyesColor;
	}

	public void setEyesColor(String eyesColor) {
		this.eyesColor = eyesColor;
	}

	@Column(name = "HourlyPayRate")
	private BigDecimal hourlyPayRate;
	public BigDecimal getHourlyPayRate() {
		return hourlyPayRate;
	}

	public void setHourlyPayRate(BigDecimal hourlyPayRate) {
		this.hourlyPayRate = hourlyPayRate;
	}

	@Column(name = "IsDeleted")
	private Boolean isDeleted = false;
	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Column(name = "Email")
	private String email;
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "Phone")
	private String phone;
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "Note")
	private String note;
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
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

	@Column(name = "ModifiedBY")
	private String modifiedBY;
	public String getModifiedBY() {
		return modifiedBY;
	}

	public void setModifiedBY(String modifiedBY) {
		this.modifiedBY = modifiedBY;
	}
	
	@Transient
	List<PhotoImage> photoImages;
	public List<PhotoImage> getPhotoImages() {
		return photoImages;
	}

	public void setPhotoImages(List<PhotoImage> photoImages) {
		this.photoImages = photoImages;
	}
	
	@Transient
	@JsonIgnore
	@Column(name = "nextPhotoshoot", updatable = false)
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime _nextPhotoshoot;
	public LocalDateTime get_nextPhotoshoot() {
		return _nextPhotoshoot;
	}
	public void set_nextPhotoshoot(LocalDateTime _nextPhotoshoot) {
		this._nextPhotoshoot = _nextPhotoshoot;
	}

	@Transient
	private String nextPhotoshoot;
	public String getNextPhotoshoot() {
		return _nextPhotoshoot != null ? _nextPhotoshoot.format(DateTimeFormatter.ofPattern(DATE_PATTERN_YYYYMMDD)) : null;
	}
	
	@Transient
	@Column(name = "ImageUrl")
	private String imageUrl;

	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	@Transient
	@Column(name = "ListOrder")
	private Integer listOrder;
	public Integer getListOrder() {
		return listOrder;
	}
	
	@Transient
	@Column(name = "IsBooked")
	private Boolean isBooked;
	public Boolean getIsBooked() {
		return isBooked;
	}

	public void setIsBooked(Boolean isBooked) {
		this.isBooked = isBooked;
	}
	
	@Transient
	List<String> nextAvailableDates;
	public List<String> getNextAvailableDates() {
		return nextAvailableDates == null ? new ArrayList<String>() : nextAvailableDates;
	}

	public void setNextAvailableDates(List<String> nextAvailableDates) {
		this.nextAvailableDates = nextAvailableDates;
	}
	
	@Transient
	@JsonIgnore
	@Column(name = "TheDate", updatable = false)
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
	
	@Transient
	@Column(name = "CalendarID")
	private Integer calendarID;
	public Integer getCalendarID() {
		return calendarID;
	}

	public void setCalendarID(Integer calendarID) {
		this.calendarID = calendarID;
	}
	
	@Transient
	@Column(name = "IsToday")
	private Integer isToday;
	public Integer getIsToday() {
		return isToday;
	}

	public void setIsToday(Integer isToday) {
		this.isToday = isToday;
	}
	
	@Transient
	@Column(name = "status")
	private String status;
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
