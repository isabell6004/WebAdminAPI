package net.fashiongo.webadmin.model.pojo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * @author Jiwon Kim
 */
public class BiddingList {


	@JsonProperty("AdID")
	@Column(name = "AdID")
	private Integer adID;
	@JsonProperty("PageID")
	@Column(name = "PageID")
	private Integer pageID;
	@JsonProperty("SpotID")
	@Column(name = "SpotID")
	private Integer spotID;
	@JsonProperty("WholeSalerID")
	@Column(name = "WholeSalerID")
	private Integer wholeSalerID;
	@JsonProperty("VendorCategoryID")
	@Column(name = "VendorCategoryID")
	private Integer vendorCategoryID;
	@JsonProperty("FromDate")
	@Column(name = "FromDate")
	private LocalDateTime fromDate;
	@JsonProperty("DateDay")
	@Column(name = "DateDay")
	private Integer dateDay;
	@JsonProperty("MonthType")
	@Column(name = "MonthType")
	private String monthType;
	@JsonProperty("ProductID")
	@Column(name = "ProductID")
	private Integer productID;
	@JsonProperty("Active")
	@Column(name = "Active")
	private Integer active;
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
	public Integer getVendorCategoryID() {
		return vendorCategoryID;
	}
	public void setVendorCategoryID(Integer vendorCategoryID) {
		this.vendorCategoryID = vendorCategoryID;
	}
	public LocalDateTime getFromDate() {
		return fromDate;
	}
	public void setFromDate(LocalDateTime fromDate) {
		this.fromDate = fromDate;
	}
	public Integer getDateDay() {
		return dateDay;
	}
	public void setDateDay(Integer dateDay) {
		this.dateDay = dateDay;
	}
	public String getMonthType() {
		return monthType;
	}
	public void setMonthType(String monthType) {
		this.monthType = monthType;
	}
	public Integer getProductID() {
		return productID;
	}
	public void setProductID(Integer productID) {
		this.productID = productID;
	}
	public Integer getActive() {
		return active;
	}
	public void setActive(Integer active) {
		this.active = active;
	}
	
	
    
	
}