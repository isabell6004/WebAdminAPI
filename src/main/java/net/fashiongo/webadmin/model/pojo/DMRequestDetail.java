package net.fashiongo.webadmin.model.pojo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Incheol Jung
 */
public class DMRequestDetail implements Serializable{
	private static final long serialVersionUID = 1L;
	@JsonProperty("CatalogID")
	private Integer catalogID;
	@JsonProperty("ImageUrlRoot")
	private String imageUrlRoot;
	@JsonProperty("DirName")
	private String dirName;
	@JsonProperty("PictureLogo")
	private String pictureLogo;
	@JsonProperty("ProductID")
	private Integer productID;
	@JsonProperty("PictureGeneral")
	private String pictureGeneral;
	@JsonProperty("ProductName")
	private String productName;
	@JsonProperty("CompanyName")
	private String companyName;
	
	public Integer getCatalogID() {
		return catalogID;
	}
	public void setCatalogID(Integer catalogID) {
		this.catalogID = catalogID;
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
	public String getPictureLogo() {
		return pictureLogo;
	}
	public void setPictureLogo(String pictureLogo) {
		this.pictureLogo = pictureLogo;
	}
	public Integer getProductID() {
		return productID;
	}
	public void setProductID(Integer productID) {
		this.productID = productID;
	}
	public String getPictureGeneral() {
		return pictureGeneral;
	}
	public void setPictureGeneral(String pictureGeneral) {
		this.pictureGeneral = pictureGeneral;
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
}