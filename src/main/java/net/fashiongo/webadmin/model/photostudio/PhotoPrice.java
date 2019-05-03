package net.fashiongo.webadmin.model.photostudio;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;

import net.fashiongo.common.conversion.LocalDateTimeConverter;
import net.fashiongo.common.dal.IPersistent;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Photo_Price")
public class PhotoPrice implements IPersistent, Serializable {
	
	private static final String DATE_PATTERN_YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
	private static final String DATE_PATTERN_YYYYMMDD = "yyyy-MM-dd";
	private static final String EFFECTIVE_FROM_TIME = " 00:00:00";
	private static final String EFFECTIVE_TO_TIME = " 23:59:59";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PriceID")
	private Integer priceID;
	public Integer getPriceID() {
		return priceID;
	}

	public void setPriceID(Integer priceID) {
		this.priceID = priceID;
	}

	@Column(name = "PriceTypeID")
	private Integer priceTypeID;
	public Integer getPriceTypeID() {
		return priceTypeID;
	}

	public void setPriceTypeID(Integer priceTypeID) {
		this.priceTypeID = priceTypeID;
	}

	@Column(name = "PhotoshootType")
	private Integer photoshootType;
	public Integer getPhotoshootType() {
		return photoshootType;
	}

	public void setPhotoshootType(Integer photoshootType) {
		this.photoshootType = photoshootType;
	}

	@Column(name = "UnitPrice")
	private BigDecimal unitPrice;
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	@JsonIgnore
	@Column(name = "FromEffectiveDate")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime _fromEffectiveDate;
	public LocalDateTime get_fromEffectiveDate() {
		return _fromEffectiveDate;
	}

	public void set_fromEffectiveDate(LocalDateTime _fromEffectiveDate) {
		this._fromEffectiveDate = _fromEffectiveDate;
	}

	@Transient
	private String fromEffectiveDate;
	public String getFromEffectiveDate() {
		return _fromEffectiveDate != null ? _fromEffectiveDate.format(DateTimeFormatter.ofPattern(DATE_PATTERN_YYYYMMDD)) : null;
	}

	public void setFromEffectiveDate(String fromEffectiveDate) {
		this.fromEffectiveDate = StringUtils.isNotEmpty(fromEffectiveDate) ? fromEffectiveDate + EFFECTIVE_FROM_TIME : null;
		this.set_fromEffectiveDate(StringUtils.isNotEmpty(this.fromEffectiveDate) ? LocalDateTime.parse(this.fromEffectiveDate, DateTimeFormatter.ofPattern(DATE_PATTERN_YYYYMMDDHHMMSS)) : null);
	}

	@JsonIgnore
	@Column(name = "ToEffectiveDate")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime _toEffectiveDate;
	public LocalDateTime get_toEffectiveDate() {
		return _toEffectiveDate;
	}

	public void set_toEffectiveDate(LocalDateTime _toEffectiveDate) {
		this._toEffectiveDate = _toEffectiveDate;
	}
	
	@Transient
	private String toEffectiveDate;
	public String getToEffectiveDate() {
		return _toEffectiveDate != null ? _toEffectiveDate.format(DateTimeFormatter.ofPattern(DATE_PATTERN_YYYYMMDD)) : null;
	}

	public void setToEffectiveDate(String toEffectiveDate) {
		this.toEffectiveDate = StringUtils.isNotEmpty(toEffectiveDate) ? toEffectiveDate + EFFECTIVE_TO_TIME: null;
		this.set_toEffectiveDate(StringUtils.isNotEmpty(this.toEffectiveDate) ? LocalDateTime.parse(this.toEffectiveDate, DateTimeFormatter.ofPattern(DATE_PATTERN_YYYYMMDDHHMMSS)) : null);
	}

	@Column(name = "MinQty")
	private Integer minQty;
	public Integer getMinQty() {
		return minQty;
	}

	public void setMinQty(Integer minQty) {
		this.minQty = minQty;
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
	
	@Column(name = "PriceTypeName")
	private String priceTypeName;
	public String getPriceTypeName() {
		return priceTypeName;
	}

	public void setPriceTypeName(String priceTypeName) {
		this.priceTypeName = priceTypeName;
	}

	@Column(name = "PackageID")
	private Integer packageId;

	public Integer getPackageId() {
		return packageId;
	}

	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}

	@Transient
	private String photoShootTypeName;
	public String getPhotoShootTypeName() {
		if(this.photoshootType == 1) {
			return "Full Model Shot";
		}else if(this.photoshootType == 2) {
			return "Flat Product Shot";
		}
		return photoShootTypeName;
	}

	public void setPhotoShootTypeName(String photoShootTypeName) {
		this.photoShootTypeName = photoShootTypeName;
	}
}
