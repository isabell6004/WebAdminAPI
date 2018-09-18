package net.fashiongo.webadmin.model.photostudio;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "Photo_Discount")
public class PhotoDiscount implements IPersistent, Serializable {

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

	@Column(name = "FromDate")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime fromDateDB;

	public LocalDateTime getFromDateDB() {
		return fromDateDB;
	}

	public void setFromDateDB(LocalDateTime fromDateDB) {
		this.fromDateDB = fromDateDB;
	}

	@Transient
	private String fromDate;

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	@Column(name = "ToDate")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime toDateDB;

	public LocalDateTime getToDateDB() {
		return toDateDB;
	}

	public void setToDateDB(LocalDateTime toDateDB) {
		this.toDateDB = toDateDB;
	}

	@Transient
	private String toDate;

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
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

	@Column(name = "UsedTimes")
	private Integer usedTimes;

	public Integer getUsedTimes() {
		return usedTimes;
	}

	public void setUsedTimes(Integer usedTimes) {
		this.usedTimes = usedTimes;
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
