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

import net.fashiongo.common.conversion.LocalDateTimeConverter;
import net.fashiongo.common.dal.IPersistent;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Photo_CancellationFee")
public class PhotoCancellationFee implements IPersistent, Serializable {
	
	private static final String DATE_PATTERN_YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
	private static final String EFFECTIVE_FROM_TIME = " 00:00:00";
	private static final String EFFECTIVE_TO_TIME = " 23:59:59";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CancelTypeID")
	private Integer cancelTypeID;
	public Integer getCancelTypeID() {
		return cancelTypeID;
	}

	public void setCancelTypeID(Integer cancelTypeID) {
		this.cancelTypeID = cancelTypeID;
	}

	@Column(name = "CancelTypeName")
	private String cancelTypeName;
	public String getCancelTypeName() {
		return cancelTypeName;
	}

	public void setCancelTypeName(String cancelTypeName) {
		this.cancelTypeName = cancelTypeName;
	}

	@Column(name = "ChargeRate")
	private BigDecimal chargeRate;
	public BigDecimal getChargeRate() {
		return chargeRate;
	}

	public void setChargeRate(BigDecimal chargeRate) {
		this.chargeRate = chargeRate;
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
		return _fromEffectiveDate != null ? _fromEffectiveDate.format(DateTimeFormatter.ofPattern(DATE_PATTERN_YYYYMMDDHHMMSS)) : null;
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
		return _toEffectiveDate != null ? _toEffectiveDate.format(DateTimeFormatter.ofPattern(DATE_PATTERN_YYYYMMDDHHMMSS)) : null;
	}

	public void setToEffectiveDate(String toEffectiveDate) {
		this.toEffectiveDate = StringUtils.isNotEmpty(toEffectiveDate) ? toEffectiveDate + EFFECTIVE_TO_TIME: null;
		this.set_toEffectiveDate(StringUtils.isNotEmpty(this.toEffectiveDate) ? LocalDateTime.parse(this.toEffectiveDate, DateTimeFormatter.ofPattern(DATE_PATTERN_YYYYMMDDHHMMSS)) : null);
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

	@Transient
	private String createdOn;

	public String getCreatedOn() {
		return createdOnDate != null ? createdOnDate.toString() : null;
	}

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

}
