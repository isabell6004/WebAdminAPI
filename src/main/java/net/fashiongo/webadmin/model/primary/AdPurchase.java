package net.fashiongo.webadmin.model.primary;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Ad_Purchase")
public class AdPurchase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AdPurchaseID")
	private Integer adPurchaseId;
	
	@Column(name = "PurchaseSessionID")
	private String purchaseSessionId;
	
	@Column(name = "AdID")
	private Integer adId;
	
	@Column(name = "WholeSalerID")
	private Integer wholeSalerId;
	
	@Column(name = "PurchaseAmount")
	private BigDecimal purchaseAmount;
	
	@Column(name = "PurchaseTypeID")
	private Integer purchaseTypeId;
	
	@Column(name = "PONumber")
	private String poNumber;
	
	@Column(name = "CreatedOn")
	private LocalDateTime createdOn;
	
	@Column(name = "CreatedBy")
	private String createdBy;
	
	@Column(name = "ModifiedOn")
	private LocalDateTime modifiedOn;
	
	@Column(name = "ModifiedBy")
	private String modifiedBy;

	public Integer getAdPurchaseId() {
		return adPurchaseId;
	}

	public void setAdPurchaseId(Integer adPurchaseId) {
		this.adPurchaseId = adPurchaseId;
	}

	public String getPurchaseSessionId() {
		return purchaseSessionId;
	}

	public void setPurchaseSessionId(String purchaseSessionId) {
		this.purchaseSessionId = purchaseSessionId;
	}

	public Integer getAdId() {
		return adId;
	}

	public void setAdId(Integer adId) {
		this.adId = adId;
	}

	public Integer getWholeSalerId() {
		return wholeSalerId;
	}

	public void setWholeSalerId(Integer wholeSalerId) {
		this.wholeSalerId = wholeSalerId;
	}

	public BigDecimal getPurchaseAmount() {
		return purchaseAmount;
	}

	public void setPurchaseAmount(BigDecimal purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}

	public Integer getPurchaseTypeId() {
		return purchaseTypeId;
	}

	public void setPurchaseTypeId(Integer purchaseTypeId) {
		this.purchaseTypeId = purchaseTypeId;
	}

	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(LocalDateTime modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

}
