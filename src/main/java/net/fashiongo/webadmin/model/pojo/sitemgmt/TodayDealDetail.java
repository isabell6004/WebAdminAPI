package net.fashiongo.webadmin.model.pojo.sitemgmt;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Incheol Jung
 */
public class TodayDealDetail implements Serializable{
	private static final long serialVersionUID = 1L;
	@JsonProperty("TodayDealID")
	private Integer todayDealID;
	@JsonProperty("Title")
	private String title;
	@JsonProperty("ProductID")
	private Integer productID;
	@JsonProperty("FromDate")
	private LocalDateTime fromDate;
	@JsonProperty("ToDate")
	private LocalDateTime toDate;
	@JsonProperty("TodayDealPrice")
	private BigDecimal todayDealPrice;
	@JsonProperty("Active")
	private Boolean active;
	@JsonProperty("AppliedOn")
	private LocalDateTime appliedOn;
	@JsonProperty("ApprovedOn")
	private LocalDateTime approvedOn;
	@JsonProperty("UnitPrice")
	private BigDecimal unitPrice;
	@JsonProperty("ProductName")
	private String productName;
	@JsonProperty("CompanyName")
	private String companyName;
	@JsonProperty("Status")
	private String status;
	@JsonProperty("CompanyTypeID")
	private Integer companyTypeID;
	@JsonProperty("CompanyTypeCode")
	private String companyTypeCode;
	@JsonProperty("CategoryID")
	private Integer categoryID;
	@JsonProperty("OrgCategoryID")
	private Integer orgCategoryID;
	@JsonProperty("CategoryName")
	private String categoryName;
	@JsonProperty("WholeSalerID")
	private Integer wholeSalerID;
	
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
	public Integer getProductID() {
		return productID;
	}
	public void setProductID(Integer productID) {
		this.productID = productID;
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
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
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
	
	public BigDecimal getTodayDealPrice() {
		return todayDealPrice;
	}
	public void setTodayDealPrice(BigDecimal todayDealPrice) {
		this.todayDealPrice = todayDealPrice;
	}
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getCompanyTypeID() {
		return companyTypeID;
	}
	public void setCompanyTypeID(Integer companyTypeID) {
		this.companyTypeID = companyTypeID;
	}
	public String getCompanyTypeCode() {
		return companyTypeCode;
	}
	public void setCompanyTypeCode(String companyTypeCode) {
		this.companyTypeCode = companyTypeCode;
	}
	public Integer getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(Integer categoryID) {
		this.categoryID = categoryID;
	}
	public Integer getOrgCategoryID() {
		return orgCategoryID;
	}
	public void setOrgCategoryID(Integer orgCategoryID) {
		this.orgCategoryID = orgCategoryID;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Integer getWholeSalerID() {
		return wholeSalerID;
	}
	public void setWholeSalerID(Integer wholeSalerID) {
		this.wholeSalerID = wholeSalerID;
	}
}
