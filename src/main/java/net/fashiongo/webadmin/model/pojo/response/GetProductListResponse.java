package net.fashiongo.webadmin.model.pojo.response;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.webadmin.model.pojo.ProductSummary;
/**
 * 
 * @author Incheol Jung
 */
public class GetProductListResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("Table")
	private List<ProductSummary> productList;

	public List<ProductSummary> getProductList() {
		return productList;
	}

	public void setProductList(List<ProductSummary> productList) {
		this.productList = productList;
	}
}