/**
 * 
 */
package net.fashiongo.webadmin.model.primary;

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

import net.fashiongo.common.conversion.LocalDateTimeConverter;
import net.fashiongo.common.dal.IPersistent;

/**
 * 
 * @author Incheol Jung
 */
@Entity
@Table(name = "TodayDeal")
public class TodayDeal implements IPersistent, Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "TodayDealID")
	private Integer todayDealId;
	
	@Column(name = "ProductID")
	private Integer productId;
	
	@Column(name = "Title")
	private String title;
	
	@Column(name = "Description")
	private String description;
	
	@Column(name = "TodayDealPrice")
	private BigDecimal todayDealPrice;
	
	@Column(name = "ToDate")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime toDate;
	
	@Column(name = "FromDate")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime fromDate;
	
	@Column(name = "CreatedOn")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime createdOn;
	
	@Column(name = "CreatedBy")
	private String createdBy;

	@Column(name = "ModifiedOn")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime modifiedOn;
	
	@Column(name = "AppliedOn")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime appliedOn;
	
	@Column(name = "ModifiedBy")
	private String modifiedBy;
	
	@Column(name = "ApprovedOn")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime approvedOn;
	
	@Column(name = "Active")
	private Boolean active;
	
	@Column(name = "RevokedOn")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime revokedOn;
	
	@Column(name = "RevokedBy")
	private String revokedBy;
	
	@Column(name = "CreatedByVendor")
	private Boolean createdByVendor;

	public Integer getTodayDealId() {
		return todayDealId;
	}

	public void setTodayDealId(Integer todayDealId) {
		this.todayDealId = todayDealId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getTodayDealPrice() {
		return todayDealPrice;
	}

	public void setTodayDealPrice(BigDecimal todayDealPrice) {
		this.todayDealPrice = todayDealPrice;
	}

	public LocalDateTime getToDate() {
		return toDate;
	}

	public void setToDate(LocalDateTime toDate) {
		this.toDate = toDate;
	}

	public LocalDateTime getFromDate() {
		return fromDate;
	}

	public void setFromDate(LocalDateTime fromDate) {
		this.fromDate = fromDate;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public LocalDateTime getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(LocalDateTime modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public LocalDateTime getAppliedOn() {
		return appliedOn;
	}

	public void setAppliedOn(LocalDateTime appliedOn) {
		this.appliedOn = appliedOn;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public LocalDateTime getApprovedOn() {
		return approvedOn;
	}

	public void setApprovedOn(LocalDateTime approvedOn) {
		this.approvedOn = approvedOn;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public LocalDateTime getRevokedOn() {
		return revokedOn;
	}

	public void setRevokedOn(LocalDateTime revokedOn) {
		this.revokedOn = revokedOn;
	}

	public String getRevokedBy() {
		return revokedBy;
	}

	public void setRevokedBy(String revokedBy) {
		this.revokedBy = revokedBy;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Boolean getCreatedByVendor() {
		return createdByVendor;
	}

	public void setCreatedByVendor(Boolean createdByVendor) {
		this.createdByVendor = createdByVendor;
	}
}
