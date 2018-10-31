package net.fashiongo.webadmin.model.pojo.parameter;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.fashiongo.webadmin.model.pojo.ProductAttribute;

/**
 * 
 * @author Reo
 *
 */
public class SetProductAttributesMappingParameter implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("tabno")
	private Integer tabNo;
	
	@JsonProperty("categoryid")
	private Integer categoryID;
	
	@JsonProperty("obj")
	private List<ProductAttribute> productAttributeList;

	public Integer getTabNo() {
		return tabNo;
	}

	public void setTabNo(Integer tabNo) {
		this.tabNo = tabNo;
	}

	public Integer getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(Integer categoryID) {
		this.categoryID = categoryID;
	}

	public List<ProductAttribute> getProductAttributeList() {
		return productAttributeList;
	}

	public void setProductAttributeList(List<ProductAttribute> productAttributeList) {
		this.productAttributeList = productAttributeList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
