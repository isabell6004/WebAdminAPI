package net.fashiongo.webadmin.model.photostudio;

import java.io.Serializable;
import java.time.LocalDateTime;

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

@Entity
@Table(name = "Photo_Image")
public class PhotoImage implements IPersistent, Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ImageID")
	private Integer imageID;

	public Integer getImageID() {
		return imageID;
	}
	public void setImageID(Integer imageID) {
		this.imageID = imageID;
	}

	@Column(name = "ImageDescription")
	private String imageDescription;

	public String getImageDescription() {
		return imageDescription;
	}
	public void setImageDescription(String imageDescription) {
		this.imageDescription = imageDescription;
	}

	@Column(name = "ImageUrl")
	private String imageUrl;

	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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
	@Column(name = "ListOrder")
	private Integer listOrder;
	public Integer getListOrder() {
		return listOrder;
	}
	public void setListOrder(Integer listOrder) {
		this.listOrder = listOrder;
	}
	
	@Transient
	@JsonIgnore
	@Column(name = "ModelID")
	private Integer modelID;

	public Integer getModelID() {
		return modelID;
	}
	public void setModelID(Integer modelID) {
		this.modelID = modelID;
	}
}
