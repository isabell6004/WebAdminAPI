package net.fashiongo.webadmin.model.pojo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * @author Jiwon Kim
 */
public class CuratedBestList {


	@JsonProperty("CollectionCategoryItemID")
	@Column(name = "CollectionCategoryItemID")
	private Integer collectionCategoryItemID;
	@JsonProperty("SpotID")
	@Column(name = "SpotID")
	private Integer spotID;
	@JsonProperty("WholeSalerID")
	@Column(name = "WholeSalerID")
	private Integer wholeSalerID;
	@JsonProperty("CompanyName")
	@Column(name = "CompanyName")
	private String companyName;
	@JsonProperty("CollectionCategoryID")
	@Column(name = "CollectionCategoryID")
	private Integer collectionCategoryID;
	@JsonProperty("CollectionCategoryType")
	@Column(name = "CollectionCategoryType")
	private Integer collectionCategoryType;
	@JsonProperty("ProductID")
	@Column(name = "ProductID")
	private Integer productID;
	@JsonProperty("ProductName")
	@Column(name = "ProductName")
	private String productName;
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
	public Integer getWholeSalerID() {
		return wholeSalerID;
	}
	public void setWholeSalerID(Integer wholeSalerID) {
		this.wholeSalerID = wholeSalerID;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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
	public Integer getActive() {
		return active;
	}
	public void setActive(Integer active) {
		this.active = active;
	}

	
    
	
}