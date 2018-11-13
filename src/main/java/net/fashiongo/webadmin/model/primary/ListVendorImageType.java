package net.fashiongo.webadmin.model.primary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Reo
 *
 */
@Entity
@Table(name = "List_VendorImageType")
public class ListVendorImageType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("VendorImageTypeID")
	@Column(name = "VendorImageTypeID")
	private Integer vendorImageTypeID;
	
	@Column(name = "VendorImageType")
	private String vendorImageType;
	
	@Column(name = "UploadedByVendor")
	private Boolean uploadedByVendor;
	
	@Column(name = "ListOrder")
	private Integer listOrder;

	public Integer getVendorImageTypeID() {
		return vendorImageTypeID;
	}

	public void setVendorImageTypeID(Integer vendorImageTypeID) {
		this.vendorImageTypeID = vendorImageTypeID;
	}

	public String getVendorImageType() {
		return vendorImageType;
	}

	public void setVendorImageType(String vendorImageType) {
		this.vendorImageType = vendorImageType;
	}

	public Boolean getUploadedByVendor() {
		return uploadedByVendor;
	}

	public void setUploadedByVendor(Boolean uploadedByVendor) {
		this.uploadedByVendor = uploadedByVendor;
	}

	public Integer getListOrder() {
		return listOrder;
	}

	public void setListOrder(Integer listOrder) {
		this.listOrder = listOrder;
	}
	
	
}
