package net.fashiongo.webadmin.model.pojo.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.webadmin.model.pojo.ProductColors;
import net.fashiongo.webadmin.model.pojo.ProductImage;
import net.fashiongo.webadmin.model.pojo.ProductInfo;
import net.fashiongo.webadmin.model.pojo.ProductSelectCheck;
import net.fashiongo.webadmin.model.pojo.ProductSize;

/**
 * @author Nayeon Kim
 */
public class GetProductDetailResponse {
	@JsonProperty("Table")
	private List<ProductInfo> productInfolist;
	
	@JsonProperty("Table1")
	private List<ProductImage> productImagelist;
	
	@JsonProperty("Table2")
	private List<ProductColors> productColorslist;
	
	@JsonProperty("Table3")
	private List<ProductSize> productSizelist;
	
	@JsonProperty("Table4")
	private List<ProductSelectCheck> productSelectChecklist;

	public List<ProductInfo> getProductInfolist() {
		return productInfolist;
	}

	public void setProductInfolist(List<ProductInfo> productInfolist) {
		this.productInfolist = productInfolist;
	}

	public List<ProductImage> getProductImagelist() {
		return productImagelist;
	}

	public void setProductImagelist(List<ProductImage> productImagelist) {
		this.productImagelist = productImagelist;
	}

	public List<ProductColors> getProductColorslist() {
		return productColorslist;
	}

	public void setProductColorslist(List<ProductColors> productColorslist) {
		this.productColorslist = productColorslist;
	}

	public List<ProductSize> getProductSizelist() {
		return productSizelist;
	}

	public void setProductSizelist(List<ProductSize> productSizelist) {
		this.productSizelist = productSizelist;
	}

	public List<ProductSelectCheck> getProductSelectChecklist() {
		return productSelectChecklist;
	}

	public void setProductSelectChecklist(List<ProductSelectCheck> productSelectChecklist) {
		this.productSelectChecklist = productSelectChecklist;
	}
}
