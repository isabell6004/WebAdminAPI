package net.fashiongo.webadmin.model.pojo;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Jiwon Kim
 */
public class VendorCount {
	@JsonProperty("VendorCount")
	@Column(name = "VendorCount")
	private Integer vendorCount;

	public Integer getVendorCount() {
		return vendorCount;
	}

	public void setVendorCount(Integer vendorCount) {
		this.vendorCount = vendorCount;
	}
}
