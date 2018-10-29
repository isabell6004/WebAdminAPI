package net.fashiongo.webadmin.model.primary;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Incheol Jung
 */
@Entity
@Table(name = "VendorCategory")
public class VendorCategory implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("VendorCategoryID")
	private Integer vendorCategoryID;
	
	private Integer wholeSalerID;
	
	private Boolean active;
	
	@JsonProperty("CategoryName")
	private String categoryName;

	public Integer getVendorCategoryID() {
		return vendorCategoryID;
	}

	public void setVendorCategoryID(Integer vendorCategoryID) {
		this.vendorCategoryID = vendorCategoryID;
	}

	public Integer getWholeSalerID() {
		return wholeSalerID;
	}

	public void setWholeSalerID(Integer wholeSalerID) {
		this.wholeSalerID = wholeSalerID;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	
}