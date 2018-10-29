package net.fashiongo.webadmin.model.pojo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * 
 * @author Jiwon Kim
 */
public class BidList {


	@JsonProperty("MapID")
	@Column(name = "MapID")
	private Integer mapID;
	@JsonProperty("ProductID")
	@Column(name = "ProductID")
	private Integer productID;
	@JsonProperty("ListOrder")
	@Column(name = "ListOrder")
	private Integer listOrder;
	@JsonProperty("WholeSalerID")
	@Column(name = "WholeSalerID")
	private Integer wholeSalerID;
	@JsonProperty("CompanyName")
	@Column(name = "CompanyName")
	private String companyName;
	@JsonProperty("PictureGeneral")
	@Column(name = "PictureGeneral")
	private String pictureGeneral;
	@JsonProperty("DirName")
	@Column(name = "DirName")
	private String dirName;
	@JsonProperty("ProductName")
	@Column(name = "ProductName")
	private String productName;
	@JsonProperty("ImageUrlRoot")
	@Column(name = "ImageUrlRoot")
	private String imageUrlRoot;
	public Integer getMapID() {
		return mapID;
	}
	public void setMapID(Integer mapID) {
		this.mapID = mapID;
	}
	public Integer getProductID() {
		return productID;
	}
	public void setProductID(Integer productID) {
		this.productID = productID;
	}
	public Integer getListOrder() {
		return listOrder;
	}
	public void setListOrder(Integer listOrder) {
		this.listOrder = listOrder;
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
	public String getPictureGeneral() {
		return pictureGeneral;
	}
	public void setPictureGeneral(String pictureGeneral) {
		this.pictureGeneral = pictureGeneral;
	}
	public String getDirName() {
		return dirName;
	}
	public void setDirName(String dirName) {
		this.dirName = dirName;
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
	
	
	
    
	
}