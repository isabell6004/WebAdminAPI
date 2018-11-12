package net.fashiongo.webadmin.model.pojo.sitemgmt;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
* @author Nayeon Kim
*/
public class TrendReportItem {
	private String imageUrlRoot;
	@JsonProperty("PictureGeneral")
	private String pictureGeneral;
	@JsonProperty("DirName")
	private String dirName;
	@JsonProperty("MapID")
	private Integer mapID;
	@JsonProperty("CompanyName")
	private String companyName;
	@JsonProperty("ProductName")
	private String productName;
	@JsonProperty("ProductID")
	private Integer productID;
	@JsonProperty("SortNo")
	private Integer sortNo;
	@JsonProperty("Active")
	private Boolean active;
	
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
	public String getDirName() {
		return dirName;
	}
	public void setDirName(String dirName) {
		this.dirName = dirName;
	}
	public Integer getMapID() {
		return mapID;
	}
	public void setMapID(Integer mapID) {
		this.mapID = mapID;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getProductID() {
		return productID;
	}
	public void setProductID(Integer productID) {
		this.productID = productID;
	}
	public Integer getSortNo() {
		return sortNo;
	}
	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
}
