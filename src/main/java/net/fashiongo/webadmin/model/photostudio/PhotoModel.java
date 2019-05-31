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

import lombok.Getter;
import lombok.Setter;
import net.fashiongo.common.conversion.LocalDateTimeConverter;
import net.fashiongo.common.dal.IPersistent;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "Photo_Model")
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PhotoModel implements IPersistent, Serializable {

	private static final String DATE_PATTERN_YYYYMMDD = "yyyy-MM-dd";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ModelID")
	private Integer modelID;

	@Column(name = "ModelName")
	private String modelName;

	@Column(name = "AgencyName")
	private String agencyName;

	@Column(name = "Type")
	private String type;

	@Column(name = "Size")
	private String size;

	@Column(name = "HeightFt")
	private Integer heightFt;

	@Column(name = "HeightIn")
	private Integer heightIn;

	@Column(name = "Weight")
	private Integer weight;

	@Column(name = "ShoeSize")
	private BigDecimal shoeSize;

	@Column(name = "Cup")
	private String cup;

	@Column(name = "Bust")
	private String bust;

	@Column(name = "Waist")
	private String waist;

	@Column(name = "Hip")
	private String hip;

	@Column(name = "HairColor")
	private String hairColor;

	@Column(name = "EyesColor")
	private String eyesColor;

	@Column(name = "HourlyPayRate")
	private BigDecimal hourlyPayRate;

	@Column(name = "IsDeleted")
	private Boolean isDeleted = false;

	@Column(name = "Email")
	private String email;

	@Column(name = "Phone")
	private String phone;

	@Column(name = "Note")
	private String note;

	@Column(name = "Warning")
	private String warning;

	@Column(name = "Popularity")
	private BigDecimal popularity;

	@JsonIgnore
	@Column(name = "CreatedOn", updatable = false)
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime createdOnDate;

	@JsonIgnore
	@Transient
	private String createdOn;
	public String getCreatedOn() {
		return createdOnDate != null ? createdOnDate.toString() : null;
	}

	@JsonIgnore
	@Column(name = "CreatedBy")
	private String createdBy;

	@JsonIgnore
	@Column(name = "ModifiedOn")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime modifiedOnDate;

	@JsonIgnore
	@Transient
	private String modifiedOn;
	public String getModifiedOn() {
		return modifiedOnDate != null ? modifiedOnDate.toString() : null;
	}

	@Column(name = "ModifiedBY")
	private String modifiedBY;

	@Transient
	List<PhotoImage> photoImages;
	public List<PhotoImage> getPhotoImages() {
		return photoImages;
	}

	@Transient
	@JsonIgnore
	@Column(name = "nextPhotoshoot", updatable = false)
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime _nextPhotoshoot;

	@Transient
	private String nextPhotoshoot;
	public String getNextPhotoshoot() {
		return _nextPhotoshoot != null ? _nextPhotoshoot.format(DateTimeFormatter.ofPattern(DATE_PATTERN_YYYYMMDD)) : null;
	}
	
	@Transient
	@Column(name = "ImageUrl")
	private String imageUrl;

	@Transient
	@Column(name = "ListOrder")
	private Integer listOrder;
	public Integer getListOrder() {
		return listOrder;
	}
	
	@Transient
	@Column(name = "IsBooked")
	private Boolean isBooked;

	@Transient
	List<String> nextAvailableDates;
	public List<String> getNextAvailableDates() {
		return nextAvailableDates == null ? new ArrayList<String>() : nextAvailableDates;
	}

	@Transient
	@JsonIgnore
	@Column(name = "TheDate", updatable = false)
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime _theDate;

	@Transient
	private String theDate;
	public String getTheDate() {
		return _theDate != null ? _theDate.format(DateTimeFormatter.ofPattern(DATE_PATTERN_YYYYMMDD)) : null;
	}
	
	@Transient
	@Column(name = "CalendarID")
	private Integer calendarID;

	@Transient
	@Column(name = "IsToday")
	private Integer isToday;

	@Transient
	@Column(name = "status")
	private String status;
}
