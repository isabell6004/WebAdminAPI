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
@Table(name = "Photo_Discount")
public class PhotoDiscount implements IPersistent, Serializable {
	
	private static final String DATE_PATTERN_YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
	private static final String DATE_PATTERN_YYYYMMDD = "yyyy-MM-dd";
	private static final String FROM_TIME = " 00:00:00";
	private static final String TO_TIME = " 23:59:59";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DiscountID")
	private Integer discountID;
	public Integer getDiscountID() {
		return discountID;
	}

	public void setDiscountID(Integer discountID) {
		this.discountID = discountID;
	}

	@Column(name = "DiscountName")
	private String discountName;
	public String getDiscountName() {
		return discountName;
	}

	public void setDiscountName(String discountName) {
		this.discountName = discountName;
	}

	@Column(name = "DiscountDescription")
	private String discountDescription;
	public String getDiscountDescription() {
		return discountDescription;
	}

	public void setDiscountDescription(String discountDescription) {
		this.discountDescription = discountDescription;
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

	@Column(name = "DiscountCode")
	private String discountCode;
	public String getDiscountCode() {
		return discountCode;
	}

	public void setDiscountCode(String discountCode) {
		this.discountCode = discountCode;
	}

	@Column(name = "IsRequiredPriceQty")
	private Boolean isRequiredPriceQty;
	public Boolean getIsRequiredPriceQty() {
		return isRequiredPriceQty;
	}

	public void setIsRequiredPriceQty(Boolean isRequiredPriceQty) {
		this.isRequiredPriceQty = isRequiredPriceQty;
	}

	@Column(name = "RequiredPrice")
	private BigDecimal requiredPrice;
	public BigDecimal getRequiredPrice() {
		return requiredPrice;
	}

	public void setRequiredPrice(BigDecimal requiredPrice) {
		this.requiredPrice = requiredPrice;
	}

	@Column(name = "RequiredQty")
	private BigDecimal requiredQty;
	public BigDecimal getRequiredQty() {
		return requiredQty;
	}

	public void setRequiredQty(BigDecimal requiredQty) {
		this.requiredQty = requiredQty;
	}

	@Column(name = "DiscountRate")
	private BigDecimal discountRate;
	public BigDecimal getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(BigDecimal discountRate) {
		this.discountRate = discountRate;
	}

	@Column(name = "DiscountAmount")
	private BigDecimal discountAmount;
	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}

	@Column(name = "Active")
	private Boolean active;
	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	
	@Column(name = "IsFirstTimeOrder")
	private Boolean isFirstTimeOrder;
	public Boolean getIsFirstTimeOrder() {
		return isFirstTimeOrder;
	}

	public void setIsFirstTimeOrder(Boolean isFirstTimeOrder) {
		this.isFirstTimeOrder = isFirstTimeOrder;
	}

	@Column(name = "CouponUsageLimit")
	private Integer couponUsageLimit;
	public Integer getCouponUsageLimit() {
		return couponUsageLimit;
	}

	public void setCouponUsageLimit(Integer couponUsageLimit) {
		this.couponUsageLimit = couponUsageLimit;
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
