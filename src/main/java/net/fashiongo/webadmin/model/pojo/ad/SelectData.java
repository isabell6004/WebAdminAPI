package net.fashiongo.webadmin.model.pojo.ad;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * 
 * @author Jiwon Kim
 */
public class SelectData {


	@JsonProperty("rowno")
	@Column(name = "rowno")
	private Integer rowno;
	@JsonProperty("ProductID")
	@Column(name = "ProductID")
	private Integer productID;
	@JsonProperty("CategoryID")
	@Column(name = "CategoryID")
	private Integer categoryID;
	@JsonProperty("ProductName")
	@Column(name = "ProductName")
	private String productName;
	@JsonProperty("Price")
	@Column(name = "Price")
	private BigDecimal price;
	@JsonProperty("UnitPrice1")
	@Column(name = "UnitPrice1")
	private BigDecimal unitPrice1;
	@JsonProperty("AvailableOn")
	@Column(name = "AvailableOn")
	private LocalDateTime availableOn;
	@JsonProperty("ActivatedOn")
	@Column(name = "ActivatedOn")
	private LocalDateTime activatedOn;
	@JsonProperty("WholeSalerID")
	@Column(name = "WholeSalerID")
	private Integer wholeSalerID;
	@JsonProperty("CompanyName")
	@Column(name = "CompanyName")
	private String companyName;
	@JsonProperty("DirName")
	@Column(name = "DirName")
	private String dirName;
	@JsonProperty("ImageUrlRoot")
	@Column(name = "ImageUrlRoot")
	private String imageUrlRoot;
	@JsonProperty("PictureGeneral")
	@Column(name = "PictureGeneral")
	private String pictureGeneral;
	@JsonProperty("ColorCount")
	@Column(name = "ColorCount")
	private Integer colorCount;
	@JsonProperty("AdVendorItemID")
	@Column(name = "AdVendorItemID")
	private Integer adVendorItemID;
	@JsonProperty("SelectItem")
	@Column(name = "SelectItem")
	private Integer selectItem;
	public Integer getRowno() {
		return rowno;
	}
	public void setRowno(Integer rowno) {
		this.rowno = rowno;
	}
	public Integer getProductID() {
		return productID;
	}
	public void setProductID(Integer productID) {
		this.productID = productID;
	}
	public Integer getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(Integer categoryID) {
		this.categoryID = categoryID;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getUnitPrice1() {
		return unitPrice1;
	}
	public void setUnitPrice1(BigDecimal unitPrice1) {
		this.unitPrice1 = unitPrice1;
	}
	public LocalDateTime getAvailableOn() {
		return availableOn;
	}
	public void setAvailableOn(LocalDateTime availableOn) {
		this.availableOn = availableOn;
	}
	public LocalDateTime getActivatedOn() {
		return activatedOn;
	}
	public void setActivatedOn(LocalDateTime activatedOn) {
		this.activatedOn = activatedOn;
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
	public String getDirName() {
		return dirName;
	}
	public void setDirName(String dirName) {
		this.dirName = dirName;
	}
	public String getImageUrlRoot() {
		return imageUrlRoot;
	}
	public void setImageUrlRoot(String imageUrlRoot) {
		this.imageUrlRoot = imageUrlRoot;
	}
	public String getPictureGeneral() {
		return pictureGeneral;
	}
	public void setPictureGeneral(String pictureGeneral) {
		this.pictureGeneral = pictureGeneral;
	}
	public Integer getColorCount() {
		return colorCount;
	}
	public void setColorCount(Integer colorCount) {
		this.colorCount = colorCount;
	}
	public Integer getAdVendorItemID() {
		return adVendorItemID;
	}
	public void setAdVendorItemID(Integer adVendorItemID) {
		this.adVendorItemID = adVendorItemID;
	}
	public Integer getSelectItem() {
		return selectItem;
	}
	public void setSelectItem(Integer selectItem) {
		this.selectItem = selectItem;
	}
	
}