package net.fashiongo.webadmin.model.primary;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Nayeon Kim
 */
@Entity
@Table(name = "Ad_Vendor")
public class AdVendor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("AdID")
	@Column(name = "AdID")
	private Integer adID;
	
	@JsonProperty("pageID")
	@Column(name = "pageID")
	private Integer pageID;
	
	@JsonProperty("SpotID")
	@Column(name = "SpotID")
	private Integer spotID;
	
	@JsonProperty("WholeSalerID")
	@Column(name = "WholeSalerID")
	private Integer wholeSalerID;
	
	@JsonProperty("CategoryID")
	@Column(name = "CategoryID")
	private Integer categoryID;
	
	@JsonProperty("BodySizeID")
	@Column(name = "BodySizeID")
	private Integer bodySizeID;
	
	@JsonProperty("VendorCategoryID")
	@Column(name = "VendorCategoryID")
	private Integer vendorCategoryID;
	
	@JsonProperty("ActualPrice")
	@Column(name = "ActualPrice")
	private BigDecimal actualPrice;
	
	@JsonProperty("FromDate")
	@Column(name = "FromDate")
	private Date fromDate;
	
	@JsonProperty("ToDate")
	@Column(name = "ToDate")
	private Date toDate;
	
	@JsonProperty("Active")
	@Column(name = "Active")
	private Boolean active;
	
	@JsonProperty("CreatedOn")
	@Column(name = "CreatedOn")
	private Date createdOn;
	
	@JsonProperty("CreatedBy")
	@Column(name = "CreatedBy")
	private String createdBy;
	
	@JsonProperty("ModifiedOn")
	@Column(name = "ModifiedOn")
	private Date modifiedOn;
	
	@JsonProperty("ModifiedBy")
	@Column(name = "ModifiedBy")
	private String modifiedBy;
	
	@JsonProperty("IsConfirmed")
	@Column(name = "IsConfirmed")
	private Boolean isConfirmed;
	
	@JsonProperty("IsConfirmedDate")
	@Column(name = "IsConfirmedDate")
	private Date isConfirmedDate;
	
	@JsonProperty("HowToInput")
	@Column(name = "HowToInput")
	private Integer howToInput;
	
	@JsonProperty("HowtoSell")
	@Column(name = "HowtoSell")
	private Integer howtoSell;
	
	@JsonProperty("AdPageSpotListID")
	@Column(name = "AdPageSpotListID")
	private Integer adPageSpotListID;
	
	@JsonProperty("InvoiceOut")
	@Column(name = "InvoiceOut")
	private Boolean invoiceOut;
	
	@JsonProperty("TransferredToCS")
	@Column(name = "TransferredToCS")
	private Date transferredToCS;

	public Integer getAdID() {
		return adID;
	}

	public void setAdID(Integer adID) {
		this.adID = adID;
	}

	public Integer getPageID() {
		return pageID;
	}

	public void setPageID(Integer pageID) {
		this.pageID = pageID;
	}

	public Integer getSpotID() {
		return spotID;
	}

	public void setSpotID(Integer spotID) {
		this.spotID = spotID;
	}

	public Integer getWholeSalerID() {
		return wholeSalerID;
	}

	public void setWholeSalerID(Integer wholeSalerID) {
		this.wholeSalerID = wholeSalerID;
	}

	public Integer getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(Integer categoryID) {
		this.categoryID = categoryID;
	}

	public Integer getBodySizeID() {
		return bodySizeID;
	}

	public void setBodySizeID(Integer bodySizeID) {
		this.bodySizeID = bodySizeID;
	}

	public Integer getVendorCategoryID() {
		return vendorCategoryID;
	}

	public void setVendorCategoryID(Integer vendorCategoryID) {
		this.vendorCategoryID = vendorCategoryID;
	}

	public BigDecimal getActualPrice() {
		return actualPrice;
	}

	public void setActualPrice(BigDecimal actualPrice) {
		this.actualPrice = actualPrice;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Boolean getIsConfirmed() {
		return isConfirmed;
	}

	public void setIsConfirmed(Boolean isConfirmed) {
		this.isConfirmed = isConfirmed;
	}

	public Date getIsConfirmedDate() {
		return isConfirmedDate;
	}

	public void setIsConfirmedDate(Date isConfirmedDate) {
		this.isConfirmedDate = isConfirmedDate;
	}

	public Integer getHowToInput() {
		return howToInput;
	}

	public void setHowToInput(Integer howToInput) {
		this.howToInput = howToInput;
	}

	public Integer getHowtoSell() {
		return howtoSell;
	}

	public void setHowtoSell(Integer howtoSell) {
		this.howtoSell = howtoSell;
	}

	public Integer getAdPageSpotListID() {
		return adPageSpotListID;
	}

	public void setAdPageSpotListID(Integer adPageSpotListID) {
		this.adPageSpotListID = adPageSpotListID;
	}

	public Boolean getInvoiceOut() {
		return invoiceOut;
	}

	public void setInvoiceOut(Boolean invoiceOut) {
		this.invoiceOut = invoiceOut;
	}

	public Date getTransferredToCS() {
		return transferredToCS;
	}

	public void setTransferredToCS(Date transferredToCS) {
		this.transferredToCS = transferredToCS;
	}
}
