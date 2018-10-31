package net.fashiongo.webadmin.model.pojo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Nayeon Kim
 */
public class FeaturedItem {
	@JsonProperty("FeaturedItemID")
	private Integer featuredItemID;
	@JsonProperty("FeaturedItemDate")
	private LocalDateTime featuredItemDate;
	@JsonProperty("BestItemUse")
	private Integer bestItemUse;
	@JsonProperty("WholeSalerID")
	private Integer wholeSalerID;
	@JsonProperty("WholeSalerName")
	private String  wholeSalerName;
	@JsonProperty("ProductID")
	private Integer productID;
	@JsonProperty("ProductName")
	private String productName;
	@JsonProperty("CreatedOn")
	private LocalDateTime createdOn;
	@JsonProperty("CreatedBy")
	private String createdBy;
	@JsonProperty("ProductID1")
	private Integer productID1;
	@JsonProperty("WholeSalerID1")
	private Integer wholeSalerID1;
	@JsonProperty("ProductName1")
	private String productName1;
	@JsonProperty("UnitPrice")
	private BigDecimal unitPrice;
	@JsonProperty("PictureGeneral")
	private String pictureGeneral;
	@JsonProperty("UrlPath")
	private String urlPath;
	@JsonProperty("DirName")
	private String dirName;
	@JsonProperty("Active")
	private Boolean active;
	@JsonProperty("ActivatedOn")
	private LocalDateTime activatedOn;
	@JsonProperty("CreatedOn1")
	private LocalDateTime createdOn1;
	@JsonProperty("ModifiedOn")
	private LocalDateTime modifiedOn;
	@JsonProperty("RowIndex")
	private Long rowIndex;
	
	public Integer getFeaturedItemID() {
		return featuredItemID;
	}
	public void setFeaturedItemID(Integer featuredItemID) {
		this.featuredItemID = featuredItemID;
	}
	public LocalDateTime getFeaturedItemDate() {
		return featuredItemDate;
	}
	public void setFeaturedItemDate(LocalDateTime featuredItemDate) {
		this.featuredItemDate = featuredItemDate;
	}
	public Integer getBestItemUse() {
		return bestItemUse;
	}
	public void setBestItemUse(Integer bestItemUse) {
		this.bestItemUse = bestItemUse;
	}
	public Integer getWholeSalerID() {
		return wholeSalerID;
	}
	public void setWholeSalerID(Integer wholeSalerID) {
		this.wholeSalerID = wholeSalerID;
	}
	public String getWholeSalerName() {
		return wholeSalerName;
	}
	public void setWholeSalerName(String wholeSalerName) {
		this.wholeSalerName = wholeSalerName;
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
	public Integer getProductID1() {
		return productID1 = this.productID;
	}
	public void setProductID1(Integer productID1) {
		this.productID1 = productID1;
	}
	public Integer getWholeSalerID1() {
		return wholeSalerID1 = this.wholeSalerID;
	}
	public void setWholeSalerID1(Integer wholeSalerID1) {
		this.wholeSalerID1 = wholeSalerID1;
	}
	public String getProductName1() {
		return productName1 = this.productName;
	}
	public void setProductName1(String productName1) {
		this.productName1 = productName1;
	}
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getPictureGeneral() {
		return pictureGeneral;
	}
	public void setPictureGeneral(String pictureGeneral) {
		this.pictureGeneral = pictureGeneral;
	}
	public String getUrlPath() {
		return urlPath;
	}
	public void setUrlPath(String urlPath) {
		this.urlPath = urlPath;
	}
	public String getDirName() {
		return dirName;
	}
	public void setDirName(String dirName) {
		this.dirName = dirName;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public LocalDateTime getActivatedOn() {
		return activatedOn;
	}
	public void setActivatedOn(LocalDateTime activatedOn) {
		this.activatedOn = activatedOn;
	}
	public LocalDateTime getCreatedOn1() {
		return createdOn1 = this.createdOn;
	}
	public void setCreatedOn1(LocalDateTime createdOn1) {
		this.createdOn1 = createdOn1;
	}
	public LocalDateTime getModifiedOn() {
		return modifiedOn;
	}
	public void setModifiedOn(LocalDateTime modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
	public Long getRowIndex() {
		return rowIndex;
	}
	public void setRowIndex(Long rowIndex) {
		this.rowIndex = rowIndex;
	}
}
