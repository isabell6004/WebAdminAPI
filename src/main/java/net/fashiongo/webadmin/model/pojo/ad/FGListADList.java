package net.fashiongo.webadmin.model.pojo.ad;

import java.math.BigDecimal;

import javax.persistence.Column;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FGListADList {

	@JsonProperty("AdID")
	private Integer adID;
	@JsonProperty("SpotID")
	private Integer spotID;
	@JsonProperty("WholeSalerID")
	private Integer wholeSalerID;
	@JsonProperty("CategoryID")
	private Integer categoryID;
	@JsonProperty("ProductID")
	private Integer productID;
	
	@JsonProperty("ItemCount")
	private Integer itemCount;
	@JsonProperty("ActualPrice")
	private BigDecimal actualPrice;
	@JsonProperty("ProductName")
	private String productName;
	@JsonProperty("CompanyName")
	private String companyName;
	@JsonProperty("ImageUrlRoot")
	private String imageUrlRoot;

	@JsonProperty("DirName")
	private String dirName;
	@JsonProperty("PictureGeneral")
	private String pictureGeneral;
	@JsonProperty("Active")
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
	public Integer getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(Integer categoryID) {
		this.categoryID = categoryID;
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
	public BigDecimal getActualPrice() {
		return actualPrice;
	}
	public void setActualPrice(BigDecimal actualPrice) {
		this.actualPrice = actualPrice;
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