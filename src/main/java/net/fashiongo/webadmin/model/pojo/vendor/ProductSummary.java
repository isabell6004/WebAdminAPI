package net.fashiongo.webadmin.model.pojo.vendor;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Incheol Jung
 */
public class ProductSummary implements Serializable {
	private static final long serialVersionUID = 1L;
	@JsonProperty("ProductID")
	private Integer productID;
	@JsonProperty("ProductName")
	private String productName;
	@JsonProperty("UnitPrice")
	private BigDecimal unitPrice;
	@JsonProperty("DirName")
	private String dirName;
	@JsonProperty("UrlPath")
	private String urlPath;
	@JsonProperty("ProductImageID")
	private Integer productImageID;
	@JsonProperty("ImageName")
	private String imageName;
	
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
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
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
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

}