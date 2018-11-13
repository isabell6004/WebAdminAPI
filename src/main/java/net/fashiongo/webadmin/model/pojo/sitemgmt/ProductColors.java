package net.fashiongo.webadmin.model.pojo.sitemgmt;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Nayeon Kim
 */
public class ProductColors {
	@JsonProperty("ProductID")
	private Integer productID;
	@JsonProperty("ColorID")
	private Integer colorID;
	@JsonProperty("Color")
	private String color;
	
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
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
}
