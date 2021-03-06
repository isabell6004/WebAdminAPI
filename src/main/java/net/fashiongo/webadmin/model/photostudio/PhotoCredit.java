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

import lombok.Builder;
import org.apache.commons.lang3.StringUtils;

import net.fashiongo.common.conversion.LocalDateTimeConverter;
import net.fashiongo.common.dal.IPersistent;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Photo_Credit")
public class PhotoCredit implements IPersistent, Serializable {
	
	private static final String DATE_PATTERN_YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
	private static final String DATE_PATTERN_YYYYMMDD = "yyyy-MM-dd";
	private static final String FROM_TIME = " 00:00:00";
	private static final String TO_TIME = " 23:59:59";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PhotoCreditID")
	private Integer photoCreditID;
	public Integer getPhotoCreditID() {
		return photoCreditID;
	}

	public void setPhotoCreditID(Integer photoCreditID) {
		this.photoCreditID = photoCreditID;
	}

	@Column(name = "PhotoCreditReason")
	private String photoCreditReason;
	public String getPhotoCreditReason() {
		return photoCreditReason;
	}

	public void setPhotoCreditReason(String photoCreditReason) {
		this.photoCreditReason = photoCreditReason;
	}

	@Column(name = "WholeSalerID")
	private Integer wholeSalerID;
	public Integer getWholeSalerID() {
		return wholeSalerID;
	}

	public void setWholeSalerID(Integer wholeSalerID) {
		this.wholeSalerID = wholeSalerID;
	}

	@Column(name = "WholeSalerCompanyName")
	private String wholeSalerCompanyName;
	public String getWholeSalerCompanyName() {
		return wholeSalerCompanyName;
	}

	public void setWholeSalerCompanyName(String wholeSalerCompanyName) {
		this.wholeSalerCompanyName = wholeSalerCompanyName;
	}

	@Column(name = "PhotoCreditAmount")
	private BigDecimal photoCreditAmount;
	public BigDecimal getPhotoCreditAmount() {
		return photoCreditAmount;
	}

	public void setPhotoCreditAmount(BigDecimal photoCreditAmount) {
		this.photoCreditAmount = photoCreditAmount;
	}

	@Column(name = "PhotoCreditBalance")
	private BigDecimal photoCreditBalance;
	public BigDecimal getPhotoCreditBalance() {
		return photoCreditBalance;
	}

	public void setPhotoCreditBalance(BigDecimal photoCreditBalance) {
		this.photoCreditBalance = photoCreditBalance;
	}

	@Column(name = "IsUsed")
	private Boolean isUsed;
	public Boolean getIsUsed() {
		return isUsed == null ? Boolean.FALSE : isUsed;
	}

	public void setIsUsed(Boolean isUsed) {
		this.isUsed = isUsed;
	}

	@Column(name = "IsCreditBack")
	private Boolean isCreditBack;
	public Boolean getIsCreditBack() {
		return isCreditBack == null ? Boolean.FALSE : isCreditBack;
	}

	public void setIsCreditBack(Boolean isCreditBack) {
		this.isCreditBack = isCreditBack;
	}

	@JsonIgnore
	@Column(name = "FromDate")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime _fromDate;
	public LocalDateTime get_fromDate() {
		return _fromDate;
	}

	public void set_fromDate(LocalDateTime _fromDate) {
		this._fromDate = _fromDate;
	}

	@Transient
	private String fromDate;
	public String getFromDate() {
		return _fromDate != null ? _fromDate.format(DateTimeFormatter.ofPattern(DATE_PATTERN_YYYYMMDD)) : null;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = StringUtils.isNotEmpty(fromDate) ? fromDate + FROM_TIME : null;
		this.set_fromDate(StringUtils.isNotEmpty(this.fromDate) ? LocalDateTime.parse(this.fromDate, DateTimeFormatter.ofPattern(DATE_PATTERN_YYYYMMDDHHMMSS)) : null);
	}

	@JsonIgnore
	@Column(name = "ToDate")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime _toDate;
	public LocalDateTime get_toDate() {
		return _toDate;
	}

	public void set_toDate(LocalDateTime _toDate) {
		this._toDate = _toDate;
	}

	@Transient
	private String toDate;
	public String getToDate() {
		return _toDate != null ? _toDate.format(DateTimeFormatter.ofPattern(DATE_PATTERN_YYYYMMDD)) : null;
	}

	public void setToDate(String toDate) {
		this.toDate = StringUtils.isNotEmpty(toDate) ? toDate + TO_TIME : null;
		this.set_toDate(StringUtils.isNotEmpty(this.toDate) ? LocalDateTime.parse(this.toDate, DateTimeFormatter.ofPattern(DATE_PATTERN_YYYYMMDDHHMMSS)) : null);
	}

	@Column(name = "Active")
	private Boolean active;
	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
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
	@JsonIgnore
	@Column(name = "theDate")
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
	@Column(name = "isAdd")
	private Boolean isAdd;
	public Boolean getIsAdd() {
		return isAdd;
	}

	public void setIsAdd(Boolean isAdd) {
		this.isAdd = isAdd;
	}

	public PhotoCredit() {}

	@Builder
	public PhotoCredit(String photoCreditReason, Integer wholeSalerID, String wholeSalerCompanyName, BigDecimal photoCreditAmount,
					   BigDecimal photoCreditBalance, Boolean isUsed, Boolean isCreditBack, LocalDateTime _fromDate,
					   LocalDateTime _toDate, Boolean active, LocalDateTime createdOnDate, String createdBy, LocalDateTime modifiedOnDate,
					   String modifiedBY, LocalDateTime _theDate, Boolean isAdd) {
		this.photoCreditReason = photoCreditReason;
		this.wholeSalerID = wholeSalerID;
		this.wholeSalerCompanyName = wholeSalerCompanyName;
		this.photoCreditAmount = photoCreditAmount;
		this.photoCreditBalance = photoCreditBalance;
		this.isUsed = isUsed;
		this.isCreditBack = isCreditBack;
		this._fromDate = _fromDate;
		this._toDate = _toDate;
		this.active = active;
		this.createdOnDate = createdOnDate;
		this.createdBy = createdBy;
		this.modifiedOnDate = modifiedOnDate;
		this.modifiedBY = modifiedBY;
		this._theDate = _theDate;
		this.isAdd = isAdd;
	}
}
