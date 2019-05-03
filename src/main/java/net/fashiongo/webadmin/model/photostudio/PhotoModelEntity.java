package net.fashiongo.webadmin.model.photostudio;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Photo_Model")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class PhotoModelEntity implements Serializable {

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

	@JsonIgnore
	@Column(name = "CreatedOn", updatable = false)
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
	List<PhotoImage> modelPhotoImages;
	public List<PhotoImage> getModelPhotoImages() {
		return modelPhotoImages;
	}

	public void setModelPhotoImages(List<PhotoImage> modelPhotoImages) {
		this.modelPhotoImages = modelPhotoImages;
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
	@Column(name = "nextPhotoshoot", updatable = false)
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

	@Transient
	@Column(name = "IsBooked")
	private Boolean isBooked;

	@JsonIgnore
	@Transient
	@Column(name = "IsToday")
	private Integer isToday;

	@Transient
	@JsonIgnore
	@Column(name = "TheDate", updatable = false)
	private LocalDateTime _theDate;

	@Transient
	private String theDate;
	public String getTheDate() {
		return _theDate != null ? _theDate.format(DateTimeFormatter.ofPattern(DATE_PATTERN_YYYYMMDD)) : null;
	}
	
	@Transient
	@Column(name = "CalendarID")
	private Integer calendarID;
}
