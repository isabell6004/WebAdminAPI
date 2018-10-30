package net.fashiongo.webadmin.model.pojo;

import java.io.Serializable;

/**
 * 
 * @author Incheol Jung
 */
public class DMRequestDetail implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer catalogID;
	private String imageUrlRoot;
	private String dirName;
	private String pictureLogo;
	private Integer productID;
	private String pictureGeneral;
	private String productName;
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