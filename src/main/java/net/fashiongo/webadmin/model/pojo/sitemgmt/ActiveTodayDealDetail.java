package net.fashiongo.webadmin.model.pojo.sitemgmt;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ActiveTodayDealDetail implements Serializable {
	private static final long serialVersionUID = 1L;
	@JsonProperty("TodayDealID")
	private Integer todayDealID;
	@JsonProperty("Title")
	private String title;
	@JsonProperty("Description")
	private String description;
	@JsonProperty("FromDate")
	private LocalDateTime fromDate;
	@JsonProperty("ToDate")
	private LocalDateTime toDate;
	@JsonProperty("TodayDealPrice")
	private BigDecimal todayDealPrice;
	@JsonProperty("AppliedOn")
	private LocalDateTime appliedOn;
	@JsonProperty("ApprovedOn")
	private LocalDateTime approvedOn;
	@JsonProperty("Active")
	private Boolean active;
	@JsonProperty("ModifiedBy")
	private String modifiedBy;
	@JsonProperty("ModifiedOn")
	private LocalDateTime modifiedOn;
	@JsonProperty("CreatedByVendor")
	private Boolean createdByVendor;
	@JsonProperty("ProductID")
	private Integer productID;
	@JsonProperty("ProductName")
	private String productName;
	@JsonProperty("ImageUrlRoot")
	private String imageUrlRoot;
	@JsonProperty("DirName")
	private String dirName;
	@JsonProperty("PictureGeneral")
	private String pictureGeneral;
	@JsonProperty("CompanyName")
	private String companyName;
	@JsonProperty("WholeSalerID")
	private Integer wholeSalerID;
	@JsonProperty("UnitPrice")
	private BigDecimal unitPrice;
	
	private Integer sYear;
	private Integer sMonth;
	public Integer getTodayDealID() {
		return todayDealID;
	}
	public void setTodayDealID(Integer todayDealID) {
		this.todayDealID = todayDealID;
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
	public LocalDateTime getFromDate() {
		return fromDate;
	}
	public void setFromDate(LocalDateTime fromDate) {
		this.fromDate = fromDate;
	}
	public LocalDateTime getToDate() {
		return toDate;
	}
	public void setToDate(LocalDateTime toDate) {
		this.toDate = toDate;
	}
	public BigDecimal getTodayDealPrice() {
		return todayDealPrice;
	}
	public void setTodayDealPrice(BigDecimal todayDealPrice) {
		this.todayDealPrice = todayDealPrice;
	}
	public LocalDateTime getAppliedOn() {
		return appliedOn;
	}
	public void setAppliedOn(LocalDateTime appliedOn) {
		this.appliedOn = appliedOn;
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
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public LocalDateTime getModifiedOn() {
		return modifiedOn;
	}
	public void setModifiedOn(LocalDateTime modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
	public Boolean getCreatedByVendor() {
		return createdByVendor;
	}
	public void setCreatedByVendor(Boolean createdByVendor) {
		this.createdByVendor = createdByVendor;
	}
	public Integer getProductID() {
		return productID;
	}
	public void setProductID(Integer productID) {
		this.productID = productID;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getImageUrlRoot() {
		return imageUrlRoot;
	}
	public void setImageUrlRoot(String imageUrlRoot) {
		this.imageUrlRoot = imageUrlRoot;
	}
	public String getDirName() {
		return dirName;
	}
	public void setDirName(String dirName) {
		this.dirName = dirName;
	}
	public String getPictureGeneral() {
		return pictureGeneral;
	}
	public void setPictureGeneral(String pictureGeneral) {
		this.pictureGeneral = pictureGeneral;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Integer getWholeSalerID() {
		return wholeSalerID;
	}
	public void setWholeSalerID(Integer wholeSalerID) {
		this.wholeSalerID = wholeSalerID;
	}
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	public Integer getsYear() {
		return sYear;
	}
	public void setsYear(Integer sYear) {
		this.sYear = sYear;
	}
	public Integer getsMonth() {
		return sMonth;
	}
	public void setsMonth(Integer sMonth) {
		this.sMonth = sMonth;
	}
	
	
}
