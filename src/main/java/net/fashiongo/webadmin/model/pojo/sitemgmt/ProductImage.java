package net.fashiongo.webadmin.model.pojo.sitemgmt;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Nayeon Kim
 */
public class ProductImage {
	@JsonProperty("DirName")
	private String dirName;
	@JsonProperty("UrlPath")
	private String urlPath;
	@JsonProperty("ProductImageID")
	private Integer productImageID;
	@JsonProperty("ProductID")
	private Integer productID;
	@JsonProperty("ColorID")
	private Integer colorID;
	@JsonProperty("ImageName")
	private String imageName;
	@JsonProperty("ListOrder")
	private Integer listOrder;
	@JsonProperty("Active")
	private Boolean active;
	
	public String getDirName() {
		return dirName;
	}
	public void setDirName(String dirName) {
		this.dirName = dirName;
	}
	public String getUrlPath() {
		return urlPath;
	}
	public void setUrlPath(String urlPath) {
		this.urlPath = urlPath;
	}
	public Integer getProductImageID() {
		return productImageID;
	}
	public void setProductImageID(Integer productImageID) {
		this.productImageID = productImageID;
	}
	public Integer getProductID() {
		return productID;
	}
	public void setProductID(Integer productID) {
		this.productID = productID;
	}
	public Integer getColorID() {
		return colorID;
	}
	public void setColorID(Integer colorID) {
		this.colorID = colorID;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public Integer getListOrder() {
		return listOrder;
	}
	public void setListOrder(Integer listOrder) {
		this.listOrder = listOrder;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
}
