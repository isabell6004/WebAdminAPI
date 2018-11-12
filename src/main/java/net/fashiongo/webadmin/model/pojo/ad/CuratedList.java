package net.fashiongo.webadmin.model.pojo.ad;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CuratedList {

	@JsonProperty("CollectionCategoryItemID")
	@Column(name = "CollectionCategoryItemID")
	private Integer collectionCategoryItemID;
	@JsonProperty("SpotID")
	@Column(name = "SpotID")
	private Integer spotID;
	@JsonProperty("FromDate")
	@Column(name = "FromDate")
	private LocalDateTime fromDate;
	@JsonProperty("CollectionCategoryID")
	@Column(name = "CollectionCategoryID")
	private Integer collectionCategoryID;
	@JsonProperty("ProductID")
	@Column(name = "ProductID")
	private Integer productID;
	@JsonProperty("CollectionCategoryType")
	@Column(name = "CollectionCategoryType")
	private Integer collectionCategoryType;
	@JsonProperty("DateDay")
	@Column(name = "DateDay")
	private Integer dateDay;
	@JsonProperty("MonthType")
	@Column(name = "MonthType")
	private String monthType;
	@JsonProperty("Active")
	@Column(name = "Active")
	private Integer active;
	
	
	public Integer getCollectionCategoryItemID() {
		return collectionCategoryItemID;
	}
	public void setCollectionCategoryItemID(Integer collectionCategoryItemID) {
		this.collectionCategoryItemID = collectionCategoryItemID;
	}
	public Integer getSpotID() {
		return spotID;
	}
	public void setSpotID(Integer spotID) {
		this.spotID = spotID;
	}
	public LocalDateTime getFromDate() {
		return fromDate;
	}
	public void setFromDate(LocalDateTime fromDate) {
		this.fromDate = fromDate;
	}
	public Integer getCollectionCategoryID() {
		return collectionCategoryID;
	}
	public void setCollectionCategoryID(Integer collectionCategoryID) {
		this.collectionCategoryID = collectionCategoryID;
	}
	public Integer getProductID() {
		return productID;
	}
	public void setProductID(Integer productID) {
		this.productID = productID;
	}
	public Integer getCollectionCategoryType() {
		return collectionCategoryType;
	}
	public void setCollectionCategoryType(Integer collectionCategoryType) {
		this.collectionCategoryType = collectionCategoryType;
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
	public Integer getActive() {
		return active;
	}
	public void setActive(Integer active) {
		this.active = active;
	}
    
	
}