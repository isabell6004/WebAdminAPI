package net.fashiongo.webadmin.model.pojo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * @author Jiwon Kim
 */
public class BiddingList2 {


	@JsonProperty("AdID")
	@Column(name = "AdID")
	private Integer adID;
	@JsonProperty("SpotID")
	@Column(name = "SpotID")
	private Integer spotID;
	@JsonProperty("WholeSalerID")
	@Column(name = "WholeSalerID")
	private Integer wholeSalerID;
	@JsonProperty("CollectionCategoryID")
	@Column(name = "CollectionCategoryID")
	private Integer collectionCategoryID;
	@JsonProperty("CollectionCategoryType")
	@Column(name = "CollectionCategoryType")
	private Integer collectionCategoryType;
	@JsonProperty("ActualPrice")
	@Column(name = "ActualPrice")
	private BigDecimal actualPrice;
	@JsonProperty("ProductID")
	@Column(name = "ProductID")
	private Integer productID;
	@JsonProperty("ItemCount")
	@Column(name = "ItemCount")
	private Integer itemCount;
	@JsonProperty("ProductName")
	@Column(name = "ProductName")
	private String productName;
	@JsonProperty("CompanyName")
	@Column(name = "CompanyName")
	private String companyName;
	@JsonProperty("ImageUrlRoot")
	@Column(name = "ImageUrlRoot")
	private String imageUrlRoot;
	@JsonProperty("DirName")
	@Column(name = "DirName")
	private String dirName;
	@JsonProperty("PictureGeneral")
	@Column(name = "PictureGeneral")
	private String pictureGeneral;
	@JsonProperty("Active")
	@Column(name = "Active")
	private Integer active;
	public Integer getAdID() {
		return adID;
	}
	public void setAdID(Integer adID) {
		this.adID = adID;
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
	public Integer getCollectionCategoryID() {
		return collectionCategoryID;
	}
	public void setCollectionCategoryID(Integer collectionCategoryID) {
		this.collectionCategoryID = collectionCategoryID;
	}
	public Integer getCollectionCategoryType() {
		return collectionCategoryType;
	}
	public void setCollectionCategoryType(Integer collectionCategoryType) {
		this.collectionCategoryType = collectionCategoryType;
	}
	public BigDecimal getActualPrice() {
		return actualPrice;
	}
	public void setActualPrice(BigDecimal actualPrice) {
		this.actualPrice = actualPrice;
	}
	public Integer getProductID() {
		return productID;
	}
	public void setProductID(Integer productID) {
		this.productID = productID;
	}
	public Integer getItemCount() {
		return itemCount;
	}
	public void setItemCount(Integer itemCount) {
		this.itemCount = itemCount;
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
	public Integer getActive() {
		return active;
	}
	public void setActive(Integer active) {
		this.active = active;
	}
	
    
	
}