package net.fashiongo.webadmin.model.pojo;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 
 * @author Incheol Jung
 */
public class TodayDealDetail implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer todayDealID;
	private String title;
	private Integer productID;
	private LocalDateTime fromDate;
	private LocalDateTime toDate;
	private Integer todayDealPrice;
	private Boolean active;
	private LocalDateTime appliedOn;
	private LocalDateTime approvedOn;
	private Integer unitPrice;
	private String productName;
	private String companyName;
	private String status;
	private Integer companyTypeID;
	private String companyTypeCode;
	private Integer categoryID;
	private Integer orgCategoryID;
	private String categoryName;
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
	public Integer getTodayDealPrice() {
		return todayDealPrice;
	}
	public void setTodayDealPrice(Integer todayDealPrice) {
		this.todayDealPrice = todayDealPrice;
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
	public Integer getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Integer unitPrice) {
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
