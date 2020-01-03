package net.fashiongo.webadmin.model.pojo.parameter;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Reo
 *
 */
public class SetDenyBannerParameter {
	@JsonProperty("imagerequestid")
	private Integer imageRequestId;

	@JsonProperty("denialreason")
	private String denialReason;

	private Integer vendorId;

	public Integer getImageRequestId() {
		return imageRequestId;
	}

	public void setImageRequestId(Integer imageRequestId) {
		this.imageRequestId = imageRequestId;
	}

	public String getDenialReason() {
		return denialReason;
	}

	public void setDenialReason(String denialReason) {
		this.denialReason = denialReason;
	}

	public Integer getVendorId() {
		return vendorId;
	}

	public void setVendorId(Integer vendorId) {
		this.vendorId = vendorId;
	}
}
